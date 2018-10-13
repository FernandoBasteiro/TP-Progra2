package tda;

public interface ColaPrioridadTDA {
	
	public void inicializar();
	
	/**
	 * inicializada. 
	 * Nota el order de la estructura puede ser cambiado, no asi su comportamiento
	 * */
	public void acolar(String paciente, String turno);
	
	/**
	 * inicializada y no vacia
	 * */
	public void dasacolar();
	
	/**
	 * inicializada y no vacia
	 * */
	public String paciente();
	
	/**
	 * inicializada y no vacia
	 * */
	public String turno();
	
	/**
	 * inicializada
	 * */
	public boolean colaVacia();
	
}
