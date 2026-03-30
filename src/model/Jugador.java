package model;

import java.util.ArrayList;
import java.util.List;

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
		Disparo nuevoDisparo = new Disparo(x, y - 1); // Crea un nuevo disparo en la posicion actual del jugador
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
}