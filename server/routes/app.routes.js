const express = require('express');
const router = express.Router();
const AuthController = require('../controllers/auth.controller');

router.get('/dashboard', (req, res) => {
    res.render('index', { req });
});

router.get('/login', (req, res) => {
    res.render('index', { req });
});

router.get('/', (req, res) => {
    res.render('index', { req });
});

module.exports = router;