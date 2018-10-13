package tda;

public interface ConjuntoTDA {

	public void inicializar();
	
	/**
	 * inicializada
	 * */
	public boolean conjuntoVacio();
	
	/**
	 * inicializada
	 * */
	public void agregar(String valor);

	/**
	 * inicializada y no vacio
	 * */
	public String elegir();
	
	/**
	 * inicializada
	 * */
	public void sacar(String valor);

	/**
	 * inicializada
	 * */
	public boolean pertenece(String valor);
}
