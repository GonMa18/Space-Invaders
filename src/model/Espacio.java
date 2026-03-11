package model;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import javax.swing.Timer;

import viewControl.FinishFrame;
import viewControl.MainFrame;

@SuppressWarnings("deprecation")
public class Espacio extends Observable {
	private static Espacio miEspacio; 			// Singleton
	private ArrayList<Enemigo> enemigos; 		// Lista de enemigos
	private Jugador jugador; 					// El jugador
	private int ancho;
	private int alto;
	
	////TIMER////
	private Timer timer; 						// Timer para actualizar el juego
	private int ticks; 					// Contador de frames para controlar la frecuencia de actualización
	
    private Espacio() {
        this.ancho = 100;		
        this.alto  = 60;
        this.enemigos   = new ArrayList<>();
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public static Espacio getInstance() { 		//Singleton
		if (miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public int getAnchura() { 
    	return ancho; 
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public int getAltura()  { 
    	return alto;  
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
     
    public Jugador getJugador() {
		return jugador;
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public ArrayList<Enemigo> getEnemigos() {
    	return enemigos;
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
	public void cambiarAMain() {
    	inicializar(); 					// Para crear el juego al pasar a la pantalla principal
    	iniciarTimer(); 				// Para iniciar el timer que actualiza el juego
    	setChanged(); 					// Para notificar a la vista que el juego ha cambiado (se ha inicializado)
    	notifyObservers("iniciar"); 				// Para que la vista se actualice
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    private void inicializar() {		
        jugador = new Jugador();
        
        enemigos.clear();				//Limpiamos enemigos por si ya habia una partida anterior

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
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    public int[][] generarMatriz() {
    	
    	//TODO -- Revisar bien
    	
    	int[][] matriz;
    	boolean jug= false;
    	boolean enem=false;
    	boolean disp=false;
    	int contE = 0;
    	int contD = 0;
    	
    	//Recorremos la matriz
    	matriz = new int[alto][ancho];
    	for (int i=0; i<alto; i++) {
			for (int j=0; j<ancho; j++) {
				
				if (!jug && matriz[i][j] == jugador.x && matriz[i][j] == jugador.y ) {
					matriz[i][j] = 1; // Jugador
					jug=true;
					
				} else if (!enem){
					for (Enemigo e: enemigos) {
						if (matriz[i][j] == e.x && matriz[i][j] == e.y) {
							matriz[i][j] = 2; // Enemigo
						}
					}contE++;
					
				} else if (	!jug && !enem) {
					for (Disparo d: jugador.getDisparos()) {
						if (matriz[i][j] == d.getX() && matriz[i][j] == d.getY()) {
							matriz[i][j] = 3; // Disparo
						}
					}disp=true;
					
					//TODO -- Disparo seguramente será mas complejo que esto
				
				} else {
					matriz[i][j] = 0; // Espacio vacío
				}
			}
		}
    	notificarVista(matriz);
    	return matriz;
    }
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    

    private void notificarVista(Object arg) {							// Para notificar a la vista que el juego ha cambiado
		setChanged();
		notifyObservers(arg);
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
 
    
    // Comprueba si x coincide o es adyacente a alguna posicion ya usada
    private boolean posicionOcupadaOAdyacente(List<Integer> posiciones, int x) {
        for (int pos : posiciones) {
            if (Math.abs(pos - x) <= 1) { 					// misma posicion o a distancia 1
                return true;
            }
        }
        return false;
    }
    
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public void moverJugador(int x, int y) { 
        if (jugador != null && jugador.sigueVivo()) { //Si el colega sigue vivo
            jugador.mover(x, y); //Me muevo a la nueva posicion
            
            notificarVista(generarMatriz()); //Aqui hay que pasarle la matriz
        }
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public void disparar() {  
        if (jugador != null && jugador.sigueVivo()) { 
            jugador.disparar();  
            
            notificarVista(generarMatriz()); //Aqui hay que pasarle la matriz
        }
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    // Metodo para mover todos los disparos un pixel arriba --> TIMER 50ms
    public void actualizarDisparo() {
    	if (jugador ==null) return; 								
        
        ArrayList<Disparo> disparos = jugador.getDisparos();

        
        for (Disparo disparo : disparos) {
            if (disparo.isShooting()) {
                disparo.subir();	
                comprobarMuertes(disparo);
                }
            }
        jugador.limpiarDisparos(); 				// Elimina los disparos que ya no están activos
        
        notificarVista(generarMatriz()); //Aqui hay que pasarle la matriz
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    // Metodo para movel los enemigos un pixel abajo --> TIMER 200ms
    public void actualizarEnemigos() {  
    	ArrayList<Disparo> disparos = jugador.getDisparos();
    	
        for (Enemigo e : enemigos) {								//Muevo todos los enem que siguen vivos
            if (e.sigueVivo()) {
            	e.mover(1,0); 										// Mueve un pixel hacia abajo (y+1)
            	for (Disparo disparo : disparos) {
            		if(disparo.isShooting() && disparo !=null) {
                		comprobarMuertes(disparo); 					// Comprueba si el disparo ha dado a algun enemigo
                	}
            	}
            	
            }
        }
        notificarVista(generarMatriz()); //Aqui hay que pasarle la matriz
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    private void comprobarMuertes(Disparo d) { 
        for (Enemigo e : enemigos) { 						
            if (e.sigueVivo() && e.getX() == d.getX() && Math.abs(e.getY() - d.getY()) <= 1) {    	//Si el disparo ha dado a un enemigo (tolerancia de un pixel)
                e.setSigueJugando(false); 															// El enemigo muere
                d.setShoot(false);    

                notificarVista(generarMatriz()); //Aqui hay que pasarle la matriz
            }
        }
    }

    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public boolean isGameOver() { 											//Enemigos llegan a la fila del jugador o muere
        
        if (jugador == null || !jugador.sigueVivo()) return true; 			//Reviso que haya muerto el jugador
        																	//Si sigue vivo:
																			//Paso a revisar donde estan los enemigos

        for (Enemigo e : enemigos) {
            if (e.sigueVivo()) {
                // Si un enemigo llega al final del espacio (fila del jugador o más abajo)
                if (e.getY() >= jugador.getY()) {
                    jugador.setSigueJugando(false);
                    
                     						// Notifico a la vista que el jugador ha perdido
                    return true;
                    }
            }
            
        }   
        notificarVista("perder");
        return false;
    }

    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public boolean isVictory() {
        for (Enemigo e : enemigos) {   						//Reviso que no quede ningun enemigo vivo
            if (e.sigueVivo()) {
                return false; 								// Todavia queda algun enemigo vivo
            }
        }
        notificarVista("ganar");
        return true; 										// Todos los enemigos han muerto --> victoria

    }
    
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	// =====================================================================
	// 								TIMER
	// =====================================================================    
    
    private void iniciarTimer() {
		ticks=0;										// Reiniciamos el contador de frames
		timer = new Timer(50,new ActionListener() {			//TICK cada 50ms --> DISPAROS
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarDisparo(); 						// Actualizamos el disparo cada 50ms
				ticks++;
				
				// TICK cada 200ms = 4 TICKS de 50ms --> ENEMIGOS
				
				if (ticks % 4 == 0) { 					// Cada 200ms (4*50ms)
					actualizarEnemigos(); 					// Actualizamos los enemigos cada 200ms
				}
				
				//Comprobar GameOver
				if (isGameOver() || isVictory()) {
					detenerTimer(); 						// Detenemos el timer si el juego ha terminado
		            
					notificarVista(generarMatriz()); //Aqui hay que pasarle la matriz
				}
			}
			
		}); timer.start(); 									// Iniciamos el timer
	}
    
    public void detenerTimer() {
		if (timer != null) {
			timer.stop(); 									// Detenemos el timer
			timer = null; 									// Liberamos la referencia al timer
		}
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
}