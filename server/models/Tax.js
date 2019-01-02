'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');

const Tax = db.define('Category',{
    idCategory:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    percentage: Sequelize.DECIMAL,
    description: Sequelize.STRING
});

Tax.belongsTo(Tax, { foreignKey: 'idTax' });
Tax.sync();

module.exports = Tax;
