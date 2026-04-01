package model;

import java.awt.Color;

public class Enemigo extends Nave {

	public Enemigo(int x, int y) {
		super(x, y);
		// Llama a la constructora de Nave (aita) para inicializar x, y y velocidad
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public void bajar() { // Va bajando un pixel cada 200ms
		this.mover(0,1);
	}

	public void bajar_zigzag() { // TODO
		Boolean zigzag = false;
		for (Coordenada c : this.getCoordenadas()) {
			if ((c.getX() >= Espacio.getAnchura() - 1)||(c.getX() <= 1)) {
				c.setY(c.getX() + 1);
				zigzag = !zigzag; // Cambia la dirección del zigzag
			}
			else{
				if (zigzag) {
					c.setX(c.getX() + 1); // Mueve un pixel a la derecha
				} else {
				c.setX(c.getX() - 1); // Mueve un pixel a la izquierda
				}
			}
			
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	protected void iniciarCuerpo(int x, int y) {
		coordenadas.add(new Coordenada(x, y, Color.WHITE));
		coordenadas.add(new Coordenada(x, y + 1, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y + 1, Color.WHITE));
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean meHanDisparado(int x, int y) {
		for (Coordenada coord : coordenadas) {
			if (coord.getX() == x && coord.getY() == y) {
				return true; // Si el disparo coincide con alguna coordenada de la nave, devuelve true
			}
		}
		return false; // Si el disparo no coincide con ninguna coordenada de la nave, devuelve false
	}

}
