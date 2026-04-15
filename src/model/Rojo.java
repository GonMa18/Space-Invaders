package model;
import java.awt.Color;

public class Rojo extends Jugador {

	// Hace @Override del metodo iniciarCuerpo() para definir la forma de la nave

	public Rojo(int x, int y) {
		super(x, y);
		this.flechas = 30; 
		this.rombos = 20;
	}

	@Override
	protected void iniciarCuerpo(int x, int y, Color primario, Color secundario) {
		super.iniciarCuerpo(x, y, Color.RED, Color.BLUE);
	}

	

}
