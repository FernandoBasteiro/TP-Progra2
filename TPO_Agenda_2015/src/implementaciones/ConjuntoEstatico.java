package implementaciones;

import java.util.Random;

import tda.ConjuntoTDA;

public class ConjuntoEstatico implements ConjuntoTDA {

	private int cantidad;
	private String[] valores;
	
	public void inicializar() {
		cantidad = 0;
		valores = new String[100];

	}

	public boolean conjuntoVacio() {
		return (cantidad == 0);
	}

	public void agregar(String valor) {
		valores[cantidad] = valor;
		cantidad++;
	}

	public String elegir() {
		Random r = new Random();
		return valores[r.nextInt(cantidad)];
	}

	public void sacar(String valor) {
		boolean encontrado = false;
		for (int i = 0; i < cantidad; i++) {
			if (encontrado) {
				valores[i-1] = valores[i];
			}
			else {
				if (valores[i].compareTo(valor) == 0) {
					encontrado = true;
				}
			}
		}
		if (encontrado) {
			cantidad--;
		}
	}

	public boolean pertenece(String valor) {
		int i = 0;
		boolean encontrado = false;
		while (i < cantidad & ! encontrado) {
			encontrado = (valores[i].compareTo(valor) == 0);
			i++;
		}
		return encontrado;
	}

}
