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
	
	
	//Crea una nave según el tipo de nave que queramos
	public Nave crearNave(int x, int y, String tipo) { 

		switch (tipo) {
			
			case "azul":
				return new Azul(x,y);	//TODO clase nave azul
			case "verde":
				return new Verde(x,y);	//TODO clase nave verde
			case "rojo":
				return new Rojo(x,y);
			case "enemigo":
				return new Enemigo(x,y);
				case "final boss":
				case "finalboss":
					return new FinalBoss(x,y);
			default:
				return null; 
		}
	}

}
