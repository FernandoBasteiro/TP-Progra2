package tda;

public interface ABBTurnosTDA {

	public void inicializar();
	
	/**
	 * inicializado
	 * */
	public void agregar(String turno, String paciente);
	
	/**
	 * inicializado
	 * */
	public void eliminar(String turno, String paciente);
	
	/**
	 * inicializado y no vacio
	 * */
	public String turno();
	
	/**
	 * inicializado y no vacio
	 * */
	public String paciente();
	
	/**
	 * inicializado y no vacio
	 * */
	public ABBTurnosTDA hijoIzq();
	
	/**
	 * inicializado y no vacio
	 * */
	public ABBTurnosTDA hijoDer();
	
	/**
	 * inicializado
	 * */
	public boolean arbolVacio();
}
