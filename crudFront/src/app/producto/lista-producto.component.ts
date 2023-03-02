import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Producto } from '../models/producto';
import { ProductoService } from '../service/producto.service';

@Component({
  selector: 'app-lista-producto',
  templateUrl: './lista-producto.component.html',
  styleUrls: ['./lista-producto.component.css']
})
export class ListaProductoComponent implements OnInit{

  productos: Producto[] = [];//Creo una lista vacía, ya que este modulo es el de listar
  //Inyectamos el servicio credo
  constructor(private productoService: ProductoService, private toastr: ToastrService){}

  ngOnInit(){
    this.cargarProductos();
  }

  cargarProductos():void{
    //Como devuelve un observable, necesitamos el subscribe
    this.productoService.lista().subscribe(
      data =>{
        this.productos = data; //El array lo lleno con lo que obtengo de la consulta
      },
      err => {
        console.log(err);
      }
    );
  }

  borrar(id?: number){
    if(id != undefined){
      this.productoService.delete(id).subscribe(
        data =>{
          this.toastr.success('Producto eliminado', 'Ok', {timeOut: 3000, positionClass: 'toast-top-center'});
          this.cargarProductos();
        },
        err =>{
          this.toastr.error(err.error.mensaje, 'Fail', {timeOut: 3000, positionClass: 'toast-top-center'});//.mensaje porque en el backend devolvemos un mensaje
        }
      );
    }    
  }
}
