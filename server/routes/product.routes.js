const express = require('express');
const router = express.Router();

const productController = require('../controllers/product.controller');

router.get('/', productController.getProductList)
        .post('/', productController.saveProduct);
router.get('/:id', productController.getProduct)
        .put('/:id', productController.editProduct);

module.exports = router;