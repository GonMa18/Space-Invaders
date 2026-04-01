package model;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Nave {

	// Atributos de cada pixel en el juego (naves enemigas y jugador)
	private boolean sigueJugando;
	protected ArrayList<Coordenada> coordenadas;

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public Nave(int x, int y) {
		this.coordenadas = new ArrayList<>();
		this.iniciarCuerpo(x, y);
		this.sigueJugando = true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public ArrayList<Coordenada> getCoordenadas() {
		return coordenadas;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean continuesPlaying() {
		return sigueJugando;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean sigueVivo() {
		return sigueJugando;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public void setSigueJugando(boolean b) {
		this.sigueJugando = b;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public void mover(int x, int y) { // TODO
		for (Coordenada c : this.coordenadas) {
			c.setX(c.getX() + x);
			c.setY(c.getY() + y);
			if (c.getX() < 0) {
				c.setX(0);
			}
			if (c.getX() > Espacio.getAnchura() - 1) {
				c.setX(Espacio.getAnchura() - 1);
			}
			if (c.getY() < 0) {
				c.setY(0);
			}
			if (c.getY() > Espacio.getAltura() - 1) {
				c.setY(Espacio.getAltura() - 1);
			}
		}
	}

	protected abstract void iniciarCuerpo(int x, int y);

	public void pintarCuerpo(Graphics g, int tamCelda) {
		for (Coordenada c : coordenadas) {
			c.pintar(g, tamCelda);
		}
	}
}