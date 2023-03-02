package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.crud.entity.Producto;
import com.example.crud.repository.ProductoRepository;

@Service
@Transactional//Para que la bbdd no pierda consistencia, Si hay un solo item de un producto en stock, 
//lo compro y sin que finalice la transacción lo compras tú también, se producirá una inconsistencia
public class ProductoService {

	//Inyectamos el repositorio que es el que tiene todos los métodos
	@Autowired
	ProductoRepository productoRepository;
	
	/*
	 * Este método sirve para listar todos los productos
	 * @return devuelve una lista con todos los productos
	 */
	public List<Producto> list(){
		return productoRepository.findAll();
	}
	/*
	 * Este método sirve para buscar un producto
	 * @param id se le pasará el id del producto a buscar
	 * @return devuele un producto si lo encuentra
	 */
	public Optional<Producto> getOne(int id){
		return productoRepository.findById(id);
	}
	
	/*
	 * Este método sirve para buscar un producto por su nombre
	 * @param nombre se le pasará el nombre del producto a buscar
	 * @return devuelve un producto si lo encuentra
	 */
	public Optional<Producto> getByNombre(String nombre){
		return productoRepository.findByNombre(nombre);
	}
	
	/*Este método sirve para guardar un producto
	 * @param producto se le pasará el producto a guardar
	 * No devuelve nada
	 */
	public void save(Producto producto) {
		productoRepository.save(producto);
	}
	
	/*Este método sirve para eliminar un producto
	 * @param id se le pasa el id para eliminarlo
	 * No devuelve nada
	 */
	public void delete (int id) {
		productoRepository.deleteById(id);
	}
	
	/*Este método sirve para comprobar si un producto existe por el ID
	 * @param id se le pasará el id del producto a buscar
	 * @return devuelve true o false
	 */
	public boolean existById(int id) {
		return productoRepository.existsById(id);
	}
	
	/*Este método sirve para comprobar si un producto por el nombre
	 * @param nombre se le pasará el nombre del producto a buscar
	 * @return devuelve true o false*/
	public boolean existByNombre(String nombre) {
		return productoRepository.existsByNombre(nombre);
	}
}
	
