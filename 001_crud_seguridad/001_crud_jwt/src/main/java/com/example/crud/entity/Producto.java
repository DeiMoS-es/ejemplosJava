package com.example.crud.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity

public class Producto {

	//Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Hacemos que el id sea un valor autoincrementa
	private int id;
	private String nombre;
	private float precio;
	
	//Constructores
	public Producto() {
		super();
	}
	public Producto( String nombre, float precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}
	//Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	//ToString
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	//Equals para decir si un producto es el mismo
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return id == other.id;
	}	
	
}
