package viewControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Enemigo;
import model.Espacio;
import model.Jugador;
import model.Disparo;

@SuppressWarnings("deprecation")
public class MainFrame extends JFrame implements Observer {
	
	// Referencia al modelo del juego para acceder a su estado y métodos
	private Espacio espacio;	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Tamaño de la matriz logica (100 columnas x 60 filas)
	private static final int COLUMNAS = 100;
	private static final int FILAS = 60;
	// Tamaño en pixeles de cada celda de la matriz
	private static final int TAM_CELDA = 5; // 100*5=500px ancho, 60*5=300px alto

	// Teclas actualmente pulsadas (para permitir varias a la vez)
	//ivate final Set<Integer> teclasPresionadas = new HashSet<>();

	// =====================================================================
	// SISTEMA A: Disparo por TICKS (sincronizado con proyectiles)
	// =====================================================================

	//private int ticksDesdeUltimoDisparo = 0;
	//private static final int TICKS_ENTRE_DISPAROS = 4; // disparar cada N ticks (N*50ms)

	// =====================================================================
	// SISTEMA B: Disparo por COOLDOWN (basado en milisegundos)
	// =====================================================================

	// private long ultimoDisparo = 0;
	// private static final long COOLDOWN_DISPARO = 175; // ms entre disparos

	public MainFrame() {
		espacio = Espacio.getInstance();	// Obtener instancia del modelo para interactuar con él
		espacio.addObserver(this);	// Registrarse como observador del modelo para recibir actualizaciones
		
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//Inicializamos el panel:
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

		pack();
		setLocationRelativeTo(null); // Centrar en pantalla
		setVisible(true);
		
		//CONTROLLER
		setFocusable(true);
		requestFocusInWindow(); // Para que reciba eventos de teclado
		Controller controller = new Controller(); // Crear instancia del controlador para manejar eventos de teclado
		addKeyListener(controller); // Agregar el controlador como KeyListener para detectar teclas presionadas

		// =====================================================================
		// SISTEMA A: Disparo por TICKS (sincronizado con proyectiles)
		// =====================================================================

//		addKeyListener(new KeyListener() {	//Para detectar las teclas pulsadas
//			@Override
//			public void keyPressed(KeyEvent e) {
//				teclasPresionadas.add(e.getKeyCode());
//				if (e.getKeyCode() == KeyEvent.VK_SPACE && ticksDesdeUltimoDisparo >= TICKS_ENTRE_DISPAROS) {
//					Espacio.getInstance().disparar();
//					ticksDesdeUltimoDisparo = 0;
//				}
//			}
//			@Override
//			public void keyReleased(KeyEvent e) {
//				teclasPresionadas.remove(e.getKeyCode());
//			}
//			@Override public void keyTyped(KeyEvent e) {}
//		});
	}

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
//		Timer timerMovimiento = new Timer(50, ev -> {	// Llamado cada 50ms para mover al jugador según las teclas presionadas
//			moverJugador();
//		});
//		timerMovimiento.start();	// Iniciar el timer de movimiento del jugador
//

		
		////////////////////////////// TIMER  JUGADOR Y DISPAROS	///////////////////////////////////////
		
