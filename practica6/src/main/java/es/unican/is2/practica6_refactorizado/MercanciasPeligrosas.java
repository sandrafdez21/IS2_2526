package es.unican.is2.practica6_refactorizado;


public class MercanciasPeligrosas extends Mercancias { // WMC = 2 WMCn = 1  CCog = 0 CCogn = 0

    private static final double EXTRA_POR_MERCANCIA_PELIGROSA = 50.0;

    public MercanciasPeligrosas(double horas, int valor) throws IllegalArgumentException { // WMC + 1
        super(horas, valor);
    }

    @Override
    public double obtenerExtra () {
        return super.obtenerExtra() + EXTRA_POR_MERCANCIA_PELIGROSA; // WMC + 1
    }
}
