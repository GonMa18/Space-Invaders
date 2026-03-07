package viewControl;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FinishFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinishFrame frame = new FinishFrame("¡Has perdido!"); // Puedes personalizar el mensaje
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
	public FinishFrame(String resultado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ImageIcon iconoFondo = new ImageIcon("Resources/Fondo.png");
		Image imagenFondo = iconoFondo.getImage();
		
		 // Establecer el fondo del panel
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(java.awt.Graphics g) {
				super.paintComponent(g);
				g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
			}
		};
		
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		 // Elegir imagen según resultado
	    String rutaImagen = resultado.equals("Win") 
	        ? "Resources/You Win.png" 
	        : "Resources/Game Over.png";
		
		ImageIcon iconoGameOver = new ImageIcon(rutaImagen);
		int alturaImagen = 200;
		int anchoImagen = iconoGameOver.getIconWidth() * alturaImagen / iconoGameOver.getIconHeight();
		Image imagenGameOver = iconoGameOver.getImage().getScaledInstance(anchoImagen, alturaImagen, Image.SCALE_SMOOTH);
		JLabel label = new JLabel(new ImageIcon(imagenGameOver));
		int x = (450 - anchoImagen) / 2; // centrado horizontal
		label.setBounds(x, 20, anchoImagen, alturaImagen);
		contentPane.add(label);

		setVisible(true);
		setLocationRelativeTo(null);
	}

}
