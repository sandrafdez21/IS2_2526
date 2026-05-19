package es.unican.is2.practica6_refactorizado;

public class Personas extends Transporte {   // WMC = 5 WMCn = 5/3 CCog = 2  CCogn = 2/3

    private int personas;

    private static final double EXTRA_HORA_NO_COLECTIVO = 0.5;
    private static final double EXTRA_HORA_COLECTIVO = 1.0;
    private static final int PERSONAS_NO_COLECTIVO = 10;

    public Personas(double horas, int valor) throws IllegalArgumentException {  // WMC + 1
        super(horas);
        if (valor <= 0) {                                                       // WMC + 1  // CCog + 1
			throw new IllegalArgumentException();
		}
        this.personas = valor;
        
    }

    @Override
    public double obtenerExtra () {                                             // WMC + 1
        if (personas < PERSONAS_NO_COLECTIVO)                                   // WMC + 1  // CCog + 1
			return super.obtenerExtra() + getHoras() * EXTRA_HORA_NO_COLECTIVO;
		else
			return super.obtenerExtra() + getHoras() * EXTRA_HORA_COLECTIVO;
    }

    @Override
    public int getPersonas() {                                                  // WMC + 1
        return personas;
    }

    
}
