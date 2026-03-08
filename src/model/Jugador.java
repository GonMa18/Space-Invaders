package model;

import java.util.ArrayList;
import java.util.List;

public class Jugador extends Nave { //Hereda de NAVE
	
	// El jugador necesita una lista de disparos activos
	// Las coordenadas (x, y), la velocidad y el estado (sigueJugando)
	// ya los hereda de Nave.
	
	private Disparo disparo;
	
	
	// Constructora: crea el jugador en la posicion inicial (y=50, x=55)
	public Jugador() {
		super(50, 55, 1); //Posicion inicial (y=50, x=55, velocidad=1)
		this.disparo = new Disparo(x,y); //Inicialmente sin disparo activo
	}
	
	@Override
	// Se llama desde Espacio.moverJugador() cuando el usuario pulsa una tecla
	public void mover(int x, int y) {
		this.x += x;
		this.y += y;
		//Limites
//		if (x < 0) {x = 0;}
//		if (x > 99) {x = 99;}
//		if (y < 0) {y = 0;}
//		if (y > 59) {y = 59;}
	}
	
	// Crea un nuevo disparo cada vez que se pulsa espacio
	public void disparar() {
		if (!disparo.isShooting()){
			disparo= new Disparo(x,y-1); //Crea un nuevo disparo en la posicion actual del jugador
			disparo.setShoot(true); // Activa el disparo
		}
	}
	
	public Disparo getDisparos() {
		return disparo;
	}
	
}