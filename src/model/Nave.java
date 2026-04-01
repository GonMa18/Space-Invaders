package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Nave {

	// Atributos de cada pixel en el juego (naves enemigas y jugador)
	private boolean sigueJugando;
	private Composite cuerpo;

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public Nave(int x, int y) {
		this.sigueJugando = true;
		this.cuerpo = new Composite(x, y);
		cuerpo.addPixel(new Coordenada(x , y, Color.WHITE));
		cuerpo.addPixel(new Coordenada(x, y + 1, Color.WHITE));
		cuerpo.addPixel(new Coordenada(x - 1, y + 1, Color.WHITE));
		cuerpo.addPixel(new Coordenada(x + 1, y + 1, Color.WHITE));

	} 

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public ArrayList<Coordenada> getCoordenadas() {
		return cuerpo.getPixeles();
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

	public void pintarCuerpo(Graphics g, int tamCelda) {
		for (Coordenada c : cuerpo.getPixeles()) {
			c.pintar(g, tamCelda);
		}
	}
	
	public void mover(int dx, int dy) {
		cuerpo.mover(dx, dy);
	}

	public boolean containPixel(int x, int y) {
		return cuerpo.containPixel(x, y);
	}

	public int getX() {
		return cuerpo.getX();
	}
	public int getY() {
		return cuerpo.getY();
	}
}