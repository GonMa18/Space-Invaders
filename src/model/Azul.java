package model;

public class Azul extends Nave {
	
	private BalaPixel pixel = new BalaPixel();
	private BalaRombo rombo = new BalaRombo();
	

	// Hace @Override del metodo iniciarCuerpo() para definir la forma de la nave

	public Azul(int x, int y) {
		super(x, y);
		inicializarNave();
		

	}
	

	@Override
	public void pintarCuerpo() {
		int bx = x;
		int by = y;
		anadirComponente(new Pixel(bx, by));
		anadirComponente(new Pixel(bx + 2, by));
		anadirComponente(new Pixel(bx, by + 1));
		anadirComponente(new Pixel(bx + 1, by + 1));
		anadirComponente(new Pixel(bx + 2, by + 1));
		anadirComponente(new Pixel(bx, by + 2));
		anadirComponente(new Pixel(bx + 1, by + 2));
		anadirComponente(new Pixel(bx + 2, by + 2));
	}

	@Override
	protected int disparoX() {
		return x + 1;
	}

	@Override
	protected int disparoY() {
		return y - 3;
	}

	@Override
	public int[][] celdasOcupadas() {
		return new int[][] {
			{ x, y },
			{ x + 2, y },
			{ x, y + 1 },
			{ x + 1, y + 1 },
			{ x + 2, y + 1 },
			{ x, y + 2 },
			{ x + 1, y + 2 },
			{ x + 2, y + 2 }
		};
	}

	@Override
	public ArrayList<StrategyDisparo> getEstrategiasPermitidas() {
		ArrayList<StrategyDisparo> estrategias = new ArrayList<StrategyDisparo>();
		estrategias.add(estrategiaPixel);
		estrategias.add(estrategiaRombo);
		return estrategias;
	}
}

	

}
