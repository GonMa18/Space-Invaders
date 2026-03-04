import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//REVISAR BIEN PORQUE LO HE HECHO CON AYUDA DE CLAUDE

/**
 * CONTROLLER (Controlador) - Patron MVC
 * 
 * Es el intermediario entre el Modelo (Espacio) y la Vista (MainFrame).
 * Se encarga de:
 *   1. Crear y conectar el modelo con la vista
 *   2. Gestionar el bucle del juego (Timers para enemigos y disparo)
 *   3. Recibir las acciones del usuario (mover, disparar) y delegarlas al modelo
 *   4. Comprobar si la partida ha terminado (victoria o derrota)
 */
public class Controller {
	
	// ===================== ATRIBUTOS =====================
	
	private Espacio modelo; 			// El modelo: contiene toda la logica del juego
	
	// NOTA: Falta la referencia a la vista (MainFrame).
	// Cuando MainFrame este implementado con WindowBuilder, añadir:
	// private MainFrame vista;
	
	// Timers para el bucle del juego (javax.swing.Timer ejecuta en el hilo de Swing)
	private Timer timerEnemigos; 		// Mueve los enemigos hacia abajo cada X ms
	private Timer timerDisparo;  		// Mueve el disparo hacia arriba cada X ms
	
	// Constantes de tiempo (en milisegundos)
	private static final int INTERVALO_ENEMIGOS = 200; 	// Cada 200ms los enemigos bajan un pixel
	private static final int INTERVALO_DISPARO  = 50;  	// Cada 50ms el disparo sube un pixel (mas rapido que los enemigos)
	
	// Dimensiones del tablero por defecto
	private static final int ANCHO_TABLERO = 100;
	private static final int ALTO_TABLERO  = 60;
	

	// ===================== CONSTRUCTORA =====================
	
	/**
	 * Crea el controlador: inicializa el modelo y prepara los timers.
	 * 
	 * NOTA: Cuando MainFrame este listo, recibir la vista como parametro
	 *       o crearla aqui y registrarla como Observer del modelo.
	 *       Ejemplo: modelo.addObserver(vista);
	 */
	public Controller() {
		// Crear el modelo (el "espacio" del juego con sus naves)
		this.modelo = new Espacio(ANCHO_TABLERO, ALTO_TABLERO);
		
		// NOTA: Aqui se conectaria la vista al modelo como observador:
		// this.vista = new MainFrame(this);
		// modelo.addObserver(vista);
		
		// Inicializar los timers (pero NO arrancarlos todavia)
		inicializarTimers();
	}
	

	// ===================== TIMERS (BUCLE DEL JUEGO) =====================
	
	/**
	 * Crea los dos timers del juego:
	 * - timerEnemigos: cada 200ms mueve los enemigos un pixel hacia abajo
	 * - timerDisparo:  cada 50ms mueve el disparo un pixel hacia arriba
	 * 
	 * Ambos comprueban si la partida ha terminado despues de cada actualizacion.
	 */
	private void inicializarTimers() {
		
		// Timer para mover los enemigos hacia abajo periodicamente
		timerEnemigos = new Timer(INTERVALO_ENEMIGOS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.actualizarEnemigos(); 	// Mueve todos los enemigos vivos un pixel abajo
				comprobarFinPartida(); 			// Reviso si alguien ha ganado/perdido
			}
		});
		
		// Timer para mover el disparo hacia arriba periodicamente
		timerDisparo = new Timer(INTERVALO_DISPARO, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.actualizarDisparo(); 	// Mueve el disparo un pixel arriba y comprueba impactos
				comprobarFinPartida(); 			// Reviso si alguien ha ganado/perdido
			}
		});
	}
	

	// ===================== CONTROL DE LA PARTIDA =====================
	
	/**
	 * Arranca la partida: activa los timers para que el juego empiece a funcionar.
	 * Llamar a este metodo cuando el usuario pulse "Jugar" en StartFrame.
	 */
	public void iniciarPartida() {
		timerEnemigos.start(); 	// Los enemigos empiezan a bajar
		timerDisparo.start();  	// El disparo empieza a moverse (si hay alguno activo)
	}
	
	/**
	 * Para los timers y gestiona el fin de la partida.
	 * Muestra la pantalla de fin (FinishFrame) con victoria o derrota.
	 */
	private void finalizarPartida() {
		timerEnemigos.stop();
		timerDisparo.stop();
		
		// Comprobar si ha sido victoria o derrota
		if (modelo.isVictory()) {
			System.out.println("¡VICTORIA! Has destruido todos los enemigos.");
			// NOTA: Aqui se mostraria FinishFrame con mensaje de victoria
			// new FinishFrame("¡Has ganado!").setVisible(true);
		} else {
			System.out.println("GAME OVER. Los enemigos te han alcanzado.");
			// NOTA: Aqui se mostraria FinishFrame con mensaje de derrota
			// new FinishFrame("Game Over").setVisible(true);
		}
	}
	
	/**
	 * Comprueba si la partida ha terminado (por victoria o derrota).
	 * Si es asi, llama a finalizarPartida().
	 */
	private void comprobarFinPartida() {
		if (modelo.isGameOver() || modelo.isVictory()) {
			finalizarPartida();
		}
	}
	
	/**
	 * Reinicia la partida: crea un nuevo modelo y rearranca los timers.
	 * Util para el boton "Volver a jugar" en FinishFrame.
	 */
	public void reiniciarPartida() {
		timerEnemigos.stop();
		timerDisparo.stop();
		
		// Crear un modelo nuevo (nuevo espacio con nuevas naves)
		this.modelo = new Espacio(ANCHO_TABLERO, ALTO_TABLERO);
		
		// NOTA: Reconectar la vista como observador del nuevo modelo:
		// modelo.addObserver(vista);
		
		// Arrancar de nuevo
		iniciarPartida();
	}
	

	// ===================== ACCIONES DEL USUARIO =====================
	// Estos metodos seran llamados por la Vista (MainFrame) cuando el
	// usuario pulse teclas o haga clic. El Controller delega al Modelo.
	
	/**
	 * Mueve el jugador a la posicion (x, y).
	 * Se llama desde MainFrame cuando el usuario pulsa las teclas de movimiento.
	 * @param x nueva coordenada x del jugador
	 * @param y nueva coordenada y del jugador
	 */
	public void moverJugador(int x, int y) {
		modelo.moverJugador(x, y);
	}
	
	/**
	 * El jugador dispara.
	 * Se llama desde MainFrame cuando el usuario pulsa la tecla de disparo (ej: espacio).
	 */
	public void disparar() {
		modelo.disparar();
	}
	

	// ===================== GETTERS =====================
	// Para que la Vista pueda consultar el estado del modelo si lo necesita
	
	/**
	 * Devuelve el modelo (Espacio) para que la vista pueda leer su estado.
	 * @return el espacio del juego con todas las naves
	 */
	public Espacio getModelo() {
		return modelo;
	}
	
}
