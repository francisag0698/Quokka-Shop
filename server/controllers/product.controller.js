'use strict';
const Sequelize = require('sequelize');
const fs = require('fs');
const uuidv4 = require('uuid/v4');

const Product = require('../models/Product');
const Company = require('../models/Company');
const Image = require('../models/Image');
const Category = require('../models/Category');
const Tax = require('../models/Tax');

const ProductController = {};
/**
 * @api {get} / Permite obtener una lista de productos
 * @apiName getProductList
 * @apiGroup ProductController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * @apiSuccess {products} devuelve un objeto con productos
 */
ProductController.getProductList = (req, res) => {
    Product.findAll({ 
        order: [ 
            [Sequelize.col('id_product'), 'DESC']
        ], include: [
            { model: Company }, { model: Image }
        ]
    })
    .then((products) => {
        res.status(200).json(products);
    })
    .catch((err) => {
        res.status(500).json(err);
    });
};
/**
 * @api {get} /featured Permite obtener los productos destacados
 * @apiName getFeaturedProducts
 * @apiGroup ProductController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * @apiSuccess {products} devuelve un objeto con productos
 */
ProductController.getFeaturedProducts = (req, res) =>{
    Product.findAll({
        order:[
            [Sequelize.col('sold'), 'DESC']
        ],
        include: [
            { model: Image }
        ],
        limit: 4
    }).then(products =>{
        res.status(200).json(products);
    }).catch((err) => {
        res.status(500).json(err);
    });
};
/**
 * @api {post} / Permite guadar productos
 * @apiName saveProduct
 * @apiGroup ProductController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * 
 */
ProductController.saveProduct = (req, res, next) => {
    Product.create({
        name: req.body.name,
        description: req.body.description,
        code: req.body.code,
        price: req.body.price,
        brand: req.body.brand,
        id_company: req.body.id_company,
        id_category: req.body.id_category,
        id_tax: req.body.id_tax
    })
    .then(product => {
        var data = [];
        req.files.forEach(element => {
            var extension = element.originalname.split(".").pop();
            var name = uuidv4() + "." + extension;
            fs.renameSync(element.destination + "/" + element.filename, "public/uploads/" + name);
            data.push({ path: name, id_product: product.id_product });
        });
        if(data.length > 0){
            Image.bulkCreate(data).then(() => {
                res.status(201).send();
            }).catch(err =>{
                console.log(err);
                res.status(500).json(err);
            });
        }
        
        res.status(201).send();
    })
    .catch((err) => {
        console.log(err)
        res.status(500).json(err);
    });
};
/**
 * @api {post} /iamge Permite guadar imagenes en un producto
 * @apiName addImage
 * @apiGroup ProductController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * 
 */
ProductController.addImage = (req, res) => {
    Product.findOne({
        where: { id_product: req.body.id_product }
    }).then(product => {
        if(product){
            var extension = req.file.originalname.split(".").pop();
            var name = uuidv4() +"."+ extension;
            fs.renameSync(req.file.destination + "/" + req.file.filename, "public/uploads/" + name);

            Image.create({
                path: name,
                id_product: req.body.id_product
            }).then(image =>{
                res.status(200).json(image);
            }).catch(err =>{
                console.log(err);
                res.status(500).send();
            });
        }
    }).catch(err =>{
        console.log(err);
        fs.unlinkSync(req.file.destination + "/" + req.file.filename);
        res.status(500).send();
    })
}
/**
 * @api {get} /:id Permite obtener el producto por el id
 * @apiName getProduct
 * @apiGroup ProductController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * @apiSuccess {product} devuelve un objeto de categoria
 */
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
/**
 * @api {put} /:id Permite modificar el producto por el id
 * @apiName editProduct
 * @apiGroup ProductController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * 
 */
ProductController.editProduct = (req, res) => {
    console.log(req.body);
    Product.update({
        name: req.body.name,
        description: req.body.description,
        code: req.body.code,
        price: req.body.price,
        brand: req.body.brand,
        id_company: req.body.id_company,
        id_category: req.body.id_category,
        id_tax: req.body.id_tax
    },{
        where: { id_product: req.params.id }
    })
    .then(() => {
        res.status(200).json();
    })
    .catch((err) => {
        console.log(err);
        res.status(500).send();
    });
};
/**
 * @api {put} /stock/:id Permite modificar el stock por el id
 * @apiName editStock 
 * @apiGroup ProductController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * 
 */
ProductController.editStock = (req, res) => {
    Product.find({
        where: { id_product: req.params.id }
    }).then(product => {
        if(product){
            product.update({
                stock: product.stock + req.body.stock
            }).then(resp => {
                res.status(200).json(resp);
            }).catch((err) => {
                console.log(err);
                res.status(500).send();
            });
        }else{
            res.status(404).send();
        }
    }).catch((err) => {
        console.log(err);
        res.status(500).send();
    });   
};
/**
 * @api {delet} /image/:id Permite eliminar una imagen
 * @apiName deleteImage 
 * @apiGroup ProductController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * 
 */
ProductController.deleteImage = (req, res) => {
    Image.find({
        where: { id_image: req.params.id }
    }).then(image => {
        if(image){
            image.destroy().then(() => {
                fs.unlinkSync("public/uploads/" + image.path);
                res.status(200).send();
            });
        }else{
            res.status(404).send();
        }
    }).catch(err => {
        console.log(err);
        res.status(500).send();
    })
}

module.exports = ProductController;