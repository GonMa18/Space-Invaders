package viewControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Espacio;


@SuppressWarnings("deprecation")
public class StartFrame extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	/**
	 * Create the frame.
	 */
    public StartFrame() {
        setTitle("Space Invaders by RetroDevs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Cierra la aplicación al cerrar el frame
        setResizable(false);
        
        Espacio.getInstance().addObserver(this); 			// Para que el frame se actualice cuando el espacio cambie
        //Esto si que es legal
        
        //// FONDO ////
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/Resources/Images/Fondo.png"));
        Image bgImage = bgIcon.getImage();

        JPanel panel = new JPanel(new GridBagLayout()) {	//Panel del fondo
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setPreferredSize(new Dimension(400, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        //// CABECERA ////
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Resources/Images/Titulo.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel titulo = new JLabel(scaledIcon);
        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.insets = new Insets(10, 0, 10, 0);
        gbcTitulo.anchor = GridBagConstraints.CENTER;
        panel.add(titulo, gbcTitulo);

        //// INSTRUCCIONES ////
        ImageIcon pressSpaceIcon = new ImageIcon(getClass().getResource("/Resources/Images/Press Space.png"));
        Image pressSpaceScaled = pressSpaceIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel subtitulo1 = new JLabel(new ImageIcon(pressSpaceScaled));
        subtitulo1.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints gbcPress = new GridBagConstraints();
        gbcPress.gridx = 0;               
        gbcPress.gridy = 1;
        gbcPress.insets = new Insets(10, 0, 10, 0);
        gbcPress.anchor = GridBagConstraints.CENTER;
        panel.add(subtitulo1, gbcPress);

        //// CONTROLES ////
        JLabel controles = new JLabel("Flechas para moverte y espacio para disparar");
        controles.setForeground(Color.DARK_GRAY);
        controles.setFont(new Font("Serif", Font.PLAIN, 10));
        GridBagConstraints gbcControles = new GridBagConstraints();
        gbcControles.gridx = 0;
        gbcControles.gridy = 2;
        gbcControles.insets = new Insets(10, 0, 10, 0);
        gbcControles.anchor = GridBagConstraints.CENTER;
        panel.add(controles, gbcControles); 

        getContentPane().add(panel);	
        
        //// DESARROLLADORES ////
        JLabel developers = new JLabel("by RetroDevs for Sprint 1");
        developers.setForeground(new Color(255, 128, 0));
        developers.setFont(new Font("SansSerif", Font.ITALIC, 10));
        GridBagConstraints gbc_developers = new GridBagConstraints();
        gbc_developers.gridx = 0;
        gbc_developers.gridy = 3;
        panel.add(developers, gbc_developers);
        				
        
        pack(); 								// Ajusta el tamaño del frame al contenido
        setLocationRelativeTo(null); 			// Centra el frame en la pantalla
        setVisible(true); 

        //// TECLADO ////
        setFocusable(true);						// Para que el frame pueda recibir eventos de teclado
        requestFocusInWindow();	
        
		// =====================================================================
		// 								CONTROLLER
		// =====================================================================
        
        Controller controller = new Controller();		// Creamos el controlador para que se encargue de gestionar el juego
        addKeyListener(controller);						// Añadimos el controlador como listener de teclado para que pueda gestionar los eventos de teclado
   
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    @Override
    public void update(Observable o, Object arg) {
    	     
		//// CASTING A STRING ////

		Object[] res = (Object[])arg;					
		String resul=(String) (res[0]);					
    	
    	if (resul.equals("iniciar")) {
    			Espacio.getInstance().deleteObserver(this); // Eliminamos el StartFrame como observador para evitar futuras actualizaciones
    			this.dispose(); 							// Cerramos el StartFrame
    			new MainFrame(); 							// Abrimos el MainFrame para iniciar el juego DESDE EL CONTROLLER
    			//Esto si es legal
    	}

	}
    
     
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
	// =====================================================================
	// 								CONTROLLER
	// =====================================================================
    
    // Gestiona la interaccion del jugadotr con el juego, recibe los eventos de teclado y gestiona el espacio
    // Seguimo la arquitectura MVC: controlador dentro de las views
    
    private class Controller implements KeyListener {		//Viene implementada ya en JAVA
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {		// Si se pulsa espacio, iniciamos el juego
		    	Espacio.getInstance().cambiarAMain();		
			}
		}
		@Override public void keyReleased(KeyEvent e) {}
		@Override public void keyTyped(KeyEvent e) {}
	}
    
    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
}
