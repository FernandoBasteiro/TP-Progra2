package algoritmos;

import implementaciones.ColaPrioridadDobleDinamica;
import implementaciones.ColaPrioridadEstatica;
import implementaciones.ConsultorioEstatico;
import tda.ColaPrioridadDobleTDA;
import tda.ColaPrioridadTDA;
import tda.ConjuntoTDA;
import tda.ConsultorioTDA;

public class Algoritmos {

	/* No modificar las siguientes lineas sin una indicación expresa por parte de la cátedra*/
	private ConsultorioTDA consultorio;
	public Algoritmos()
	{
		consultorio = new ConsultorioEstatico();
		cargoDatos();
	}
	/* Fin de la no modificacion de lineas sin una indicación expresa por parte de la cátedra*/
	
	
	/**	
	 * Agregarle un turno a un médico determinado en una fecha determinada en un horario determinado 
	 * para un paciente determinado.
	 * 
	 */
	public void agregarTurno(String medico, String fecha, String hora, String paciente)
	{
		ColaPrioridadTDA cola;
		boolean duplicado = false;
		
		if (consultorio.medicos().pertenece(medico)) {
			if (consultorio.fechas(medico).pertenece(fecha)) {
				cola = consultorio.turnos(medico, fecha);
				while (! cola.colaVacia() && cola.turno().compareTo(hora) < 0)
					cola.dasacolar();
				if (cola.turno().compareTo(hora) == 0)
					duplicado = true;
			}
		}
		if (! duplicado)
			consultorio.agregar(medico, fecha, hora, paciente);
	}
	
	/**
	 * 	Eliminar un turno determinado de un paciente determinado a un médico determinado 
	 *  en una fecha determinada.
	 *  
	 */
	public void eliminarTurno(String medico, String fecha, String hora, String paciente)
	{
		ColaPrioridadTDA cola;
		if (consultorio.medicos().pertenece(medico)) {
			if (consultorio.fechas(medico).pertenece(fecha)) {
				cola = consultorio.turnos(medico, fecha);
				while (! cola.colaVacia() && cola.turno().compareTo(hora) < 0)
					cola.dasacolar();
				if (cola.turno().compareTo(hora) == 0 && cola.paciente().compareTo(paciente) == 0)
					consultorio.eliminarTurno(medico, fecha, hora, paciente);
			}
		}		
	}
	
	/**
	 * 	Eliminar una fecha determinada a un medico determinado. 
	 *  
	 */
	public void eliminarFechaMedico(String medico, String fecha)
	{
		consultorio.eliminarFecha(medico,  fecha);
	}
	
	/**
	 * Obtener los turnos de un médico en una fecha determinada pasada como parámetro, 
	 * los turnos deben estar ordenados de menor a mayor.
	 * 
	 */
	
	
	public String[][] turnosEnFecha(String medico, String fecha)
	{
		String[][] aux;
		ColaPrioridadTDA cola = consultorio.turnos(medico, fecha);

		int cantidad = 0;
		while (!cola.colaVacia()) {
			cola.dasacolar();
			cantidad++;
		}
		aux = new String[cantidad][2];
		cola = consultorio.turnos(medico, fecha);
		int i = 0;
		while (!cola.colaVacia()) {
			aux[i][0] = cola.turno();
			aux[i][1] = cola.paciente();
			cola.dasacolar();
			i++;
		}
		return aux;
	}
	
	/**
	 * Obtener las fechas en las que un médico determinado posee turnos en un rango de  
	 * fechas pasadas como parámetro, los fechas deben estar ordenados de menor a mayor.
	 * 
	 */

	public String[] fechasOcupadas(String medico, String fechaDesde, String fechaHasta)
	{
		String[] fechas;
		ColaPrioridadTDA colafechas = new ColaPrioridadEstatica();
		ConjuntoTDA conjuntofechas = consultorio.fechas(medico);
		colafechas.inicializar();
		String fecha;
		int count = 0;
		while (! conjuntofechas.conjuntoVacio()) {
			fecha = conjuntofechas.elegir();
			if (fecha.compareTo(fechaDesde) >= 0 && fecha.compareTo(fechaHasta) <= 0) {
				colafechas.acolar(fecha, fecha);
				count++;
			}
			conjuntofechas.sacar(fecha);
		}
		fechas = new String[count];
		for (int i = 0; i < count; i++) {
			fechas[i] = colafechas.turno();
			colafechas.dasacolar();
		}
		return fechas;
	}
	
