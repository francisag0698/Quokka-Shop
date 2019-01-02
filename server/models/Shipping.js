'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');
const Order = require('./Order');

const Shipping = db.define('Shipping', {
    id_shipping: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    type: Sequelize.STRING,
    description: Sequelize.STRING
});

Shipping.belongsTo(Order, { foreignKey: 'id_order' });
Shipping.sync();

module.exports = Shipping;