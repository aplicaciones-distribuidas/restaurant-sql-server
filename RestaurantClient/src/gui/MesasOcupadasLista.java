package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import dto.MesaOcupacionView;
import dto.MesaView;

import javax.swing.*;

public class MesasOcupadasLista extends JInternalFrame {
	private static final long serialVersionUID = -644489175035527974L;
	private MesasOcupadasLista aux;

	public MesasOcupadasLista(List<MesaOcupacionView> mesas) {
		super("Mesas Ocupadas", true, true, false, true);
		configurar(mesas);
		this.setVisible(true);
		this.pack();
		aux = this;
	}

	private void configurar(List<MesaOcupacionView> mesasOcupacion) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(mesasOcupacion.size() + 1, 4));

		JLabel lblTituloNumero = new JLabel("Mesas");
		p.add(lblTituloNumero);

		JLabel lblTituloSectorSalon = new JLabel("Sector Salón");
		p.add(lblTituloSectorSalon);

		JLabel lblTituloProductos = new JLabel("Productos");
		p.add(lblTituloProductos);

		JLabel lblTituloCerrar = new JLabel("Cerrar");
		p.add(lblTituloCerrar);

		for (MesaOcupacionView mesaOcupacion : mesasOcupacion) {
			String mesasAsignadas = "";
			String sectorSalon = "";
			for (MesaView mesa : mesaOcupacion.getMesaItems()) {
				if (mesasAsignadas.length() > 0)
					mesasAsignadas += ", ";
				mesasAsignadas += mesa.getNumero();
				sectorSalon = mesa.getSectorSalon().getNombre();
			}
			p.add(new JLabel(mesasAsignadas));
			p.add(new JLabel(sectorSalon));

			JButton btnProductos = new JButton("Agregar");
			p.add(btnProductos);

			JButton btnCerrar = new JButton("Cerrar");
			p.add(btnCerrar);

			btnProductos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MesaAgregarProducto mesaAgregarProducto = new MesaAgregarProducto(mesaOcupacion.getId());
					aux.getParent().add(mesaAgregarProducto);
					aux.cerrar();
				}
			});

			btnCerrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MesaCerrar mesaCerrar = new MesaCerrar(mesaOcupacion);
					aux.getParent().add(mesaCerrar);
					aux.cerrar();
				}
			});
		}

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void cerrar() {
		try {
			this.setClosed(true);
		} catch (PropertyVetoException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cerrar ventana");
		}
	}
}
