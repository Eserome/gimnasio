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
import javax.swing.JTextField;
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



public class App {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	

	
	
	
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
		frame.setBounds(100, 100, 1400, 700);
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
				/*
				textField.setText(model.getValueAt(index, 0).toString());
				textField_Serie.setText(model.getValueAt(index, 1).toString());
				textField_Temporadas.setText(model.getValueAt(index, 2).toString());
				textField_Episodios.setText(model.getValueAt(index, 3).toString());
				
				*/
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
				/*
				textField.setText(model.getValueAt(index, 0).toString());
				textField_Serie.setText(model.getValueAt(index, 1).toString());
				textField_Temporadas.setText(model.getValueAt(index, 2).toString());
				textField_Episodios.setText(model.getValueAt(index, 3).toString());
				
				*/
			}
		});
		
		JTable tablaCE = new JTable(modelCE);
		tablaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablaCE.getSelectedRow();
				TableModel model = tablaCE.getModel();
				/*
				textField.setText(model.getValueAt(index, 0).toString());
				textField_Serie.setText(model.getValueAt(index, 1).toString());
				textField_Temporadas.setText(model.getValueAt(index, 2).toString());
				textField_Episodios.setText(model.getValueAt(index, 3).toString());
				
				*/
			}
		});
		
		JScrollPane scrollPaneClientes = new JScrollPane(tablaClientes);
		scrollPaneClientes.setBounds(32, 32, 536, 182);
		frame.getContentPane().add(scrollPaneClientes);
		
		JScrollPane scrollPaneEjercicios = new JScrollPane(tablaEjercicios);
		scrollPaneEjercicios.setBounds(591, 32, 536, 182);
		frame.getContentPane().add(scrollPaneEjercicios);
		
		JScrollPane scrollPaneCE = new JScrollPane(tablaCE);
		scrollPaneCE.setBounds(1152, 32, 224, 182);
		frame.getContentPane().add(scrollPaneCE);
		
		
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
					/*
					Cliente s = new Cliente(textField_Serie.getText(),Integer.parseInt(textField_Temporadas.getText()), Integer.parseInt(textField_Episodios.getText()));
					ClienteDAO.insertCliente(s);
					btnActualizarTabla.doClick();
					*/
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
					/*
					Cliente s = ClienteDAO.selectSerieByID(Integer.valueOf(textField.getText()));
					s.setTitulo(textField_Serie.getText());
					s.setEpisodios(Integer.valueOf(textField_Episodios.getText()));
					s.setTemporadas(Integer.valueOf(textField_Temporadas.getText()));
					ClienteDAO.updateSerie(s);
					btnActualizarTabla.doClick();
					*/
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
					/*
					Cliente s = ClienteDAO.selectSerieByID(Integer.valueOf(textField.getText()));
					ClienteDAO.deleteSerie(s.getId());
					btnActualizarTabla.doClick();
					*/
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
		
		
		
		JLabel lblClientes_1_1 = new JLabel("CLIENTE - EJERCICIO");
		lblClientes_1_1.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes_1_1.setBounds(1156, 12, 179, 32);
		frame.getContentPane().add(lblClientes_1_1);
		
		JButton btnGuardar_1 = new JButton("Añadir");
		btnGuardar_1.setBounds(591, 226, 132, 32);
		frame.getContentPane().add(btnGuardar_1);
		
		JButton btnActualizar_1 = new JButton("Actualizar");
		btnActualizar_1.setBounds(735, 226, 157, 32);
		frame.getContentPane().add(btnActualizar_1);
		
		JButton btnBorrar_1 = new JButton("Borrar");
		btnBorrar_1.setBounds(904, 226, 157, 32);
		frame.getContentPane().add(btnBorrar_1);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(1075, 226, 52, 32);
		frame.getContentPane().add(button_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(32, 317, 470, 279);
		frame.getContentPane().add(textPane);
		
		JLabel lblEjerciciosDelCliente = new JLabel("EJERCICIOS DEL CLIENTE:");
		lblEjerciciosDelCliente.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblEjerciciosDelCliente.setBounds(33, 293, 231, 32);
		frame.getContentPane().add(lblEjerciciosDelCliente);
		
		JComboBox comboBoxMostrarEjercicios = new JComboBox();
		comboBoxMostrarEjercicios.setBounds(262, 293, 52, 24);
		frame.getContentPane().add(comboBoxMostrarEjercicios);
		
		JButton btnGuardar_1_1 = new JButton("Añadir");
		btnGuardar_1_1.setBounds(1152, 256, 224, 32);
		frame.getContentPane().add(btnGuardar_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(1152, 226, 107, 24);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(1271, 226, 107, 24);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblNombre = new JLabel("nombre:");
		lblNombre.setBounds(591, 277, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblRepeticiones = new JLabel("repeticiones:");
		lblRepeticiones.setBounds(855, 277, 94, 15);
		frame.getContentPane().add(lblRepeticiones);
		
		JLabel lblSeries = new JLabel("series:");
		lblSeries.setBounds(762, 277, 70, 15);
		frame.getContentPane().add(lblSeries);
		
		textField = new JTextField();
		textField.setBounds(813, 274, 37, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(951, 272, 37, 25);
		frame.getContentPane().add(textField_1);
		
		JLabel lblCargaEnKg = new JLabel("carga en KG:");
		lblCargaEnKg.setBounds(994, 277, 94, 15);
		frame.getContentPane().add(lblCargaEnKg);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(1090, 275, 37, 25);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(655, 272, 107, 25);
		frame.getContentPane().add(textField_3);
		
		List<Cliente> clientes = ClienteDAO.selectAllClientes();
		for(Cliente c: clientes) {
			comboBoxMostrarEjercicios.addItem(c.getId());
		}
		
		
		comboBoxMostrarEjercicios.getSelectedItem();
		
	}
}
