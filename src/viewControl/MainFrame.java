package viewControl;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Espacio;

public class MainFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MainFrame() {
		setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// FONDO //
		ImageIcon iconoFondo = new ImageIcon("Resources/Fondo.png");
		Image imagenFondo = iconoFondo.getImage();

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(java.awt.Graphics g) {
				super.paintComponent(g);
				g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setPreferredSize(new Dimension(400, 300));
		contentPane.setLayout(null); // Layout null para posicionar naves manualmente despues
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
		// Aqui ira la logica de repintado del tablero
		repaint();
	}

}
