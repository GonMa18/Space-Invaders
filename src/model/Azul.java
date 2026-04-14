package model;

import java.awt.Color;

public class Azul extends Jugador {
	

	// Hace @Override del metodo iniciarCuerpo() para definir la forma de la nave

	public Azul(int x, int y) {
		super(x, y);
		this.flechas = 0; 
		this.rombos = 20;
	}

	@Override
	protected void iniciarCuerpo(int x, int y, Color primario, Color secundario) {
		super.iniciarCuerpo(x, y, Color.BLUE, Color.CYAN);
	}
}
