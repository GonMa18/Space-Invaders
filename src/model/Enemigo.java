package model;
import java.awt.Color;
import java.util.ArrayList;
public class Enemigo extends Nave {
	
	
	public Enemigo(int x, int y) {
		super(x, y);			
															// Llama a la constructora de Nave (aita) para inicializar x, y y velocidad
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	@Override
	public void mover(int y, int x) {
		this.y += y;
		this.x += x;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public void bajar() {
		y++; 						//Va bajando un pixel cada 200ms 
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
	@Override
	protected void iniciarCuerpo(int x, int y) {
		// Fila superior (y - 7)
		coordenadas.add(new Coordenada(x - 1, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 7, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 7, Color.WHITE));

		// y - 6
		coordenadas.add(new Coordenada(x - 2, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x - 1, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 6, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 6, Color.WHITE));

		// y - 5
		coordenadas.add(new Coordenada(x - 3, y - 5, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 5, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 5, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 5, Color.WHITE));
		coordenadas.add(new Coordenada(x + 3, y - 5, Color.WHITE));

		// y - 4
		coordenadas.add(new Coordenada(x - 4, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x - 3, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x - 1, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x + 3, y - 4, Color.WHITE));
		coordenadas.add(new Coordenada(x + 4, y - 4, Color.WHITE));

		// y - 3
		coordenadas.add(new Coordenada(x - 5, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x - 4, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x - 1, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x + 4, y - 3, Color.WHITE));
		coordenadas.add(new Coordenada(x + 5, y - 3, Color.WHITE));

		// y - 2
		coordenadas.add(new Coordenada(x - 5, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x - 4, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x - 3, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x - 2, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x - 1, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x + 2, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x + 3, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x + 4, y - 2, Color.WHITE));
		coordenadas.add(new Coordenada(x + 5, y - 2, Color.WHITE));

		// y - 1
		coordenadas.add(new Coordenada(x - 4, y - 1, Color.WHITE));
		coordenadas.add(new Coordenada(x - 3, y - 1, Color.WHITE));
		coordenadas.add(new Coordenada(x, y - 1, Color.WHITE));
		coordenadas.add(new Coordenada(x + 3, y - 1, Color.WHITE));
		coordenadas.add(new Coordenada(x + 4, y - 1, Color.WHITE));

		// Fila inferior (y)
		coordenadas.add(new Coordenada(x - 4, y, Color.WHITE));
		coordenadas.add(new Coordenada(x - 1, y, Color.WHITE));
		coordenadas.add(new Coordenada(x + 1, y, Color.WHITE));
		coordenadas.add(new Coordenada(x + 4, y, Color.WHITE));
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
