import java.awt.Color;

import javax.swing.JFrame;



public class Main extends JFrame {

	public Main() {
		super("Analizador Lexico - MARVIN DAVID DIAZ 0900-10-4557");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Ventana panel = new Ventana();
		panel.setOpaque(false);
		this.setBackground(Color.white);
		super.setContentPane(panel);
		repaint();
		super.setVisible(true);
		super.setSize(600, 500);
		super.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
