public class Enemigo extends Nave {
	
	public Enemigo(int x, int y) {
		super(x, y, 1);			//Velocidad = 1
	}
	
	@Override
	public void mover() {
		y++; //Va bajando un pixel cada 200ms (puesto en eGela)
	}

}