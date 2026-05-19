package es.unican.is2.practica6_refactorizado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransporteTest {

    @Test
    public void testConstructor() {

        // Casos validos
        Transporte sut = new Mercancias(1, 1);
        assertEquals(1, sut.getHoras());
        assertEquals(1, sut.getToneladas());
        assertEquals(0, sut.getPersonas());
        
        sut = new MercanciasPeligrosas(10, 1000);
        assertEquals(10, sut.getHoras());
        assertEquals(1000, sut.getToneladas());
        assertEquals(0, sut.getPersonas());

        sut = new Personas(10, 10);
        assertEquals(10, sut.getHoras());
        assertEquals(10, sut.getPersonas());
        assertEquals(0, sut.getToneladas());

        // Casos no validos
        assertThrows(IllegalArgumentException.class, () -> new Mercancias(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Mercancias(10, 0));
    }

}
