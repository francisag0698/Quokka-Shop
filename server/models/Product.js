'use strict';

const Sequelize = require('sequelize');
const db = require('../database');
const Company = require('./Company');
const Category= require('./Category');
const Tax= require('./Tax');

const Product = db.define('Product', {
    id_product:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    code: Sequelize.STRING,
    name: Sequelize.STRING,
    description: Sequelize.STRING(100),
    code: Sequelize.STRING(100),
    price: Sequelize.DECIMAL(10,2),
    brand: Sequelize.STRING(70),
    state: Sequelize.BOOLEAN
},{
    timestamps: false   
});


Company.hasMany(Product, {foreignKey:'id_company'});
Product.belongsTo(Company,{foreignKey:'id_company'});


Category.hasMany(Product,{foreignKey:'id_category'});
Product.belongsTo(Category,{foreignKey:'id_category'});


Tax.hasMany(Product,{foreignKey:'id_tax'});
Product.belongsTo(Tax,{foreignKey:'id_tax'});


Product.sync();



module.exports = Product;