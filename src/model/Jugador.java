package model;

import java.util.ArrayList;

public abstract class Jugador extends Nave { // Hereda de NAVE

	// El jugador necesita una lista de disparos activos
	// Las coordenadas (x, y), la velocidad y el estado (sigueJugando)
	// ya los hereda de Nave.

	// Hereda de Nave las coordenadas y el estado de sigueJugando

	protected ArrayList<Disparo> disparos;
	protected int flechas;
	protected int rombos;

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public Jugador(int x, int y) {
		super(x, y);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public void disparar() { // TODO
		Disparo nuevoDisparo = new Disparo(55, 60); // Crea un nuevo disparo en la posicion actual del jugador
		nuevoDisparo.setShoot(true); // Activa el disparo
		disparos.add(nuevoDisparo); // Agrega el nuevo disparo a la lista de disparos activos
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public ArrayList<Disparo> getDisparos() { // TODO
		return disparos;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// Elimina los disparos que ya no están activos
	public void limpiarDisparos() { // TODO
		for (int i = disparos.size() - 1; i >= 0; i--) {
			if (!disparos.get(i).isShooting()) {
				disparos.remove(i);
			}
		}
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
}