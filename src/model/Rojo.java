package model;

import java.awt.Color;
import java.util.ArrayList;

public class Rojo extends Nave{
	
	private ArrayList<Coordenada> coordenadas;
	
	private ArrayList<Disparo> flechas;
	private ArrayList<Disparo> rombos;
	private ArrayList<Disparo> bala;

	public Rojo(int x, int y, int velocidad) {
		super(x, y, velocidad);
		
		

	}
	public Rojo getRojo() {
		return this;
	}
	
	
	public void dibujarCuerpo() {
		
		// Base and Engines
		coordenadas.add(new Coordenada(48,55,Color.RED));
		coordenadas.add(new Coordenada(49,55,Color.RED));
		coordenadas.add(new Coordenada(50,55,Color.WHITE)); // Tail (Reference)
		coordenadas.add(new Coordenada(51,55,Color.RED));
		coordenadas.add(new Coordenada(52,55,Color.RED));

		coordenadas.add(new Coordenada(48,54,Color.RED));
		coordenadas.add(new Coordenada(49,54,Color.WHITE));
		coordenadas.add(new Coordenada(50,54,Color.WHITE));
		coordenadas.add(new Coordenada(51,54,Color.WHITE));
		coordenadas.add(new Coordenada(52,54,Color.RED));

		// Lower Body and Outer Wings
		coordenadas.add(new Coordenada(45,53,Color.WHITE));
		coordenadas.add(new Coordenada(48,53,Color.WHITE));
		coordenadas.add(new Coordenada(49,53,Color.WHITE));
		coordenadas.add(new Coordenada(50,53,Color.WHITE));
		coordenadas.add(new Coordenada(51,53,Color.WHITE));
		coordenadas.add(new Coordenada(52,53,Color.WHITE));
		coordenadas.add(new Coordenada(55,53,Color.WHITE));

		coordenadas.add(new Coordenada(45,52,Color.WHITE));
		coordenadas.add(new Coordenada(46,52,Color.WHITE));
		coordenadas.add(new Coordenada(47,52,Color.WHITE));
		coordenadas.add(new Coordenada(48,52,Color.WHITE));
		coordenadas.add(new Coordenada(52,52,Color.WHITE));
		coordenadas.add(new Coordenada(53,52,Color.WHITE));
		coordenadas.add(new Coordenada(54,52,Color.WHITE));
		coordenadas.add(new Coordenada(55,52,Color.WHITE));

		// Central Zone and Cockpit
		coordenadas.add(new Coordenada(45,51,Color.WHITE));
		coordenadas.add(new Coordenada(46,51,Color.WHITE));
		coordenadas.add(new Coordenada(47,51,Color.WHITE));
		coordenadas.add(new Coordenada(48,51,Color.WHITE));
		coordenadas.add(new Coordenada(49,51,Color.WHITE));
		coordenadas.add(new Coordenada(50,51,Color.RED));
		coordenadas.add(new Coordenada(51,51,Color.WHITE));
		coordenadas.add(new Coordenada(52,51,Color.WHITE));
		coordenadas.add(new Coordenada(53,51,Color.WHITE));
		coordenadas.add(new Coordenada(54,51,Color.WHITE));
		coordenadas.add(new Coordenada(55,51,Color.WHITE));

		coordenadas.add(new Coordenada(45,50,Color.RED));
		coordenadas.add(new Coordenada(46,50,Color.WHITE));
		coordenadas.add(new Coordenada(47,50,Color.BLUE));
		coordenadas.add(new Coordenada(48,50,Color.WHITE));
		coordenadas.add(new Coordenada(49,50,Color.RED));
		coordenadas.add(new Coordenada(50,50,Color.RED));
		coordenadas.add(new Coordenada(51,50,Color.RED));
		coordenadas.add(new Coordenada(52,50,Color.WHITE));
		coordenadas.add(new Coordenada(53,50,Color.BLUE));
		coordenadas.add(new Coordenada(54,50,Color.WHITE));
		coordenadas.add(new Coordenada(55,50,Color.RED));

		coordenadas.add(new Coordenada(46,49,Color.WHITE));
		coordenadas.add(new Coordenada(47,49,Color.WHITE));
		coordenadas.add(new Coordenada(48,49,Color.BLUE));
		coordenadas.add(new Coordenada(49,49,Color.WHITE));
		coordenadas.add(new Coordenada(50,49,Color.RED));
		coordenadas.add(new Coordenada(51,49,Color.WHITE));
		coordenadas.add(new Coordenada(52,49,Color.BLUE));
		coordenadas.add(new Coordenada(53,49,Color.WHITE));
		coordenadas.add(new Coordenada(54,49,Color.WHITE));

		// Upper Structure
		coordenadas.add(new Coordenada(46,48,Color.RED));
		coordenadas.add(new Coordenada(47,48,Color.WHITE));
		coordenadas.add(new Coordenada(48,48,Color.WHITE));
		coordenadas.add(new Coordenada(49,48,Color.WHITE));
		coordenadas.add(new Coordenada(50,48,Color.WHITE));
		coordenadas.add(new Coordenada(51,48,Color.WHITE));
		coordenadas.add(new Coordenada(52,48,Color.WHITE));
		coordenadas.add(new Coordenada(53,48,Color.WHITE));
		coordenadas.add(new Coordenada(54,48,Color.RED));

		coordenadas.add(new Coordenada(47,47,Color.WHITE));
		coordenadas.add(new Coordenada(48,47,Color.WHITE));
		coordenadas.add(new Coordenada(49,47,Color.WHITE));
		coordenadas.add(new Coordenada(50,47,Color.WHITE));
		coordenadas.add(new Coordenada(51,47,Color.WHITE));
		coordenadas.add(new Coordenada(52,47,Color.WHITE));
		coordenadas.add(new Coordenada(53,47,Color.WHITE));

		// Tip and Secondary Cannons
		coordenadas.add(new Coordenada(48,46,Color.RED));
		coordenadas.add(new Coordenada(49,46,Color.WHITE));
		coordenadas.add(new Coordenada(50,46,Color.WHITE));
		coordenadas.add(new Coordenada(51,46,Color.WHITE));
		coordenadas.add(new Coordenada(52,46,Color.RED));

		coordenadas.add(new Coordenada(49,45,Color.WHITE));
		coordenadas.add(new Coordenada(50,45,Color.WHITE));
		coordenadas.add(new Coordenada(51,45,Color.WHITE));

		coordenadas.add(new Coordenada(49,44,Color.WHITE));
		coordenadas.add(new Coordenada(50,44,Color.WHITE));
		coordenadas.add(new Coordenada(51,44,Color.WHITE));

		coordenadas.add(new Coordenada(50,43,Color.WHITE));
		coordenadas.add(new Coordenada(50,42,Color.WHITE));
		coordenadas.add(new Coordenada(50,41,Color.WHITE));
		coordenadas.add(new Coordenada(50,40,Color.WHITE));
		coordenadas.add(new Coordenada(50,39,Color.WHITE));
		coordenadas.add(new Coordenada(50,38,Color.WHITE));
		coordenadas.add(new Coordenada(50,37,Color.RED));		
	}
	
	

}
