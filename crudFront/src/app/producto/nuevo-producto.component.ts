import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Producto } from '../models/producto';
import { ProductoService } from '../service/producto.service';

@Component({
  selector: 'app-nuevo-producto',
  templateUrl: './nuevo-producto.component.html',
  styleUrls: ['./nuevo-producto.component.css']
})
export class NuevoProductoComponent {

  nombre: string = '';
  precio: number;

  constructor(private productoService: ProductoService, private toastr: ToastrService, private router: Router){}

  ngOnInit(){
    
  }

  //No devuelve nada porque así lo especificamos en el backend
  onCreate(): void{
    const producto = new Producto(this.nombre, this.precio);
    this.productoService.save(producto).subscribe(
      data => {
        this.nombre = data.nombre;
        this.toastr.success('Producto creado', 'Ok', {timeOut: 3000, positionClass: 'toast-top-center'});
        this.router.navigate(['/']);
      },
      err =>{
        this.toastr.error(err.error.mensaje, 'Fail', {timeOut: 3000, positionClass: 'toast-top-center'});//.mensaje porque en el backend devolvemos un mensaje
        this.router.navigate(['/']);
      }
    )
  }
}
