export class Order {

    constructor(id_order= '', num_order = '', tax = '', total = ''){
        this.id_order = id_order;
        this.num_order = num_order;
        this.tax = tax;
        this.total = total;
    }
    id_order: string;
    num_order: string;
    tax: string;
    total: string;
    state: boolean;
}