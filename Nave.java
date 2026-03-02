public abstract class Naves {
	
	// Atributos de cada pixel en el juego (naves enemigas y jugador)
	private int velocidad;
	private boolean sigueJugando;
	private int x;
	private int y;
	
	// Contructora
	public Naves(int y, int x, int velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.sigueJugando = true;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean continuesPlaying() {
		return sigueJugando;
	}
	
	public void setSigueJugando(boolean b) {
		this.sigueJugando = b;
	}
	
	public abstract void mover(); 	// Metodo que todas las naves que hereden "Nave" tienen que implementar
									// No pongo codigo porque enemigos y jugador se mueven distinto
}