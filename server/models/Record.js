'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');
const Person = require('./Person');

const Record = db.define('Record', {
    id_record: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    action: Sequelize.STRING,
    description: Sequelize.STRING,
    reference: Sequelize.STRING
});


Record.sync();

module.exports = Record;