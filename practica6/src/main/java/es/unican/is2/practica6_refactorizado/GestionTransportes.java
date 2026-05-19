package es.unican.is2.practica6_refactorizado;
import java.util.ArrayList;
import java.util.List;

public class GestionTransportes { // WMC = 6  WMCn = 2 CCog = 4 CCogn = 4/3

	private ArrayList<Conductor> conductores = new ArrayList<Conductor>();
	
	public Conductor buscaConductor(String DNI) {		// WMC + 1
		for(Conductor c: conductores)		// WMC + 1	// CCog + 1
			if (c.getDni().equals(DNI))		// WMC + 1	// CCog + 2
				return c;
		
		return null;
	}
	
	public boolean anhadeConductor(Conductor conductor) {		// WMC + 1
		if (buscaConductor(conductor.getDni()) != null)			// WMC + 1	// CCog + 1
			return false;
		conductores.add(conductor);
		return true;
	}

	public List<Conductor> getConductores() {					// WMC + 1
		return conductores;
	}
	
}
