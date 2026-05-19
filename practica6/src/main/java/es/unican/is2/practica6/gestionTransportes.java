package es.unican.is2.practica6;
import java.util.ArrayList;
import java.util.List;

public class gestionTransportes {	// WMC = 6  WMCn = 2   CCog = 4   CCogn = 1.33

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();
	
	public Conductor buscaConductor(String DNI) {	// WMC + 1	
		for(Conductor c: cs) 						// WMC + 1   CCog + 1
			if (c.dni().equals(DNI))				// WMC + 1   CCog + 2
				return c;
		
		return null;
	}
	
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {		// WMC + 1
		if (buscaConductor(dni) != null)																					// WMC + 1  CCog + 1
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}

	public List<Conductor> conductores() {			// WMC + 1
		return cs;
	}
	
}
