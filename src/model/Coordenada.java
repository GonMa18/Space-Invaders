package model;
import java.awt.Color;

public class Coordenada {
	
	private int x;
	private int y;
	private Color color;
	
	public Coordenada(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void mover(int dx, int dy) {
		this.x += dx;
		this.y += dy;
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

	public Color getColor() {
		return color;
	}

}
