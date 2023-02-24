package com.example.crud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductoDto {

	@NotBlank
	private String nombre;
	@Min(0)
	private float precio;
	
	public ProductoDto() {
		super();
	}
	
	public ProductoDto(String nombre, float precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}
