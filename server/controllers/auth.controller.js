'use strict';
const Account = require('../models/Account');
const bcrypt = require('bcryptjs');

const AuthController = {};

AuthController.getSession = (username, password, done) => {
    Account.findOne({
        where: { user_name: username }
    }).then((account) =>{
        if(!account) return done(null, false, { message: 'Incorrect username.' });
        if(!bcrypt.compareSync(password, account.password)) return done(null, false, { message: 'Incorrect password.' });     
        return done(null, account);  
    }).catch((err) => {
        console.log(err);
        return done(err);
    })
}

AuthController.verifySession = (req, res, next) => {
    if(req.user) next();
    else res.redirect('/login'); 
}

AuthController.verifyLogin = (req, res, next) => {
    if(!req.user) next();
    else res.redirect('/dashboard'); 
}

module.exports = AuthController;