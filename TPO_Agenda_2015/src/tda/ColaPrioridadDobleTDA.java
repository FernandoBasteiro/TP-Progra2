package tda;

public interface ColaPrioridadDobleTDA {
	public void inicializar();
	
	/**
	 * inicializada. 
	 * Nota el order de la estructura puede ser cambiado, no asi su comportamiento
	 * */
	public void acolar(String fecha, String turno, String medico);
	
	/**
	 * inicializada y no vacia
	 * */
	public void desacolar();
	
	/**
	 * inicializada y no vacia
	 * */
	public String fecha();
	
	/**
	 * inicializada y no vacia
	 * */
	public String turno();
	
	/**
	 * inicializada y no vacia
	 * */
	public String medico();

	/**
	 * inicializada
	 * */
	public boolean colaVacia();
}
