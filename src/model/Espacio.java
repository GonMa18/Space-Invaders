package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Espacio extends Observable {
	private static Espacio miEspacio; // Singleton
	private int ancho;
	private int alto;
	private List<Nave> naves;
	
	//Constructora
    private Espacio() {
        this.ancho = 100;
        this.alto  = 60;
        this.naves   = new ArrayList<>();
        inicializar(); //Para que empiece directamente
    }
    
    public static Espacio getInstance() { //Singleton
		if (miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}
    private void notificarVista() {
    	setChanged();
    	notifyObservers();
    }

    // notificarObservadores() ya está heredado de Observable
    // notificarVista() puede llamar directamente al método del padre

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
    
    public List<Nave> getNaves() {
        return naves;
    }

    public Jugador getJugador() {
        for (Nave n : naves) {
            if (n instanceof Jugador) { // Si la nave es un jugador, lo devuelvo
                return (Jugador) n;
            }
        }
        return null;
    }

    public List<Enemigo> getEnemigos() {
        List<Enemigo> enemigos = new ArrayList<>(); // Creo una lista de enemigos
        for (Nave n : naves) {
            if (n instanceof Enemigo) { // Si la nave es un enemigo, lo añado a la lista de enemigos
                enemigos.add((Enemigo) n);  // Cast a Enemigo para añadirlo a la lista de enemigos
            }
        }
        return enemigos;
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
        Jugador j = getJugador();   //Reviso que el jugador siga vivo y tenga un disparo activo 
        if (j != null) {
            Disparo d = j.getDisparo(); 
            if (d != null && d.isShooting()) {  // Si el disparo sigue activo
                d.subir(); // Mueve el disparo un pixel hacia arriba
                comprobarMuertes(d); // Comprueba si ha dado a algun enemigo
            }
        }
        notificarVista();
    }

    // Metodo para movel los enemigos un pixel abajo --> CONTADOR 200ms
    public void actualizarEnemigos() {  
        for (Enemigo e : getEnemigos()) {	//Muevo todos los enem que siguen vivos
            if (e.sigueVivo()) {
                e.mover();
            }
        } //REVISAR
        notificarVista();
    }

    private void comprobarMuertes(Disparo d) { //Si el disparo le ha dado a algo
        for (Enemigo e : getEnemigos()) { //Reviso todos los enemigos que siguen vivos
            if (e.sigueVivo() && e.getX() == d.getX() && e.getY() == d.getY()) {    //Si el disparo ha dado a un enemigo
                e.setSigueJugando(false); // El enemigo muere
                d.setShoot(false);        // El disparo desaparece
                return; // Un disparo solo mata a un enemigo
            }
        }
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
        for (Enemigo e : getEnemigos()) {   //Reviso que no quede ningun enemigo vivo
            if (e.sigueVivo()) {
                return false; // Todavia queda algun enemigo vivo
            }
        }
        return true; // Todos los enemigos han muerto --> victoria
    }

    public void iniciarEspacio() { //Reiniciar el espacio para empezar un nuevo juego
    	
    	/// LLAMADA A INICIALIZAR PARA CREAR EL JUEGO
    	setChanged();
    	notifyObservers();
    	

    	
    }

}