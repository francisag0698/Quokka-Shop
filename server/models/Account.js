'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');
const Person = require('./Person');

const Account = db.define('Account', {
    id_account: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    user_name: Sequelize.STRING,
    email: Sequelize.STRING,
    password: Sequelize.STRING,
    secure_token: Sequelize.STRING,
    phone_number: Sequelize.STRING,
    state: Sequelize.BOOLEAN,
    external_id: { type: Sequelize.STRING, defaultValue: Sequelize.UUIDV4 }
});

//Relacion 1 a 1 con Persona
Account.belongsTo(Person, { foreignKey: 'id_person' });
Account.sync();

module.exports = Account;