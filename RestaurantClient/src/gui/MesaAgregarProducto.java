package gui;

import business_delegate.BusinessDelegate;
import excepciones.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

public class MesaAgregarProducto extends JInternalFrame {
	private static final long serialVersionUID = 3623782223140267378L;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JLabel lblCantidadProducto;
	private JTextField txtCantidadProducto;
	private JButton btnAgregar, btnSalir;
	private MesaAgregarProducto aux;

	private Long idMesaOcupacion;

	public MesaAgregarProducto(Long idMesaOcupacion) {
		super("Agregar Producto", false, true, false, true);

		this.idMesaOcupacion = idMesaOcupacion;

		configurar();
		this.setVisible(true);
		this.pack();
		aux = this;
	}

	private void configurar() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));

		lblProducto = new JLabel("Producto", JLabel.TRAILING);
		txtProducto = new JTextField(10);
		lblProducto.setLabelFor(txtProducto);
		p.add(lblProducto);
		p.add(txtProducto);

		lblCantidadProducto = new JLabel("Cantidad del Producto", JLabel.TRAILING);
		txtCantidadProducto = new JTextField("1", 10);
		lblCantidadProducto.setLabelFor(txtCantidadProducto);
		p.add(lblCantidadProducto);
		p.add(txtCantidadProducto);

		btnAgregar = new JButton("Agregar");
		btnSalir = new JButton("Salir");
		p.add(btnAgregar);
		p.add(btnSalir);

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String producto = txtProducto.getText();
				if (producto == null || producto.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar un producto");
					return;
				}

				String cantidadProducto = txtCantidadProducto.getText();
				if (cantidadProducto == null || cantidadProducto.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar una cantidad del producto");
					return;
				}

				Long idProducto;
				try {
					idProducto = Long.parseLong(producto);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(aux, "Producto debe ser un número");
					return;
				}

				int cantProducto;
				try {
					cantProducto = Integer.parseInt(cantidadProducto);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(aux, "Cantidad del Producto debe ser un número");
					return;
				}

				try {
					BusinessDelegate.getInstancia().agregarProductoAMesa(aux.idMesaOcupacion, idProducto, cantProducto);
					JOptionPane.showMessageDialog(aux, "Producto agregado correctamente");
					aux.cerrar();
				} catch (BaseDeDatosException | ProductoNoExisteException | InsumoNoExisteException | MesaOcupacionNoExisteException | ProductoSinStockException | ConexionException ex) {
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
