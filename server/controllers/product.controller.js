'use strict';
const Sequelize = require('sequelize');
const Product = require('../models/Product');
const Company = require('../models/Company');
const Category = require('../models/Category');
const Tax = require('../models/Tax');

const ProductController = {};

ProductController.getProductList = (req, res) => {
    Product.findAll({ 
        order: [ 
            [Sequelize.col('id_product'), 'DESC']
        ], include: { model: Company }
    })
    .then((products) => {
        res.status(200).json(products);
    })
    .catch((err) => {
        res.status(500).json(err);
    });
};

ProductController.saveProduct = (req, res) => {
    Product.create({
        name: req.body.name,
        description: req.body.description,
        code: req.body.code,
        price: req.body.price,
        brand: req.body.brand,
        id_company: req.body.company,
        id_category: req.body.category,
        id_tax: req.body.tax
    })
    .then(() => {
        res.status(201).json();
    })
    .catch((err) => {
        console.log(err)
        res.status(500).json(err);
    });
};

ProductController.getProduct = (req, res) =>{
    Product.findOne({
        where: { id_product: req.params.id }
    })
    .then((product) => {
        product = product.toJSON();
        delete product.id_product;
        res.status(200).json(product);
    })
    .catch((err) => {
        res.status(500).json();            
    });
};

ProductController.editProduct = (req, res) => {
    Product.update({
        name: req.body.name,
        description: req.body.description,
        code: req.body.code,
        price: req.body.price,
        brand: req.body.brand,
        id_company: req.body.company,
        id_category: req.body.category,
        id_tax: req.body.tax
    },{
        where: { id_product: req.params.id }
    })
    .then(() => {
        res.status(200).json();
    })
    .catch((err) => {
        res.status(500).json(err);
    });
};

module.exports = ProductController;