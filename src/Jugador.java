
public class Jugador extends Nave { //Heredea de NAVE
	
	//Atributos???
	//Segun yo (Dani) solo el disparo porque NAVE ya tiene las coordenadas,
	//la velocidad y el booleno de si sigue jugando
	//Y JUGADOR = NAVE
	
	private Disparo disparo;
	
	
	public Jugador() {
		super(50, 55, 1); //Posicion inicial (salen en eGela)
		this.disparo = new Disparo(x, y);
	}
	
	@Override
	public void mover() { //Heredado: tiene que aparecer si o si
	}
	
	public void mover(int x, int y) {
		//Falta
	}
	
	public void disparar() {
		if (!disparo.isShooting()) {
			disparo = new Disparo(x, y-1); //Justo un pixel ppor encima
			disparo.setShoot(true);
		}
	}
	
	public Disparo getDisparo() {
		return disparo;
	}


	
}