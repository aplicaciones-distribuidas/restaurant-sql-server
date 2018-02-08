package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.MesaView;

public class MesasDisponiblesLista extends JInternalFrame {
	private static final long serialVersionUID = -644489175035527974L;
	// private JInternalFrame aux;

	public MesasDisponiblesLista(List<MesaView> mesas) {
		super("Mesas Disponibles", true, true, false, true);
		configurar(mesas);
		this.setVisible(true);
		this.pack();
		// aux = this;
	}

	private void configurar(List<MesaView> mesas) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(mesas.size() + 1, 3));

		JLabel lblTituloNumero = new JLabel("Número");
		JLabel lblTituloSectorSalon = new JLabel("Sector Salón");
		JLabel lblTituloAccion = new JLabel("Acción");
		p.add(lblTituloNumero);
		p.add(lblTituloSectorSalon);
		p.add(lblTituloAccion);

		for (MesaView mesa : mesas) {
			p.add(new JLabel(String.valueOf(mesa.getNumero())));
			p.add(new JLabel(mesa.getSectorSalon().getNombre()));
			JButton btnOcupar = new JButton("Ocupar");
			btnOcupar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(mesa.toString());
				}
			});
			p.add(btnOcupar);
		}

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
