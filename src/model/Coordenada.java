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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
