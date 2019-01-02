'use strict';
const Sequelize = require('sequelize');
const db = require('../database');

const Company = db.define('Company', {
    idCompany:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    name: Sequelize.STRING,
    country: Sequelize.STRING,
    city: Sequelize.STRING,
    address: Sequelize.STRING
});
Account.sync();

module.exports = Company;