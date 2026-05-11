package model;

import java.awt.Color;
import java.util.ArrayList;

public class BalaFlecha implements StrategyBala {
	
	@Override
	public ArrayList<Disparo> disparar(int x, int y){
		ArrayList<Coordenada> forma = new ArrayList<>();
		forma.add(new Coordenada(0, 0, Color.YELLOW));
		forma.add(new Coordenada(-1, 1, Color.YELLOW));
		forma.add(new Coordenada(1, 1, Color.YELLOW));
		Disparo d = new Disparo(x, y);			//Una flecha amarilla
		for (Coordenada c : forma) {
			d.addPixel(new Coordenada(x + c.getX(), y + c.getY(), Color.YELLOW));
		}
		d.setStrategyBala(this);
		d.setShoot(true);
		ArrayList<Disparo> disparos = new ArrayList<>();
		disparos.add(d);
		return disparos;
	}

	@Override
	public int getDaño() {
		return 25;
	}
}

