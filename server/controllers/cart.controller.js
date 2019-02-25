const Product = require('../models/Product');
const Category = require('../models/Category');


const CartController = {};

CartController.getCart = (req, res) => {
    res.status(200).json(req.session.cart);
}

CartController.addItem = (req, res) => {
    const ext = req.params.ext;
    Product.findOne({ where: { external_id: ext }, include: { model: Category } })
        .then(product => {
            var pos = verificar(req.session.cart, ext);
            if (pos == -1) {
                var data = { external: ext, name: product.name, category: product.Category.name, cant: 1, pu: product.price, pt: product.price, id: product.id_product };
                req.session.cart.push(data);
                console.log(req.session.cart);
            } else {
                var data = req.session.cart[pos];
                data.cant = data.cant + 1;
                data.pt = data.cant * data.pu;
                req.session.cart[pos] = data;
            }
            res.status(200).json(req.session.cart);
        }).catch((err) => {
            console.log(err)
            res.status(500).json();
        });
};

CartController.minusItem = (req, res) => {
    const ext = req.params.ext;
    var pos = verificar(req.session.cart, ext);
    var data = req.session.cart[pos];
    if(data.cant > 1){
        data.cant = data.cant - 1;
        data.pt = data.cant * data.pu;
        req.session.cart[pos] = data;
    }else{
        req.session.cart.splice(pos, 1);
    }
    res.status(200).json(req.session.cart);
};

CartController.plusItem = (req, res) =>{
    const ext = req.params.ext;
    var pos = verificar(req.session.cart, ext);
    var data = req.session.cart[pos];
    data.cant = data.cant + 1;
    data.pt = data.cant * data.pu;
    req.session.cart[pos] = data;
    res.status(200).json(req.session.cart);
};

function verificar(list, ext){
    var pos = -1;
    for (var i = 0; i < list.length; i++) {
        if (list[i].external == ext) {
            pos = i;
            break;
        }
    }
    return pos;
}

module.exports = CartController;