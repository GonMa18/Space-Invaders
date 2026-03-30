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

		// Base and Engines
		coordenadas.add(new Coordenada(x - 2, y, Color.PINK));
		coordenadas.add(new Coordenada(x - 1, y, Color.PINK));
		coordenadas.add(new Coordenada(x, y, Color.WHITE)); // Tail (Reference)
		coordenadas.add(new Coordenada(x + 1, y, Color.RED));
		coordenadas.add(new Coordenada(x + 2, y, Color.RED));

		coordenadas.add(new Coordenada(x - 2, y - 1, Color.RED));
		coordenadas.add(new Coordenada(x - 1, y - 1, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 1, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 1, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 1, Color.RED));

		// Lower Body and Outer Wings
		coordenadas.add(new Coordenada(x - 5, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x - 1, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x + 5, y - 2, Color.WHITE));

		coordenadas.add(new Coordenada(x - 10, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x - 9, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x - 8, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x - 7, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x - 3, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x - 1, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 3, Color.WHITE));

		// Central Zone and Cockpit
		coordenadas.add(new Coordenada(x - 10, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x - 9, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x - 8, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x - 7, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x - 6, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x - 5, y - 4, Color.RED));
		coordenadas.add(new Coordenada(x - 4, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x - 3, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x + 3, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x + 4, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x + 5, y - 4, Color.WHITE));

		coordenadas.add(new Coordenada(x - 10, y - 5, Color.RED));
		coordenadas.add(new Coordenada(x - 9, y - 5, Color.WHITE));
		coordenadas.add(new Coordenada(x - 8, y - 5, Color.BLUE));
		coordenadas.add(new Coordenada(x - 7, y - 5, Color.WHITE));
		coordenadas.add(new Coordenada(x - 6, y - 5, Color.RED));
		coordenadas.add(new Coordenada(x - 5, y - 5, Color.RED));
		coordenadas.add(new Coordenada(x - 4, y - 5, Color.RED));
		coordenadas.add(new Coordenada(x - 3, y - 5, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 5, Color.BLUE));
		coordenadas.add(new Coordenada(x + 4, y - 5, Color.WHITE));
		coordenadas.add(new Coordenada(x + 5, y - 5, Color.RED));

		coordenadas.add(new Coordenada(x - 4, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x - 3, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 6, Color.BLUE));
		coordenadas.add(new Coordenada(x - 1, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 6, Color.RED));
		coordenadas.add(new Coordenada(x + 1, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 6, Color.BLUE));
		coordenadas.add(new Coordenada(x + 3, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x + 4, y - 6, Color.WHITE));

		// Upper Structure
		coordenadas.add(new Coordenada(x - 4, y - 7, Color.RED));
		coordenadas.add(new Coordenada(x - 3, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x - 1, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x + 3, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x + 4, y - 7, Color.RED));

		coordenadas.add(new Coordenada(x - 3, y - 8, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 8, Color.WHITE));
		coordenadas.add(new Coordenada(x - 1, y - 8, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 8, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 8, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 8, Color.WHITE));
		coordenadas.add(new Coordenada(x + 3, y - 8, Color.WHITE));

		// Tip and Secondary Cannons
		coordenadas.add(new Coordenada(x - 2, y - 9, Color.RED));
		coordenadas.add(new Coordenada(x - 1, y - 9, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 9, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 9, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 9, Color.RED));

		coordenadas.add(new Coordenada(x - 1, y - 10, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 10, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 10, Color.WHITE));

		coordenadas.add(new Coordenada(x - 1, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 7, Color.WHITE));

		coordenadas.add(new Coordenada(x, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 5, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 8, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 9, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 10, Color.WHITE));
	}

	

}
