package model;

import java.util.ArrayList;

public abstract class Nave {
	
	// Atributos de cada pixel en el juego (naves enemigas y jugador)
	private int velocidad;
	private boolean sigueJugando;
	protected int x;					//Protected para que las que heredan puedan accedder
	protected int y;
    
	private ArrayList<Disparo> disparos;

	
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public Nave(int x, int y, int velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.sigueJugando = true;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
	
	
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
    
    
	public  void mover(int x, int y){	//REVISAR
		this.x += x;
		this.y += y;
		
		 //// LIMITES ////
	    if (this.x < 0) { this.x = 0; }
	    if (this.x > Espacio.getAnchura()-1) { this.x = Espacio.getAnchura()-1; }
	    if (this.y < 0) { this.y = 0; }
	    if (this.y > Espacio.getAltura()-1) { this.y = Espacio.getAltura()-1; }
	}
    
    
	
	public void disparar() {
		Disparo nuevoDisparo = new Disparo(x, y-1); 	//Crea un nuevo disparo en la posicion actual del jugador
		nuevoDisparo.setShoot(true); 					// Activa el disparo
		disparos.add(nuevoDisparo); 					// Agrega el nuevo disparo a la lista de disparos activos
	}
	
	public ArrayList<Disparo> getDisparos() {
		return disparos;
	}


// Elimina los disparos que ya no están activos
public void limpiarDisparos() {
	 for (int i = disparos.size() - 1; i >= 0; i--) {
	        if (!disparos.get(i).isShooting()) {
	            disparos.remove(i);
	        }
	  }    
}
}