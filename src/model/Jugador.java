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
	private StrategyBala strategyBala = new BalaPixel();	//Por defecto

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public Jugador(int x, int y) {
		super(x, y);
		this.disparos = new ArrayList<>();
	}

	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void disparar() {
		disparos.addAll(strategyBala.disparar(this.getX(), this.getY() - 10));		// Crea un nuevo disparo en la posicion actual del jugador
																				// Agrega el nuevo disparo a la lista de disparos activos	
	}
	
	public void changestrategyBala(StrategyBala sb) {
		this.strategyBala = sb;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public ArrayList<Disparo> getDisparos() { // TODO
		return disparos;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// Elimina los disparos que ya no están activos
	public void limpiarDisparos() { // TODO
		if (disparos == null) {
			return; // Si la lista de disparos es null, no hay nada que limpiar
		}
		for (int i = disparos.size() - 1; i >= 0; i--) {
			if (!disparos.get(i).isShooting()) {
				disparos.remove(i);
			}
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	
}