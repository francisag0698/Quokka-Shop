const express = require('express');
const morgan = require('morgan');

const app = express();

//Settings
const { db } = require('./database');
const models = require('./models/models');

//Middlewares
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

//Routes

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