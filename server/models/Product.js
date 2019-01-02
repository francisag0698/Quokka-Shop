'use strict';

const Sequelize = require('sequelize');
const db = require('../database');
const Company = require('./Company');
const Category= require('/Category');
const Tax= require('./Tax');

const Product = db.define('Product', {
    idProduct:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    code: Sequelize.STRING,
    name: Sequelize.STRING,
    description: Sequelize.STRING,
    code: Sequelize.STRING,
    price: Sequelize.STRING,
    brand: Sequelize.STRING,
    state: Sequelize.STRING,
    idTax:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    } 
},{
    timestamps: false   
});

Person.belongsTo(Company, { foreignKey: 'idCompany' });
Person.belongsTo(Category, { foreignKey: 'idCategory' });
Person.belongsTo(Tax, { foreignKey: 'idTax' });
Person.sync();

module.exports = Product;