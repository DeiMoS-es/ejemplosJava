package com.example.crud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductoDto {

	@NotBlank
	private String nombre;
	@Min(0)
	private Float precio;
	
	public ProductoDto() {
		super();
	}
		
	public ProductoDto(@NotBlank String nombre, @Min(0) Float precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
