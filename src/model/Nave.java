package model;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Nave {

	// Atributos de cada pixel en el juego (naves enemigas y jugador)
	private boolean sigueJugando;
	private  Composite cuerpo;
	private ArrayList<Disparo> disparos;
	private int flechas;
	private int rombos;
	private StrategyBala strategyBala = new BalaPixel();	//Por defecto

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public Nave(int x, int y) {
		this.sigueJugando = true;
		this.cuerpo = new Composite(x, y);
		this.disparos = new ArrayList<>();
		this.flechas = 0;
		this.rombos = 0;
		this.iniciarCuerpo(x, y);	// Por defecto, el cuerpo se inicia con rojo y azul
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

	protected void iniciarCuerpo(int x, int y) {
		this.addPixel(new Coordenada(x , y, Color.WHITE));
		this.addPixel(new Coordenada(x, y + 1, Color.WHITE));
		this.addPixel(new Coordenada(x - 1, y + 1, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y + 1, Color.WHITE));
	}

	protected void addPixel(Coordenada coordenada) {
		this.cuerpo.addPixel(coordenada);
	}

	protected void setFlechas(int flechas) {
		this.flechas = flechas;
	}

	protected void setRombos(int rombos) {
		this.rombos = rombos;
	}

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
	public ArrayList<Disparo> getDisparos() { // TODO
		return disparos;
	}

	public int getFlechas() {
		return flechas;
	}

	public int getRombos() {
		return rombos;
	}
	public void limpiarDisparos() { // TODO
//		if (disparos == null) {
//			return; // Si la lista de disparos es null, no hay nada que limpiar
//		}
//		for (int i = disparos.size() - 1; i >= 0; i--) {
//			if (!disparos.get(i).isShooting()) {
//				disparos.remove(i);
//			}
//		}
		disparos.removeIf(d -> !d.isShooting());
	}
}
