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

		// TECLADO - Controles del jugador //
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				teclasPresionadas.add(e.getKeyCode());
				procesarTeclas(); // Cada repeticion del SO procesa TODAS las teclas pulsadas
			}
			@Override
			public void keyReleased(KeyEvent e) {
				teclasPresionadas.remove(e.getKeyCode());
			}
			@Override public void keyTyped(KeyEvent e) {}
		});

		// TIMER - Mover el disparo hacia arriba cada 50ms //
		Timer timerDisparo = new Timer(50, ev -> {
			Espacio.getInstance().actualizarDisparo();
		});
		timerDisparo.start();

		// TIMER - Mover los enemigos hacia abajo cada 200ms //
		Timer timerEnemigos = new Timer(200, ev -> {
			Espacio.getInstance().actualizarEnemigos();
		});
		timerEnemigos.start();
	}

	@Override
	public void update(Observable o, Object arg) {
		// Repintar el tablero cuando el modelo notifique cambios
		repaint();
	}

	// Procesa todas las teclas pulsadas a la vez
	private void procesarTeclas() {
		Espacio espacio = Espacio.getInstance();
		Jugador j = espacio.getJugador();
		if (j == null) return;

		int nuevaX = j.getX();
		int nuevaY = j.getY();
		boolean movido = false;

		if (teclasPresionadas.contains(KeyEvent.VK_LEFT)) {
			nuevaX = Math.max(0, nuevaX - 1);
			movido = true;
		}
		if (teclasPresionadas.contains(KeyEvent.VK_RIGHT)) {
			nuevaX = Math.min(COLUMNAS - 1, nuevaX + 1);
			movido = true;
		}
		if (teclasPresionadas.contains(KeyEvent.VK_UP)) {
			nuevaY = Math.max(0, nuevaY - 1);
			movido = true;
		}
		if (teclasPresionadas.contains(KeyEvent.VK_DOWN)) {
			nuevaY = Math.min(FILAS - 1, nuevaY + 1);
			movido = true;
		}
		if (teclasPresionadas.contains(KeyEvent.VK_SPACE)) {
			espacio.disparar();
		}
		if (movido) {
			espacio.moverJugador(nuevaX, nuevaY);
		}
	}

	// Pinta las naves en la matriz 100x60
	private void pintarMatriz(Graphics g) {
		Espacio espacio = Espacio.getInstance();
		List<Nave> naves = espacio.getNaves();

		for (Nave n : naves) {
			int px = n.getX() * TAM_CELDA; // Convertir coordenada logica a pixel
			int py = n.getY() * TAM_CELDA;

			if (n instanceof Jugador) {
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
