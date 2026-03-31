package model;
import java.awt.Color;
public class Enemigo extends Nave {
	
	
	public Enemigo(int x, int y) {
		super(x, y);			
															// Llama a la constructora de Nave (aita) para inicializar x, y y velocidad
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public void bajar() {					//Va bajando un pixel cada 200ms
		for (Coordenada c : this.getCoordenadas()) {
			c.setY(c.getY() + 1);			//Actualiza las coordenadas de cada pixel de la nave
		}
	}
	
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
	@Override
	protected void iniciarCuerpo(int x, int y) {
		coordenadas.add(new Coordenada(x , y, Color.WHITE));
		coordenadas.add(new Coordenada(x, y + 1, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y + 1, Color.WHITE));
	}


    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public boolean meHanDisparado(int x, int y) {
		for (Coordenada coord : coordenadas) {
			if (coord.getX() == x && coord.getY() == y) {
				return true; 			// Si el disparo coincide con alguna coordenada de la nave, devuelve true
			}
		}
		return false; 				// Si el disparo no coincide con ninguna coordenada de la nave, devuelve false
	}

}
