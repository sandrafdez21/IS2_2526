package es.unican.is2.practica6_refactorizado;

import java.util.LinkedList;
import java.util.List;
import fundamentos.*;

/**
 * Gestion de una empresa de transportes
 */
public class GestionTransportesGUI {  // WMC = 22   WMCn = 3.67 	CCog = 18	CCogn = 3

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {		// WMC + 1
		// opciones del menu
		final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1, 
		SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

		// crea la empresa de transportes
		GestionTransportes gt = new GestionTransportes();
		// crea la ventana de menu
		Menu menu = new Menu("Transportes");
		menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
		menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
		menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
		menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);
		
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) {											// WMC + 1		 CCog + 1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {									// CCog + 2
				case ANHADE_CONDUCTOR:							// WMC + 1
					anhadirConductor(gt);
					break;
				case ANHADE_TRANSPORTE:							// WMC + 1
					anhadirTransporte(gt);
					break;
				case SUELDO_CONDUCTOR:							// WMC + 1
					mostrarSueldo(gt);
					break;
				case MEJOR_CONDUCTOR:							// WMC + 1
					mostrarMejorConductor(gt);
					break;
			}
		}

	}

	private static void mostrarMejorConductor(GestionTransportes gt) { // WMC + 1
		List<Conductor> resultado = new LinkedList<Conductor>();
		double maxSueldo = 0.0;
		for (Conductor c : gt.getConductores()) {			// WMC + 1		 CCog + 1
			if (c.sueldo() > maxSueldo) {					// WMC + 1		 CCog + 2
				maxSueldo = c.sueldo();
				resultado.clear();
				resultado.add(c);
			} else if (c.sueldo() == maxSueldo) {			// WMC + 1		// CCog + 1
				resultado.add(c);
			}
		}		
		String msj = "";
		if (resultado.size() == 0) {						// WMC + 1		// CCog + 1
			msj = "No hay conductores";
		} else {											// CCog + 1
			for (Conductor c : resultado) {					// WMC + 1 // CCog + 2
				msj += c.getNombre() + " "+c.getApellido1()+"\n";
			}
		}
		mensaje("MEJOR CONDUCTOR", msj);
	}

	private static void mostrarSueldo(GestionTransportes gt) {	// WMC + 1
		Lectura lect = new Lectura("Transportes Peligrosos");
		lect.creaEntrada("DNI", "");
		lect.esperaYCierra();
		String dni = lect.leeString("DNI");
		Conductor conductor = gt.buscaConductor(dni);
		if (conductor!=null){								// WMC + 1	// CCog + 1
			mensaje("Sueldo", "El sueldo del conductor es: " + conductor.sueldo());
		} else {											// CCog + 1
			mensaje("ERROR", "No existe un conductor con DNI "+dni);
		}
	}

	private static void anhadirTransporte(GestionTransportes gt) { // WMC + 1
		Lectura lect = new Lectura("Nuevo transporte");
		lect.creaEntrada("DNI", "");
		lect.creaEntrada("Tipo Transporte: P | M | MP", "");
		lect.creaEntrada("Horas", 0);
		lect.creaEntrada("Personas", 0);
		lect.creaEntrada("Toneladas", 0);
		lect.esperaYCierra();
		String dni = lect.leeString("DNI");
		String tipo = lect.leeString("Tipo Transporte: P | M | MP");
		int horas = lect.leeInt("Horas");
		int personas = lect.leeInt("Personas");
		int toneladas = lect.leeInt("Toneladas");

		Transporte transporte = null;
		Conductor conductor = gt.buscaConductor(dni);
		if (conductor!=null) {									// WMC + 1	// CCog + 1
			switch (tipo) {													// CCog + 2
				case "P":										// WMC + 1
					transporte = new Personas(horas, personas);
					conductor.anhadeTransporte(transporte);
					break;
				case "M":										// WMC + 1
					transporte = new Mercancias(horas, toneladas);
					conductor.anhadeTransporte(transporte);
					break;
				case "MP":										// WMC + 1
					transporte = new MercanciasPeligrosas(horas, toneladas);
					conductor.anhadeTransporte(transporte);
					break;		
			}
		} else {												// CCog + 1
			mensaje("ERROR", "No existe un conductor con DNI "+dni);
		}
	}

	private static void anhadirConductor(GestionTransportes gt) {	// WMC + 1
		Lectura lect = new Lectura("Datos Conductor");
		lect.creaEntrada("DNI", "");
		lect.creaEntrada("Nombre","");
		lect.creaEntrada("Apellido1", "");
		lect.creaEntrada("Apellido2", "");
		lect.creaEntrada("Direccion", "");
		lect.esperaYCierra();
		String dni = lect.leeString("DNI");
		String nombre = lect.leeString("Nombre");
		String apellido1 = lect.leeString("Apellido1");
		String apellido2 = lect.leeString("Apellido2");
		String direccion = lect.leeString("Direccion");
		// Anhade el conductor
		Conductor conductor3 = new Conductor(dni, nombre, apellido1, apellido2, direccion);
		if (!gt.anhadeConductor(conductor3)) 						// WMC + 1	// CCog + 1
			mensaje("ERROR", "Ya existe un conductor con DNI "+dni);
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {	// WMC + 1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
