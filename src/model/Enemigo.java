package model;
public class Enemigo extends Nave {
	
	public Enemigo(int x, int y) {
		super(x, y, 1);			//Velocidad = 1
								// Llama a la constructora de Nave (aita) para inicializar x, y y velocidad
	}
	
	@Override
	public void mover() {
		y++; //Va bajando un pixel cada 200ms (puesto en eGela)
	}

}