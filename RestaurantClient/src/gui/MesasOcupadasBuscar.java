package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.*;

import business_delegate.BusinessDelegate;
import dto.MesaOcupacionView;
import dto.SucursalView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
import excepciones.SucursalNoExisteException;

public class MesasOcupadasBuscar extends JInternalFrame {
	private static final long serialVersionUID = -52201933573009057L;
	private JLabel lblSucursal;
	private JComboBox<SucursalView> comboSucursal;
	private JButton btnBuscar, btnSalir;
	private MesasOcupadasBuscar aux;

	public MesasOcupadasBuscar(String[] sucursales) {
		super("Mesas Ocupadas", false, true, false, true);
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

		btnBuscar = new JButton("Buscar");
		btnSalir = new JButton("Salir");
		p.add(btnBuscar);
		p.add(btnSalir);

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sucursal = (String) comboSucursal.getSelectedItem();
				if (sucursal == null || sucursal.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar una sucursal");
					return;
				}

				try {
					List<MesaOcupacionView> mesas = BusinessDelegate.getInstancia().mesasOcupadas(sucursal);
					if (mesas.size() == 0) {
						JOptionPane.showMessageDialog(aux, "No hay mesas ocupadas");
						return;
					}
					MesasOcupadasLista mesasOcupadasLista = new MesasOcupadasLista(mesas);
					aux.getParent().add(mesasOcupadasLista);
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
