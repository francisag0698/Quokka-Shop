const express = require('express');
const router = express.Router();
const AuthController = require('../controllers/auth.controller');

router.get('/dashboard', AuthController.verifySession, (req, res) => {
    res.render('index', { req });
});

router.get('/login', AuthController.verifyLogin, (req, res) => {
    res.render('index', { req });
});

router.get('/', (req, res) => {
    res.render('index', { req });
});

module.exports = router;