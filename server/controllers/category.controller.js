'use strict';

const Sequelize = require('sequelize');
const Category = require('../models/Category');

const CategoryController ={};
/**
 * @api {get} / Permite obtener una lista de categorias
 * @apiName getCategoryList
 * @apiGroup CategoryController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * 
 * @apiSuccess {categorys} devuelve un objeto con categorias
 */
CategoryController.getCategoryList = (req, res) => {
    Category.findAll({
        order: [
            [Sequelize.col('id_category'), 'DESC']
        ]
    })
    .then((categorys) => {
        res.status(200).json(categorys);
    })
    .catch((err) => {
        res.status(500).json(err);
    });
};

/**
 * @api {post} / Permite guadar categorias
 * @apiName saveCategory
 * @apiGroup CategoryController
 *
  * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * 
 */
CategoryController.saveCategory = (req, res) =>{
    Category.create({
        name: req.body.name
    })
    .then(() => {
        res.status(201).json();
    })
    .catch((err)=>{
        res.status(500).json(err);
    });
};
/**
 * @api {get} /:id Permite obtener la categoria por el id
 * @apiName getCategory
 * @apiGroup CategoryController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 *
 * @apiSuccess {category} devuelve un objeto de categoria
 */
CategoryController.getCategory = (req, res) =>{
    Category.findOne({
        where:{ id_category: req.params.id}
    }).then((category)=>{
        category = category.toJSON();
        delete category.id_category;
        res.status(200).json(category);
    })
    .catch((err)=>{
        res.status(500).json();
    });
};
/**
 * @api {put} /:id Permite modificar la categoria por el id
 * @apiName editCategory
 * @apiGroup CategoryController
 *
 * @apiParam {req} es el objeto de peticion
 * @apiParam {res} es el objeto de respuetas
 * 
 */ 
CategoryController.editCategory = (req, res)=>{
    Category.update({
        name: req.body.name
    },{
        where:{id_category: req.params.id}
    }).then(() =>{
        res.status(200).json();
    })
    .catch((err)=>{
        res.status(500).json(err);
    });
};

module.exports  = CategoryController;