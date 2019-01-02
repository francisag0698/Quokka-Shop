const express = require('express');
const morgan = require('morgan');

const app = express();

//Settings
const { db } = require('./database');

//Middlewares
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

//Routes
app.use('/api/role', require('./routes/role.routes'));
app.use('/api/person', require('./routes/person.routes'));
app.use('/api/account', require('./routes/account.routes'));

//Server Settings
app.use(function(req, res, next) {
  next(errors(404));
});

app.use(function(err, req, res, next) {
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;