const express = require('express');
const router = express.Router();
var multer = require('multer') ({
        dest: 'public/uploads'
    });

const productController = require('../controllers/product.controller');

router.get('/', productController.getProductList)
        .post('/', multer.any(), productController.saveProduct);
router.get('/:id', productController.getProduct)
        .put('/:id', productController.editProduct);

module.exports = router;