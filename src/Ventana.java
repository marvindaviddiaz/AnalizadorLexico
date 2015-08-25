import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana extends JPanel implements ActionListener {

	private JButton analizar;
	private JButton abrir;
	private JFileChooser fileChooser;
	Image image;
	Lector lector;
	Analizador analizador;
	private static final String SALTO_LINEA = "\n";

	public Ventana() {
		image = new ImageIcon(getClass().getResource("logo.jpg")).getImage();
		this.setBackground(Color.white);
		this.setLayout(null);
		super.setVisible(true);
		super.setSize(600, 500);
		fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos txt's", "txt");
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Seleccione archivo para Escanear");

		abrir = new JButton("Abrir");
		abrir.addActionListener(this);
		abrir.setBounds(100, 100, 100, 25);
		this.add(abrir);

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == abrir) {
			int seleccion = fileChooser.showOpenDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					FileInputStream fis = new FileInputStream(file);
					List<StringBuilder> lista;
					lector = new Lector(fis);
					lista = lector.obtenerTokens();
					analizador = new Analizador(lista);
					StringBuilder resultado = new StringBuilder();
					for (StringBuilder s : analizador.analizar()) {
						resultado.append(s).append(SALTO_LINEA);
					}

					String ruta = file.getAbsolutePath() + "_" + new Date().getTime() + ".txt";
					FileOutputStream fos = new FileOutputStream(ruta);

					DataOutputStream dos = new DataOutputStream(fos);
					String tittle = new String("######################   RESULTADO   ######################\n\n");
					dos.writeBytes(tittle);
					dos.writeBytes(resultado.toString());
					dos.close();
					fos.close();
					JOptionPane.showMessageDialog(this, "El archivo se escaneo exitosamente:\n " + ruta);

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, getWidth() - 384, getHeight() - 236, 384, 236, this);
		super.paint(g);
	}
}
