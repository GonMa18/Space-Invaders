package model;
import java.awt.Color;
import java.awt.Graphics;

public class Coordenada {
	
	private int x;
	private int y;
	private Color color;
	
	public Coordenada(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void mover(int x, int y) {
		this.x += x;
		this.y += y;
		if (this.x < 0) {
			this.x = 0;
		}
		if (this.y < 0) {
			this.y = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void pintar(Graphics g, int tamCelda) {
	    g.setColor(color);
	    g.fillRect(x * tamCelda, y * tamCelda, tamCelda, tamCelda);
	}

}
