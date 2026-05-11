package model;
import java.awt.Color;

public class Verde extends Nave {

	// Hace @Override del metodo iniciarCuerpo() para definir la forma de la nave

	public Verde(int x, int y) {
		super(x, y);
		this.setVida(3);
		this.setFlechas(30); 
		this.setRombos(0);
	}

	@Override
	protected void iniciarCuerpo(int x, int y) {
		this.addPixel(new Coordenada(x, y - 8, Color.WHITE));
		this.addPixel(new Coordenada(x, y - 7, Color.WHITE));
		this.addPixel(new Coordenada(x, y - 6, Color.WHITE));

		this.addPixel(new Coordenada(x - 1, y - 5, Color.WHITE));
		this.addPixel(new Coordenada(x, y - 5, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y - 5, Color.WHITE));

		this.addPixel(new Coordenada(x - 1, y - 4, Color.WHITE));
		this.addPixel(new Coordenada(x, y - 4, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y - 4, Color.WHITE));

		this.addPixel(new Coordenada(x - 1, y - 3, Color.WHITE));
		this.addPixel(new Coordenada(x, y - 3, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y - 3, Color.WHITE));

		this.addPixel(new Coordenada(x - 4, y - 2, Color.GREEN));
		this.addPixel(new Coordenada(x - 1, y - 2, Color.WHITE));
		this.addPixel(new Coordenada(x, y - 2, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y - 2, Color.WHITE));
		this.addPixel(new Coordenada(x + 4, y - 2, Color.GREEN));

		this.addPixel(new Coordenada(x - 4, y - 1, Color.GREEN));
		this.addPixel(new Coordenada(x - 2, y - 1, Color.WHITE));
		this.addPixel(new Coordenada(x - 1, y - 1, Color.WHITE));
		this.addPixel(new Coordenada(x, y - 1, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y - 1, Color.WHITE));
		this.addPixel(new Coordenada(x + 2, y - 1, Color.WHITE));
		this.addPixel(new Coordenada(x + 4, y - 1, Color.GREEN));

		this.addPixel(new Coordenada(x - 7, y, Color.GREEN));
		this.addPixel(new Coordenada(x - 4, y, Color.WHITE));
		this.addPixel(new Coordenada(x - 3, y, Color.ORANGE));
		this.addPixel(new Coordenada(x - 2, y, Color.WHITE));
		this.addPixel(new Coordenada(x - 1, y, Color.WHITE));
		this.addPixel(new Coordenada(x, y, Color.GREEN));
		this.addPixel(new Coordenada(x + 1, y, Color.WHITE));
		this.addPixel(new Coordenada(x + 2, y, Color.WHITE));
		this.addPixel(new Coordenada(x + 3, y, Color.ORANGE));
		this.addPixel(new Coordenada(x + 4, y, Color.WHITE));
		this.addPixel(new Coordenada(x + 7, y, Color.GREEN));

		this.addPixel(new Coordenada(x - 7, y + 1, Color.GREEN));
		this.addPixel(new Coordenada(x - 4, y + 1, Color.ORANGE));
		this.addPixel(new Coordenada(x - 3, y + 1, Color.WHITE));
		this.addPixel(new Coordenada(x - 2, y + 1, Color.WHITE));
		this.addPixel(new Coordenada(x - 1, y + 1, Color.GREEN));
		this.addPixel(new Coordenada(x, y + 1, Color.GREEN));
		this.addPixel(new Coordenada(x + 1, y + 1, Color.GREEN));
		this.addPixel(new Coordenada(x + 2, y + 1, Color.WHITE));
		this.addPixel(new Coordenada(x + 3, y + 1, Color.WHITE));
		this.addPixel(new Coordenada(x + 4, y + 1, Color.ORANGE));
		this.addPixel(new Coordenada(x + 7, y + 1, Color.GREEN));

		this.addPixel(new Coordenada(x - 7, y + 2, Color.WHITE));
		this.addPixel(new Coordenada(x - 4, y + 2, Color.WHITE));
		this.addPixel(new Coordenada(x - 3, y + 2, Color.WHITE));
		this.addPixel(new Coordenada(x - 2, y + 2, Color.WHITE));
		this.addPixel(new Coordenada(x - 1, y + 2, Color.GREEN));
		this.addPixel(new Coordenada(x, y + 2, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y + 2, Color.GREEN));
		this.addPixel(new Coordenada(x + 2, y + 2, Color.WHITE));
		this.addPixel(new Coordenada(x + 3, y + 2, Color.WHITE));
		this.addPixel(new Coordenada(x + 4, y + 2, Color.WHITE));
		this.addPixel(new Coordenada(x + 7, y + 2, Color.WHITE));

		this.addPixel(new Coordenada(x - 7, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x - 5, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x - 4, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x - 3, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x - 2, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x - 1, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x + 2, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x + 3, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x + 4, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x + 5, y + 3, Color.WHITE));
		this.addPixel(new Coordenada(x + 7, y + 3, Color.WHITE));

		this.addPixel(new Coordenada(x - 7, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x - 6, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x - 5, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x - 4, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x - 3, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x - 2, y + 4, Color.GREEN));
		this.addPixel(new Coordenada(x - 1, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x + 2, y + 4, Color.GREEN));
		this.addPixel(new Coordenada(x + 3, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x + 4, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x + 5, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x + 6, y + 4, Color.WHITE));
		this.addPixel(new Coordenada(x + 7, y + 4, Color.WHITE));

		this.addPixel(new Coordenada(x - 7, y + 5, Color.WHITE));
		this.addPixel(new Coordenada(x - 6, y + 5, Color.WHITE));
		this.addPixel(new Coordenada(x - 5, y + 5, Color.WHITE));
		this.addPixel(new Coordenada(x - 3, y + 5, Color.GREEN));
		this.addPixel(new Coordenada(x - 2, y + 5, Color.GREEN));
		this.addPixel(new Coordenada(x - 1, y + 5, Color.WHITE));
		this.addPixel(new Coordenada(x, y + 5, Color.WHITE));
		this.addPixel(new Coordenada(x + 1, y + 5, Color.WHITE));
		this.addPixel(new Coordenada(x + 2, y + 5, Color.GREEN));
		this.addPixel(new Coordenada(x + 3, y + 5, Color.GREEN));
		this.addPixel(new Coordenada(x + 5, y + 5, Color.WHITE));
		this.addPixel(new Coordenada(x + 6, y + 5, Color.WHITE));
		this.addPixel(new Coordenada(x + 7, y + 5, Color.WHITE));

		this.addPixel(new Coordenada(x - 7, y + 6, Color.WHITE));
		this.addPixel(new Coordenada(x - 6, y + 6, Color.WHITE));
		this.addPixel(new Coordenada(x - 3, y + 6, Color.GREEN));
		this.addPixel(new Coordenada(x - 2, y + 6, Color.GREEN));
		this.addPixel(new Coordenada(x, y + 6, Color.WHITE));
		this.addPixel(new Coordenada(x + 2, y + 6, Color.GREEN));
		this.addPixel(new Coordenada(x + 3, y + 6, Color.GREEN));
		this.addPixel(new Coordenada(x + 6, y + 6, Color.WHITE));
		this.addPixel(new Coordenada(x + 7, y + 6, Color.WHITE));

		this.addPixel(new Coordenada(x - 7, y + 7, Color.WHITE));
		this.addPixel(new Coordenada(x, y + 7, Color.WHITE));
		this.addPixel(new Coordenada(x + 7, y + 7, Color.WHITE));
	}

	

}

