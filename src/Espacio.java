import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Espacio extends Observable {
	
	private int ancho;
	private int alto;
	private List<Nave> naves;
	
	//Constructora
    public Espacio(int ancho, int alto) {
        this.ancho = ancho;
        this.alto  = alto;
        this.naves   = new ArrayList<>();
        inicializar(); //Para que empiece directamente
    }
    
    private void notificarVista() {
        notificarObservadores();		//REVISAR
    }

    private void notificarObservadores() {
		// TODO							  //REVISAR
		
	}

	public int getAnchura() { 
    	return ancho; 
    }
    
    public int getAltura()  { 
    	return alto;  
    }

    private void inicializar() {
        // Crear jugador
        naves.add(new Jugador()); 		//PORQUE ME DA ERROR
        //El jugador se crea por defecto en (,)

        // Crear enemigo aleatorio
        Random rn = new Random();	//Random de la libreria Random de JAVA
        int pos_x = rn.nextInt(ancho);   // x aleatorio entrte 0 y 99 
        int pos_y = rn.nextInt(5);      // y en la parte de arriba del espacio 0 a 4
        naves.add(new Enemigo(pos_x, pos_y));
    }
    
    public List<Naves> getNaves() {
        return naves;
    }

    public Jugador getJugador() {
        //TODO
    }

    public List<Enemigo> getEnemigos() {
        //TODO
    }

    public void moverJugador(int x, int y) { //Mover Jugador
        Jugador j = getJugador();
        if (j != null && j.sigueVivo()) { //Si el colega sigue vivo
            j.mover(x, y); //Me muevo a la nueva posicion
            notificarVista(); //Notifico a la vista
        }
    }

    public void disparar() {  //Disparar
        Jugador j = getJugador();
        if (j != null && j.sigueVivo()) { 
            j.disparar();  //Disparo
            notificarVista(); //Notifico a la vista
        }
    }

    // Metodo para movel el disparo un pixel arriba --> CONTADOR
    public void actualizarDisparo() {
        //TODO
    	notificarVista();
      
    }

    // Metodo para movel los enemigos un pixel abajo --> CONTADOR
    public void actualizarEnemigos() {
        for (Enemigo e : getEnemigos()) {	//Muevo todos los enem que siguen vivos
            if (e.sigueVivo()) {
                e.mover();
            }
        } //REVISAR
        notificarVista();
    }

    private void comprobarMuertes(Disparo d) { //Si el disparo le ha dado a algo
        //TODO
    }

  
    public boolean isGameOver() { //Enemigos llegan a la fila del jugador o muere
        Jugador j = getJugador();
        if (j == null || !j.sigueVivo()) return true; //Reviso que haya muerto el jugador
        												//Si sigue vivo:
        for (Enemigo e : getEnemigos()) { //Paso a revisar donde estan los enemigos
            if (e.sigueVivo() && e.getY() >= j.getY()) return true; //Han llegado a la fila del jugador
        }
        return false; //Sino, el juego continua
    }

    public boolean isVictory() {
        //TODO
    }

    

}