package model;

public class Verde extends Nave {
	
	private BalaPixel pixel = new BalaPixel();	
	private BalaFlecha flecha = new BalaFlecha();

	// Hace @Override del metodo iniciarCuerpo() para definir la forma de la nave

	public Verde(int x, int y) {
		super(x, y);	//es de tipo verde TODO
		inicializarNave(); //TODO

	}
	
	@Override
	public void pintarCuerpo() {//TODO
		int bx = x;
		int by = y;
		anadirComponente(new Pixel(bx, by));
		anadirComponente(new Pixel(bx + 2, by));
		anadirComponente(new Pixel(bx, by + 1));
		anadirComponente(new Pixel(bx + 1, by + 1));
		anadirComponente(new Pixel(bx + 2, by + 1));
		anadirComponente(new Pixel(bx - 1, by + 2));
		anadirComponente(new Pixel(bx, by + 2));
		anadirComponente(new Pixel(bx + 1, by + 2));
		anadirComponente(new Pixel(bx + 2, by + 2));
		anadirComponente(new Pixel(bx + 3, by + 2));
	}
	
	@Override
	protected int disparoX() {
		return x + 2;
	}

	@Override
	protected int disparoY() {
		return y - 3;
	}

	@Override
	public int[][] celdasOcupadas() { //TODO
		return new int[][] {
			{ x, y },
			{ x + 2, y },
			{ x, y + 1 },
			{ x + 1, y + 1 },
			{ x + 2, y + 1 },
			{ x - 1, y + 2 },
			{ x, y + 2 },
			{ x + 1, y + 2 },
			{ x + 2, y + 2 },
			{ x + 3, y + 2 },
		};
	}

	@Override
	public ArrayList<StrategyDisparo> getEstrategiasPermitidas() {	//TODO
		
		ArrayList<StrategyDisparo> estrategias = new ArrayList<StrategyDisparo>();
		estrategias.add(estrategiaPixel);
		estrategias.add(estrategiaFlecha);
		return estrategias;
	}
}
	

	

}
