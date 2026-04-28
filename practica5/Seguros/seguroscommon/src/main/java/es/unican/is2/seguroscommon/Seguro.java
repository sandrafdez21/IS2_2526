package es.unican.is2.seguroscommon;


import java.time.LocalDate;

/**
 * Clase que representa un seguro de coche.
 */
public class Seguro {
	
	private long id;

    private String matricula;

	private int potencia;

    private Cobertura cobertura;
    
    private LocalDate fechaInicio;

	private String conductorAdicional;

	public class PotenciaNoValida extends RuntimeException {

		public PotenciaNoValida() {
			super("La potencia del vehículo no puede ser menor o igual a cero.");
		}
		
	}

	/**
	 * Retorna el identificador del seguro
	 */
	public long getId() {
		return id;
	}

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retorna la matricula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 *  Asigna el valor de la matrícula del seguro
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * Retorna la fecha de contratacion del seguro
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicio del seguro
	 * @param fechaInicio La fecha de inicio del seguro
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Asigna el tipo de cobertura del seguro
	 * @param cobertura El tipo de cobertura del seguro
	 */	
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;		
}

	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	/**
	 * Retorna el conductor adicional del seguro, si lo hay
	 * @return El conductor adicional si lo hay
	 * 		null en caso contrario
	 */
	public String getConductorAdicional() {
		return conductorAdicional;
	}

	/**
	 * Asigna el conductor adicional del seguro
	 * @param conductorAdicional
	 */
	public void setConductorAdicional(String conductorAdicional) {
		this.conductorAdicional = conductorAdicional;
	}
    
    /**
     * Retorna el precio del seguro. 
	 * El precio se calcula a partir de la cobertura, la potencia del coche y el tiempo que lleva contratado el seguro
	 * @return El precio del seguro
	 *         0 si el seguro todavía no está en vigor (no se ha alcanzado su fecha de inicio)
     */
	public double precio() {
		double precio;
		LocalDate hoy = LocalDate.now();

		if (fechaInicio == null || cobertura == null) {
			throw new NullPointerException();
		}

		if (potencia <= 0) {
			throw new PotenciaNoValida();
		}

		// Devuelve 0 si todavia no ha entrado en vigor
		if (hoy.isBefore(fechaInicio)) {
			return 0.0;
		}

		// Aplica cobertura
		if (cobertura == Cobertura.TODO_RIESGO) {
			precio = 1000.0;
		} else if (cobertura == Cobertura.TERCEROS_LUNAS) {
			precio = 600.0;
		} else {
			precio = 400.0;
		}

		// Subida en funcion de potencia
		if (potencia > 110) {
			precio = precio * 1.20;
		} else if (potencia >= 90) {
			precio = precio * 1.05;
		}

		// Se descuenta oferta por el primer año
		LocalDate finAno = fechaInicio.plusYears(1).plusDays(1);
		if (hoy.isBefore(finAno)) {
			precio = precio * 0.80;
		}

		return precio;
	}
	
}
