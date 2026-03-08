package viewControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Enemigo;
import model.Espacio;
import model.Jugador;
import model.Nave;

public class MainFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Tamaño de la matriz logica (100 columnas x 60 filas)
	private static final int COLUMNAS = 100;
	private static final int FILAS = 60;
	// Tamaño en pixeles de cada celda de la matriz
	private static final int TAM_CELDA = 5; // 100*5=500px ancho, 60*5=300px alto

	// Teclas actualmente pulsadas (para permitir varias a la vez)
	private final Set<Integer> teclasPresionadas = new HashSet<>();

	// =====================================================================
	// SISTEMA A: Disparo por TICKS (sincronizado con proyectiles)
	// =====================================================================

	private int ticksDesdeUltimoDisparo = 0;
	private static final int TICKS_ENTRE_DISPAROS = 4; // disparar cada N ticks (N*50ms)

	// =====================================================================
	// SISTEMA B: Disparo por COOLDOWN (basado en milisegundos)
	// =====================================================================

	// private long ultimoDisparo = 0;
	// private static final long COOLDOWN_DISPARO = 175; // ms entre disparos

	public MainFrame() {
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// FONDO //
		ImageIcon iconoFondo = new ImageIcon("Resources/Fondo.png");
		Image imagenFondo = iconoFondo.getImage();

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Pintar fondo
				g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
				// Pintar la matriz del juego
				pintarMatriz(g);
			}
		};
		contentPane.setPreferredSize(new Dimension(COLUMNAS * TAM_CELDA, FILAS * TAM_CELDA));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Registrarse como observador del modelo
		Espacio.getInstance().addObserver(this);

		pack();
		setLocationRelativeTo(null); // Centrar en pantalla
		setVisible(true);
		requestFocusInWindow(); // Para que reciba eventos de teclado

		// =====================================================================
		// SISTEMA A: Disparo por TICKS (sincronizado con proyectiles)
		// =====================================================================

		addKeyListener(new KeyListener() {	//Para detectar las teclas pulsadas
			@Override
			public void keyPressed(KeyEvent e) {
				teclasPresionadas.add(e.getKeyCode());
				if (e.getKeyCode() == KeyEvent.VK_SPACE && ticksDesdeUltimoDisparo >= TICKS_ENTRE_DISPAROS) {
					Espacio.getInstance().disparar();
					ticksDesdeUltimoDisparo = 0;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				teclasPresionadas.remove(e.getKeyCode());
			}
			@Override public void keyTyped(KeyEvent e) {}
		});

		// =====================================================================
		// SISTEMA B: Disparo por COOLDOWN (basado en milisegundos)
		// =====================================================================

		// addKeyListener(new KeyListener() {
		// 	@Override
		// 	public void keyPressed(KeyEvent e) {
		// 		teclasPresionadas.add(e.getKeyCode());
		// 		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		// 			intentarDisparar();
		// 		}
		// 	}
		// 	@Override
		// 	public void keyReleased(KeyEvent e) {
		// 		teclasPresionadas.remove(e.getKeyCode());
		// 	}
		// 	@Override public void keyTyped(KeyEvent e) {}
		// });

		// TIMER - Movimiento continuo del jugador cada 50ms //
		Timer timerMovimiento = new Timer(50, ev -> {	// Llamado cada 50ms para mover al jugador según las teclas presionadas
			moverJugador();
		});
		timerMovimiento.start();	// Iniciar el timer de movimiento del jugador


		
		////////////////////////////// TIMER  JUGADOR Y DISPAROS	///////////////////////////////////////
		
		// =====================================================================
		// SISTEMA A: Disparo por TICKS (sincronizado con proyectiles)
		// =====================================================================

		Timer[] timerProyectiles = new Timer[1]; // Array para poder referenciarlo dentro del lambda
		timerProyectiles[0] = new Timer(50, ev -> {	// Llamado cada 50ms para actualizar proyectiles y manejar disparos
			Espacio.getInstance().actualizarDisparo(); // 1. Mover proyectiles
			ticksDesdeUltimoDisparo++;                  // 2. Contar tick
			if (teclasPresionadas.contains(KeyEvent.VK_SPACE)
					&& ticksDesdeUltimoDisparo >= TICKS_ENTRE_DISPAROS) {	// Intentar disparar si se mantiene espacio y ha pasado el tiempo
				Espacio.getInstance().disparar();       // 3. Disparar
				ticksDesdeUltimoDisparo = 0;            // 4. Reset contador
			}
			
			if(Espacio.getInstance().isVictory()) {	// Si se ha ganado el juego
				timerProyectiles[0].stop();	// Detener timer de proyectiles
				timerMovimiento.stop();	// Detener timer de movimiento del jugador
				//timerEnemigos[0].stop();
				dispose(); // Cierra la ventana del juego
				new FinishFrame("Win").setVisible(true); // Abre la ventana de fin de juego --> VICTORIA
			}
		});
		timerProyectiles[0].start();	// Iniciar el timer de proyectiles

		// =====================================================================
		// SISTEMA B: Disparo por COOLDOWN (basado en milisegundos)
		// =====================================================================

		// Timer timerDisparoJugador = new Timer(50, ev -> {
		// 	if (teclasPresionadas.contains(KeyEvent.VK_SPACE)) {
		// 		intentarDisparar();
		// 	}
		// });
		// timerDisparoJugador.start();
		//
		// Timer timerDisparo = new Timer(50, ev -> {
		// 	Espacio.getInstance().actualizarDisparo();
		// });
		// timerDisparo.start();
		
		////////////////////////////// TIMER  ENEMIGOS	///////////////////////////////////////

		// Mover los enemigos hacia abajo cada 200ms
		Timer[] timerEnemigos = new Timer[1]; // Array para poder referenciarlo dentro del lambda
		timerEnemigos[0] = new Timer(200, ev -> {	// Llamado cada 200ms para mover enemigos y verificar condiciones de juego
			Espacio.getInstance().actualizarEnemigos(); // Mover enemigos hacia abajo
			if(Espacio.getInstance().isGameOver()) {	// Si se ha perdido el juego (enemigo llegó al fondo o colisionó con jugador)
				timerEnemigos[0].stop();	// Detener timer de enemigos
				timerMovimiento.stop();		// Detener timer de movimiento del jugado
				timerProyectiles[0].stop();	// Detener timer de proyectiles
				dispose(); // Cierra la ventana del juego
				new FinishFrame("Game Over").setVisible(true); // Abre la ventana de fin de juego --> DERROTA
			}
		});
		timerEnemigos[0].start();	
	}

	@Override
	public void update(Observable o, Object arg) {	// Método llamado por el modelo (Espacio) cuando hay cambios que requieren actualizar la vista
		// Repintar el tablero cuando el modelo notifique cambios
		repaint();
	}

	// =====================================================================
	// SISTEMA B: Disparo por COOLDOWN (basado en milisegundos)
	// =====================================================================
	// private void intentarDisparar() {
	// 	long ahora = System.currentTimeMillis();
	// 	if (ahora - ultimoDisparo >= COOLDOWN_DISPARO) {
	// 		Espacio.getInstance().disparar();
	// 		ultimoDisparo = ahora;
	// 	}
	// }
	
	////////////////////////////// MOVIMIENTO DEL JUGADOR	///////////////////////////////////////


	// Mueve al jugador segun las teclas de direccion pulsadas (llamado por Timer)
	private void moverJugador() {
		Espacio espacio = Espacio.getInstance();	// Obtener instancia del modelo para acceder al jugador
		Jugador j = espacio.getJugador();	// Obtener el jugador actual del modelo
		if (j == null) return;

		int nuevaX = j.getX();	// Coordenada actual X  del jugador
		int nuevaY = j.getY();	// Coordenada actual Y del jugador
		boolean movido = false;	

		if (teclasPresionadas.contains(KeyEvent.VK_LEFT)) {	// Si la tecla izquierda está presionada
			nuevaX = Math.max(0, nuevaX - 1);	// Mover izquierda y asegurar que no salga del borde izquierdo (mínimo 0)
			movido = true;
		}
		if (teclasPresionadas.contains(KeyEvent.VK_RIGHT)) {	// Si la tecla derecha está presionada
			nuevaX = Math.min(COLUMNAS - 1, nuevaX + 1);	// Mover derecha y asegurar que no salga del borde derecho (máximo COLUMNAS-1)
			movido = true;
		}
		if (teclasPresionadas.contains(KeyEvent.VK_UP)) {	// Si la tecla arriba está presionada
			nuevaY = Math.max(0, nuevaY - 1); 	// Mover arriba y asegurar que no salga del borde superior (mínimo 0)
			movido = true;
		}
		if (teclasPresionadas.contains(KeyEvent.VK_DOWN)) {	// Si la tecla abajo está presionada
			nuevaY = Math.min(FILAS - 1, nuevaY + 1);	// Mover abajo y asegurar que no salga del borde inferior (máximo FILAS-1)
			movido = true;
		}
		if (movido) {
			espacio.moverJugador(nuevaX, nuevaY);	// Notificar al modelo que el jugador se ha movido a las nuevas coordenadas
		}
	}

	// Pinta las naves en la matriz 100x60
	private void pintarMatriz(Graphics g) {
		Espacio espacio = Espacio.getInstance();
		List<Nave> naves = espacio.getNaves();

		for (Nave n : naves) {
			int px = n.getX() * TAM_CELDA; // Convertir coordenada logica a pixel
			int py = n.getY() * TAM_CELDA;

			if (n instanceof Jugador) {	// Si la nave es el jugador
				g.setColor(Color.GREEN); // Jugador en verde
			} else if (n instanceof Enemigo) {
				if (n.continuesPlaying()) {
					g.setColor(Color.RED); // Enemigos vivos en rojo
				} else {
					continue; // No pintar enemigos muertos
				}
			}
			g.fillRect(px, py, TAM_CELDA, TAM_CELDA); // Pintar celda
		}

		// Pintar todos los disparos activos
		Jugador j = espacio.getJugador();
		if (j != null) {
			g.setColor(Color.YELLOW); // Disparos en amarillo
			for (model.Disparo d : j.getDisparos()) {
				if (d.isShooting()) {
					int dx = d.getX() * TAM_CELDA;
					int dy = d.getY() * TAM_CELDA;
					g.fillRect(dx, dy, TAM_CELDA, TAM_CELDA);
				}
			}
		}
	}

}
