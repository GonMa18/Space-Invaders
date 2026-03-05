package model;

import java.util.ArrayList;
import java.util.List;

public class Jugador extends Nave { //Hereda de NAVE
	
	// El jugador necesita una lista de disparos activos
	// Las coordenadas (x, y), la velocidad y el estado (sigueJugando)
	// ya los hereda de Nave.
	
	private List<Disparo> disparos;
	
	
	// Constructora: crea el jugador en la posicion inicial (y=50, x=55)
	public Jugador() {
		super(50, 55, 1); //Posicion inicial (y=50, x=55, velocidad=1)
		this.disparos = new ArrayList<>();
	}
	
	@Override
	// Se llama desde Espacio.moverJugador() cuando el usuario pulsa una tecla
	public void mover(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Crea un nuevo disparo cada vez que se pulsa espacio
	public void disparar() {
		Disparo d = new Disparo(x, y - 2); //Dos pixeles por encima del jugador
		d.setShoot(true);
		disparos.add(d);
	}
	
	public List<Disparo> getDisparos() {
		return disparos;
	}
	
}