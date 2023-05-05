package com.hibernate.gui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.ClienteDAO;
import com.hibernate.dao.ClienteEjercicioDAO;
import com.hibernate.dao.EjercicioDAO;
import com.hibernate.model.CE;
import com.hibernate.model.Cliente;
import com.hibernate.model.Ejercicio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JList;



public class App {

	private JFrame frame;
	private JTextField textField_series;
	private JTextField textField_repeticiones;
	private JTextField textField_cargaEnKg;
	private JTextField textField_nombreEjercicio;
	private JTextField textField_nombreCliente;
	private JTextField textField_apellidosCliente;
	private JTextField textField_edad;
	private JTextField textField_altura;
	private JTextField textField_peso;
	private JTextField textField_idCliente;
	private JTextField textField_idEjercicio;
	private JTextField textField;
	private JTextField textField_1;
	

	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1475, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		DefaultTableModel modelClientes = new DefaultTableModel();
		
		modelClientes.addColumn("id");
		modelClientes.addColumn("Nombre");
		modelClientes.addColumn("Apellidos");
		modelClientes.addColumn("Edad");
		modelClientes.addColumn("Altura");
		modelClientes.addColumn("Peso");
		
		DefaultTableModel modelEjercicios = new DefaultTableModel();
		
		modelEjercicios.addColumn("id");
		modelEjercicios.addColumn("Nombre");
		modelEjercicios.addColumn("Numero de Series");
		modelEjercicios.addColumn("Repeticiones");
		modelEjercicios.addColumn("Carga en Kg");
		
		DefaultTableModel modelCE = new DefaultTableModel();
		
