'use strict';
const Sequelize = require('sequelize');
const Tax = require('../models/Tax');

const TaxController = {};
/**
 * @api {get} / Permite obtener una lista de impuesto
 * @apiName getTaxList 
 * @apiGroup TaxController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * @apiSuccess {taxs} devuelve un objeto con impuestos
 */
TaxController.getTaxList = (req, res) => {
    Tax.findAll({
        order: [
            [Sequelize.col('id_tax'), 'DESC']
        ]
    })
    .then((taxs) => {
        res.status(200).json(taxs);
    })
    .catch((err) =>{
        res.status(500).json(err);
    });
};
/**
 * @api {post} / Permite guadar impuestos
 * @apiName saveTax 
 * @apiGroup TaxController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * 
 */
TaxController.saveTax = (req, res) => {
    Tax.create({
        percentage: req.body.percentage,
        description: req.body.description
    })
    .then(() => {
        res.status(201).json();
    })
    .catch(() => {
        res.status(500).json();
    });
};
/**
 * @api {get} /:id Permite obtener el impuesto por el id
 * @apiName getTax
 * @apiGroup TaxController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * @apiSuccess {tax} devuelve un objeto de impuesto
 */
TaxController.getTax = (req, res) => {
    Tax.findOne({
        where: { id_tax: req.params.id }
    })
    .then((tax) => {
        tax = tax.toJSON();
        delete tax.id_tax;
        res.status(200).json(tax);
    })
    .catch((err) => {
        res.status(500).json(err);
    });
};
/**
 * @api {put} /:id Permite modificar el impuesto por el id
 * @apiName editTax
 * @apiGroup axController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * 
 */
TaxController.editTax = (req, res) => {
    Tax.update({
        percentage: req.body.percentage,
        description: req.body.description
    },{
        where: { id_tax: req.params.id }
    })
    .then(() => {
        res.status(200).json();
    })
    .catch(() => {
        res.status(500).json(err);
    });
};

module.exports = TaxController;

