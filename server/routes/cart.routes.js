const express = require('express');
const router = express.Router();

const cartController = require('../controllers/cart.controller');

router.get('/all', cartController.getCart)
        .post('/add/:ext', cartController.addItem);

router.post('/plus/:ext', cartController.plusItem);
router.post('/minus/:ext', cartController.minusItem);

module.exports = router;