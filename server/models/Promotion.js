'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');

const Promotion = db.define('Promotion', {
    id_promotion: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    name: Sequelize.STRING,
    description: Sequelize.STRING,
    percentage: Sequelize.DOUBLE,
    start_date: Sequelize.DATEONLY,
    end_date: Sequelize.DATEONLY
});


Promotion.sync();

module.exports = Promotion;