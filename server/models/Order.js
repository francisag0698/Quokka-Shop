'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');
const Person = require('./Person');

const Order = db.define('Order', {
    id_order: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    num_order: Sequelize.INTEGER,
    total: Sequelize.DOUBLE,
    state: Sequelize.BOOLEAN,
    external_id: { type: Sequelize.STRING, defaultValue: Sequelize.UUIDV4 }
});

Order.belongsTo(Person, { foreignKey: 'id_person' });
Order.sync();

module.exports = Order;