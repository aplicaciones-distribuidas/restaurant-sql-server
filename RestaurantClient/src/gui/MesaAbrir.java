package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.*;

import business_delegate.BusinessDelegate;
import dto.MesaOcupacionView;
import dto.MesaView;
import excepciones.*;

public class MesaAbrir extends JInternalFrame {
	private static final long serialVersionUID = 3623782223140267378L;
	private JLabel lblSucursal;
	private JComboBox comboSucursal;
	private JLabel lblCantidadPersonas;
	private JTextField txtCantidadPersonas;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnAbrir, btnSalir;
	private MesaAbrir aux;

	public MesaAbrir(String[] sucursales) {
		super("Abrir Mesa", false, true, false, true);
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

		lblEmpleado = new JLabel("Empleado", JLabel.TRAILING);
		txtEmpleado = new JTextField(10);
		lblEmpleado.setLabelFor(txtEmpleado);
		p.add(lblEmpleado);
		p.add(txtEmpleado);

		btnAbrir = new JButton("Abrir");
		btnSalir = new JButton("Salir");
		p.add(btnAbrir);
		p.add(btnSalir);

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnAbrir.addActionListener(new ActionListener() {
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

				int cantPersonas;
				try {
					cantPersonas = Integer.parseInt(cantidadPersonas);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(aux, "Cantidad de Personas debe ser un número");
					return;
				}

				String empleado = txtEmpleado.getText();
				if (empleado == null || empleado.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar un empleado");
					return;
				}

				Long idEmpleado;
				try {
					idEmpleado = Long.parseLong(empleado);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(aux, "Empleado debe ser un número");
					return;
				}

				try {
					MesaOcupacionView mesaOcupacion = BusinessDelegate.getInstancia().abrirMesa(sucursal, cantPersonas,
							idEmpleado);
					String mesasAsignadas = "";
					for (MesaView mesa : mesaOcupacion.getMesaItems()) {
						if (mesasAsignadas.length() > 0)
							mesasAsignadas += ", ";
						mesasAsignadas += mesa.getNumero();
					}
					JOptionPane.showMessageDialog(aux, "Mesas asignadas: " + mesasAsignadas);
					aux.cerrar();
				} catch (ConexionException | BaseDeDatosException | NoHayMesasDisponiblesException | SucursalNoExisteException | EmpleadoNoExisteException ex) {
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
