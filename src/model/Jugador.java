package model;

import java.util.ArrayList;
import java.util.List;

public class Jugador extends Nave { 	//Hereda de NAVE
	
	// El jugador necesita una lista de disparos activos
	// Las coordenadas (x, y), la velocidad y el estado (sigueJugando)
	// ya los hereda de Nave.
	
	private ArrayList<Disparo> disparos;
	
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public Jugador() {
		super(50, 55, 1); 						//Posicion inicial (y=50, x=55, velocidad=1)
		this.disparos = new ArrayList<>(); 
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	@Override
	// Se llama desde Espacio.moverJugador() cuando el usuario pulsa una tecla
	public void mover(int x, int y) {
		this.x += x;
		this.y += y;
		
		 //// LIMITES ////
	    if (this.x < 0) { this.x = 0; }
	    if (this.x > Espacio.getAnchura()-1) { this.x = Espacio.getAnchura()-1; }
	    if (this.y < 0) { this.y = 0; }
	    if (this.y > Espacio.getAltura()-1) { this.y = Espacio.getAltura()-1; }
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public void disparar() {
		Disparo nuevoDisparo = new Disparo(x, y-1); 	//Crea un nuevo disparo en la posicion actual del jugador
		nuevoDisparo.setShoot(true); 					// Activa el disparo
		disparos.add(nuevoDisparo); 					// Agrega el nuevo disparo a la lista de disparos activos
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public ArrayList<Disparo> getDisparos() {
		return disparos;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    // Elimina los disparos que ya no están activos
    public void limpiarDisparos() {
    	 for (int i = disparos.size() - 1; i >= 0; i--) {
    	        if (!disparos.get(i).isShooting()) {
    	            disparos.remove(i);
    	        }
    	  }    
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
}