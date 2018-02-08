package gui;

import business_delegate.BusinessDelegate;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
import excepciones.NoHayMesasDisponiblesException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Date;

public class MesaReservar extends JInternalFrame {
	private static final long serialVersionUID = 3623782223140267378L;
	private JLabel lblSucursal;
	private JComboBox comboSucursal;
	private JLabel lblCantidadPersonas;
	private JTextField txtCantidadPersonas;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JButton btnReservar, btnSalir;
	private MesaReservar aux;

	public MesaReservar(String[] sucursales) {
		super("Reservar Mesa", false, true, false, true);
		configurar(sucursales);
		this.setVisible(true);
		this.pack();
		aux = this;
	}

	private void configurar(String[] sucursales) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 2));

		lblSucursal = new JLabel("Sucursal", JLabel.TRAILING);
		comboSucursal = new JComboBox(sucursales);
		lblSucursal.setLabelFor(comboSucursal);
		p.add(lblSucursal);
		p.add(comboSucursal);

		lblCantidadPersonas = new JLabel("Cantidad de Personas", JLabel.TRAILING);
		txtCantidadPersonas = new JTextField(10);
		lblCantidadPersonas.setLabelFor(txtCantidadPersonas);
		p.add(lblCantidadPersonas);
		p.add(txtCantidadPersonas);

		lblFecha = new JLabel("Fecha", JLabel.TRAILING);
		txtFecha = new JTextField(10);
		lblFecha.setLabelFor(txtFecha);
		p.add(lblFecha);
		p.add(txtFecha);

		btnReservar = new JButton("Reservar");
		btnSalir = new JButton("Salir");
		p.add(btnReservar);
		p.add(btnSalir);

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnReservar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sucursal = (String) comboSucursal.getSelectedItem();
				if (sucursal == null || sucursal.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar una sucursal");
					return;
				}

				String cantidadPersonas = txtCantidadPersonas.getText();
				if (cantidadPersonas == null || cantidadPersonas.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar una cantidad de personas");
					return;
				}

				int cantPersonas = 0;
				try {
					cantPersonas = Integer.parseInt(cantidadPersonas);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(aux, "Cantidad de Personas debe ser un número");
					return;
				}

				String fecha = txtFecha.getText();
				if (fecha == null || fecha.length() == 0 || Date.valueOf(fecha) == null) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar una fecha válida");
					return;
				}

				try {
					BusinessDelegate.getInstancia().reservarMesa(sucursal, cantPersonas, Date.valueOf(fecha));
					JOptionPane.showMessageDialog(aux, "Mesas reservada correctamente");
					aux.cerrar();
				} catch (BaseDeDatosException | ConexionException | NoHayMesasDisponiblesException ex) {
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
