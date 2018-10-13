package implementaciones;

import tda.ABBTurnosTDA;
import tda.ColaPrioridadTDA;
import tda.ConjuntoTDA;
import tda.ConsultorioTDA;

public class ConsultorioEstatico implements ConsultorioTDA {

	private int cantidad;
	private String[] claves;
	private int[] cant_valores;
	private NodoFecha[][] valores;

	public void inicializar() {
		cantidad = 0;
		claves = new String[100];
		valores = new NodoFecha[100][100];
		cant_valores = new int[100];
	}

	public void agregar(String medico, String fecha, String hora, String paciente) {
		if (hora.compareTo("09:00") >= 0 && hora.compareTo("19:30") <= 0) {
			int i = 0;
			while (i < cantidad && claves[i].compareTo(medico) != 0)
				i++;
			if (i == cantidad) {
				claves[i] = medico;
				cant_valores[i] = 0;
				cantidad++;
			}

			int j = 0;
			while (j < cant_valores[i] && valores[i][j].fecha.compareTo(fecha) != 0)
				j++;
			if (j == cant_valores[i]) {
				valores[i][j] = new NodoFecha();
				valores[i][j].fecha = fecha;
				valores[i][j].turnos = new ArbolTurnos();
				valores[i][j].turnos.inicializar();
				cant_valores[i]++;
			}
			valores[i][j].turnos.agregar(hora, paciente);
		}
	}

	public void eliminarTurno(String medico, String fecha, String turno, String paciente) {
		int i = 0;
		while (claves[i].compareTo(medico) != 0)
			i++;
		int j = 0;
		while (valores[i][j].fecha.compareTo(fecha) != 0)
			j++;
		valores[i][j].turnos.eliminar(turno, paciente);
		if (valores[i][j].turnos.arbolVacio())
			eliminarFecha(medico, fecha);
	}

	public void eliminarFecha(String medico, String fecha) {
		int i = 0;
		while (i < cantidad && claves[i].compareTo(medico) != 0)
			i++;
		if (i < cantidad) {
			int j = 0;
			while (j < cant_valores[i] && valores[i][j].fecha.compareTo(fecha) != 0)
				j++;
			if (j < cant_valores[i]) {
				cant_valores[i]--;
				if (j != cant_valores[i])
					valores[i][j] = valores[i][cant_valores[i]];
				if (cant_valores[i] == 0)
					eliminarMedico(medico);
			}
		}
	}

	public void eliminarMedico(String medico) {
		int i = 0;
		while (i < cantidad && claves[i].compareTo(medico) != 0)
			i++;
		if (i < cantidad) // Corta el while antes de llegar al final, osea,
							// existe el medico.
		{
			cantidad--;
			if (i < cantidad) // Reemplaza el espacio que ocupaba el medico,
								// siempre y cuando, no fuera el ultimo.
			{
				for (int j = 0; j < cant_valores[cantidad]; j++) {
					valores[i][j] = valores[cantidad][j];
				}
				cant_valores[i] = cant_valores[cantidad];
				cant_valores[cantidad] = 0;
			}
		}
	}

	public ConjuntoTDA medicos() {
		ConjuntoTDA aux = new ConjuntoEstatico();
		aux.inicializar();
		for (int i = 0; i < cantidad; i++)
			aux.agregar(claves[i]);
		return aux;
	}

	public ConjuntoTDA fechas(String medico) {
		ConjuntoTDA aux = new ConjuntoEstatico();
		aux.inicializar();
		int i = 0;
		while (i < cantidad && claves[i].compareTo(medico) != 0)
			i++;
		if (i < cantidad) {
			for (int j = 0; j < cant_valores[i]; j++)
				aux.agregar(valores[i][j].fecha);
		}
		return aux;
	}

	public ColaPrioridadTDA turnos(String medico, String fecha) {
		ColaPrioridadTDA aux = new ColaPrioridadEstatica();
		aux.inicializar();
		int i = 0;
		while (i < cantidad && claves[i].compareTo(medico) != 0)
			i++;
		if (i < cantidad) {
			int j = 0;
			while (j < cant_valores[i] && valores[i][j].fecha.compareTo(fecha) != 0)
				j++;
			if (j < cant_valores[i])
				ABBaCola(valores[i][j].turnos, aux);
		}
		return aux;
	}

	private void ABBaCola(ABBTurnosTDA arbol, ColaPrioridadTDA cola) {
		if (!arbol.arbolVacio()) {
			cola.acolar(arbol.paciente(), arbol.turno());
			ABBaCola(arbol.hijoDer(), cola);
			ABBaCola(arbol.hijoIzq(), cola);
		}
	}
}
