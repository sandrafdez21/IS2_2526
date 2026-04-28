package es.unican.is2.seguroscommon;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.seguroscommon.Seguro.PotenciaNoValida;

public class SeguroTest {

    private Seguro seguro;
    private LocalDate hoy;

    @BeforeEach
    public void setUp() {
        seguro = new Seguro();
        hoy = LocalDate.now();
    }

    @Test
    public void testPrecio() {
        
        // Caso de prueba válidos, los indicados en teoría
        seguro.setFechaInicio(hoy.minusYears(2));
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(1);
        assertEquals(1000.0, seguro.precio());

        seguro.setFechaInicio(hoy.minusYears(1));
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(45);
        assertEquals(480.0, seguro.precio());

        seguro.setFechaInicio(hoy.plusDays(1));
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(89);
        assertEquals(0.0, seguro.precio());

        seguro.setFechaInicio(hoy.minusYears(1).minusDays(1));
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(90);
        assertEquals(1050.0, seguro.precio());

        seguro.setFechaInicio(hoy.minusMonths(1));
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(100);
        assertEquals(504.0, seguro.precio());

        seguro.setFechaInicio(hoy.plusYears(1));
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(110);
        assertEquals(0.0, seguro.precio());

        seguro.setFechaInicio(hoy);
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(111);
        assertEquals(960.0, seguro.precio());

        seguro.setFechaInicio(hoy);
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(200);
        assertEquals(576.0, seguro.precio());

        // Casos de Prueba No Valido
        seguro.setFechaInicio(null);
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(1);
        assertThrows(NullPointerException.class, () -> seguro.precio());

        seguro.setFechaInicio(hoy);
        seguro.setCobertura(null);
        seguro.setPotencia(1);
        assertThrows(NullPointerException.class, () -> seguro.precio());

        seguro.setFechaInicio(hoy);
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(-100);
        assertThrows(PotenciaNoValida.class, () -> seguro.precio());

        seguro.setFechaInicio(hoy);
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(0);
        assertThrows(PotenciaNoValida.class, () -> seguro.precio());
    }
}