		// =====================================================================
		// SISTEMA A: Disparo por TICKS (sincronizado con proyectiles)
		// =====================================================================

//		Timer[] timerProyectiles = new Timer[1]; // Array para poder referenciarlo dentro del lambda
//		timerProyectiles[0] = new Timer(50, ev -> {	// Llamado cada 50ms para actualizar proyectiles y manejar disparos
//			Espacio.getInstance().actualizarDisparo(); // 1. Mover proyectiles
//			ticksDesdeUltimoDisparo++;                  // 2. Contar tick
//			if (teclasPresionadas.contains(KeyEvent.VK_SPACE)
//					&& ticksDesdeUltimoDisparo >= TICKS_ENTRE_DISPAROS) {	// Intentar disparar si se mantiene espacio y ha pasado el tiempo
//				Espacio.getInstance().disparar();       // 3. Disparar
//				ticksDesdeUltimoDisparo = 0;            // 4. Reset contador
//			}
//			
//			if(Espacio.getInstance().isVictory()) {	// Si se ha ganado el juego
//				timerProyectiles[0].stop();	// Detener timer de proyectiles
//				timerMovimiento.stop();	// Detener timer de movimiento del jugador
//				//timerEnemigos[0].stop();
//				dispose(); // Cierra la ventana del juego
//				new FinishFrame("Win").setVisible(true); // Abre la ventana de fin de juego --> VICTORIA
//			}
//		});
//		timerProyectiles[0].start();	// Iniciar el timer de proyectiles

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
//		Timer[] timerEnemigos = new Timer[1]; // Array para poder referenciarlo dentro del lambda
//		timerEnemigos[0] = new Timer(200, ev -> {	// Llamado cada 200ms para mover enemigos y verificar condiciones de juego
//			Espacio.getInstance().actualizarEnemigos(); // Mover enemigos hacia abajo
//			if(Espacio.getInstance().isGameOver()) {	// Si se ha perdido el juego (enemigo llegó al fondo o colisionó con jugador)
//				timerEnemigos[0].stop();	// Detener timer de enemigos
//				timerMovimiento.stop();		// Detener timer de movimiento del jugado
//				timerProyectiles[0].stop();	// Detener timer de proyectiles
//				dispose(); // Cierra la ventana del juego
//				new FinishFrame("Game Over").setVisible(true); // Abre la ventana de fin de juego --> DERROTA
//			}
//		});
//		timerEnemigos[0].start();	
//	}
//	
//	
	//////// CONTROLLER 	///////////////////////////////////////

	@Override
	public void update(Observable o, Object arg) {	// Método llamado por el modelo (Espacio) cuando hay cambios que requieren actualizar la vista
	    Espacio esp = Espacio.getInstance();
	    if (esp.isGameOver() || esp.isVictory()) {
	        esp.deleteObserver(this);   // Dejar de observar
	        String resultado = esp.isVictory() 
	        		? "Win" 
	        		: "Lose";
	        dispose();                   // Cerrar MainFrame
	        new FinishFrame(resultado);  // Abrir FinishFrame
	    } else {
	        repaint();
	    }
	}
	
	private class Controller implements KeyListener {	// Clase interna para manejar eventos de teclado
		@Override
        public void keyPressed(KeyEvent e) {
            if (espacio.isGameOver() || espacio.isVictory()) return;

            switch (e.getKeyCode()) {	// Mover jugador según tecla de dirección presionada o disparar si se presiona espacio
                case KeyEvent.VK_LEFT:  espacio.moverJugador(-1,  0); break;
                case KeyEvent.VK_RIGHT: espacio.moverJugador( 1,  0); break;
                case KeyEvent.VK_UP:    espacio.moverJugador( 0, -1); break;
                case KeyEvent.VK_DOWN:  espacio.moverJugador( 0,  1); break;
                case KeyEvent.VK_SPACE: espacio.disparar();           break;
            }
        }
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override public void keyTyped(KeyEvent e) {}
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
//	private void moverJugador() {
//		Espacio espacio = Espacio.getInstance();	// Obtener instancia del modelo para acceder al jugador
//		Jugador j = espacio.getJugador();	// Obtener el jugador actual del modelo
//		if (j == null) return;
//
//		int nuevaX = j.getX();	// Coordenada actual X  del jugador
//		int nuevaY = j.getY();	// Coordenada actual Y del jugador
//		boolean movido = false;	
//
//		if (teclasPresionadas.contains(KeyEvent.VK_LEFT)) {	// Si la tecla izquierda está presionada
//			nuevaX = Math.max(0, nuevaX - 1);	// Mover izquierda y asegurar que no salga del borde izquierdo (mínimo 0)
//			movido = true;
//		}
//		if (teclasPresionadas.contains(KeyEvent.VK_RIGHT)) {	// Si la tecla derecha está presionada
//			nuevaX = Math.min(COLUMNAS - 1, nuevaX + 1);	// Mover derecha y asegurar que no salga del borde derecho (máximo COLUMNAS-1)
//			movido = true;
//		}
//		if (teclasPresionadas.contains(KeyEvent.VK_UP)) {	// Si la tecla arriba está presionada
//			nuevaY = Math.max(0, nuevaY - 1); 	// Mover arriba y asegurar que no salga del borde superior (mínimo 0)
//			movido = true;
//		}
//		if (teclasPresionadas.contains(KeyEvent.VK_DOWN)) {	// Si la tecla abajo está presionada
//			nuevaY = Math.min(FILAS - 1, nuevaY + 1);	// Mover abajo y asegurar que no salga del borde inferior (máximo FILAS-1)
//			movido = true;
//		}
//		if (movido) {
//			espacio.moverJugador(nuevaX, nuevaY);	// Notificar al modelo que el jugador se ha movido a las nuevas coordenadas
//		}
//	}

