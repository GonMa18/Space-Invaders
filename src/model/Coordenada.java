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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void pintar(Graphics g, int tamCelda) {
	    g.setColor(color);
	    g.fillRect(x * tamCelda, y * tamCelda, tamCelda, tamCelda);
	}

}
