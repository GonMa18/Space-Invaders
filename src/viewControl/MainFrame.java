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
		ImageIcon iconoFondo = new ImageIcon("Resources/Fondo.png");
		Image imagenFondo = iconoFondo.getImage();

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
				pintarMatriz(g);
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
	}

    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	@Override
	public void update(Observable o, Object arg) {	
	    Espacio esp = Espacio.getInstance();
	    if (esp.isGameOver() || esp.isVictory()) {
	        esp.deleteObserver(this);   			// Dejar de observar
	        String resultado = esp.isVictory() 
	        		? "Win" 
	        		: "Lose";
	        dispose();                   			// Cerrar MainFrame
	        new FinishFrame(resultado);  			// Abrir FinishFrame
	    } else {
	        repaint();
	    }
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	
	// =====================================================================
	// 								CONTROLLER
	// =====================================================================
	
	private class Controller implements KeyListener {			// Clase interna para manejar eventos de teclado
		@Override
        public void keyPressed(KeyEvent e) {
            if (espacio.isGameOver() || espacio.isVictory()) return;

            switch (e.getKeyCode()) {							
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
	
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    

	//// PINTAR LA MATRIZ ////
	private void pintarMatriz(Graphics g) {
		Espacio espacio = Espacio.getInstance();
		
		//Jugador -- Verde
		Jugador j= espacio.getJugador();
		if(j!=null && j.sigueVivo()) {					// Si el jugador existe y sigue vivo
			int px = j.getX() * TAM_CELDA; 				// Convertir coordenada logica a pixel
			int py = j.getY() * TAM_CELDA;
			g.setColor(Color.GREEN); 				
			g.fillRect(px, py, TAM_CELDA, TAM_CELDA); 	// Pintar celda del jugador
		}
		
		//Disparos -- Amarillo	
		ArrayList<Disparo> disparos = j.getDisparos();
		for(Disparo d : disparos) {
			if (d.isShooting()) {						// Si el disparo está activo
				int dx = d.getX() * TAM_CELDA; 			// Convertir coordenada logica a pixel
				int dy = d.getY() * TAM_CELDA;
				g.setColor(Color.YELLOW); 
				g.fillRect(dx, dy, TAM_CELDA, TAM_CELDA);
			}
		}

		//Enemigo -- Rojo
		for (Enemigo e : espacio.getEnemigos()) {
			if (e.sigueVivo()) {							// Si el enemigo sigue vivo
				int px = e.getX() * TAM_CELDA; 				// Convertir coordenada logica a pixel
				int py = e.getY() * TAM_CELDA;
				g.setColor(Color.RED); 
				g.fillRect(px, py, TAM_CELDA, TAM_CELDA);	 // Pintar celda del enemigo
			}
		}
	}

	
}
