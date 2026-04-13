package model;

import java.awt.Color;
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
		if (strategyBala instanceof BalaFlecha && flechas > 0) {
			flechas--; // Decrementa el contador de flechas
		}
		else if (strategyBala instanceof BalaRombo && rombos > 0) {
			rombos--; // Decrementa el contador de rombos
		}
		else if (strategyBala instanceof BalaPixel) {
			// No hay necesidad de decrementar nada para BalaPixel
		} else {
			return; // No hay munición disponible para el tipo de bala actual, no se dispara
		}
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

	public int getFlechas() {
		return flechas;
	}

	public int getRombos() {
		return rombos;
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

		@Override
	protected void iniciarCuerpo(int x, int y, Color primario, Color secundario) {
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

		this.cuerpo.addPixel(new Coordenada(x - 4, y - 2, primario));
		this.cuerpo.addPixel(new Coordenada(x - 1, y - 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y - 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y - 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y - 2, primario));

		this.cuerpo.addPixel(new Coordenada(x - 4, y - 1, primario));
		this.cuerpo.addPixel(new Coordenada(x - 2, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y - 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y - 1, primario));

		this.cuerpo.addPixel(new Coordenada(x - 7, y, primario));
		this.cuerpo.addPixel(new Coordenada(x - 4, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y, secundario));
		this.cuerpo.addPixel(new Coordenada(x - 2, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y, primario));
		this.cuerpo.addPixel(new Coordenada(x + 1, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 3, y, secundario));
		this.cuerpo.addPixel(new Coordenada(x + 4, y, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y, primario));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 1, primario));
		this.cuerpo.addPixel(new Coordenada(x - 4, y + 1, secundario));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 1, primario));
		this.cuerpo.addPixel(new Coordenada(x, y + 1, primario));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 1, primario));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 1, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y + 1, secundario));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 1, primario));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 4, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 2, primario));
		this.cuerpo.addPixel(new Coordenada(x, y + 2, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 2, primario));
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
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 4, primario));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 4, primario));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 4, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 5, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 6, y + 4, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 4, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 6, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 5, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 5, primario));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 5, primario));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 5, primario));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 5, primario));
		this.cuerpo.addPixel(new Coordenada(x + 5, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 6, y + 5, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 5, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 6, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 6, y + 6, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 6, primario));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 6, primario));
		this.cuerpo.addPixel(new Coordenada(x, y + 6, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 6, primario));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 6, primario));
		this.cuerpo.addPixel(new Coordenada(x + 6, y + 6, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 6, Color.WHITE));

		this.cuerpo.addPixel(new Coordenada(x - 7, y + 7, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x, y + 7, Color.WHITE));
		this.cuerpo.addPixel(new Coordenada(x + 7, y + 7, Color.WHITE));
	}

	
}