import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  //El service es el que se conecta, tengo que pasarle la url a la que tiene que conectar
  productoUrl = 'http://localhost:8080/producto/';
  constructor(private httpClient: HttpClient) { }

  //metodos del backend

  //Metodo para listar los productos, devuelve un Observable porque así lo definimos en el backend y un array de Productos por eso los []
  public lista(): Observable<Producto[]>{
    return this.httpClient.get<Producto[]>(this.productoUrl + 'lista');
  }

  //Este metodo como necesita parámetro, para pasarlo por la Url ponemos comillas `` y el parámetro a pasar entre ${}
  public detail(id: number): Observable<Producto>{
    return this.httpClient.get<Producto>(this.productoUrl + `detail/${id}`);
  }

  public detailName(nombre: string): Observable<Producto>{
    return this.httpClient.get<Producto>(this.productoUrl + `detailname/${nombre}`);
  }

  //Como este método no devuelve nada, así lo especificamos en el backend, se pone any en el tipo devuelto
  //En el método post hay un requestbody, así lo especificamos en el backend, entonces hay que decirle lo que le vamos a enviar, en este caso un {producto}
  //Si utilizo un post y no envio un requestbody, se le pasarían {} (vacio)
  public save(producto: Producto): Observable<any>{
    console.log(producto)
    return this.httpClient.post<any>(this.productoUrl + 'create', producto);
  }

  public update(id: number ,producto: Producto): Observable<any>{
    return this.httpClient.put<any>(this.productoUrl + `update/${id}`, producto);
  }

  public delete(id: number):Observable<any>{
    return this.httpClient.delete<any>(this.productoUrl + `delete/${id}`);
  }
}
