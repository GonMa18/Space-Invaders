package viewControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Enemigo;
import model.Espacio;
import model.Jugador;
import model.Disparo;

@SuppressWarnings("deprecation")
public class MainFrame extends JFrame implements Observer {	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Tamaño de la matriz logica (100 columnas x 60 filas)
	private static final int COLUMNAS = 100;
	private static final int FILAS = 60;
	
	// Tamaño en pixeles de cada celda de la matriz
	private static final int TAM_CELDA = 5; // 100*5=500px ancho, 60*5=300px alto
	
	// Referencia al modelo del juego para acceder a su estado y métodos
	private Espacio espacio;

    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	public MainFrame() {
		
		espacio = Espacio.getInstance();	// Obtener instancia del modelo para interactuar con él
		espacio.addObserver(this);			// Registrarse como observador del modelo para recibir actualizaciones
		
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//// FONDO ////
		ImageIcon iconoFondo = new ImageIcon("Resources/Images/Fondo.png");
		Image imagenFondo = iconoFondo.getImage();

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
//				pintarMatriz(g, null);
				//TODO -- se quita o se deja??
			}
		};
		
		contentPane.setPreferredSize(new Dimension(COLUMNAS * TAM_CELDA, FILAS * TAM_CELDA));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		pack();
		setLocationRelativeTo(null); 		// Centrar en pantalla
		setVisible(true);
		
		
		// =====================================================================
		// 								CONTROLLER
		// =====================================================================
		
		setFocusable(true);
		requestFocusInWindow(); 						// Para que reciba eventos de teclado
		Controller controller = new Controller(); 		// Crear instancia del controlador para manejar eventos de teclado
		addKeyListener(controller); 					// Agregar el controlador como KeyListener para detectar teclas presionadas
		addWindowListener(controller); 					// Agregar el controlador como WindowListener para detectar eventos de ventana (opcional)
	}

    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	@Override
	public void update(Observable o, Object arg) {	
		
		//// CASTING A STRING ////
		Object[] res = (Object[])arg;					
		String resul=(String) (res[0]);			
		
		//// CASTING A MATRIZ ////
		//TODO
		
		
	   // Espacio esp = Espacio.getInstance();
//	    if (esp.isGameOver() || esp.isVictory()) {
//	        esp.deleteObserver(this);   			// Dejar de observar
//	        String resultado = esp.isVictory() 
//	        		? "Win" 
//	        		: "Lose";
//	        dispose();                   			// Cerrar MainFrame
//	        new FinishFrame(resultado);  			// Abrir FinishFrame
//	    } else {
//	        repaint();
//	    }
		
		if (resul== "ganar" || resul== "perder") {
			Espacio.getInstance().deleteObserver(this); 		// Eliminamos el MainFrame como observador para evitar futuras actualizaciones
    		this.dispose(); 									// Cerramos el MainFrame
			new FinishFrame(resul);

		}
		
		//FALTA LA CONDICION DE CUANDO RECIBE LA MATRIZ --> ACTUALIZA pintarMatriz
		//TODO
		
					
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	
	// =====================================================================
	// 								CONTROLLER
	// =====================================================================
	
	private class Controller implements KeyListener, WindowListener {			// Clase interna para manejar eventos de teclado
		
		private final Set<Integer> teclasPulsadas = new HashSet<>();	//Disoarar mientras me muevo
		
		@Override
        public void keyPressed(KeyEvent e) {
            //if (espacio.isGameOver() || espacio.isVictory()) return;	//modelo

            teclasPulsadas.add(e.getKeyCode());							//Disparar mientras me muevo

            if (teclasPulsadas.contains(KeyEvent.VK_SPACE)) {
            	espacio.disparar();
            }

            int dx = 0;
            int dy = 0;
            if (teclasPulsadas.contains(KeyEvent.VK_LEFT))  dx--;
            if (teclasPulsadas.contains(KeyEvent.VK_RIGHT)) dx++;
            if (teclasPulsadas.contains(KeyEvent.VK_UP))    dy--;
            if (teclasPulsadas.contains(KeyEvent.VK_DOWN))  dy++;

            if (dx != 0 || dy != 0) {
            	espacio.moverJugador(dx, dy);
            }
        }
		@Override
		public void keyReleased(KeyEvent e) {							//Disparar mientras me muevo
			teclasPulsadas.remove(e.getKeyCode());
		}
		@Override 
		public void keyTyped(KeyEvent e) {}
		@Override
		public void windowOpened(WindowEvent e) {	
			
			//Pinto la matriz una primera vez (cuando MainFrame se abre)
			pintarMatriz(contentPane.getGraphics(), espacio.generarMatriz());
			//Desde el controller y SOLO desde el controller puedo llamar a espacio
			
			
		}
		@Override
		public void windowClosing(WindowEvent e) {}
		@Override
		public void windowClosed(WindowEvent e) {}
		@Override
		public void windowIconified(WindowEvent e) {}
		@Override
		public void windowDeiconified(WindowEvent e) {}
		@Override
		public void windowActivated(WindowEvent e) {}
		@Override
		public void windowDeactivated(WindowEvent e) {}
	}
	
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    

	//// PINTAR LA MATRIZ ////
	public void pintarMatriz(Graphics g, int[][] matriz) {
		
//		Espacio espacio = Espacio.getInstance();
//		
//		//Jugador -- Verde
//		Jugador j= espacio.getJugador();
//		if(j!=null && j.sigueVivo()) {					// Si el jugador existe y sigue vivo
//			int px = j.getX() * TAM_CELDA; 				// Convertir coordenada logica a pixel
//			int py = j.getY() * TAM_CELDA;
//			g.setColor(Color.GREEN); 				
//			g.fillRect(px, py, TAM_CELDA, TAM_CELDA); 	// Pintar celda del jugador
//		}
//		
//		//Disparos -- Amarillo	
//		ArrayList<Disparo> disparos = j.getDisparos();
//		for(Disparo d : disparos) {
//			if (d.isShooting()) {						// Si el disparo está activo
//				int dx = d.getX() * TAM_CELDA; 			// Convertir coordenada logica a pixel
//				int dy = d.getY() * TAM_CELDA;
//				g.setColor(Color.YELLOW); 
//				g.fillRect(dx, dy, TAM_CELDA, TAM_CELDA);
//			}
//		}
//
//		//Enemigo -- Rojo
//		for (Enemigo e : espacio.getEnemigos()) {
//			if (e.sigueVivo()) {							// Si el enemigo sigue vivo
//				int px = e.getX() * TAM_CELDA; 				// Convertir coordenada logica a pixel
//				int py = e.getY() * TAM_CELDA;
//				g.setColor(Color.RED); 
//				g.fillRect(px, py, TAM_CELDA, TAM_CELDA);	 // Pintar celda del enemigo
//			}
//		}
		
		
		
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				int valor = matriz[i][j];
				if (valor == 1) { 					// Jugador
					g.setColor(Color.GREEN);
					g.fillRect(j * TAM_CELDA, i * TAM_CELDA, TAM_CELDA, TAM_CELDA);
				} else if (valor == 2) { 			// Enemigo
					g.setColor(Color.RED);
					g.fillRect(j * TAM_CELDA, i * TAM_CELDA, TAM_CELDA, TAM_CELDA);
				} else if (valor == 3) { 			// Disparo
					g.setColor(Color.YELLOW);
					g.fillRect(j * TAM_CELDA, i * TAM_CELDA, TAM_CELDA, TAM_CELDA);
				}else {
					g.setColor(Color.BLACK);
					g.fillRect(j * TAM_CELDA, i * TAM_CELDA, TAM_CELDA, TAM_CELDA);
				}
			}
		}
		
		
		
		
	}

	
}
