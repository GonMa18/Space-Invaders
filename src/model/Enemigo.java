package model;
import java.awt.Color;
public class Enemigo extends Nave {

	public Enemigo(int x, int y) {
		super(x, y);
		// Llama a la constructora de Nave (aita) para inicializar x, y y velocidad
	}


	@Override
	protected void iniciarCuerpo(int x, int y) {
		this.cuerpo.addPixel(new Coordenada(x - 3, y - 3, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 3, y - 3, Color.GREEN));

		this.cuerpo.addPixel(new Coordenada(x - 2, y - 2, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 2, y - 2, Color.GREEN));

		this.cuerpo.addPixel(new Coordenada(x - 3, y - 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 2, y - 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 1, y - 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x, y - 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 1, y - 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 2, y - 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 3, y - 1, Color.GREEN));

		this.cuerpo.addPixel(new Coordenada(x - 4, y, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 3, y, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 1, y, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x, y, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 1, y, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 3, y, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 4, y, Color.GREEN));

		this.cuerpo.addPixel(new Coordenada(x - 5, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 4, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 4, y + 1, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 5, y + 1, Color.GREEN));

		this.cuerpo.addPixel(new Coordenada(x - 5, y + 2, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 2, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 2, y + 2, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 2, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x, y + 2, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 2, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 2, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 2, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 5, y + 2, Color.GREEN));

		this.cuerpo.addPixel(new Coordenada(x - 5, y + 3, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 3, y + 3, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 3, y + 3, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 5, y + 3, Color.GREEN));

		this.cuerpo.addPixel(new Coordenada(x - 2, y + 4, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x - 1, y + 4, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 1, y + 4, Color.GREEN));
		this.cuerpo.addPixel(new Coordenada(x + 2, y + 4, Color.GREEN));
	}

}
