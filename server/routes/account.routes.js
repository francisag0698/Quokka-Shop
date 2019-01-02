const express = require('express');
const router = express.Router();

const accountController = require('../controllers/account.controller');

router.get('/', accountController.getAccountList)
        .post('/', accountController.saveAccount);
router.get('/:id', accountController.getAccount)
        .put('/:id', accountController.editAccount);

module.exports = router;