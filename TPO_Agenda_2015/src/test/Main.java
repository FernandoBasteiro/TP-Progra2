package test;

import algoritmos.Algoritmos;

public class Main {

	public static void main(String[] args) {
		Algoritmos programa = new Algoritmos();
		Test mostrar = new Test();
		programa.agregarTurno("Ana", "20150615", "12:30", "Alberto");
		System.out.println("Prueba de agendaConsultorio:");
		mostrar.mostrarMatriz(programa.agendaConsultorio());
		System.out.println("----------------------------");
		programa.agregarTurno("Ana", "20150616", "12:00", "Alberto");
		programa.eliminarTurno("Ana", "20150615", "12:30", "Alberto");

		System.out.println("Prueba de obtenerMedicos y obtenerFechas:");
		String[] medicos = programa.obtenerMedicos();
		for (int i = 0; i < programa.obtenerMedicos().length; i++) {
			System.out.println(medicos[i]);
			mostrar.mostrarArray(programa.obtenerFechas(medicos[i]));
		}
		System.out.println("----------------------------");
		System.out.println("Prueba de fechasOcupadas (Tomas entre 20150611 y 20150621):");
		mostrar.mostrarArray(programa.fechasOcupadas("Tomas", "20150611", "20150621"));
		System.out.println("----------------------------");
		System.out.println("Prueba de turnosDePaciente (Fernando entre 20150601 y 20150615):");
		mostrar.mostrarMatriz(programa.turnosDePaciente("Fernando", "20150601", "20150615"));
		System.out.println("----------------------------");
		System.out.println("Prueba de turnosEnFecha (Victoria en 20150621):");
		mostrar.mostrarMatriz(programa.turnosEnFecha("Victoria", "20150621"));
	}

}
