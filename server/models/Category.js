'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');

const Category = db.define('Category',{
    idCategory:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    name: Sequelize.STRING
});

Category.belongsTo(Category, { foreignKey: 'idProduct' });
Category.sync();

module.exports = Category;