	/**
	 * Obtener la lista de turnos que posee un paciente entre dos fechas pasadas como parámetro, devuelve 
	 * la fecha, el horario y el médico con el cual el paciente tiene turno. El resultado debe estar ordenados 
	 * por fecha y dentro de la fecha por horario de menor a mayor.
	 * 
	 */

	public String[][] turnosDePaciente(String paciente, String fechaDesde,
			String fechaHasta) {
		ColaPrioridadDobleTDA cola = new ColaPrioridadDobleDinamica();
		cola.inicializar();
		String medico, fecha;
		ConjuntoTDA cMedicos = consultorio.medicos();
		ConjuntoTDA cFechas;
		int cantidad = 0;
		ColaPrioridadTDA colaTurnos;
		while (!cMedicos.conjuntoVacio()) {
			medico = cMedicos.elegir();
			cFechas = consultorio.fechas(medico);
			while (!cFechas.conjuntoVacio()) {
				fecha = cFechas.elegir();
				if (fecha.compareTo(fechaDesde) >= 0 && fecha.compareTo(fechaHasta) <= 0) {
					colaTurnos = consultorio.turnos(medico, fecha);
					while (!colaTurnos.colaVacia()) {
						if (colaTurnos.paciente().compareTo(paciente) == 0) {
							cola.acolar(fecha, colaTurnos.turno(), medico);
							cantidad++;
						}
						colaTurnos.dasacolar();
					}
				}
				cFechas.sacar(fecha);
			}
			cMedicos.sacar(medico);
		}
		String[][] turnosPaciente = new String[cantidad][3];
		int i = 0;
		while (!cola.colaVacia()) {
			turnosPaciente[i][1] = cola.fecha();
			turnosPaciente[i][2] = cola.turno();
			turnosPaciente[i][0] = cola.medico();
			cola.desacolar();
			i++;
		}
		return turnosPaciente;
	}
    
	/**
	 * Obtener la agenda del consultorio ordenada por médico, fecha, turno y paciente
	 * */
	public String[][] agendaConsultorio()
	{
		int lineas = 0;
		String[] medicos = obtenerMedicos();
		String[] fechas;
		ColaPrioridadTDA turnos;
		for (int i = 0; i < medicos.length; i++) {
			fechas = obtenerFechas(medicos[i]);
			for (int j = 0; j < fechas.length; j++) {
				turnos = consultorio.turnos(medicos[i], fechas[j]);
				while (! turnos.colaVacia()) {
					lineas++;
					turnos.dasacolar();
				}
			}
		}
		String[][] agenda = new String[lineas][4];
		lineas = 0;
		for (int i = 0; i < medicos.length; i++) {
			fechas = obtenerFechas(medicos[i]);
			for (int j = 0; j < fechas.length; j++) {
				turnos = consultorio.turnos(medicos[i], fechas[j]);
				while (! turnos.colaVacia()) {
					agenda[lineas][0] = medicos[i];
					agenda[lineas][1] = fechas[j];
					agenda[lineas][2] = turnos.turno();
					agenda[lineas][3] = turnos.paciente();
					turnos.dasacolar();
					lineas++;
				}
			}
		}
		return agenda;
	}

	/**
	 * Obtiene los médicos ordenados alfabeticamente;
	 * 
	 */
	public String[] obtenerMedicos() {
		ColaPrioridadTDA cpeMedicos = new ColaPrioridadEstatica();
		ConjuntoTDA cMedicos = consultorio.medicos();
		int count = 0;
		String temp;
		cpeMedicos.inicializar();
		while (!cMedicos.conjuntoVacio()) {
			temp = cMedicos.elegir();
			cpeMedicos.acolar(temp, temp);
			cMedicos.sacar(temp);
			count++;
		}
		String devMedicos[] = new String[count];
		cMedicos = consultorio.medicos();
		for (int i = 0; i < count; i++) {
			devMedicos[i] = cpeMedicos.turno();
			cpeMedicos.dasacolar();
		}
		return devMedicos;
	}

	/**
	 * Obtiene las fechas de un medico pasado como parámetro ordenadas
	 * cronologicamente;
	 * 
	 */
	public String[] obtenerFechas(String medico) {
		ColaPrioridadEstatica cpeFechas = new ColaPrioridadEstatica();
		ConjuntoTDA cFechas = consultorio.fechas(medico);
		int count = 0;
		String temp;
		cpeFechas.inicializar();
		while (!cFechas.conjuntoVacio()) {
			temp = cFechas.elegir();
			cpeFechas.acolar(temp, temp);
			cFechas.sacar(temp);
			count++;
		}
		String devFechas[] = new String[count];
		for (int i = 0; i < count; i++) {
			devFechas[i] = cpeFechas.turno();
			cpeFechas.dasacolar();
		}
		return devFechas;
	}
	
