export class Producto {
    id?: number;//?: quiere decir que no es campo obligatorio, cuando cree no es necesario, pero cuando busque si
    nombre: string;
    precio: number;

    constructor(nombre: string, precio: number){
        this.nombre = nombre;
        this.precio = precio;
    }

}

