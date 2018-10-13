package test;

import tda.ABBTurnosTDA;
import tda.ColaPrioridadTDA;
import tda.ConjuntoTDA;

public class Test {
	
	public void mostrarCola (ColaPrioridadTDA cola) {
		while (! cola.colaVacia()) {
			System.out.println("----");
			System.out.println(cola.paciente());
			System.out.println(cola.turno());
			cola.dasacolar();
		}
	}
	
	public void mostrarABB (ABBTurnosTDA arbol) {
		if (! arbol.hijoIzq().arbolVacio())
			mostrarABB(arbol.hijoIzq());
		System.out.println("---");
		System.out.println(arbol.paciente());
		System.out.println(arbol.turno());
		if (! arbol.hijoDer().arbolVacio())
			mostrarABB(arbol.hijoDer());
	}
	
	public void mostrarConjunto (ConjuntoTDA conjunto) {
		String aux;
		while (! conjunto.conjuntoVacio()) {
			aux = conjunto.elegir();
			conjunto.sacar(aux);
			System.out.println(aux);
		}
	}
	
	public void mostrarArray (String[] array) {
		for (int i = 0; i < array.length; i++) 
			System.out.println(array[i]);
	}
	
	public void mostrarMatriz (String[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j]);
				if (j < array[i].length - 1)
					System.out.print(" - ");
			}
			System.out.println("");
		}
	}
}
