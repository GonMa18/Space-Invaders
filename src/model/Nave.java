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
	private int vida;
	private StrategyBala strategyBala = new BalaPixel();	//Por defecto
	private long tiempoInvulnerabilidad; // Timestamp de cuando comienza la invulnerabilidad
	private static final long DURACION_INVULNERABILIDAD = 2000; // 2 segundos
	private int puntos;

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public Nave(int x, int y) {
		this.sigueJugando = true;
		this.cuerpo = new Composite(x, y);
		this.disparos = new ArrayList<>();
		this.flechas = 0;
		this.rombos = 0;
		this.vida = 100;
		this.puntos = 0;
		this.iniciarCuerpo(x, y);	// Por defecto, el cuerpo se inicia con rojo y azul
	} 

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public ArrayList<Component> getCoordenadas() {
		return cuerpo.getPixeles();
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean sigueVivo() {
		return sigueJugando && vida > 0;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public void setSigueJugando(boolean b) {
		this.sigueJugando = b;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public int getVida() {
		return vida;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public void recibirDaño(int daño) {
		// No recibe daño si está en período de invulnerabilidad
		if (estaEnInvulnerabilidad()) {
			return;
		}
		this.vida -= daño;
		if (this.vida < 0) {
			this.vida = 0;
		}
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

	protected void setVida(int vida) {
		this.vida = vida;
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

	public int getPuntos() {
		return puntos;
	}

	public void addPuntos(int cantidad) {
		this.puntos += cantidad;
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

	// Métodos para invulnerabilidad y parpadeo
	public void activarInvulnerabilidad() {
		this.tiempoInvulnerabilidad = System.currentTimeMillis();
	}

	public boolean estaEnInvulnerabilidad() {
		if (tiempoInvulnerabilidad == 0) {
			return false;
		}
		long tiempoTranscurrido = System.currentTimeMillis() - tiempoInvulnerabilidad;
		if (tiempoTranscurrido >= DURACION_INVULNERABILIDAD) {
			tiempoInvulnerabilidad = 0; // Finalizar invulnerabilidad
			return false;
		}
		return true;
	}

	public boolean debeParpadear() {
		if (!estaEnInvulnerabilidad()) {
			return false;
		}
		long tiempoTranscurrido = System.currentTimeMillis() - tiempoInvulnerabilidad;
		// Parpadeo cada 200ms (100ms visible, 100ms invisible)
		return (tiempoTranscurrido / 100) % 2 == 0;
	}
}
