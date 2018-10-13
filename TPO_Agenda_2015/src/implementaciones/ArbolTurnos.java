package implementaciones;

import tda.ABBTurnosTDA;

public class ArbolTurnos implements ABBTurnosTDA {
	private NodoABBTurno raiz;
	
	public void inicializar() {
		raiz = null;
	}

	public void agregar(String turno, String paciente) {
		if (raiz == null) {
			raiz = new NodoABBTurno();
			raiz.hora = turno;
			raiz.paciente = paciente;
			raiz.hijoDer = new ArbolTurnos();
			raiz.hijoIzq = new ArbolTurnos();
			raiz.hijoDer.inicializar();
			raiz.hijoIzq.inicializar();
		}
		else {
			if (raiz.hora.compareToIgnoreCase(turno) > 0)
				raiz.hijoIzq.agregar(turno, paciente);
			else {
				if (raiz.hora.compareToIgnoreCase(turno) < 0)
					raiz.hijoDer.agregar(turno, paciente);
			}
		}
	}

	public void eliminar(String turno, String paciente) {
		if (raiz != null) {
			if (raiz.hora.compareToIgnoreCase(turno) == 0 && raiz.hijoDer.arbolVacio() && raiz.hijoIzq.arbolVacio())
				raiz = null;
			else {
				if (raiz.hora.compareToIgnoreCase(turno) == 0 && ! raiz.hijoIzq.arbolVacio()) {
					NodoTurno aux = new NodoTurno();
					aux = mayor(raiz.hijoIzq);
					raiz.hora = aux.hora;
					raiz.paciente = aux.paciente;
					raiz.hijoIzq.eliminar(aux.hora, aux.paciente);
				}
				else {
					if (raiz.hora.compareToIgnoreCase(turno) == 0 && ! raiz.hijoDer.arbolVacio()) {
						NodoTurno aux = new NodoTurno();
						aux = menor(raiz.hijoDer);
						raiz.hora = aux.hora;
						raiz.paciente = aux.paciente;
						raiz.hijoDer.eliminar(aux.hora,  aux.paciente);
					}
					else {
						if (raiz.hora.compareToIgnoreCase(turno) > 0) {
							raiz.hijoIzq.eliminar(turno, paciente);
						}
						else {
							raiz.hijoDer.eliminar(turno, paciente);
						}
					}
				}
			}
		}
	}

	public String turno() {
		return raiz.hora;
	}

	public String paciente() {
		return raiz.paciente;
	}

	public ABBTurnosTDA hijoIzq() {
		return raiz.hijoIzq;
	}

	public ABBTurnosTDA hijoDer() {
		return raiz.hijoDer;
	}

	public boolean arbolVacio() {
		return (raiz == null);
	}
	
	private NodoTurno mayor (ABBTurnosTDA arbol) {
		if (arbol.hijoDer().arbolVacio()) {
			NodoTurno aux = new NodoTurno();
			aux.hora = arbol.turno();
			aux.paciente = arbol.paciente();
			return aux;
		}
		else
			return mayor(arbol.hijoDer());
	}
	
	private NodoTurno menor (ABBTurnosTDA arbol) {
		if (arbol.hijoIzq().arbolVacio()) {
			NodoTurno aux = new NodoTurno();
			aux.hora = arbol.turno();
			aux.paciente = arbol.paciente();
			return aux;
		}
			else
			return menor(arbol.hijoIzq());
	}
}

class NodoTurno {
	String hora;
	String paciente;
}