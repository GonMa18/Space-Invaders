package model;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import javax.swing.Timer;

@SuppressWarnings("deprecation")
public class Espacio extends Observable {
	private static Espacio miEspacio; // Singleton
	private ArrayList<Enemigo> enemigos; // Lista de enemigos
	private Jugador jugador; // El jugador
	private int ancho;
	private int alto;
	//private List<Nave> naves;
	
	//TIMER//
	private Timer timer; // Timer para actualizar el juego
	private int frameCount; // Contador de frames para controlar la frecuencia de actualización
	
	//Constructora
    private Espacio() {
        this.ancho = 100;		//REVISAR
        this.alto  = 60;
        this.enemigos   = new ArrayList<>();
        //inicializar(); //Para que empiece directamente
    }
    
    public static Espacio getInstance() { //Singleton
		if (miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}
//    private void notificarVista() {
//    	setChanged();
//    	notifyObservers();
//    }

    // notificarObservadores() ya está heredado de Observable
    // notificarVista() puede llamar directamente al método del padre

	public int getAnchura() { 
    	return ancho; 
    }
    
    public int getAltura()  { 
    	return alto;  
    }
    
    public Jugador getJugador() {
		return jugador;
	}
    
    public ArrayList<Enemigo> getEnemigos() {
    	return enemigos;
    }
    
    
	public void cambiarAMain() {
    	inicializar(); // Para crear el juego al pasar a la pantalla principal
    	iniciarTimer(); // Para iniciar el timer que actualiza el juego
    	setChanged(); // Para notificar a la vista que el juego ha cambiado (se ha inicializado)
    	notifyObservers(); // Para que la vista se actualice
    }

    private void inicializar() {		// REVISAR RANDOM ENEMIGOS
        // Crear jugador
        jugador = new Jugador();
        
        //Limpiamos enemigos por si ya habia una partida anterior
        enemigos.clear();

        // Crear entre 4 y 8 enemigos en y=5 con x aleatoria (sin tocarse entre ellos)
        Random rn = new Random();
        int numEnemigos = 4 + rn.nextInt(5); // 4, 5, 6, 7 u 8
        List<Integer> posicionesUsadas = new ArrayList<>();
        for (int i = 0; i < numEnemigos; i++) {
            int x;
            do {
                x = rn.nextInt(ancho); // x aleatorio entre 0 y 99
            } while (posicionOcupadaOAdyacente(posicionesUsadas, x));
            posicionesUsadas.add(x);
            enemigos.add(new Enemigo(x, 5));
        }
    }
    
    ///////// TIMER ////////////////////
    private void iniciarTimer() {
		frameCount=0;	// Reiniciamos el contador de frames
		timer = new Timer(50,new ActionListener() {	//TICK cada 50ms --> DISPAROS
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarDisparo(); // Actualizamos el disparo cada 50ms
				frameCount++;
				
				// TICK cada 200ms = 4 TICKS de 50ms --> ENEMIGOS
				if (frameCount % 4 == 0) { // Cada 200ms (4*50ms)
					actualizarEnemigos(); // Actualizamos los enemigos cada 200ms
				}
				
				//Comprobar GameOver
				if (isGameOver() || isVictory()) {
					detenerTimer(); // Detenemos el timer si el juego ha terminado
					notificarVista(); // Notificamos a la vista para que muestre el FinishFrame
				}
			}
			
		}); timer.start(); // Iniciamos el timer
	}
    
    public void detenerTimer() {
		if (timer != null) {
			timer.stop(); // Detenemos el timer
			timer = null; // Liberamos la referencia al timer
		}
	}

    private void notificarVista() {	// Para notificar a la vista que el juego ha cambiado
		setChanged();
		notifyObservers();
	}
    
