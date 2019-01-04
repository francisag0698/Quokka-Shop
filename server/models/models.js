const fs = require('fs');

const models = fs
    .readdirSync(__dirname)
    .filter(file =>{
        return (file.indexOf('.') !== 0) && (file !== __filename) && (file.slice(-3) === '.js');
    })
    .forEach(file => {
        var model = require('./'+file);
        console.log(model);
    });

module.exports = models;