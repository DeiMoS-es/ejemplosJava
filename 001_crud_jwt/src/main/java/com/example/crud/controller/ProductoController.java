package com.example.crud.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.crud.dto.Mensaje;
import com.example.crud.dto.ProductoDto;
import com.example.crud.entity.Producto;
import com.example.crud.service.ProductoService;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")//Limitamos las peticiones al puerto de angular
public class ProductoController {
	
	//Inyectamos el servicio que es el que tiene todos los métodos implementados CRUD
	@Autowired
	ProductoService productoService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Producto>> list(){
		List<Producto> list = productoService.list();
		//No es necesario poner que va a devolver el ResponseEntity, pero yo lo pongo para ayudar a la hora de leer el código
		return new ResponseEntity<List<Producto>>(list, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/detail/{id}")
	//Este método recibe un parametro por url con el ID
	public ResponseEntity<Producto> getById(@PathVariable("id") int id){
		if(!productoService.existById(id)) {
			return new ResponseEntity(new Mensaje ("no existe"), HttpStatus.NOT_FOUND);
		}
		//Creamos el producto, OJO como es un Optional necesitamos el .get() para poder acceder
		Producto producto = productoService.getOne(id).get();		
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	//Este método es igual al anterior salvo que buscamos por nombre
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/detailname/{nombre}")
	//Este método recibe un parametro por url con el ID
	public ResponseEntity<Producto> getBynombre(@PathVariable("nombre") String nombre){
		if(!productoService.existByNombre(nombre)) {
			return new ResponseEntity(new Mensaje ("no existe"), HttpStatus.NOT_FOUND);
		}
		//Creamos el producto, OJO como es un Optional necesitamos el .get() para poder acceder
		Producto producto = productoService.getByNombre(nombre).get();		
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
		if(StringUtils.isBlank(productoDto.getNombre())) {
			System.out.println(productoDto.getPrecio());
			return new ResponseEntity(new Mensaje ("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if( productoDto.getPrecio() == null || productoDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje ("el precio debe ser mayor que cero"), HttpStatus.BAD_REQUEST);
		if(productoService.existByNombre(productoDto.getNombre()))
			return new ResponseEntity(new Mensaje ("el nombre ya existe"), HttpStatus.BAD_REQUEST);
		//Si todo está bien creamos el producto
		Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
		//Una vez creado guardamos el producto
		productoService.save(producto);
		return new ResponseEntity(new Mensaje ("el producto se ha creado"), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/update/{id}")
	public ResponseEntity<?> create(@PathVariable("id") int id, @RequestBody ProductoDto productoDto){
		//Primero comprobamos que el producto existe en BBDD
		if(!productoService.existById(id)) 
			return new ResponseEntity(new Mensaje ("no existe"), HttpStatus.NOT_FOUND);
		//Compruebo que el producto exista por nombre, y si su id no es igual a la que llega por parámetro, estoy intentando poner el mismo nombre al producto de otro que ya existe
		if(productoService.existByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId() != id)
			return new ResponseEntity(new Mensaje ("Se esta intentando actualizar un producto con un nombre que ya existe"), HttpStatus.BAD_REQUEST);
		if(StringUtils.isBlank(productoDto.getNombre()))
			return new ResponseEntity(new Mensaje ("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if(productoDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje ("el precio debe ser mayor que cero"), HttpStatus.BAD_REQUEST);
		
		//Si todo está bien recupero el producto existente
		Producto producto = productoService.getOne(id).get();
		//Modifico el nombre del producto existente, por el nuevo que llega
		producto.setNombre(productoDto.getNombre());
		//Modifico el precio del producto existente, por el nuevo que llega
		producto.setPrecio(productoDto.getPrecio());
		//Una vez actualizado guardamos el producto
		productoService.save(producto);
		return new ResponseEntity(new Mensaje ("el producto se ha actualizado"), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		if(!productoService.existById(id)) 
			return new ResponseEntity(new Mensaje ("no existe"), HttpStatus.NOT_FOUND);
		productoService.delete(id);
		return new ResponseEntity(new Mensaje ("producto eliminado"), HttpStatus.OK);
	}

}
