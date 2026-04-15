package model;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Nave {

	// Atributos de cada pixel en el juego (naves enemigas y jugador)
	private boolean sigueJugando;
	protected Composite cuerpo;

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public Nave(int x, int y) {
		this.sigueJugando = true;
		this.cuerpo = new Composite(x, y);
		this.iniciarCuerpo(x, y, Color.WHITE, Color.WHITE);	// Por defecto, el cuerpo se inicia con rojo y azul
	} 

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public ArrayList<Component> getCoordenadas() {
		return cuerpo.getPixeles();
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

	public void mover(int dx, int dy) {
		cuerpo.moverNave(dx, dy);
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

	public Color getColor(int x, int y) {
		return cuerpo.getColor(x, y);
	}

	protected void iniciarCuerpo(int x, int y, Color primario, Color secundario) {
		this.cuerpo.addPixel(new Coordenada(x , y, primario));
		this.cuerpo.addPixel(new Coordenada(x, y + 1, primario));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 1, primario));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 1, primario));
	}
}