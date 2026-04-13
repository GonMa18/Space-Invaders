package model;

public class FactoryNaves {
	
	//Crea naves según el tipo
	
	private static FactoryNaves miFactory = null;
	
	private FactoryNaves() { }
	
	public static FactoryNaves getFactory() {
		if (miFactory == null) {
			miFactory = new FactoryNaves();
		}
		return miFactory;
	}
	
	
	//Crea un jugador según el tipo de nave que queramos
	public Jugador crearJugador(int x, int y, String tipo) { 

		switch (tipo) {
			
			case "azul":
				return new Azul(x,y);	//TODO clase nave azul
			case "verde":
				return new Verde(x,y);	//TODO clase nave verde
			case "rojo":
				return new Rojo(x,y);
			
			default:
				return new Rojo(x,y); 
		}
	}

}
