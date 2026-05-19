package es.unican.is2.practica6;

import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor {		// WMC = 18   WMCn = 2  CCog = 8   CCogn = 0.89

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {										// WMC + 1
		if (dni == null || nombre == null || apellido1 == null || direccion == null) {	// WMC + 4 	 CCog + 1
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dire = direccion;
	}

	public String dni() {			// WMC + 1
		return dni;
	}

	public String getDni() {		// WMC + 1
		return dni;
	}

	public String getNombre() {		// WMC + 1
		return nombre;
	}

	public String getApellido1() {	// WMC + 1
		return apellido1;
	}

	public String apellido2() {		// WMC + 1
		return apellido2;
	}

	public String getDire() {		// WMC + 1
		return dire;
	}

	public double sueldo() {				// WMC + 1
		double sueldoTransportes = 0;
		for (Transporte t : transportes) {	// WMC + 1		CCog + 1
			double sueldoExtraTransporte = 0.0;
			switch (t.categoria()) {						// CCog + 2
				case Mercancias:							// WMC + 1		
					sueldoExtraTransporte = t.ton() * 2;
					break;
				case MercanciasPeligrosas:					// WMC + 1
					sueldoExtraTransporte = t.ton() * 2 + 50;
					break;
				case Personas:								// WMC + 1
					if (t.getPersonas() < 10)				// WMC + 1  CCog + 3
						sueldoExtraTransporte = t.horas() * 0.5;
					else									// CCog + 1
						sueldoExtraTransporte = t.horas();
					break;
			}
			sueldoTransportes += t.horas() * 5 + sueldoExtraTransporte;
		}
		return 700 + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) {		// WMC + 1
		transportes.add(t);
	}

}
