const express = require('express');
const logger = require('morgan');
const errors = require('http-errors');
const cookie = require('cookie-parser');
const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const session = require('express-session');
const fs = require('fs');
const path = require('path');

const Vue = require('vue');
const app = express();

const createApp =  require('../ui/public/js/server.bundle.js');
const renderer = require('vue-server-renderer').createRenderer({
  template: fs.readFileSync('./index.html', 'utf-8')
});

app.use(express.static(path.join(__dirname, '../ui/public')));

//Middlewares
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookie());
app.use(session({
  name: 'example',
  secret: 'shuush',
  resave: false,
  saveUninitialized: true,
  cookie: {
    path: '/',
    httpOnly: true,
    secure: false,
    expires: new Date('Monday, 18 January 2028')
  }
}));
app.use(passport.initialize());
app.use(passport.session());

passport.serializeUser(function(user, done) {
  done(null, user);
});

passport.deserializeUser(function(user, done) {
  done(null, user);
});

//Settings
const { db } = require('./database');
const AuthController = require('./controllers/auth.controller');
passport.use(new LocalStrategy(AuthController.getSession));

//Routes
app.use('/api/role', require('./routes/role.routes'));
app.use('/api/person', require('./routes/person.routes'));
app.use('/api/account', require('./routes/account.routes'));
app.get('*', (req, res) => {
  const context = { url: req.url }

  createApp(context).then(app => {
    renderer.renderToString(app, (err, html) => {
      if (err) {
        if (err.code === 404) {
          res.status(404).end('Page not found')
        } else {
          res.status(500).end('Internal Server Error')
        }
      } else {
        res.end(html)
      }
    })
  })
});

//Server Settings
app.use(function(req, res, next) {
  next(errors(404));
});

app.use(function(err, req, res, next) {
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};
  res.status(err.status || 500).send(err.message);
});

module.exports = app;