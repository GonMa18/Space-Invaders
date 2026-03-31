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

	public abstract void mover(int x, int y);

	protected abstract void iniciarCuerpo(int x, int y);

	public void pintarCuerpo(Graphics g, int tamCelda) {
		for (Coordenada c : coordenadas) {
			c.pintar(g, tamCelda);
		}
	}
}