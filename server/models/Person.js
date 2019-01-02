
'use strict';
const Sequelize = require('sequelize');
const  db = require('../database');
const Role = require('./Role');

const Person = db.define('Person', {
    id_person: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    dni_type: Sequelize.STRING,
    dni: Sequelize.STRING,
    first_name: Sequelize.STRING,
    last_name: Sequelize.STRING,
    birthdate: Sequelize.DATEONLY,
    postal_code: Sequelize.STRING,
    external_id: { type: Sequelize.STRING, defaultValue: Sequelize.UUIDV4 }
}, {
    timestamps: false
});

Person.belongsTo(Role, { foreignKey: 'id_role' });
Person.sync();

module.exports = Person;
