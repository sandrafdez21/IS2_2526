package es.unican.is2.seguroscommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.seguroscommon.Cliente.NoHaySeguros;
import es.unican.is2.seguroscommon.Cliente.SeguroNoExistente;
import es.unican.is2.seguroscommon.Cliente.SeguroYaContratado;

import java.time.LocalDate;

public class ClienteTest {

    private Cliente cliente;
    private Seguro seguro1;
    private Seguro seguro2;
    private Seguro seguro3;
    private LocalDate hoy;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        hoy = LocalDate.now();

        seguro1 = new Seguro();
        seguro1.setFechaInicio(hoy.minusYears(2));
        seguro1.setCobertura(Cobertura.TODO_RIESGO);
        seguro1.setPotencia(10);

        seguro2 = new Seguro();
        seguro2.setFechaInicio(hoy);
        seguro2.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro2.setPotencia(200);

        seguro3 = new Seguro();

    }

    @Test
    public void testTotalSegurosValidos() {

        cliente.setMinusvalia(true);
        assertEquals(0.0, cliente.totalSeguros());

        cliente.setMinusvalia(false);
        cliente.anhadirSeguro(seguro1);
        assertEquals(1000.0, cliente.totalSeguros());

        cliente.setMinusvalia(true);
        cliente.anhadirSeguro(seguro2);
        assertEquals(1182.0, cliente.totalSeguros());

        cliente.setSeguros(null);
        cliente.setMinusvalia(true);
        assertThrows(NullPointerException.class, () -> cliente.totalSeguros());
    }

    @Test
    public void testAnhadirSeguro() {
        assertEquals(0, cliente.getSeguros().size());

        cliente.anhadirSeguro(seguro1);
        assertEquals(1, cliente.getSeguros().size());
        assertTrue(cliente.getSeguros().contains(seguro1));

        cliente.anhadirSeguro(seguro2);
        assertEquals(2, cliente.getSeguros().size());
        assertTrue(cliente.getSeguros().contains(seguro1));
        assertTrue(cliente.getSeguros().contains(seguro2));

        cliente.anhadirSeguro(seguro3);
        assertEquals(3, cliente.getSeguros().size());
        assertTrue(cliente.getSeguros().contains(seguro1));
        assertTrue(cliente.getSeguros().contains(seguro2));
        assertTrue(cliente.getSeguros().contains(seguro3));

        // No validos
        assertThrows(NullPointerException.class, () -> cliente.anhadirSeguro(null));

        assertThrows(SeguroYaContratado.class, () -> cliente.anhadirSeguro(seguro1));


        cliente.setSeguros(null); 
        assertThrows(NullPointerException.class, () -> cliente.anhadirSeguro(seguro1));
    }

    @Test
    public void quitarSeguroTest() {
    
        // Casos Validos
        cliente.anhadirSeguro(seguro1);
        cliente.quitarSeguro(seguro1);
        assertEquals(0, cliente.getSeguros().size());

        cliente.anhadirSeguro(seguro1);
        cliente.anhadirSeguro(seguro2);
        cliente.quitarSeguro(seguro1);
        
        assertEquals(1, cliente.getSeguros().size());
        assertTrue(cliente.getSeguros().contains(seguro2));

        // Casos No Validos
        cliente.anhadirSeguro(seguro1);
        assertThrows(NullPointerException.class, () -> cliente.quitarSeguro(null));

        cliente.quitarSeguro(seguro1); 
        cliente.quitarSeguro(seguro2);

        assertThrows(NoHaySeguros.class, () -> cliente.quitarSeguro(seguro1));

        cliente.anhadirSeguro(seguro2);
        assertThrows(SeguroNoExistente.class, () -> cliente.quitarSeguro(seguro1)); // Intentamos borrar el 1

        cliente.setSeguros(null);
        assertThrows(NullPointerException.class, () -> cliente.quitarSeguro(seguro1));
    }


}