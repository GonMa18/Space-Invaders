package model;

public class Jugador extends Nave { //Hereda de NAVE
	
	// El jugador solo necesita un atributo propio: el disparo.
	// Las coordenadas (x, y), la velocidad y el estado (sigueJugando)
	// ya los hereda de Nave.
	
	private Disparo disparo;
	
	
	// Constructora: crea el jugador en la posicion inicial (y=50, x=55)
	// y le asigna un disparo inactivo en su misma posicion
	public Jugador() {
		super(50, 55, 1); //Posicion inicial (y=50, x=55, velocidad=1)
		this.disparo = new Disparo(x, y);
	}
	
	// mover() sin parametros: obligatorio por ser abstracto en Nave.
	// El jugador no se mueve solo, asi que se deja vacio.
	
	@Override
	// Se llama desde Espacio.moverJugador() cuando el usuario pulsa una tecla
	public void mover(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Solo dispara si no hay ya un disparo activo en pantalla
	public void disparar() {
		if (!disparo.isShooting()) {
			disparo = new Disparo(x, y - 1); //Justo un pixel por encima
			disparo.setShoot(true);
		}
	}
	
	public Disparo getDisparo() {
		return disparo;
	}
	
}