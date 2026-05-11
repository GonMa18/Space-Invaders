package model;

import java.util.ArrayList;

public interface StrategyBala {
	public ArrayList<Disparo> disparar(int x, int y);
	public int getDaño();
}
