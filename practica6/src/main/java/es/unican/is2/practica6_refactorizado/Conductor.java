package es.unican.is2.practica6_refactorizado;

import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor {  // WMC = 13  WMCn = 13/8 CCog = 2 CCogn = 2/8

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;

	private static final double SUELDO_BASE = 700.0;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {											// WMC + 1
		if (dni == null || nombre == null || apellido1 == null || direccion == null) {		// WMC + 4 // CCog + 1
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
	}

	public String getDni() {																// WMC + 1
		return dni;
	}

	public String getNombre() {																// WMC + 1
		return nombre;
	}

	public String getApellido1() {															// WMC + 1
		return apellido1;
	}

	public String getApellido2() {															// WMC + 1
		return apellido2;
	}

	public String getDireccion() {															// WMC + 1
		return direccion;
	}

	public double sueldo() {																// WMC + 1
		double sueldoTransportes = 0;
		for (Transporte t : transportes) {													// WMC + 1	// CCog + 1
			sueldoTransportes += t.obtenerExtra();
		}
		return SUELDO_BASE + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) {											// WMC + 1
		transportes.add(t);
	}

}
