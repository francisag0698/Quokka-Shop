'use strict';
const Role = require('../models/Role');

const RoleController = {};
/**
 * @api {get} / Permite obtener una lista de roles
 * @apiName getRoleList 
 * @apiGroup RoleController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * @apiSuccess {roleList} devuelve un objeto con roles
 */
RoleController.getRoleList = async (req, res) => {
    const roleList = await Role.findAll();
    res.json(roleList);
};
/**
 * @api {post} / Permite guadar roles
 * @apiName saveRole
 * @apiGroup RoleController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * 
 */
RoleController.saveRole = async (req, res) => {
    await Role.create(req.body);
    res.json({
        msg: 'Role Saved!'
    });
};
/**
 * @api {get} /:id Permite obtener la role por el id
 * @apiName getRole
 * @apiGroup RoleController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * @apiSuccess {role} devuelve un objeto de rol
 */
RoleController.getRole = async (req, res) => {
    const role = await Role.findOne({
        where: { id_role: req.params.id }
    });
    res.json(role);
};
/**
 * @api {put} /:id Permite modificar un role por el id
 * @apiName editRole
 * @apiGroup RoleController
 *
 * @apiParam {req, res} permiten realizar la peticiones y devoluciones de repuesta 
 *
 * 
 */
RoleController.editRole = async (req, res) => {
    await Role.update({
        name: req.body.name
    },{
        where: { id: req.params.id }
    });
    res.json({
        msg: 'Role Updated!'
    });
};

module.exports = RoleController;
