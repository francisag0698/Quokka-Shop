export class Product {

    constructor(id='', name='', description='', code='', price='', brand='', company='', category='', tax=''){
        this.id_product = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.price = price;
        this.brand = brand;
        this.id_company = company;
        this.id_category = category;
        this.id_tax = tax;
    }

    id_product: string;
    name: string;
    description: string;
    code: string;
    price: string;
    brand: string;
    id_company: string;
    id_category: string;
    id_tax: string;
}