    // Comprueba si x coincide o es adyacente a alguna posicion ya usada
    private boolean posicionOcupadaOAdyacente(List<Integer> posiciones, int x) {
        for (int pos : posiciones) {
            if (Math.abs(pos - x) <= 1) { // misma posicion o a distancia 1
                return true;
            }
        }
        return false;
    }
    
 

    public void moverJugador(int x, int y) { //Mover Jugador
        
        if (jugador != null && jugador.sigueVivo()) { //Si el colega sigue vivo
            jugador.mover(x, y); //Me muevo a la nueva posicion
            notificarVista(); //Notifico a la vista
        }
    }

    public void disparar() {  //Disparar
        if (jugador != null && jugador.sigueVivo()) { 
            jugador.disparar();  //Disparo
            notificarVista(); //Notifico a la vista
        }
    }

    // Metodo para mover todos los disparos un pixel arriba --> TIMER 50ms
    public void actualizarDisparo() {
    	if (jugador ==null) return; // Si no hay jugador, no hay disparos que actualizar
        
        ArrayList<Disparo> disparos = jugador.getDisparos();

//        if (disparo.isShooting()) {
//        	disparo.subir(); // Mueve el disparo un pixel hacia arriba
//            comprobarMuertes(disparo); // Comprueba si ha dado a algun enemigo
//        }
        
        for (Disparo disparo : disparos) {
            if (disparo.isShooting()) {
                disparo.subir();	//Mueve el disparo un pixel hacia arriba
                // Si el disparo sale de la pantalla, desactivarlo
                comprobarMuertes(disparo);
                }
            }
        jugador.limpiarDisparos(); // Elimina los disparos que ya no están activos
        notificarVista();
    }

    // Metodo para movel los enemigos un pixel abajo --> TIMER 200ms
    public void actualizarEnemigos() {  
    	ArrayList<Disparo> disparos = jugador.getDisparos();
    	
        for (Enemigo e : enemigos) {	//Muevo todos los enem que siguen vivos
            if (e.sigueVivo()) {
                //e.bajar(); // Baja un pixel
            	e.mover(1,0); // Mueve un pixel hacia abajo (y+1)
            	for (Disparo disparo : disparos) {
            		if(disparo.isShooting() && disparo !=null) {
                		comprobarMuertes(disparo); // Comprueba si el disparo ha dado a algun enemigo
                	}
            	}
            	
            }
        }
        notificarVista();
    }

    private void comprobarMuertes(Disparo d) { //Si el disparo le ha dado a algo
        for (Enemigo e : enemigos) { //Reviso todos los enemigos que siguen vivos
            if (e.sigueVivo() && e.getX() == d.getX() && Math.abs(e.getY() - d.getY()) <= 1) {    //Si el disparo ha dado a un enemigo (tolerancia de un pixel)
                e.setSigueJugando(false); // El enemigo muere
                d.setShoot(false);        // El disparo desaparece
                notificarVista(); 
            }
        }
    }

  
    public boolean isGameOver() { //Enemigos llegan a la fila del jugador o muere
        
        if (jugador == null || !jugador.sigueVivo()) return true; //Reviso que haya muerto el jugador
        												//Si sigue vivo:
        for (Enemigo e : enemigos) { //Paso a revisar donde estan los enemigos
            if (e.sigueVivo() && e.getY() >= jugador.getY()) return true; //Han llegado a la fila del jugador
        }
        return false; //Sino, el juego continua
    }

    public boolean isVictory() {
        for (Enemigo e : enemigos) {   //Reviso que no quede ningun enemigo vivo
            if (e.sigueVivo()) {
                return false; // Todavia queda algun enemigo vivo
            }
        }
        return true; // Todos los enemigos han muerto --> victoria
    }

//    public void iniciarEspacio() { //Reiniciar el espacio para empezar un nuevo juego
//    	
//    	/// LLAMADA A INICIALIZAR PARA CREAR EL JUEGO
//    	setChanged();
//    	notifyObservers();
//    	
//
//    	
//    }

}