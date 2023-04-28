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
import com.hibernate.model.Cliente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Component;



public class App {

	private JFrame frame;
	private JTable tablaPeliculas;
	private JTable tableEjercicios;

	
	
	
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
		
		JScrollPane scrollPaneClientes = new JScrollPane(tablaClientes);
		scrollPaneClientes.setBounds(32, 32, 536, 182);
		frame.getContentPane().add(scrollPaneClientes);
		
		
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
			}
		});
		btnActualizarTabla.setBounds(32, 383, 117, 25);
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
		
		JScrollPane scrollPaneEjercicios = new JScrollPane((Component) null);
		scrollPaneEjercicios.setBounds(591, 32, 536, 182);
		frame.getContentPane().add(scrollPaneEjercicios);
		
		tableEjercicios = new JTable((TableModel) null);
		scrollPaneEjercicios.setViewportView(tableEjercicios);
		
		JLabel lblClientes_1 = new JLabel("EJERCICIOS");
		lblClientes_1.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes_1.setBounds(592, 12, 117, 32);
		frame.getContentPane().add(lblClientes_1);
		
	}
}
