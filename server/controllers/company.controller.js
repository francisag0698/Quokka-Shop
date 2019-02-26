'use strict';
const Sequelize = require('sequelize');
const Company = require('../models/Company');

const CompanyController = {};
/**
 * @api {get} / Permite obtener una lista de companias
 * @apiName getCompanyList
 * @apiGroup CompanyController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * @apiSuccess {companys} devuelve un objeto con companias
 */
CompanyController.getCompanyList = (req, res) => {
    Company.findAll({
        order: [
            [Sequelize.col('id_company'), 'DESC']
        ]
    })
    .then((companys) => {
        res.status(200).json(companys);    
    })
    .catch((err) => {
        res.status(500).json(err);
    });
};
/**
 * @api {post} / Permite guadar companias
 * @apiName saveCompany
 * @apiGroup CompanyController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * 
 */
CompanyController.saveCompany = (req, res) => {
    Company.create({
        name: req.body.name,
        country: req.body.country,
        city: req.body.city,
        address: req.body.address
    })
    .then(() =>{
        res.status(201).json(); 
    })
    .catch((err) =>{
        res.status(500).json(err);
    });
};
/**
 * @api {get} /:id Permite obtener la compania por el id
 * @apiName getCompany
 * @apiGroup CompanyController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * @apiSuccess {company} devuelve un objeto de compania
 */
CompanyController.getCompany = (req, res) => {
    Company.findOne({
        where: { id_company: req.params.id }
    }).then((company) => {
        company = company.toJSON();
        delete company.id_company;
        res.status(200).json(company);
    })
    .catch((err) => {
        res.status(500).json();
    });
};
/**
 * @api {put} /:id Permite modificar la compania por el id
 * @apiName editCompany
 * @apiGroup CompanyController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * 
 */
CompanyController.editCompany = (req, res) => {
    Company.update({
        name: req.body.name,
        country: req.body.country,
        city: req.body.city,
        address: req.body.address
    },{
        where: { id_company: req.params.id }
    }).then(() => {
        res.status(200).json(); 
    })
    .catch((err) => {
        res.status(500).json(err); 
    });
};

module.exports = CompanyController;
