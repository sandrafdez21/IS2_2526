package es.unican.is2.segurosmain;

import es.unican.is2.seguroscommon.*;
import es.unican.is2.segurosbusiness.*;
import es.unican.is2.segurosgui.*;
import es.unican.is2.segurosdaoh2.*;

public class Runner {

	public static void main(String[] args) {
		IClientesDAO daoClientes = new ClientesDAO();
		ISegurosDAO daoSeguros = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
		VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		vista.setVisible(true);
	}

}
