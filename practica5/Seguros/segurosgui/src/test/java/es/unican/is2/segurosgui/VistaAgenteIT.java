package es.unican.is2.segurosgui;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

import es.unican.is2.seguroscommon.*;
import es.unican.is2.segurosdaoh2.*;    
import es.unican.is2.segurosbusiness.*;

public class VistaAgenteIT {
    private FrameFixture demo;

	@BeforeEach
	public void setUp() {
        IClientesDAO clientesDAO = new ClientesDAO(); 
        ISegurosDAO segurosDAO = new SegurosDAO();

        GestionSeguros negocio = new GestionSeguros(clientesDAO, segurosDAO);

		VistaAgente gui = new VistaAgente(negocio, negocio, negocio);
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}
	
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}

    @Test
    public void testAspectoInicial() {
        // Comprobamos que la interfaz tiene el aspecto deseado
        demo.button("btnBuscar").requireText("Buscar");
    }

    @Test
    public void testClienteVariosSeguros() {
        // 1º Prueba: Cliente valido con mas de un seguro contratado
        demo.textBox("txtDniCliente").enterText("11111111A");
        demo.button("btnBuscar").click();
        // Comprobamos que el nombre, el total a pagar y el nº de seguros contratado son correctos
        demo.textBox("txtNombreCliente").requireText("Juan");
        demo.textBox("txtTotalCliente").requireText("1820.0");
        demo.list("listSeguros").requireItemCount(3);
    }

    @Test 
    public void testClienteUnSeguro() {
        // 2º Prueba: Cliente valido con un seguro contratado
        demo.textBox("txtDniCliente").enterText("22222222A");
        demo.button("btnBuscar").click();
        // Comprobamos que el nombre, el total a pagar y el nº de seguros contratado son correctos
        demo.textBox("txtNombreCliente").requireText("Ana");
        demo.textBox("txtTotalCliente").requireText("600.0");
        demo.list("listSeguros").requireItemCount(1);
    }

    @Test
    public void testClienteSinSeguros() {
        // 3º Prueba: Cliente valido sin ningun seguro contratado
        demo.textBox("txtDniCliente").enterText("33333333A");
        demo.button("btnBuscar").click();
        // Comprobamos que el nombre, el total a pagar y el nº de seguros contratado son correctos
        demo.textBox("txtNombreCliente").requireText("Luis");
        demo.textBox("txtTotalCliente").requireText("0.0");
        demo.list("listSeguros").requireItemCount(0);
    }
        
    @Test 
    public void testDniNoValido() {
        // 4º Prueba: DNI no se encuentra en la base de datos
        demo.textBox("txtDniCliente").enterText("11111111B");
        demo.button("btnBuscar").click();
        // Comprobamos que los valores mostrados son los esperados
        demo.textBox("txtNombreCliente").requireText("Error en BBDD");
        demo.textBox("txtTotalCliente").requireText("");
        demo.list("listSeguros").requireItemCount(0);
    }

    @Test 
    public void testDniVacio() {
        // 5º Prueba: No se introduce ningun String para buscar
        demo.textBox("txtDNICliente").enterText("");
        demo.button("btnBuscar").click();
        // Comprobamos que los valores mostrados son los esperados
        demo.textBox("txtNombreCliente").requireText("Error en BBDD"); 
        demo.textBox("txtTotalCliente").requireText("");
        demo.list("listSeguros").requireItemCount(0);
    }

        


}
