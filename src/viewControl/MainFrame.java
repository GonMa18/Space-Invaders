package viewControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
