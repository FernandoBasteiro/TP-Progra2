package implementaciones;

import tda.ColaPrioridadTDA;

public class ColaPrioridadEstatica implements ColaPrioridadTDA {

	private int cantidadPacientes; // Cantidad de Valores
	private String[] aTurno; // Prioridad
	private String[] aPaciente; // Valores

	public void inicializar() {
		cantidadPacientes = 0;
		aTurno = new String[100];
		aPaciente = new String[100];
	}

	public void acolar(String paciente, String turno) {
		for (int i = 0; i < cantidadPacientes; i++) {
			if (turno.compareTo(aTurno[i]) < 0) {
				for (int j = cantidadPacientes; j > i; j--) {
					aPaciente[j] = aPaciente[j - 1];
					aTurno[j] = aTurno[j - 1];
				}
				aPaciente[i] = paciente;
				aTurno[i] = turno;
				cantidadPacientes++;
				return;
			}
		}
		aPaciente[cantidadPacientes] = paciente;
		aTurno[cantidadPacientes] = turno;
		cantidadPacientes++;
	}

	public void dasacolar() {
		for (int i = 0; i < cantidadPacientes - 1; i++) {
			aPaciente[i] = aPaciente[i + 1];
			aTurno[i] = aTurno[i + 1];
		}
		cantidadPacientes--;
	}

	public String paciente() {
		return aPaciente[0];
	}

	public String turno() {
		return aTurno[0];
	}

	public boolean colaVacia() {
		return cantidadPacientes == 0;
	}

}