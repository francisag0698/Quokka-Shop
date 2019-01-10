const express = require('express');
const router = express.Router();
const passport = require('passport');

const accountController = require('../controllers/account.controller');

router.get('/', accountController.getAccountList)
        .post('/', accountController.saveAccount);
router.get('/:id', accountController.getAccount)
        .put('/:id', accountController.editAccount);

router.post('/session/login', passport.authenticate('local', { successRedirect: '/api/account',
                                failureRedirect: '/api/person',
                                failureFlash: false }));
router.get('/session/login', accountController.getAccountList);

module.exports = router;