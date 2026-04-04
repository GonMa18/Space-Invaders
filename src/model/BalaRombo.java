package model;

import java.awt.Color;
import java.util.ArrayList;

public class BalaRombo implements StrategyBala {
	
	@Override
	public ArrayList<Disparo> disparar(int x, int y){
		ArrayList<Coordenada> forma = new ArrayList<>();
		//Fila de arriba
		forma.add(new Coordenada(0, -2, Color.YELLOW));
		//Segunda fila
		forma.add(new Coordenada(-1, -1, Color.YELLOW));
		forma.add(new Coordenada(0, -1, Color.YELLOW));
		forma.add(new Coordenada(1, -1, Color.YELLOW));
		//Fila cental
		forma.add(new Coordenada(-2, 0, Color.YELLOW));
		forma.add(new Coordenada(-1, 0, Color.YELLOW));
		forma.add(new Coordenada(0, 0, Color.YELLOW));
		forma.add(new Coordenada(1, 0, Color.YELLOW));
		forma.add(new Coordenada(2, 0, Color.YELLOW));
		//Cuarta fila
		forma.add(new Coordenada(-1, 1, Color.YELLOW));
		forma.add(new Coordenada(0, 1, Color.YELLOW));
		forma.add(new Coordenada(1, 1, Color.YELLOW));
		//Ultima fila
		forma.add(new Coordenada(0, 2, Color.YELLOW));
		Disparo d = new Disparo(x, y, forma);			//Un rombo amarillo
		d.setShoot(true);
		ArrayList<Disparo> disparos = new ArrayList<>();
		disparos.add(d);
		return disparos;
	}

}
