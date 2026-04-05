package model;

public class Verde extends Jugador {

	// Hace @Override del metodo iniciarCuerpo() para definir la forma de la nave

	public Verde(int x, int y) {
		super(x, y, "verde");	//es de tipo verde TODO
		this.flechas = 30;	//TODO
		this.rombos = 20;	//TODO

	}
	
	@Override
	public void pintarCuerpo() {
		//TODO -- porque error?
	}
	

	

}
