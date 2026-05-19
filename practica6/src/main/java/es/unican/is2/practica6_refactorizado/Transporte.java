package es.unican.is2.practica6_refactorizado;

/* Clase que representa un transporte realizado por un conductor */
public abstract class Transporte {	// WMC = 6 WMCn = 6/5 CCog = 1 Ccog = 1/5
	
	private double horas;

	private static final double EXTRA_BASICO_HORA = 5.0;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 
	public Transporte(double horas) throws IllegalArgumentException {	// WMC + 1
		if (horas <= 0) {												// WMC + 1	// CCog + 1
			throw new IllegalArgumentException();
		}
		this.horas = horas;
	}

	public double obtenerExtra () {										// WMC + 1
		return getHoras() * EXTRA_BASICO_HORA;	
	}
	
	public double getHoras() {											// WMC + 1
		return horas;
	}

	public int getToneladas() {											// WMC + 1
        return 0;
    }

	public int getPersonas() {											// WMC + 1
		return 0;
	}

	
	
}
