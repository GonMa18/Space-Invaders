package model;

import java.util.ArrayList;

public class Disparo {
	
	private int x;
	private int y;
	private boolean shoot;
	private Composite cuerpo;
	private StrategyBala strategyBala;
	//private ArrayList<Coordenada> forma;
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public Disparo(int x, int y/*, ArrayList<Coordenada> forma*/) {
		this.x = x;
		this.y = y;
		this.shoot = false;
		this.strategyBala = null;
		//this.forma = forma;
		this.cuerpo = new Composite(x, y);
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public int getX() {
		return x;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public int getY() {
		return y;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public boolean isShooting() {
		return shoot;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public void setShoot(boolean b) {
		this.shoot = b;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public void setStrategyBala(StrategyBala strategy) {
		this.strategyBala = strategy;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public int getDaño() {
		if (strategyBala != null) {
			return strategyBala.getDaño();
		}
		return 10; // Daño por defecto (BalaPixel)
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public void subir() { 	
		if (shoot) {	
			cuerpo.mover(0, -1);				//Cuando se dispare
			this.y--;
			
			if (y<0) {			//Cuando desaparezca del frame
				shoot = false;
			}
		}
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
	public ArrayList<Component> getPixeles(){
		return cuerpo.getPixeles();
	}

	public void addPixel(Coordenada coordenada) {
		this.cuerpo.addPixel(coordenada);
	}
	
   //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
}
	
