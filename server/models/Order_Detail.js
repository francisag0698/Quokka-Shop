'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');
const Order = require('./Order');

const Order_Detail = db.define('Order_Detail', {
    id_detail: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    quantity: Sequelize.INTEGER(6)
});

Order_Detail.belongsTo(Order, { foreignKey: 'id_order' });
Order_Detail.sync();

module.exports = Order_Detail;