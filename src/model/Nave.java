package model;
 
public abstract class Nave {
	
	// Atributos de cada pixel en el juego (naves enemigas y jugador)
	private int velocidad;
	private boolean sigueJugando;
	protected int x;					//Protected para que las que heredan puedan accedder
	protected int y;
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public Nave(int x, int y, int velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.sigueJugando = true;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public int getX() {
		return x;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public int getY() {
		return y;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public boolean continuesPlaying() {
		return sigueJugando;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public boolean sigueVivo() {
		return sigueJugando;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public void setSigueJugando(boolean b) {
		this.sigueJugando = b;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public abstract void mover(int x, int y); 	// Metodo que todas las naves que hereden "Nave" tienen que implementar
												// No pongo codigo porque enemigos y jugador se mueven distinto
}