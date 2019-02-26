define({ "api": [
  {
    "type": "put",
    "url": "/",
    "title": "Permite editar una cuenta",
    "name": "editAccount",
    "group": "AccountController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./account.controller.js",
    "groupTitle": "AccountController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite listar obtener cuenta por por el id",
    "name": "getAccount",
    "group": "AccountController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "account",
            "optional": false,
            "field": "Devuelve",
            "description": "<p>una cuenta</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./account.controller.js",
    "groupTitle": "AccountController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite listar Cuentas",
    "name": "getAccountList",
    "group": "AccountController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "accounts",
            "optional": false,
            "field": "Devuelve",
            "description": "<p>una lista de cuentas.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./account.controller.js",
    "groupTitle": "AccountController"
  },
  {
    "type": "post",
    "url": "/",
    "title": "Permite Guardar Cuentas",
    "name": "saveAccount",
    "group": "AccountController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./account.controller.js",
    "groupTitle": "AccountController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite obtener una sesion",
    "name": "getSession",
    "group": "AuthController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "account",
            "optional": false,
            "field": "Devuelve",
            "description": "<p>una sesion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./auth.controller.js",
    "groupTitle": "AuthController"
  },
  {
    "type": "post",
    "url": "/add/:ext",
    "title": "Permite agregar un item",
    "name": "addItem",
    "group": "CartController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./cart.controller.js",
    "groupTitle": "CartController"
  },
  {
    "type": "get",
    "url": "/order",
    "title": "Permite obtener una ordem",
    "name": "getOrder",
    "group": "CartController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "orders",
            "optional": false,
            "field": "Devuelve",
            "description": "<p>una orden</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./cart.controller.js",
    "groupTitle": "CartController"
  },
  {
    "type": "get",
    "url": "/all",
    "title": "Permite obtener una orden",
    "name": "getOrder",
    "group": "CartController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "orders",
            "optional": false,
            "field": "Devuelve",
            "description": "<p>una orden</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./cart.controller.js",
    "groupTitle": "CartController"
  },
  {
    "type": "post",
    "url": "/minus/:ext",
    "title": "Permite disminuir un item del pedido",
    "name": "minusItem",
    "group": "CartController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "req.session.cart",
            "optional": false,
            "field": "devuelve",
            "description": "<p>el pedido disminuido</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./cart.controller.js",
    "groupTitle": "CartController"
  },
  {
    "type": "post",
    "url": "/plus/:ext",
    "title": "Permite disminuir un item del pedido",
    "name": "plusItem",
    "group": "CartController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "req.session.cart",
            "optional": false,
            "field": "devuelve",
            "description": "<p>el pedido aumentado</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./cart.controller.js",
    "groupTitle": "CartController"
  },
  {
    "type": "post",
    "url": "/process",
    "title": "Permite aumentar un item del pedido",
    "name": "processing",
    "group": "CartController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./cart.controller.js",
    "groupTitle": "CartController"
  },
  {
    "type": "put",
    "url": "/:id",
    "title": "Permite modificar la categoria por el id",
    "name": "editCategory",
    "group": "CategoryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./category.controller.js",
    "groupTitle": "CategoryController"
  },
  {
    "type": "get",
    "url": "/:id",
    "title": "Permite obtener la categoria por el id",
    "name": "getCategory",
    "group": "CategoryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "category",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto de categoria</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./category.controller.js",
    "groupTitle": "CategoryController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite obtener una lista de categorias",
    "name": "getCategoryList",
    "group": "CategoryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "categorys",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto con categorias</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./category.controller.js",
    "groupTitle": "CategoryController"
  },
  {
    "type": "post",
    "url": "/",
    "title": "Permite guadar categorias",
    "name": "saveCategory",
    "group": "CategoryController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./category.controller.js",
    "groupTitle": "CategoryController"
  },
  {
    "type": "put",
    "url": "/:id",
    "title": "Permite modificar la compania por el id",
    "name": "editCompany",
    "group": "CompanyController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./company.controller.js",
    "groupTitle": "CompanyController"
  },
  {
    "type": "get",
    "url": "/:id",
    "title": "Permite obtener la compania por el id",
    "name": "getCompany",
    "group": "CompanyController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "company",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto de compania</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./company.controller.js",
    "groupTitle": "CompanyController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite obtener una lista de companias",
    "name": "getCompanyList",
    "group": "CompanyController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "companys",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto con companias</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./company.controller.js",
    "groupTitle": "CompanyController"
  },
  {
    "type": "post",
    "url": "/",
    "title": "Permite guadar companias",
    "name": "saveCompany",
    "group": "CompanyController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./company.controller.js",
    "groupTitle": "CompanyController"
  },
  {
    "type": "put",
    "url": "/:id",
    "title": "Permite modificar la persona por el id",
    "name": "editPerson",
    "group": "PersonController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./person.controller.js",
    "groupTitle": "PersonController"
  },
  {
    "type": "get",
    "url": "/:id",
    "title": "Permite obtener la persona por el id",
    "name": "getPerson",
    "group": "PersonController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "person",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto de categoria</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./person.controller.js",
    "groupTitle": "PersonController"
  },
  {
    "type": "get",
    "url": "/role/:dni",
    "title": "Permite obtener la persona por el dni rol",
    "name": "getPersonAccount",
    "group": "PersonController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./person.controller.js",
    "groupTitle": "PersonController"
  },
  {
    "type": "get",
    "url": "/account/:dni",
    "title": "Permite obtener la persona por el dni cuenta",
    "name": "getPersonAccount",
    "group": "PersonController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./person.controller.js",
    "groupTitle": "PersonController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite obtener una lista de personas",
    "name": "getPersonList",
    "group": "PersonController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "personList",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto con personas</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./person.controller.js",
    "groupTitle": "PersonController"
  },
  {
    "type": "post",
    "url": "/",
    "title": "Permite guadar personas",
    "name": "savePerson",
    "group": "PersonController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./person.controller.js",
    "groupTitle": "PersonController"
  },
  {
    "type": "post",
    "url": "/admin",
    "title": "Permite guadar personas",
    "name": "savePersonAdmin",
    "group": "PersonController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./person.controller.js",
    "groupTitle": "PersonController"
  },
  {
    "type": "post",
    "url": "/iamge",
    "title": "Permite guadar imagenes en un producto",
    "name": "addImage",
    "group": "ProductController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./product.controller.js",
    "groupTitle": "ProductController"
  },
  {
    "type": "delet",
    "url": "/image/:id",
    "title": "Permite eliminar una imagen",
    "name": "deleteImage",
    "group": "ProductController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./product.controller.js",
    "groupTitle": "ProductController"
  },
  {
    "type": "put",
    "url": "/:id",
    "title": "Permite modificar el producto por el id",
    "name": "editProduct",
    "group": "ProductController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./product.controller.js",
    "groupTitle": "ProductController"
  },
  {
    "type": "put",
    "url": "/stock/:id",
    "title": "Permite modificar el stock por el id",
    "name": "editStock",
    "group": "ProductController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./product.controller.js",
    "groupTitle": "ProductController"
  },
  {
    "type": "get",
    "url": "/featured",
    "title": "Permite obtener los productos destacados",
    "name": "getFeaturedProducts",
    "group": "ProductController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "products",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto con productos</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./product.controller.js",
    "groupTitle": "ProductController"
  },
  {
    "type": "get",
    "url": "/:id",
    "title": "Permite obtener el producto por el id",
    "name": "getProduct",
    "group": "ProductController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "product",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto de categoria</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./product.controller.js",
    "groupTitle": "ProductController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite obtener una lista de productos",
    "name": "getProductList",
    "group": "ProductController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "products",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto con productos</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./product.controller.js",
    "groupTitle": "ProductController"
  },
  {
    "type": "post",
    "url": "/",
    "title": "Permite guadar productos",
    "name": "saveProduct",
    "group": "ProductController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./product.controller.js",
    "groupTitle": "ProductController"
  },
  {
    "type": "put",
    "url": "/:id",
    "title": "Permite modificar la promocion por el id",
    "name": "editPromotion",
    "group": "PromotionController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./promotion.controller.js",
    "groupTitle": "PromotionController"
  },
  {
    "type": "get",
    "url": "/:id",
    "title": "Permite obtener la promocion por el id",
    "name": "getPromotion",
    "group": "PromotionController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "promotion",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto de promocion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./promotion.controller.js",
    "groupTitle": "PromotionController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite obtener una lista de promociones",
    "name": "getPromotionList",
    "group": "PromotionController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "promotions",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto con promociones</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./promotion.controller.js",
    "groupTitle": "PromotionController"
  },
  {
    "type": "post",
    "url": "/",
    "title": "Permite guadar promociones",
    "name": "savePromotion",
    "group": "PromotionController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./promotion.controller.js",
    "groupTitle": "PromotionController"
  },
  {
    "type": "put",
    "url": "/:id",
    "title": "Permite modificar un role por el id",
    "name": "editRole",
    "group": "RoleController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./role.controller.js",
    "groupTitle": "RoleController"
  },
  {
    "type": "get",
    "url": "/:id",
    "title": "Permite obtener la role por el id",
    "name": "getRole",
    "group": "RoleController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "role",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto de rol</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./role.controller.js",
    "groupTitle": "RoleController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite obtener una lista de roles",
    "name": "getRoleList",
    "group": "RoleController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "roleList",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto con roles</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./role.controller.js",
    "groupTitle": "RoleController"
  },
  {
    "type": "post",
    "url": "/",
    "title": "Permite guadar roles",
    "name": "saveRole",
    "group": "RoleController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./role.controller.js",
    "groupTitle": "RoleController"
  },
  {
    "type": "get",
    "url": "/:id",
    "title": "Permite obtener el impuesto por el id",
    "name": "getTax",
    "group": "TaxController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "tax",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto de impuesto</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./tax.controller.js",
    "groupTitle": "TaxController"
  },
  {
    "type": "get",
    "url": "/",
    "title": "Permite obtener una lista de impuesto",
    "name": "getTaxList",
    "group": "TaxController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "taxs",
            "optional": false,
            "field": "devuelve",
            "description": "<p>un objeto con impuestos</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./tax.controller.js",
    "groupTitle": "TaxController"
  },
  {
    "type": "post",
    "url": "/",
    "title": "Permite guadar impuestos",
    "name": "saveTax",
    "group": "TaxController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./tax.controller.js",
    "groupTitle": "TaxController"
  },
  {
    "type": "put",
    "url": "/:id",
    "title": "Permite modificar el impuesto por el id",
    "name": "editTax",
    "group": "axController",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "req",
            "optional": false,
            "field": "es",
            "description": "<p>el objeto de peticion</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./tax.controller.js",
    "groupTitle": "axController"
  }
] });
