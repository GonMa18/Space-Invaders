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
	public Jugador crearJugador(String tipo) { 
		
		int x= 50;
		int y=55;
//		if (tipo==null) {
//			return new Rojo(x,y); //Si no se especifica el tipo, por defecto es rojo
//		}
		
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