		modelCE.addColumn("cliente_id");
		modelCE.addColumn("ejercicio_id");
	
		
		JTable tablaEjercicios = new JTable(modelEjercicios);
		tablaEjercicios.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaEjercicios.getColumnModel().getColumn(1).setPreferredWidth(150);
		tablaEjercicios.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablaEjercicios.getColumnModel().getColumn(3).setPreferredWidth(20);
		tablaEjercicios.getColumnModel().getColumn(4).setPreferredWidth(20);
		tablaEjercicios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablaEjercicios.getSelectedRow();
				TableModel model = tablaEjercicios.getModel();
				textField_idEjercicio.setText(model.getValueAt(index, 0).toString());
				textField_nombreEjercicio.setText(model.getValueAt(index, 1).toString());
				textField_series.setText(model.getValueAt(index, 2).toString());
				textField_repeticiones.setText(model.getValueAt(index, 3).toString());
				textField_cargaEnKg.setText(model.getValueAt(index, 4).toString());
				
				
				
				
			}
		});
		
		
		
		JTable tablaClientes = new JTable(modelClientes);
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(50);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(20);
		tablaClientes.getColumnModel().getColumn(4).setPreferredWidth(20);
		tablaClientes.getColumnModel().getColumn(5).setPreferredWidth(20);
		tablaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablaClientes.getSelectedRow();
				TableModel model = tablaClientes.getModel();
				
				textField_idCliente.setText(model.getValueAt(index, 0).toString());
				textField_nombreCliente.setText(model.getValueAt(index, 1).toString());
				textField_apellidosCliente.setText(model.getValueAt(index, 2).toString());
				textField_edad.setText(model.getValueAt(index, 3).toString());
				textField_altura.setText(model.getValueAt(index, 4).toString());
				textField_peso.setText(model.getValueAt(index, 5).toString());
				
			}
		});
		
		JTable tablaCE = new JTable(modelCE);
		
		
		JScrollPane scrollPaneClientes = new JScrollPane(tablaClientes);
		scrollPaneClientes.setBounds(32, 32, 536, 182);
		frame.getContentPane().add(scrollPaneClientes);
		
		JScrollPane scrollPaneEjercicios = new JScrollPane(tablaEjercicios);
		scrollPaneEjercicios.setBounds(591, 32, 536, 182);
		frame.getContentPane().add(scrollPaneEjercicios);
		
		JScrollPane scrollPaneCE = new JScrollPane(tablaCE);
		scrollPaneCE.setBounds(1152, 432, 290, 182);
		frame.getContentPane().add(scrollPaneCE);
		
		JComboBox comboBox_MostrarClientes = new JComboBox();
		comboBox_MostrarClientes.setBounds(262, 348, 134, 24);
		frame.getContentPane().add(comboBox_MostrarClientes);
		
		JComboBox comboBox_clienteId = new JComboBox();
		comboBox_clienteId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cliente_id = comboBox_clienteId.getSelectedItem().toString();
				String[] clienting = cliente_id.split(":");
				String idClienteExtraido = clienting[0];
				
				List<> 
			}
		});
		
		comboBox_clienteId.setBounds(1161, 183, 144, 24);
		frame.getContentPane().add(comboBox_clienteId);
				
		JComboBox comboBox_MostrarEjercicios = new JComboBox();
		comboBox_MostrarEjercicios.setBounds(1308, 368, 134, 24);
		frame.getContentPane().add(comboBox_MostrarEjercicios);
		
		JButton btnActualizarTabla = new JButton("");
		btnActualizarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelClientes.setRowCount(0);
				List<Cliente> clientes = ClienteDAO.selectAllClientes();
				for(Cliente s: clientes) {
					Object[] row = new Object[6];
					row[0] = s.getId();
					row[1] = s.getNombre();
					row[2] = s.getApellidos();
					row[3] = s.getEdad();
					row[4] = s.getAltura();
					row[5] = s.getPeso();
					modelClientes.addRow(row);
				}
				modelEjercicios.setRowCount(0);
				List<Ejercicio> ejercicios = EjercicioDAO.selectAllEjercicios();
				for(Ejercicio ej: ejercicios) {
					Object[] row = new Object[5];
					row[0] = ej.getId();
					row[1] = ej.getNombre();
					row[2] = ej.getNumeroDeSeries();
					row[3] = ej.getRepeticiones();
					row[4] = ej.getCargaEnKg();
					modelEjercicios.addRow(row);
				}
				modelCE.setRowCount(0);
				List<CE> clientesejercicios = ClienteEjercicioDAO.selectAllCES();
				for(CE ce: clientesejercicios) {
					Object[] row = new Object[2];
					row[0] = ce.getCliente_id();
					row[1] = ce.getEjercicio_id();
					modelCE.addRow(row);
				}
				
				comboBox_MostrarClientes.removeAllItems();
				comboBox_clienteId.removeAllItems();
				comboBox_MostrarEjercicios.removeAllItems();
			
				List<Cliente> clientesId = ClienteDAO.selectAllClientes();
				for(Cliente c: clientesId) {
					comboBox_MostrarClientes.addItem(c.getId() + ":" + c.getNombre());

					comboBox_clienteId.addItem(c.getId() + ":" + c.getNombre());
					
				}
				
				List<Ejercicio> ejerciciosId = EjercicioDAO.selectAllEjercicios();
				for(Ejercicio ej: ejerciciosId) {
					comboBox_MostrarEjercicios.addItem(ej.getId() + ":" + ej.getNombre());
				}
				
			}
		});
		btnActualizarTabla.setBounds(1271, 626, 117, 25);
		frame.getContentPane().add(btnActualizarTabla);
		btnActualizarTabla.setVisible(false);
		btnActualizarTabla.doClick();
		
		
		JButton btnGuardar = new JButton("Añadir");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Cliente s = new Cliente(textField_nombreCliente.getText(),textField_apellidosCliente.getText(), Integer.parseInt(textField_edad.getText()),Double.parseDouble(textField_altura.getText()),Integer.parseInt(textField_peso.getText()));
					ClienteDAO.insertCliente(s);
					btnActualizarTabla.doClick();
					
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}
		});
		btnGuardar.setBounds(32, 226, 132, 32);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Cliente c = ClienteDAO.selectClienteByID(Integer.valueOf(textField_idCliente.getText()));
					c.setNombre(textField_nombreCliente.getText());
					c.setApellidos(textField_apellidosCliente.getText());
					c.setEdad(Integer.valueOf(Integer.parseInt(textField_edad.getText())));
					c.setAltura(Double.parseDouble(textField_altura.getText()));
					c.setPeso(Integer.parseInt(textField_peso.getText()));
					ClienteDAO.updateCliente(c);
					btnActualizarTabla.doClick();
					
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btnActualizar.setBounds(176, 226, 157, 32);
		frame.getContentPane().add(btnActualizar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Cliente s = ClienteDAO.selectClienteByID(Integer.valueOf(textField_idCliente.getText()));
					ClienteDAO.deleteCliente(s.getId());
					btnActualizarTabla.doClick();
					
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btnBorrar.setBounds(345, 226, 157, 32);
		frame.getContentPane().add(btnBorrar);
		
		JLabel lblClientes = new JLabel("CLIENTES");
		lblClientes.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes.setBounds(33, 12, 88, 32);
		frame.getContentPane().add(lblClientes);
		
		JButton button = new JButton("");
		button.setBounds(514, 226, 52, 32);
		frame.getContentPane().add(button);
		
		
		
		JLabel lblClientes_1 = new JLabel("EJERCICIOS");
		lblClientes_1.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes_1.setBounds(592, 12, 117, 32);
		frame.getContentPane().add(lblClientes_1);
		
		JButton btnGuardar_1 = new JButton("Añadir");
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Ejercicio ej = new Ejercicio(textField_nombreEjercicio.getText(),Integer.parseInt(textField_series.getText()), Integer.parseInt(textField_repeticiones.getText()),Integer.parseInt(textField_cargaEnKg.getText()));
				EjercicioDAO.insertEjercicio(ej);
				btnActualizarTabla.doClick();
			}
		});
		btnGuardar_1.setBounds(591, 226, 132, 32);
		frame.getContentPane().add(btnGuardar_1);
		
		JButton btnActualizar_1 = new JButton("Actualizar");
		btnActualizar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Ejercicio ej = EjercicioDAO.selectEjercicioByID(Integer.valueOf(textField_idEjercicio.getText()));
				ej.setNombre(textField_nombreEjercicio.getText());
				ej.setNumeroDeSeries(Integer.parseInt(textField_series.getText()));
				ej.setRepeticiones(Integer.valueOf(Integer.parseInt(textField_repeticiones.getText())));
				ej.setCargaEnKg(Integer.parseInt(textField_cargaEnKg.getText()));
				EjercicioDAO.updateEjercicio(ej);
				btnActualizarTabla.doClick();
				
			}
		});
		btnActualizar_1.setBounds(735, 226, 157, 32);
		frame.getContentPane().add(btnActualizar_1);
		
		JButton btnBorrar_1 = new JButton("Borrar");
		btnBorrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ejercicio ej = EjercicioDAO.selectEjercicioByID(Integer.valueOf(textField_idEjercicio.getText()));
				EjercicioDAO.deleteEjercicio(ej.getId());
				btnActualizarTabla.doClick();
			}
		});
		btnBorrar_1.setBounds(904, 226, 157, 32);
		frame.getContentPane().add(btnBorrar_1);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(1075, 226, 52, 32);
		frame.getContentPane().add(button_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(32, 383, 470, 268);
		frame.getContentPane().add(textPane);
		
		JLabel lblEjerciciosDelCliente = new JLabel("EJERCICIOS DEL CLIENTE:");
		lblEjerciciosDelCliente.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblEjerciciosDelCliente.setBounds(33, 348, 231, 32);
		frame.getContentPane().add(lblEjerciciosDelCliente);
		
		
		
		JButton btnBorrarRutina = 
				new JButton("Borrar");
		btnBorrarRutina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cliente_id = comboBox_clienteId.getSelectedItem().toString();
				String[] clienting = cliente_id.split(":");
				String idClienteExtraido = clienting[0];
				
				String ejercicio_id = comboBox_MostrarEjercicios.getSelectedItem().toString();
				String[] ejercicing = ejercicio_id.split(":");
				String idEjercicioExtraido = ejercicing[0];
				
				CE ce = new CE(Integer.parseInt(idClienteExtraido), Integer.parseInt(idEjercicioExtraido));
				ClienteEjercicioDAO.deleteCE(ce);
				btnActualizarTabla.doClick();
				
			}
		});
		btnBorrarRutina.setBounds(1161, 300, 249, 32);
		frame.getContentPane().add(btnBorrarRutina);
		
		
		
		
		JLabel lblNombre = new JLabel("nombre:");
		lblNombre.setBounds(591, 277, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblRepeticiones = new JLabel("repeticiones:");
		lblRepeticiones.setBounds(761, 309, 94, 15);
		frame.getContentPane().add(lblRepeticiones);
		
		JLabel lblSeries = new JLabel("series:");
		lblSeries.setBounds(967, 271, 52, 15);
		frame.getContentPane().add(lblSeries);
		
		textField_series = new JTextField();
		textField_series.setBounds(1024, 269, 37, 25);
		frame.getContentPane().add(textField_series);
		textField_series.setColumns(10);
		
		textField_repeticiones = new JTextField();
		textField_repeticiones.setColumns(10);
		textField_repeticiones.setBounds(855, 304, 37, 25);
		frame.getContentPane().add(textField_repeticiones);
		
		JLabel lblCargaEnKg = new JLabel("carga en KG:");
		lblCargaEnKg.setBounds(925, 309, 94, 15);
		frame.getContentPane().add(lblCargaEnKg);
		
		textField_cargaEnKg = new JTextField();
		textField_cargaEnKg.setColumns(10);
		textField_cargaEnKg.setBounds(1024, 304, 37, 25);
		frame.getContentPane().add(textField_cargaEnKg);
		
		textField_nombreEjercicio = new JTextField();
		textField_nombreEjercicio.setColumns(10);
		textField_nombreEjercicio.setBounds(655, 270, 237, 25);
		frame.getContentPane().add(textField_nombreEjercicio);
		
		JLabel lblNombre_1 = new JLabel("nombre:");
		lblNombre_1.setBounds(32, 273, 70, 15);
		frame.getContentPane().add(lblNombre_1);
		
		textField_nombreCliente = new JTextField();
		textField_nombreCliente.setColumns(10);
		textField_nombreCliente.setBounds(94, 270, 107, 25);
		frame.getContentPane().add(textField_nombreCliente);
		
		textField_apellidosCliente = new JTextField();
		textField_apellidosCliente.setColumns(10);
		textField_apellidosCliente.setBounds(285, 270, 217, 25);
		frame.getContentPane().add(textField_apellidosCliente);
		
		JLabel lblNombre_1_1 = new JLabel("apellidos:");
		lblNombre_1_1.setBounds(213, 273, 70, 15);
		frame.getContentPane().add(lblNombre_1_1);
		
		textField_edad = new JTextField();
		textField_edad.setColumns(10);
		textField_edad.setBounds(380, 306, 37, 25);
		frame.getContentPane().add(textField_edad);
		
		JLabel lblEdad = new JLabel("edad:");
		lblEdad.setBounds(333, 309, 70, 15);
		frame.getContentPane().add(lblEdad);
		
		JLabel lblAltura = new JLabel("altura:");
		lblAltura.setBounds(233, 307, 94, 15);
		frame.getContentPane().add(lblAltura);
		
		textField_altura = new JTextField();
		textField_altura.setColumns(10);
		textField_altura.setBounds(285, 306, 37, 25);
		frame.getContentPane().add(textField_altura);
		
		JLabel lblPeso = new JLabel("peso:");
		lblPeso.setBounds(423, 311, 59, 15);
		frame.getContentPane().add(lblPeso);
		
		textField_peso = new JTextField();
		textField_peso.setColumns(10);
		textField_peso.setBounds(465, 306, 37, 25);
		frame.getContentPane().add(textField_peso);
		
		JLabel lblNombre_1_2 = new JLabel("id:");
		lblNombre_1_2.setBounds(32, 311, 31, 15);
		frame.getContentPane().add(lblNombre_1_2);
		
		textField_idCliente = new JTextField();
		textField_idCliente.setColumns(10);
		textField_idCliente.setBounds(55, 300, 37, 25);
		frame.getContentPane().add(textField_idCliente);
		textField_idCliente.setEnabled(false);
		
		JLabel lblNombre_1_2_1 = new JLabel("id:");
		lblNombre_1_2_1.setBounds(630, 309, 31, 15);
		frame.getContentPane().add(lblNombre_1_2_1);
		
		textField_idEjercicio = new JTextField();
		textField_idEjercicio.setEnabled(false);
		textField_idEjercicio.setColumns(10);
		textField_idEjercicio.setBounds(655, 304, 37, 25);
		frame.getContentPane().add(textField_idEjercicio);
		
		JButton btnEliminar = new JButton("Añadir");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cliente_id = comboBox_clienteId.getSelectedItem().toString();
				String[] clienting = cliente_id.split(":");
				String idClienteExtraido = clienting[0];
				String ejercicio_id = comboBox_MostrarEjercicios.getSelectedItem().toString();
				String[] ejercicing = ejercicio_id.split(":");
				String idEjercicioExtraido = ejercicing[0];
				CE ce = new CE(Integer.parseInt(idClienteExtraido), Integer.parseInt(idEjercicioExtraido));
				ClienteEjercicioDAO.insertClienteEjercicio(ce);
				btnActualizarTabla.doClick();
			}
		});
		btnEliminar.setBounds(1161, 79, 270, 32);
		frame.getContentPane().add(btnEliminar);
		
		tablaCE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablaCE.getSelectedRow();
				TableModel model = tablaCE.getModel();
				
				int idCliente =  Integer.parseInt( model.getValueAt(index, 0).toString());
				int idEjercicio =  Integer.parseInt( model.getValueAt(index, 1).toString());
				
				
				JOptionPane.showConfirmDialog(frame, "Desea eliminar la rutina?", "Alerta", JOptionPane.YES_NO_OPTION, 0);
				
				
				
				Cliente c = ClienteDAO.selectClienteByID(idCliente);
				Ejercicio ej = EjercicioDAO.selectEjercicioByID(idEjercicio);
				
				
				
				comboBox_clienteId.setSelectedItem(idCliente + ":" + ClienteDAO.selectClienteByID(idCliente).getNombre());
				comboBox_MostrarEjercicios.setSelectedItem(idEjercicio + ":" + EjercicioDAO.selectEjercicioByID(idEjercicio).getNombre());
			}
		});
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 343, 1539, 15);
		frame.getContentPane().add(separator);
		
		JButton button_2 = new JButton("");
		button_2.setBounds(514, 269, 52, 62);
		frame.getContentPane().add(button_2);
		
		JButton button_2_1 = new JButton("");
		button_2_1.setBounds(1075, 269, 52, 62);
		frame.getContentPane().add(button_2_1);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.setBounds(400, 348, 102, 25);
		frame.getContentPane().add(btnMostrar);
		
		JList list = new JList();
		DefaultListModel modeloListaEjercicios = new DefaultListModel();
		list.setBounds(1160, 219, 250, 72);
		frame.getContentPane().add(list);
		
		JLabel lblClientes_1_1 = new JLabel("AFEGIR RUTINA");
		lblClientes_1_1.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes_1_1.setBounds(1161, 12, 161, 32);
		frame.getContentPane().add(lblClientes_1_1);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(1162, 44, 70, 15);
		frame.getContentPane().add(lblCliente);
		
		JLabel lblCliente_1 = new JLabel("Ejercicio");
		lblCliente_1.setBounds(1299, 44, 70, 15);
		frame.getContentPane().add(lblCliente_1);
		
		textField = new JTextField();
		textField.setBounds(1162, 59, 123, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(1297, 59, 134, 19);
		frame.getContentPane().add(textField_1);
		
		JLabel lblClientes_1_1_1 = new JLabel("BORRAR RUTINA");
		lblClientes_1_1_1.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes_1_1_1.setBounds(1161, 158, 161, 32);
		frame.getContentPane().add(lblClientes_1_1_1);
		
		
		
	}
}