	// Pinta las naves en la matriz 100x60
	private void pintarMatriz(Graphics g) {
		Espacio espacio = Espacio.getInstance();
		//List<Nave> naves = espacio.getNaves();
		
		//Jugador -- Verde
		Jugador j= espacio.getJugador();
		if(j!=null && j.sigueVivo()) {	// Si el jugador existe y sigue vivo
			int px = j.getX() * TAM_CELDA; // Convertir coordenada logica a pixel
			int py = j.getY() * TAM_CELDA;
			g.setColor(Color.GREEN); // Jugador en verde
			g.fillRect(px, py, TAM_CELDA, TAM_CELDA); // Pintar celda del jugador
		}
		
		//Disparos -- Amarillo	//REVISAR
		ArrayList<Disparo> disparos = j.getDisparos();
		for(Disparo d : disparos) {
			if (d.isShooting()) {	// Si el disparo está activo
				int dx = d.getX() * TAM_CELDA; // Convertir coordenada logica a pixel
				int dy = d.getY() * TAM_CELDA;
				g.setColor(Color.YELLOW); // Disparos en amarillo
				g.fillRect(dx, dy, TAM_CELDA, TAM_CELDA);
			}
		}
////		if (j != null) {
////			Disparo d = j.getDisparos();
////			if (d.isShooting()) {	// Si el disparo está activo
////				int dx = d.getX() * TAM_CELDA; // Convertir coordenada logica a pixel
////				int dy = d.getY() * TAM_CELDA;
////				g.setColor(Color.YELLOW); // Disparos en amarillo
////				g.fillRect(dx, dy, TAM_CELDA, TAM_CELDA);
////				
////			}
//		}
		
		//Enemigo -- rojo
		for (Enemigo e : espacio.getEnemigos()) {
			if (e.sigueVivo()) {	// Si el enemigo sigue vivo
				int px = e.getX() * TAM_CELDA; // Convertir coordenada logica a pixel
				int py = e.getY() * TAM_CELDA;
				g.setColor(Color.RED); // Enemigos vivos en rojo
				g.fillRect(px, py, TAM_CELDA, TAM_CELDA); // Pintar celda del enemigo
			}
		}
	

//		for (Nave n : naves) {
//			int px = n.getX() * TAM_CELDA; // Convertir coordenada logica a pixel
//			int py = n.getY() * TAM_CELDA;
//
//			if (n instanceof Jugador) {	// Si la nave es el jugador
//				g.setColor(Color.GREEN); // Jugador en verde
//			} else if (n instanceof Enemigo) {
//				if (n.continuesPlaying()) {
//					g.setColor(Color.RED); // Enemigos vivos en rojo
//				} else {
//					continue; // No pintar enemigos muertos
//				}
//			}
//			g.fillRect(px, py, TAM_CELDA, TAM_CELDA); // Pintar celda
//		}

		// Pintar todos los disparos activos
//		Jugador jug = espacio.getJugador();
//		if (jug != null) {
//			g.setColor(Color.YELLOW); // Disparos en amarillo
//			for (model.Disparo d : d.getDisparos()) {
//				if (d.isShooting()) {
//					int dx = d.getX() * TAM_CELDA;
//					int dy = d.getY() * TAM_CELDA;
//					g.fillRect(dx, dy, TAM_CELDA, TAM_CELDA);
//				}
//			}
//		}
	}

}
