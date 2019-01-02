'use strict';
const Sequelize = require('sequelize');
const Account = require('../models/Account');

const crypto = require('crypto');

const AccountController = {};

AccountController.getAccountList = (req, res) => {
    Account.findAll()
    .then((accounts) =>{
        res.status(200).send(accounts); })
    .catch((err) =>{
        res.status(500).send(err);
    });
};

AccountController.saveAccount = (req, res) => {
    req.body.password = crypto.createHmac('sha512',req.body.password).update(req.body.password).digest("base64");
    Account.create(req.body)
    .then(() =>{
        res.status(201).send(); })
    .catch((err) => {
        res.status(500).send(err);
    });
};

AccountController.getAccount = (req, res) => {
    Account.findOne({
        where: { id_account: req.params.id }
    }).then((account) => {
        account = account.toJSON();
        delete account.id_account;
        delete account.createdAt;
        delete account.updatedAt;
        delete account.id_person;
        res.status(200).send(account); })
    .catch((err) => {
        res.status(500).send();
    });
};

AccountController.editAccount = (req, res) => {
    let hash = crypto.createHmac('sha512',req.body.password).update(req.body.password).digest("base64");
    Account.update({
        password: hash,
        email: req.body.email,
        secure_token: req.body.secure_token,
        phone_number: req.body.phone_number,
        state: req.body.state,
        updatedAt: Sequelize.NOW
    },{
        where: { id_account: req.params.id }
    }).then(() => {
        res.status(200).send(); })
    .catch((err) => {
        res.status(500).send(err);
    });
};

module.exports = AccountController;