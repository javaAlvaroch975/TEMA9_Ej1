package com.hibernate.model;

import java.util.Properties;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;

import org.hibernate.cfg.Environment;

import org.hibernate.service.ServiceRegistry;

import com.hibernate.model.Ciudad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ciudades")
public class Ciudad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_Ciudad")
	private int id;
	
	@Column(name="Nombre")
	private String nombre;
	
	@Column(name="N_habitantes")
	private int num_habitantes;
	
	public Ciudad() {
		super();
	}
	
	public Ciudad(String nombre, int num_habitantes) {
		super();
		this.nombre = nombre;
		this.num_habitantes = num_habitantes;
	}
	
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

	public int getNum_habitantes() {
		return num_habitantes;
	}

	public void setNum_habitantes(int num_habitantes) {
		this.num_habitantes = num_habitantes;
	}
}