	private void cargoDatos()
	{
		consultorio.inicializar();
		agregarTurno("Teresa","20150602","11:00","Andrea");
		agregarTurno("Tomas","20150602","17:30","Analia");
		agregarTurno("Victor","20150602","18:00","Juan");
		agregarTurno("Santiago","20150603","09:00","Julian");
		agregarTurno("Victor","20150603","13:30","Pedro");
		agregarTurno("Santiago","20150603","14:30","Lionel");
		agregarTurno("Victoria","20150604","18:00","Juan");
		agregarTurno("Victoria","20150605","10:00","Julian");
		agregarTurno("Victoria","20150605","10:30","Julian");
		agregarTurno("Victoria","20150605","16:00","Brian");
		agregarTurno("Teresa","20150605","16:00","Fernando");
		agregarTurno("Tomas","20150606","17:30","Julian");
		agregarTurno("Victoria","20150607","10:00","Lionel");
		agregarTurno("Victoria","20150607","14:00","Jesica");
		agregarTurno("Teresa","20150607","14:00","Fernando");
		agregarTurno("Victor","20150607","18:00","Lionel");
		agregarTurno("Victor","20150607","19:00","Fernando");
		agregarTurno("Tomas","20150608","12:00","Maria");
		agregarTurno("Victor","20150608","12:00","Andrea");
		agregarTurno("Victor","20150608","13:30","Federico");
		agregarTurno("Santiago","20150609","10:00","Julian");
		agregarTurno("Victoria","20150609","13:30","Lionel");
		agregarTurno("Victoria","20150609","17:00","Claudia");
		agregarTurno("Teresa","20150610","16:30","Andrea");
		agregarTurno("Teresa","20150610","18:00","Lionel");
		agregarTurno("Victor","20150611","09:00","Juan");
		agregarTurno("Teresa","20150611","13:00","Carlos");
		agregarTurno("Tomas","20150611","14:00","Claudia");
		agregarTurno("Tomas","20150611","16:30","Brian");
		agregarTurno("Victor","20150612","14:30","Maria");
		agregarTurno("Victoria","20150612","14:30","Laura");
		agregarTurno("Teresa","20150613","16:00","Fernando");
		agregarTurno("Teresa","20150613","16:00","Julian");
		agregarTurno("Victoria","20150615","14:30","Fernando");
		agregarTurno("Victoria","20150616","17:30","Claudia");
		agregarTurno("Santiago","20150617","10:00","Federico");
		agregarTurno("Victoria","20150617","11:30","Pedro");
		agregarTurno("Tomas","20150617","15:00","Lionel");
		agregarTurno("Victor","20150617","16:00","Lionel");
		agregarTurno("Santiago","20150619","13:00","Andrea");
		agregarTurno("Victor","20150619","13:30","Analia");
		agregarTurno("Tomas","20150619","17:30","Raul");
		agregarTurno("Teresa","20150620","09:00","Raul");
		agregarTurno("Victoria","20150620","10:00","Carlos");
		agregarTurno("Teresa","20150620","11:00","Carlos");
		agregarTurno("Santiago","20150620","12:30","Claudia");
		agregarTurno("Tomas","20150620","16:30","Maria");
		agregarTurno("Tomas","20150620","19:00","Carlos");
		agregarTurno("Victoria","20150621","09:00","Raul");
		agregarTurno("Victoria","20150621","09:30","Julian");
		agregarTurno("Teresa","20150621","14:30","Carlos");
		agregarTurno("Victor","20150621","15:00","Sandra");
		agregarTurno("Teresa","20150621","16:30","Analia");
		agregarTurno("Teresa","20150621","17:30","Fernando");
		agregarTurno("Tomas","20150622","10:30","Jesica");
		agregarTurno("Santiago","20150622","13:30","Federico");
		agregarTurno("Teresa","20150622","15:30","Federico");
		agregarTurno("Victor","20150622","16:00","Julian");
		agregarTurno("Teresa","20150622","17:00","Andrea");
		agregarTurno("Tomas","20150622","19:30","Maria");
		
	}
}