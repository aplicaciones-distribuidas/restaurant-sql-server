package gui;

import business_delegate.BusinessDelegate;
import dto.ComisionView;
import dto.SucursalView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
import excepciones.SucursalNoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

public class ComisionesBuscar extends JInternalFrame {
	private static final long serialVersionUID = -52210234575006057L;
	private JLabel lblSucursal;
	private JComboBox<SucursalView> comboSucursal;
	private JButton btnVer, btnSalir;
	private ComisionesBuscar aux;

	public ComisionesBuscar(String[] sucursales) {
		super("Comisiones", false, true, false, true);
		configurar(sucursales);
		this.setVisible(true);
		this.pack();
		aux = this;
	}

	private void configurar(String[] sucursales) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));

		lblSucursal = new JLabel("Sucursal", JLabel.TRAILING);
		comboSucursal = new JComboBox(sucursales);
		lblSucursal.setLabelFor(comboSucursal);
		p.add(lblSucursal);
		p.add(comboSucursal);

		btnVer = new JButton("Ver");
		btnSalir = new JButton("Salir");
		p.add(btnVer);
		p.add(btnSalir);

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnVer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sucursal = (String) comboSucursal.getSelectedItem();
				if (sucursal == null || sucursal.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar una sucursal");
					return;
				}

				try {
					List<ComisionView> comisiones = BusinessDelegate.getInstancia().getComisionesMozos(sucursal);
					if (comisiones.size() == 0) {
						JOptionPane.showMessageDialog(aux, "No hay comisiones");
						return;
					}
					ComisionesLista comisionesLista = new ComisionesLista(comisiones);
					aux.getParent().add(comisionesLista);
					aux.cerrar();
				} catch (BaseDeDatosException | SucursalNoExisteException | ConexionException ex) {
					JOptionPane.showMessageDialog(aux, ex.getMessage());
				}
			}
		});

		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aux.cerrar();
			}
		});
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
