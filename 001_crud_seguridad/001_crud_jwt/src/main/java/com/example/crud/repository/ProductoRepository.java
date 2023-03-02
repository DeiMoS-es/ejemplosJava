package com.example.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.entity.Producto;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Integer>{
	//Esta clase implementa los métodos principales
	
	Optional<Producto> findByNombre(String nombre);
	Boolean existsByNombre (String nombre);
	
}
