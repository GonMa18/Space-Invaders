package model;

import java.awt.Color;

public class Rojo extends Jugador {

	// Hace @Override del metodo iniciarCuerpo() para definir la forma de la nave

	public Rojo(int x, int y) {
		super(x, y);
		this.flechas = 30;
		this.rombos = 20;

	}

	@Override
	public void iniciarCuerpo(int x, int y) { // REVISAR FORMA

		coordenadas.add(new Coordenada(x , y, Color.WHITE));
		coordenadas.add(new Coordenada(x, y + 1, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y + 1, Color.WHITE));
	}

	

}
