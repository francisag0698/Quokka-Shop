'use strict';
const Sequelize = require ('sequelize');
const db = require ('../database');
const Product = require('./Product');

const Image = db.define('Image', {
    idImage:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    idProduct:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    path: Sequelize.STRING
    
});

Image.belongsTo(Product, { foreignKey: 'idProduct' });
Image.sync();

module.exports = Image;

