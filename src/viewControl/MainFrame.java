package viewControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Espacio;

@SuppressWarnings("deprecation")
public class MainFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Tamaño de la matriz logica (100 columnas x 60 filas)
	private static final int COLUMNAS = 160;
	private static final int FILAS = 120;

	// Tamaño en pixeles de cada celda de la matriz
	private static final int TAM_CELDA = 5; // 100*5=500px ancho, 60*5=300px alto

	// Matriz logica del juego que se pintará en el MainFrame
	private int[][] matrizActual;

	// Imagen de fondo
	private Image fondo;

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	public MainFrame() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		Espacio.getInstance().addObserver(this); // Registrarse como observador del modelo para recibir actualizaciones
		// Esto si es legal

		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		//// FONDO ////
		ImageIcon iconoFondo = new ImageIcon(getClass().getResource("/Resources/Images/Fondo.png"));
		fondo = iconoFondo.getImage();
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
				if (matrizActual != null) { // Si la matriz ya ha sido inicializada, la pintamos
					pintarMatriz(g, matrizActual); // Pintamos la matriz actual del juego en el fondo
				}
			}
		};

		contentPane.setPreferredSize(new Dimension(COLUMNAS * TAM_CELDA, FILAS * TAM_CELDA));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		pack();
		setLocationRelativeTo(null); // Centrar en pantalla
		setVisible(true);

		// =====================================================================
		// CONTROLLER
		// =====================================================================

		setFocusable(true);
		requestFocusInWindow(); // Para que reciba eventos de teclado
		Controller controller = new Controller(); // Crear instancia del controlador para manejar eventos de teclado
		addKeyListener(controller); // Agregar el controlador como KeyListener para detectar teclas presionadas
		addWindowListener(controller); // Agregar el controlador como WindowListener para detectar eventos de ventana
										// (opcional)
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public void update(Observable o, Object arg) {

		//// CASTING A STRING ////
		Object[] res = (Object[]) arg;
		String resul = (String) (res[0]);

		//// CASTING A MATRIZ ////
		int[][] matriz = (int[][]) res[1]; // Suponiendo que el segundo elemento del array es la matriz actualizada

		if (resul.equals("ganar") || resul.equals("perder")) {
			Espacio.getInstance().deleteObserver(this); // Eliminamos el MainFrame como observador para evitar futuras
														// actualizaciones
			this.dispose(); // Cerramos el MainFrame
			new FinishFrame(resul);
			// Esto si es legal

		} else if (resul == "actualizar" && matriz != null) {
			repintar(matriz); // Llamamos a repintar para que se vuelva a pintar el fondo con la nueva matriz
			matrizActual = matriz; // Actualizamos la matriz actual para que se pinte en el fondo
		}

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// Repinta solo las celdas que han cambiado entre la matriz anterior y la nueva,
	// Metodo equivalente a repaint(), pero sin chatGPT -_-

	private void repintar(int[][] m) {
		
	    Graphics g = contentPane.getGraphics();			// Dibujamos sobre el contentPane
	    if (g == null) return;

	    ImageIcon imagen = new ImageIcon(getClass().getResource("/Resources/Images/Fondo.png"));
	    Image fondo = imagen.getImage();				// Cargamos la imagen de fondo

	    for (int i = 0; i < FILAS; i++) {
	        for (int j = 0; j < COLUMNAS; j++) {		// Recorremos cada celda de la matriz
	            int nuevoValor = m[i][j];				// Comparamos el nuevo valor con el valor anterior en la misma posición//
	            int viejoValor = (matrizActual != null) 
	            		? matrizActual[i][j] 			// Si la matriz actual no es null, obtenemos el valor anterior de esa celda
	            		: -1;							// Si la matriz actual es null (primer repintado), valor anterior es -1 para que se pinten todas las celdas

	            if (nuevoValor != viejoValor) {			// Si el valor ha cambiado, repintamos esa celda
	                int x = j * TAM_CELDA;
	                int y = i * TAM_CELDA;

	                pintarCelda(g, nuevoValor, x, y);	// Pintamos la celda con el nuevo valor
	                
	            }
	        }
	    }
	    g.dispose();		// Cerramps los recursos gráficos después de pintar
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	//// PINTAR LA MATRIZ ////
	public void pintarMatriz(Graphics g, int[][] matriz) {

		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {

				int x = j * TAM_CELDA;
				int y = i * TAM_CELDA;

				pintarCelda(g, matriz[i][j], x, y);

			}
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	//// PINTAR LA CASILLA ////
	private void pintarCelda(Graphics g, int valor, int x, int y) {

		switch (valor) {
			case 1 -> {
				// Jugador
				g.setColor(Color.WHITE);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
			case 2 -> {
				// Enemigo
				g.setColor(Color.RED);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
			case 3 -> {
				// Disparo
				g.setColor(Color.YELLOW);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
			default -> {
				// Celda vacia -- negro
				g.setColor(Color.BLACK);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
		}
            
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// =====================================================================
	// CONTROLLER
	// =====================================================================

	private class Controller implements KeyListener, WindowListener { // Clase interna para manejar eventos de teclado

		private final Set<Integer> teclasPulsadas = new HashSet<>(); // Disoarar mientras me muevo

		@Override
		public void keyPressed(KeyEvent e) {
			// if (espacio.isGameOver() || espacio.isVictory()) return; //modelo
			teclasPulsadas.add(e.getKeyCode()); // Disparar mientras me muevo

			if (teclasPulsadas.contains(KeyEvent.VK_SPACE)) {
				//System.out.println("Espacio pulsado"); // Para verificar que se detecta la pulsación de espacio
				Espacio.getInstance().disparar();
				//System.out.println("Disparo realizado"); // Para verificar que se dispara al pulsar espacio
			}

			int dx = 0;
			int dy = 0;
			if (teclasPulsadas.contains(KeyEvent.VK_LEFT))
				dx--;
			if (teclasPulsadas.contains(KeyEvent.VK_RIGHT))
				dx++;
			if (teclasPulsadas.contains(KeyEvent.VK_UP))
				dy--;
			if (teclasPulsadas.contains(KeyEvent.VK_DOWN))
				dy++;

			if (dx != 0 || dy != 0) {
				Espacio.getInstance().moverJugador(dx, dy);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) { // Disparar mientras me muevo
			teclasPulsadas.remove(e.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void windowOpened(WindowEvent e) {
			//Graphics g = contentPane.getGraphics();
			//g.setColor(Color.GREEN);
			//g.fillRect(40, 30, 5, 5);
			//g.dispose();
			// Pinto la matriz una primera vez (cuando MainFrame se abre)
			pintarMatriz(contentPane.getGraphics(), Espacio.getInstance().generarMatriz());
			// Desde el controller y SOLO desde el controller puedo llamar a espacio
		}

		@Override
		public void windowClosing(WindowEvent e) {
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

}
