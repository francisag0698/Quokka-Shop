'use strict';
const Person = require('../models/Person');
const Role = require('../models/Role');

const PersonController = {};

PersonController.getPersonList = async (req, res) => {
    const personList = await Person.findAll();
    res.json(personList);
};

PersonController.savePerson = async (req, res) => {
    await Person.create(req.body);
    res.json({
        msg: 'Person Saved!'
    });
};

PersonController.getPerson = async (req, res) => {
    const person = await Person.findOne({
        where: { dni: req.params.dni }
    });
    res.json(person);
};

PersonController.getPersonAccount = async (req, res) => {
    const person = await Person.findOne({
        where: { dni: req.params.dni }
    });
    res.json(await person.getAccount());
};

PersonController.getPersonRole = async (req, res) => {
    const person = await Person.findOne({
        where: { dni: req.params.dni }
    });
    res.json(await person.getRole());
};

PersonController.editPerson = async (req, res) => {
    await Person.update({
        dni_type: req.body.dni_type,
        dni: req.body.dni,
        first_name: req.body.first_name,
        last_name: req.body.last_name,
        birthdate: req.body.birthdate,
        postal_code: req.body.postal_code
    },
    { where: { dni: req.params.dni } });
    res.json({
        msg: 'Person Updated!'
    });
};

module.exports = PersonController;