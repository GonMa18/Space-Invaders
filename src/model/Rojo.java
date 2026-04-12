package model;

public class Rojo extends Nave {
	
	private BalaFlecha flecha = new BalaFlecha();
	private BalaRombo rombo = new BalaRombo();
	private BalaPixel pixel = new BalaPixel();

	// Hace @Override del metodo iniciarCuerpo() para definir la forma de la nave

	public Rojo(int x, int y) {
		super(x, y);
		inicializarNave();

	}

	@Override
	public void pintarCuerpo() {
		int bx = x;
		int by = y;
		anadirComponente(new Pixel(bx + 1, by));
		anadirComponente(new Pixel(bx, by + 1));
		anadirComponente(new Pixel(bx + 1, by + 1));
		anadirComponente(new Pixel(bx + 2, by + 1));
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
			{ x + 1, y },
			{ x, y + 1 },
			{ x + 1, y + 1 },
			{ x + 2, y + 1 }
		};
	}

	@Override
	public ArrayList<StrategyDisparo> getEstrategiasPermitidas() { //TODO revisar
		ArrayList<StrategyDisparo> estrategias = new ArrayList<StrategyDisparo>();
		estrategias.add(estrategiaPixel);
		estrategias.add(estrategiaFlecha);
		estrategias.add(estrategiaRombo);
		return estrategias;
	}
}

}
