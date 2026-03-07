package es.unican.is2.segurosbusiness;

import es.unican.is2.seguroscommon.*;

/**
 * De lleva a cabo la gestion de seguros
 */
public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {
    
    // Atributos
    private IClientesDAO clientesDAO;
    private ISegurosDAO  segurosDAO;

    // Metodos
	/**
	 * Metodo constructor de GestionSeguros
	 * @param clientesDAO Acceso a los clientes 
	 * @param segurosDAO Acceso a los seguros
	 */
	public GestionSeguros (IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
		this.clientesDAO = clientesDAO;
		this.segurosDAO = segurosDAO;
	}

    /**
	 * Persiste un nuevo cliente
	 * @param c Cliente que desea persistir
	 * @return El cliente persitido
	 * 		   null si no se persiste porque ya existe
	  * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
		c = clientesDAO.creaCliente(c);
		return c;
    }
	
	/**
	 * Elimina el cliente cuyo dni se indica
	 * @param dni DNI del cliente que se quiere eliminar
	 * @return El cliente eliminado
	 * 		   null si no se elimina porque no se encuentra 
	 * @throws OperacionNoValida si el cliente existe 
	 *         pero tiene seguros a su nombre
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Cliente bajaCliente(String dni) throws OperacionNoValida,DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
		if (c == null) {
			return null;
		}
		if (!c.getSeguros().isEmpty()) {
			throw new OperacionNoValida("El cliente no se puede eliminar porque tiene seguros a su nombre");
		}
		return clientesDAO.eliminaCliente(dni);
    }
		
    /**
	 * Agrega un nuevo seguro al cliente cuyo dni se indica.
	 * @param s Seguro que desea agregar
	 * @param dni DNI del cliente
	 * @return El seguro agregado
	 * 		   null si no se agrega porque no se encuentra el cliente
	 * @throws OperacionNoValida si el seguro ya existe
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
		if (c == null) {
			return null;
		}

		if (segurosDAO.seguroPorMatricula(s.getMatricula()) != null) {
			throw new OperacionNoValida("El seguro ya existe");
		}

		c.anhadirSeguro(s);

		clientesDAO.actualizaCliente(c);
		return segurosDAO.creaSeguro(s);
    }
	
	/**
	 * Elimina el seguro cuya matricula se indica y 
	 * que pertenece al cliente cuyo dni se indica
	 * @param matricula Identificador del seguro a eliminar
	 * @param dni DNI del propietario del seguro
 	 * @return El seguro eliminado
 	 *         null si el seguro o el cliente no existen
 	 * @throws OperacionNoValida si el seguro no pertenece al dni indicado
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
		Seguro s = segurosDAO.seguroPorMatricula(matricula);
		
		if (c == null || s == null) {
			return null;
		}

		Boolean lePertenece = false;
		for (Seguro s1: c.getSeguros()) {
			if (s1.equals(s)) {
				lePertenece = true;
				break;
			}
		}
		if (!lePertenece) {
			throw new OperacionNoValida("El seguro no le pertenece a esa persona");
		}

		c.quitarSeguro(s);

		clientesDAO.actualizaCliente(c);

		return segurosDAO.eliminaSeguro(s.getId());
    }

	/**
	 * Agrega o modifica el conductor adicional al seguro cuya matricula se indica
	 * @param matricula Identificador del seguro
	 * @param conductor Nombre del conductor adicional a agregar
 	 * @return El seguro modificado
 	 *         null si el seguro no existe
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        Seguro s = segurosDAO.seguroPorMatricula(matricula);
		if (s == null) {
			return null;
		}

		s.setConductorAdicional(conductor);

		return segurosDAO.actualizaSeguro(s);
    }

    /**
	 * Retorna el cliente cuyo dni se indica
	 * @param dni DNI del cliente buscado
	 * @return El cliente cuyo dni coincide
	 * 		   null en caso de que no exista
	 * @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Cliente cliente(String dni) throws DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
		
		if (c == null) {
			return null;
		}
		
		return c;
    }
	
	/**
	 * Retorna el seguro cuya matricula se indica
	 * @param matricula Identificador del seguro
	 * @return El seguro indicado
	 * 	       null si no existe
	* @throws DataAccessException si se produce un error 
	 * en el acceso a la base de datos
	 */
	public Seguro seguro(String matricula) throws DataAccessException {
		Seguro s = segurosDAO.seguroPorMatricula(matricula);
		
		if (s == null) {
			return null;
		}

		return s;
    }
}
