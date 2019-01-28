import 'zone.js/dist/zone-node';
import 'reflect-metadata';
const { enableProdMode } = require('@angular/core');
const { ngExpressEngine } = require('@nguniversal/express-engine');
const { provideModuleMap } = require('@nguniversal/module-map-ngfactory-loader');

const express = require('express');
const logger = require('morgan');
const errors = require('http-errors');
const cookie = require('cookie-parser');
const { join } = require('path');
const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const session = require('express-session');
const {AppServerModuleNgFactory, LAZY_MODULE_MAP} = require('./dist/server/main');

// SETTINGS ***********************
const app = express();
const { db } = require('./server/database');

enableProdMode();
const DIST_FOLDER = join(process.cwd(), 'dist/browser');

// (found @ https://github.com/angular/universal/tree/master/modules/express-engine)
app.engine('html', ngExpressEngine({
  bootstrap: AppServerModuleNgFactory,
  providers: [
    provideModuleMap(LAZY_MODULE_MAP)
  ]
}));

app.set('view engine', 'html');
app.set('views', DIST_FOLDER);

const AuthController = require('./server/controllers/auth.controller');
passport.use(new LocalStrategy(AuthController.getSession));

// MIDDLEWARES ***********************
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

// ROUTES ***********************
app.use('/api/role', require('./server/routes/role.routes'));
app.use('/api/person', require('./server/routes/person.routes'));
app.use('/api/account', require('./server/routes/account.routes'));
app.use('/auth', require('./server/routes/auth.routes'));

// Server static files from /browser
app.get('*.*', express.static(DIST_FOLDER, {
  maxAge: '1y'
}));

app.use('/', require('./server/routes/app.routes'));

// SERVER SETTINGS 
app.use(function(req, res, next) {
  next(errors(404));
});

app.use(function(err, req, res, next) {
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};
  res.status(err.status || 500).send(err.message);
});

// START UP THE NODE SERVER ***********************
const PORT = process.env.PORT || 4000;
app.listen(PORT || 4000, () => {
  console.log(`Node Express server listening on http://localhost:${PORT}`);
});