package model;

import java.awt.Color;

public class Azul extends Nave {
	

	// Hace @Override del metodo iniciarCuerpo() para definir la forma de la nave

	public Azul(int x, int y) {
		super(x, y);
		this.flechas = 0; 
		this.rombos = 20;
	}

	@Override
	protected void iniciarCuerpo(int x, int y) {
		this.cuerpo.addPixel(new Coordenada(x, y - 8, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y - 7, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y - 6, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 1, y - 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y - 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y - 5, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 1, y - 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y - 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y - 4, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 1, y - 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y - 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y - 3, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 4, y - 2, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y - 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y - 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y - 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y - 2, Color.BLUE));

		this.cuerpo.addPixel(new Coordenada(x - 4, y - 1, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x - 2, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y - 1, Color.BLUE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x - 4, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y, Color.CYAN));
		this.cuerpo.addPixel(new Coordenada(x - 2, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 3, y, Color.CYAN));
		this.cuerpo.addPixel(new Coordenada(x + 4, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y, Color.BLUE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 1, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x - 4, y + 1, Color.CYAN));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 1, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x, y + 1, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 1, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y + 1, Color.CYAN));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 1, Color.BLUE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 4, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 2, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 2, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 2, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 5, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 4, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 5, y + 3, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 3, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 6, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 5, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 4, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 4, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 4, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 5, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 6, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 4, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 6, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 5, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 5, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 5, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 5, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 5, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x + 5, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 6, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 5, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 6, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 6, y + 6, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 6, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 6, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x, y + 6, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 6, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 6, Color.BLUE));
		this.cuerpo.addPixel(new Coordenada(x + 6, y + 6, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 6, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 7, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y + 7, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 7, Color.WHITE));
	}
}
