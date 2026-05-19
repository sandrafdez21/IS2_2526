package es.unican.is2.practica6;

/* Clase que representa un transporte realizado por un conductor */
public class Transporte {             // WMC = 9   WMCn = 1.8  CCog = 3  CCogn = 0.6
	
	private double horas;
	private int ton;
	private int personas;
	private CategoriaTransporte cat;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 
	public Transporte(double horas, CategoriaTransporte cat, int valor) throws IllegalArgumentException {   // WMC + 1
		if (horas <= 0 || valor <= 0 || cat == null) {    // WMC + 3     CCog + 1
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		if (cat.equals(CategoriaTransporte.Personas)) {   // WMC + 1    CCog + 1
			this.personas = valor;
		} else  {                                         // CCog + 1
			this.ton = valor;
		}
	}
	
	public double horas() {    // WMC + 1
		return horas;
	}

	public CategoriaTransporte categoria() { // WMC + 1
		return cat;
	}

	public int ton() {    // WMC + 1
		return ton;
	}

	public int getPersonas() {		// WMC + 1
		return personas;
	}
	
}
