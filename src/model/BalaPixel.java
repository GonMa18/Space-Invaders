package model;

import java.awt.Color;
import java.util.ArrayList;

public class BalaPixel implements StrategyBala{
	
	@Override
	public ArrayList<Disparo> disparar(int x, int y) {
		//ArrayList<Coordenada> forma = new ArrayList<>();
		//forma.add(new Coordenada(0, 0, Color.YELLOW));
		Disparo d = new Disparo(x, y);			//Un unico pixel amarillo
		d.addPixel(new Coordenada(x, y, Color.YELLOW));
		d.setStrategyBala(this);
		d.setShoot(true);
		ArrayList<Disparo> disparos = new ArrayList<>();
		disparos.add(d);
		return disparos;
	}

	@Override
	public int getDaño() {
		return 10;
	}
}

