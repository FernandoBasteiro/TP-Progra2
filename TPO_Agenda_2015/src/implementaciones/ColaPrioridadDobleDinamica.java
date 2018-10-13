package implementaciones;

import tda.ColaPrioridadDobleTDA;

public class ColaPrioridadDobleDinamica implements ColaPrioridadDobleTDA {
	NodoColaPrioridadDoble primero;
	public void inicializar() {
		primero = null;
	}

	public void acolar(String fecha, String turno, String medico) {
		NodoColaPrioridadDoble aux;
		NodoColaPrioridadDoble recorre;
		if (primero == null) {
			primero = new NodoColaPrioridadDoble();
			primero.fecha = fecha;
			primero.turno = turno;
			primero.medico = medico;
			primero.siguiente = null;
		}
		else {
			aux = new NodoColaPrioridadDoble();
			aux.fecha = fecha;
			aux.turno = turno;
			aux.medico = medico;
			if (fecha.compareTo(primero.fecha) < 0 || (fecha.compareTo(primero.fecha) == 0 && turno.compareTo(primero.turno) <= 0)) {
				aux.siguiente = primero;
				primero = aux;
			}
			else {
				recorre = primero;
				
				while (recorre.siguiente != null && recorre.siguiente.fecha.compareTo(fecha) < 0)
					recorre = recorre.siguiente;
				if (recorre.siguiente == null || recorre.siguiente.fecha.compareTo(fecha) > 0) {
					aux.siguiente = recorre.siguiente;
					recorre.siguiente = aux;
				}
				else {
					while (recorre.siguiente != null && recorre.siguiente.fecha.compareTo(fecha) == 0 && recorre.siguiente.turno.compareTo(turno) < 0)
						recorre = recorre.siguiente;
					aux.siguiente = recorre.siguiente;
					recorre.siguiente = aux;
				}
			}	
		}
	}

	public void desacolar() {
		primero = primero.siguiente;
	}

	public String fecha() {
		return primero.fecha;
	}

	public String turno() {
		return primero.turno;
	}

	public String medico() {
		return primero.medico;
	}

	public boolean colaVacia() {
		return (primero == null);
	}

}

class NodoColaPrioridadDoble {
	String fecha;
	String turno;
	String medico;
	NodoColaPrioridadDoble siguiente;
}