

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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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

	/**
	 * Create the frame.
	 */
    public StartFrame() {
        setTitle("Space Invaders");
        setTitle("by RetroDevs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel central con fondo negro
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(400, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        // CABECERA //
        ImageIcon originalIcon = new ImageIcon("Resources/Titulo.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(350, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel titulo = new JLabel(scaledIcon);
        gbc.gridy = 0;
        panel.add(titulo, gbc);

        JLabel subtitulo = new JLabel("by RetroDevs for Sprint 1");
        subtitulo.setForeground(Color.ORANGE);
        subtitulo.setFont(new Font("Serif", Font.ITALIC, 15));
        gbc.gridy = 1;
        panel.add(subtitulo, gbc);
        

        // INSTRUCCIONES //
        ImageIcon pressSpaceIcon = new ImageIcon("Resources/Press Space.png");
        Image pressSpaceScaled = pressSpaceIcon.getImage().getScaledInstance(250, 50, Image.SCALE_SMOOTH);
        JLabel subtitulo1 = new JLabel(new ImageIcon(pressSpaceScaled));
        subtitulo1.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = 1;
        panel.add(subtitulo1, gbc);
        
       // JLabel subtitulo = new JLabel("by RetroDevs for Sprint 1");
        //subtitulo.setForeground(Color.ORANGE);
        //subtitulo.setFont(new Font("Serif", Font.ITALIC, 15));
        //gbc.gridy = 1;
        //panel.add(subtitulo, gbc);

        JLabel controles = new JLabel("Flechas para moverte y espacio para disparar");
        controles.setForeground(Color.DARK_GRAY);
        controles.setFont(new Font("Serif", Font.PLAIN, 10));
        gbc.gridy = 3;
        panel.add(controles, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // TECLADO //
        setFocusable(true);	// Para que el frame pueda recibir eventos de teclado
        requestFocusInWindow();	

        // SPACE PARA INICIAR //
        addKeyListener(new KeyListener() { //Listener que escucha el teclado
            @Override
            public void keyPressed(KeyEvent e) {	//Cuando se pulse una tecla
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {	//Si la tecla es espacio
                    iniciarJuego();	//Iniciamos el juego
                }
            }
            @Override public void keyReleased(KeyEvent e) {}
            @Override public void keyTyped(KeyEvent e) {}
        });
    }

    private void iniciarJuego() {	//Iniciamos el juego
        dispose(); 	// Cerramos el frame de inicio: lo destruye
        
        //setVisible(false);     //Solo lo oculta, pero queremos
        						//seguir usando el SPACE, asi que lo destruimos
        new MainFrame();	// Abrimos el frame principal
    }

}
