package es.unican.is2.practica6_refactorizado;

public class Mercancias extends Transporte { // WMC = 4 WMCn = 4/3  CCog = 1 CCogn = 1/3

    private int toneladas;

    private static final double EXTRA_POR_TONELADA = 2.0;


    public Mercancias(double horas, int valor) throws IllegalArgumentException {    // WMC + 1
        super(horas);
        if (valor <= 0) {                                                           // WMC + 1  // CCog + 1
			throw new IllegalArgumentException();
		}
        this.toneladas = valor;
    }

    @Override
    public double obtenerExtra () {                                                 // WMC + 1
        return super.obtenerExtra() + toneladas * EXTRA_POR_TONELADA;
    }

    @Override
    public int getToneladas() {                                                     // WMC + 1
        return toneladas;
    }
    
}
