'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');

const Category = db.define('Category',{
    id_category:{
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    name: Sequelize.STRING(80)
});


module.exports = Category;