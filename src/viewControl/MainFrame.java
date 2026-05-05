package viewControl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Espacio;

@SuppressWarnings("deprecation")
public class MainFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelInfo;
	private JPanel heartPanel;
	private JLabel labelFlechas;
	private JLabel labelRombos;
	private JLabel labelPuntos;
	private int vidaActual;
	private int vidaMaxima;

	// Tamaño de la matriz logica (100 columnas x 60 filas)
	private static final int COLUMNAS = Espacio.getInstance().getAnchura();
	private static final int FILAS = Espacio.getInstance().getAltura();

	// Tamaño en pixeles de cada celda de la matriz
	private static final int TAM_CELDA = 6; // 100*5=500px ancho, 60*5=300px alto

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
				g.drawImage(fondo, 0, 0, COLUMNAS * TAM_CELDA, FILAS * TAM_CELDA, this);
				if (matrizActual != null) { // Si la matriz ya ha sido inicializada, la pintamos
					pintarMatriz(g, matrizActual); // Pintamos la matriz actual del juego en el fondo
				}
			}
		};

		contentPane.setPreferredSize(new Dimension(COLUMNAS * TAM_CELDA, FILAS * TAM_CELDA));
		contentPane.setLayout(null);

		//// PANEL DE INFORMACIÓN ////
		panelInfo = new JPanel();
		panelInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		panelInfo.setBackground(Color.BLACK);
		panelInfo.setPreferredSize(new Dimension(COLUMNAS * TAM_CELDA, 40));

		heartPanel = new HeartPanel();
		// El tamaño se ajustará dinámicamente cuando se conozca la vida máxima

		labelFlechas = new JLabel("Flechas: 0");
		labelFlechas.setForeground(Color.CYAN);
		labelFlechas.setFont(labelFlechas.getFont().deriveFont(14f));

		labelRombos = new JLabel("Rombos: 0");
		labelRombos.setForeground(Color.MAGENTA);
		labelRombos.setFont(labelRombos.getFont().deriveFont(14f));

		labelPuntos = new JLabel("Puntos: 0");
		labelPuntos.setForeground(Color.YELLOW);
		labelPuntos.setFont(labelPuntos.getFont().deriveFont(14f));

		panelInfo.add(heartPanel);
		panelInfo.add(labelFlechas);
		panelInfo.add(labelRombos);
		panelInfo.add(labelPuntos);

		//// LAYOUT PRINCIPAL ////
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(panelInfo, BorderLayout.NORTH);
		mainPanel.add(contentPane, BorderLayout.CENTER);
		setContentPane(mainPanel);

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
		// Actualizar información del jugador
		updateGameInfo();
		if (resul.equals("ganar") || resul.equals("perder")) {
			Espacio.getInstance().deleteObserver(this); // Eliminamos el MainFrame como observador para evitar futuras
														// actualizaciones
			this.dispose(); // Cerramos el MainFrame
			new FinishFrame(resul);
			// Esto si es legal

		} else if (resul.equals("actualizar") && matriz != null) {
			repintar3(matriz); // Llamamos a repintar para que se vuelva a pintar el fondo con la nueva matriz
			//matrizActual = matriz; // Actualizamos la matriz actual para que se pinte en el fondo
		}

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	private void updateGameInfo() {
		if (Espacio.getInstance().getJugador() != null) {
			vidaActual = Espacio.getInstance().getJugador().getVida();
			// Establecer vida máxima solo la primera vez
			if (vidaMaxima == 0) {
				vidaMaxima = vidaActual;
				int heartSize = 20;
				int spacing = 10;
				int heartPanelWidth = (heartSize + spacing) * vidaMaxima + 50;
				heartPanel.setPreferredSize(new Dimension(heartPanelWidth, 30));
				panelInfo.revalidate();
			}
			heartPanel.repaint();
			labelFlechas.setText("Flechas: " + Espacio.getInstance().getJugador().getFlechas());
			labelRombos.setText("Rombos: " + Espacio.getInstance().getJugador().getRombos());
			labelPuntos.setText("Puntos: " + Espacio.getInstance().getJugador().getPuntos());
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

//	private void repintar(int[][] m) {
//		
//	    Graphics g = contentPane.getGraphics();			// Dibujamos sobre el contentPane
//	    if (g == null) return;
//
//	    for (int i = 0; i < FILAS; i++) {
//	        for (int j = 0; j < COLUMNAS; j++) {		// Recorremos cada celda de la matriz
//	            int nuevoValor = m[i][j];				// Comparamos el nuevo valor con el valor anterior en la misma posición//
//	            int viejoValor = (matrizActual != null) 
//	            		? matrizActual[i][j] 			// Si la matriz actual no es null, obtenemos el valor anterior de esa celda
//	            		: -1;							// Si la matriz actual es null (primer repintado), valor anterior es -1 para que se pinten todas las celdas
//
//	            if (nuevoValor != viejoValor) {			// Si el valor ha cambiado, repintamos esa celda
//	                int x = j * TAM_CELDA;
//	                int y = i * TAM_CELDA;
//
//	                pintarCelda(g, nuevoValor, x, y);	// Pintamos la celda con el nuevo valor
//	                
//	            }
//	        }
//	    }
//	    
//	    
//	    g.dispose();		// Cerramps los recursos gráficos después de pintar
//	    matrizActual = m;	// Actualizamos la matriz actual con la nueva matriz después de repintar
//	}
	private void repintar(int[][] m) {
		
/* 		String VERDE = "\u001B[32m";
		String ROJO  = "\u001B[31m";
		String RESET = "\u001B[0m";

		String estado = ROJO + "Error" + RESET;
		for (int[] i : m) {
			for (int j : i) {
				if (j != 0) {
					estado = VERDE + "Correcto" + RESET;
				}
			}
		}
		System.out.println(estado); */

	    Graphics g = contentPane.getGraphics();
	    if (g == null) return;

	    // 1 — Pinta el fondo entero de una sola vez
	    //g.drawImage(fondo, 0, 0, COLUMNAS * TAM_CELDA, FILAS * TAM_CELDA, contentPane);

	    // 2 — Encima pinta solo los elementos (jugador, enemigos, disparos)
	    for (int i = 0; i < FILAS; i++) {
	        for (int j = 0; j < COLUMNAS; j++) {
	            int valor = m[i][j];
				int valorAnterior =  (matrizActual != null) ? matrizActual[i][j] : -1; // Si es el primer repintado, valorAnterior es -1 para que se pinten todas las celdas
				if (valor != valorAnterior) { // Solo repintamos si el valor ha cambiado respecto a la matriz actual
					if (valor != 0) { // solo pinta si no es fondo
						int x = j * TAM_CELDA;
						int y = i * TAM_CELDA;
						pintarCelda(g, valor, x, y);
					}
					else {
						int x = j * TAM_CELDA;
						int y = i * TAM_CELDA;
						int anchoTablero = COLUMNAS * TAM_CELDA;
						int altoTablero = FILAS * TAM_CELDA;
						int fondoW = fondo.getWidth(contentPane);
						int fondoH = fondo.getHeight(contentPane);

						if (fondoW > 0 && fondoH > 0) {
							int sx1 = x * fondoW / anchoTablero;
							int sy1 = y * fondoH / altoTablero;
							int sx2 = (x + TAM_CELDA) * fondoW / anchoTablero;
							int sy2 = (y + TAM_CELDA) * fondoH / altoTablero;
							g.drawImage(fondo, x, y, x + TAM_CELDA, y + TAM_CELDA, sx1, sy1, sx2, sy2, contentPane);
						}
					}
				}
	        }
	    }

	    g.dispose();
	    matrizActual = m;

/* 		int contador = 0;
		if (contador % 1 == 0) {
			for (int[] i : matrizActual) {
				for (int j : i) {
					if (j != 0) {
					System.out.print(j);
					}
				}
        	}
			System.out.println();
			System.out.println("-");
		}
		contador++; */
           
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	//// REPINTAR 2 (FONDO NEGRO) ////
	public void repintar2(int[][] m) {
		
	    Graphics g = contentPane.getGraphics();
	    if (g == null) return;

	    // Recorre la matriz y pinta con los colores correspondientes
	    // Si encuentra 0s (fondo), los pinta de negro en lugar de la imagen
	    for (int i = 0; i < FILAS; i++) {
	        for (int j = 0; j < COLUMNAS; j++) {
	            int valor = m[i][j];
	            int x = j * TAM_CELDA;
	            int y = i * TAM_CELDA;
	            
	            if (valor != 0) { // Elemento (jugador, enemigos, disparos, etc.)
	                pintarCelda(g, valor, x, y);
	            }
	            else { // Fondo (0s) - pintar de negro
	                g.setColor(Color.BLACK);
	                g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
	            }
	        }
	    }

	    g.dispose();
	    matrizActual = m;
	}

	public void repintar3(int[][] m) {
		matrizActual = m;
		repaint();
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
			case 4 -> {
				// Disparo especial
				g.setColor(Color.CYAN);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
			case 5 -> {
				// Disparo super especial
				g.setColor(Color.MAGENTA);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
			case 6 -> {
				// Bomba
				g.setColor(Color.ORANGE);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
			case 7 -> {
				// Escudo
				g.setColor(Color.GREEN);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
			case 8 -> {
				// Enemigo azul
				g.setColor(Color.BLUE);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
			case 9 -> {
				// Enemigo rosa
				g.setColor(Color.PINK);
				g.fillRect(x, y, TAM_CELDA, TAM_CELDA);
			}
//			default -> {
//				// Fondo
//				int anchoTablero = COLUMNAS * TAM_CELDA;
//				int altoTablero = FILAS * TAM_CELDA;
//				int fondoW = fondo.getWidth(contentPane);
//				int fondoH = fondo.getHeight(contentPane);
//
//				if (fondoW > 0 && fondoH > 0) {
//					int sx1 = x * fondoW / anchoTablero;
//					int sy1 = y * fondoH / altoTablero;
//					int sx2 = (x + TAM_CELDA) * fondoW / anchoTablero;
//					int sy2 = (y + TAM_CELDA) * fondoH / altoTablero;
//					g.drawImage(fondo, x, y, x + TAM_CELDA, y + TAM_CELDA, sx1, sy1, sx2, sy2, contentPane);
//				}
//			}
		}
            
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------

	// =====================================================================
	// HEART PANEL - Dibuja corazones basados en la vida actual
	// =====================================================================

	private class HeartPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.BLACK);

			int heartSize = 20;
			int spacing = 10;
			int startX = 5;
			int startY = getHeight() / 2 - heartSize / 2;

			// Dibujar corazones dinámicamente según la vida máxima
			if (vidaMaxima > 0) {
				for (int i = 0; i < vidaMaxima; i++) {
					int heartX = startX + i * (heartSize + spacing);
					boolean isFilled = vidaActual > i;
					drawHeart(g, heartX, startY, heartSize, isFilled);
				}
			}

			// Dibujar el número de vida debajo
			g.setColor(Color.WHITE);
			g.setFont(g.getFont().deriveFont(12f));
			g.drawString("HP: " + vidaActual, startX, startY + heartSize + 20);
		}

		private void drawHeart(Graphics g, int x, int y, int size, boolean filled) {
			if (filled) {
				g.setColor(Color.RED);
			} else {
				g.setColor(new Color(100, 100, 100)); // Gris oscuro para corazones vacíos
			}

			// Definir el patrón del corazón con pixeles
			// 1 = pixel del corazón, 0 = vacío
			int[][] heartPattern = {
				{0, 1, 1, 0, 0, 1, 1, 0},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 1, 1},
				{0, 1, 1, 1, 1, 1, 1, 0},
				{0, 0, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 1, 1, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0}
			};

			int pixelSize = size / 8; // Cada pixel del corazón
			
			for (int row = 0; row < heartPattern.length; row++) {
				for (int col = 0; col < heartPattern[row].length; col++) {
					if (heartPattern[row][col] == 1) {
						int px = x + col * pixelSize;
						int py = y + row * pixelSize;
						g.fillRect(px, py, pixelSize, pixelSize);
					}
				}
			}
		}
	}

	// =====================================================================
	// CONTROLLER
	// =====================================================================

	private class Controller implements KeyListener, WindowListener { // Clase interna para manejar eventos de teclado

		private final Set<Integer> teclasPulsadas = new HashSet<>(); // Disoarar mientras me muevo
		private final Espacio espacio = Espacio.getInstance();

		@Override
		public void keyPressed(KeyEvent e) {
			// if (espacio.isGameOver() || espacio.isVictory()) return; //modelo
			teclasPulsadas.add(e.getKeyCode()); // Disparar mientras me muevo

			if (teclasPulsadas.contains(KeyEvent.VK_SPACE)) {
				//System.out.println("Espacio pulsado"); // Para verificar que se detecta la pulsación de espacio
				espacio.dispararJugador("pixel");
				//System.out.println("Disparo realizado"); // Para verificar que se dispara al pulsar espacio
			}
			if(teclasPulsadas.contains(KeyEvent.VK_C)) {
				espacio.dispararJugador("flecha");
			}
			if(teclasPulsadas.contains(KeyEvent.VK_V)) {
				espacio.dispararJugador("rombo");
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
				espacio.moverJugador(dx, dy);
				
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
