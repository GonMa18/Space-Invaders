package viewControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
				Espacio espacio = Espacio.getInstance();
				Jugador j = espacio.getJugador();
				if (j == null) return;

				int nuevaX = j.getX();
				int nuevaY = j.getY();

				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:  // Flecha izquierda
						nuevaX = Math.max(0, nuevaX - 1);
						break;
					case KeyEvent.VK_RIGHT: // Flecha derecha
						nuevaX = Math.min(COLUMNAS - 1, nuevaX + 1);
						break;
					case KeyEvent.VK_UP:    // Flecha arriba
						nuevaY = Math.max(0, nuevaY - 1);
						break;
					case KeyEvent.VK_DOWN:  // Flecha abajo
						nuevaY = Math.min(FILAS - 1, nuevaY + 1);
						break;
					case KeyEvent.VK_SPACE: // Espacio para disparar
						espacio.disparar();
						return;
				}
				espacio.moverJugador(nuevaX, nuevaY); // Mover jugador a la nueva posicion
			}
			@Override public void keyReleased(KeyEvent e) {}
			@Override public void keyTyped(KeyEvent e) {}
		});

		// TIMER - Mover el disparo hacia arriba cada 50ms //
		Timer timerDisparo = new Timer(50, ev -> {
			Espacio.getInstance().actualizarDisparo();
		});
		timerDisparo.start();
	}

	@Override
	public void update(Observable o, Object arg) {
		// Repintar el tablero cuando el modelo notifique cambios
		repaint();
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

		// Pintar disparo si esta activo
		Jugador j = espacio.getJugador();
		if (j != null && j.getDisparo() != null && j.getDisparo().isShooting()) {
			g.setColor(Color.YELLOW); // Disparo en amarillo
			int dx = j.getDisparo().getX() * TAM_CELDA;
			int dy = j.getDisparo().getY() * TAM_CELDA;
			g.fillRect(dx, dy, TAM_CELDA, TAM_CELDA);
		}
	}

}
