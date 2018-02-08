package gui;

import dto.ComisionView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComisionesLista extends JInternalFrame {
	private static final long serialVersionUID = -644289145055567974L;
	// private JInternalFrame aux;

	public ComisionesLista(List<ComisionView> comisiones) {
		super("Comisiones", true, true, false, true);
		configurar(comisiones);
		this.setVisible(true);
		this.pack();
		// aux = this;
	}

	private void configurar(List<ComisionView> comisiones) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(comisiones.size() + 1, 3));

		JLabel lblTituloNumero = new JLabel("Nombre");
		JLabel lblTituloSectorSalon = new JLabel("Apellido");
		JLabel lblTituloAccion = new JLabel("Comisión");
		p.add(lblTituloNumero);
		p.add(lblTituloSectorSalon);
		p.add(lblTituloAccion);

		for (ComisionView comision : comisiones) {
			p.add(new JLabel(String.valueOf(comision.getNombre())));
			p.add(new JLabel(comision.getApellido()));
			p.add(new JLabel(Float.toString(comision.getComision())));
		}

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
