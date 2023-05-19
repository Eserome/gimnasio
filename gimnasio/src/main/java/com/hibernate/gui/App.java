package com.hibernate.gui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JList;
import java.awt.Dimension;

public class App {

	private JFrame frame;
	private JScrollPane scrollLista;
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
	private JTextField textField_añadirClienteRutina;
	private JTextField textField_añadirEjercicioCliente;
	private JTextField picClientTextPath;
	private JTextField picExerciceTextPath;

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

	public static boolean comprobarValidezCamposEjercicio(JTextField textField_nombreEjercicio, JTextField textField_cargaEnKg, JTextField textField_repeticiones, JTextField textField_series, JTextField textField_nombreCliente, JTextField textField_apellidosCliente) {
		boolean todoOk = false;
		
		Pattern patternNombre = Pattern.compile("^\\w{1,12}$");
		Matcher matcherNombre = patternNombre.matcher(textField_nombreEjercicio.getText());
		String nombre = textField_nombreEjercicio.getText();
		
		Pattern patternCarga = Pattern.compile("^\\d{1,3}$");
		Matcher matcherCarga = patternCarga.matcher(textField_cargaEnKg.getText());
		int carga = Integer.parseInt(textField_cargaEnKg.getText());
		
		Pattern patternRepeticiones = Pattern.compile("^\\d{1,3}$");
		Matcher matcherRepeticiones = patternRepeticiones.matcher(textField_repeticiones.getText());
		int repeticiones = Integer.parseInt(textField_repeticiones.getText());
		
		Pattern patternSeries = Pattern.compile("^\\d{1,3}$");
		Matcher matcherSeries = patternCarga.matcher(textField_series.getText());
		int series = Integer.parseInt(textField_series.getText());
		
		if(textField_nombreCliente != null){
			todoOk = true;
		}else {
			todoOk = false;
			JOptionPane.showMessageDialog(null, "El campo nombre está vacío");
		}
		
		if(textField_apellidosCliente != null){
			todoOk = true;
		}else {
			todoOk = false;
			JOptionPane.showMessageDialog(null, "El campo apellido está vacío");
		}
		
		
		if(matcherNombre.matches()) {
				todoOk=true;
		} else {
			todoOk=false;
			JOptionPane.showMessageDialog(null, "El nombre del ejercicio debe contener máximo 12 carácteres");
		}
		if(matcherCarga.matches()) {
				if(carga >= 0 && carga <= 256) {
					todoOk=true;
				} else {
					todoOk=false;
					JOptionPane.showMessageDialog(null, "La carga está fuera de los rangos (0-256) KG");
				}
		} else {
			todoOk = false;
			JOptionPane.showMessageDialog(null, "La carga debe estar comprendida entre 0-256 KG");
		}
		if(matcherRepeticiones.matches() && matcherSeries.matches()) {
			if((repeticiones >= 0 && repeticiones <= 100) || (series >= 0 && series <= 100)) {
				todoOk=true;
			} else {
				todoOk=false;
				JOptionPane.showMessageDialog(null, "Las repeticiones o las series están fuera de los rangos (0-100)");
			}
	} else {
		todoOk = false;
		JOptionPane.showMessageDialog(null, "Las repeticiones y las series deben estar comprendidas entre 0 y 100");
	}
		
		
		return todoOk;
			
		
		
	}
	
	public static boolean comprobarValidezCamposCliente(JTextField textField_altura, JTextField textField_edad,
			JTextField textField_peso, JTextField textField_nombreCliente, JTextField textField_apellidosCliente,
			JTextField picClientTextPath) {
		boolean todoOk = false;

		Pattern patternComprobarNumero = Pattern.compile("^\\d+$");
		Matcher matcherNumero = patternComprobarNumero.matcher(textField_edad.getText());
		boolean esNumero = matcherNumero.matches();
		
		Pattern patternAltura = Pattern.compile("^\\d?\\.?\\d{1,2}$");
		Matcher matcherAltura = patternAltura.matcher(textField_altura.getText());
		double altura = Double.parseDouble(textField_altura.getText());

		
		
		//TO DO : PONER RETURN PARA QUE NO SE ME CONCATENEN ERRORES
		
		

		if (!textField_nombreCliente.getText().isEmpty()) {
			todoOk = true;
		} else {
			todoOk = false;
			JOptionPane.showMessageDialog(null, "El campo nombre está vacío");
			return false;
		}

		if (!textField_apellidosCliente.getText().isEmpty()) {
			todoOk = true;
		} else {
			todoOk = false;
			JOptionPane.showMessageDialog(null, "El campo apellido está vacío");
			return false;
		}

		if (matcherAltura.matches() || !textField_altura.getText().isEmpty()) {
			if (altura < 2.99 && altura > 0.50) {
				todoOk = true;
			} else {
				todoOk = false;
				JOptionPane.showMessageDialog(null, "Altura fuera de los limites permitidos: (0.50-2.99)");
			}
		} else {
			todoOk = false;
			JOptionPane.showMessageDialog(null, "El formato de la altura permitido es (0.50 a 2.99)");
		}
		
		Pattern patternEdad = Pattern.compile("^\\d{1,2}$");
		Matcher matcherEdad = patternEdad.matcher(textField_edad.getText());
		int edad = 0;
		if(esNumero) {
			edad = Integer.parseInt(textField_edad.getText());
		} else {
			JOptionPane.showMessageDialog(null, "El campo edad no está rellenado con un dato numérico");
		}
		
		if (matcherEdad.matches() || !textField_edad.getText().isEmpty()) {
			if (edad >= 18 && edad <= 99) {
				todoOk = true;
			} else {
				todoOk = false;
				JOptionPane.showMessageDialog(null, "La edad está fuera de los rangos (18-99)");
			}
		
		}
		
		Pattern patternPeso = Pattern.compile("^\\d{1,3}$");
		Matcher matcherPeso = patternEdad.matcher(textField_peso.getText());
		int peso = Integer.parseInt(textField_peso.getText());
		
		if (matcherPeso.matches() || !textField_peso.getText().isEmpty()) {
			if (peso >= 20 && peso <= 256) {
				todoOk = true;
			} else {
				todoOk = false;
				JOptionPane.showMessageDialog(null, "El peso está fuera de los rangos (20-256) Kg");
			}
		} else {
			todoOk = false;
			JOptionPane.showMessageDialog(null, "El peso debe estar comprendido entre 20 y 256 Kg");
		}

		return todoOk;
	}

	public static void mostrarImagen(String picPath, JLabel labelImagen, int x, int y, int z, int e) {
		try {
            BufferedImage img = ImageIO.read(new File(picPath));

            labelImagen.setBounds(x, y, z, e);

            Image dimg = img.getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(),
                    Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(dimg);

            labelImagen.setIcon(icon);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1575, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		DefaultListModel modeloListaEjercicios = new DefaultListModel();
		
		
		
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
		
		
		picClientTextPath = new JTextField();
		picClientTextPath.setBounds(323, 335, 183, 24);
		frame.getContentPane().add(picClientTextPath);
		picClientTextPath.setColumns(10);
		
		
		JTable tablaClientes = new JTable(modelClientes);
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(50);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(20);
		tablaClientes.getColumnModel().getColumn(4).setPreferredWidth(20);
		tablaClientes.getColumnModel().getColumn(5).setPreferredWidth(20);
		
		JTable tablaCE = new JTable(modelCE);
		
		
		JScrollPane scrollPaneClientes = new JScrollPane(tablaClientes);
		scrollPaneClientes.setBounds(33, 44, 536, 182);
		frame.getContentPane().add(scrollPaneClientes);
		
		JScrollPane scrollPaneEjercicios = new JScrollPane(tablaEjercicios);
		scrollPaneEjercicios.setBounds(591, 44, 536, 182);
		frame.getContentPane().add(scrollPaneEjercicios);
		
		JScrollPane scrollPaneCE = new JScrollPane(tablaCE);
		scrollPaneCE.setBounds(1161, 228, 269, 107);
		frame.getContentPane().add(scrollPaneCE);
		
		JComboBox comboBox_MostrarClientes = new JComboBox();
		comboBox_MostrarClientes.setBounds(262, 437, 134, 24);
		frame.getContentPane().add(comboBox_MostrarClientes);
		
		JComboBox comboBox_clienteId = new JComboBox();
		
		comboBox_clienteId.setBounds(1161, 343, 269, 24);
		frame.getContentPane().add(comboBox_clienteId);
				
		JComboBox comboBox_MostrarEjercicios = new JComboBox();
		comboBox_MostrarEjercicios.setBounds(1161, 379, 269, 24);
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
					boolean valido = comprobarValidezCamposCliente(textField_altura, textField_edad, textField_peso, textField_nombreCliente, textField_apellidosCliente, picClientTextPath);
					
					if(valido) {
						Cliente s = new Cliente(textField_nombreCliente.getText(),textField_apellidosCliente.getText(), Integer.parseInt(textField_edad.getText()),Double.parseDouble(textField_altura.getText()),Integer.parseInt(textField_peso.getText()),picClientTextPath.getText());
						ClienteDAO.insertCliente(s);
						btnActualizarTabla.doClick();
					} 
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Falta rellenar campos de Cliente");
				}
					
			
		}});
		
		btnGuardar.setBounds(33, 371, 176, 32);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnActualizar = new JButton("Actualizar");
		
		btnActualizar.setBounds(221, 371, 175, 32);
		frame.getContentPane().add(btnActualizar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cliente c = ClienteDAO.selectClienteByID(Integer.valueOf(textField_idCliente.getText()));
				ClienteDAO.deleteCliente(c.getId());
				btnActualizarTabla.doClick();
				
			}
		});
		btnBorrar.setBounds(408, 371, 161, 32);
		frame.getContentPane().add(btnBorrar);
		
		JLabel lblClientes = new JLabel("CLIENTES");
		lblClientes.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes.setBounds(33, 12, 88, 32);
		frame.getContentPane().add(lblClientes);
		
		JButton buttonAñadirImagenCliente = new JButton("");
		
		buttonAñadirImagenCliente.setPreferredSize(new Dimension(15, 10));
		buttonAñadirImagenCliente.setBounds(516, 335, 53, 24);
		frame.getContentPane().add(buttonAñadirImagenCliente);
		
		buttonAñadirImagenCliente.setIcon(new ImageIcon(App.class.getResource("/com/hibernate/gui/añadirImagen.png")));
		ImageIcon icono = new ImageIcon(App.class.getResource("/com/hibernate/gui/añadirImagen.png"));
		
		Image imagenReducida = icono.getImage().getScaledInstance(20, 20, 5);
		buttonAñadirImagenCliente.setIcon(new ImageIcon(imagenReducida));
		
		
		
		
		JLabel lblClientes_1 = new JLabel("EJERCICIOS");
		lblClientes_1.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes_1.setBounds(592, 12, 117, 32);
		frame.getContentPane().add(lblClientes_1);
		
		JButton btnGuardarEjercicio = new JButton("Añadir");
		btnGuardarEjercicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					boolean valido = comprobarValidezCamposEjercicio(textField_nombreEjercicio, textField_cargaEnKg, textField_repeticiones, textField_series, textField_nombreCliente, textField_apellidosCliente);
					
					if(valido) {
						Ejercicio ej = new Ejercicio(textField_nombreEjercicio.getText(),Integer.parseInt(textField_series.getText()), Integer.parseInt(textField_repeticiones.getText()),Integer.parseInt(textField_cargaEnKg.getText()),picExerciceTextPath.getText());
						EjercicioDAO.insertEjercicio(ej);
						btnActualizarTabla.doClick();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Falta rellenar campos del Ejercicio");
				}
				
						
					
				
				
				
			}
		});
		btnGuardarEjercicio.setBounds(591, 371, 168, 32);
		frame.getContentPane().add(btnGuardarEjercicio);
		
		JButton btnActualizar_1 = new JButton("Actualizar");
		
		btnActualizar_1.setBounds(771, 371, 183, 32);
		frame.getContentPane().add(btnActualizar_1);
		
		JButton btnBorrar_1 = new JButton("Borrar");
		btnBorrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ejercicio ej = EjercicioDAO.selectEjercicioByID(Integer.valueOf(textField_idEjercicio.getText()));
				EjercicioDAO.deleteEjercicio(ej.getId());
				btnActualizarTabla.doClick();
			}
		});
		btnBorrar_1.setBounds(966, 371, 161, 32);
		frame.getContentPane().add(btnBorrar_1);
		
		JButton buttonAñadirImagenEjercicio = new JButton("");
		
		buttonAñadirImagenEjercicio.setBounds(1074, 335, 53, 24);
		frame.getContentPane().add(buttonAñadirImagenEjercicio);
		
		buttonAñadirImagenEjercicio.setIcon(new ImageIcon(imagenReducida));
		
		JLabel lblEjerciciosDelCliente = new JLabel("EJERCICIOS DEL CLIENTE:");
		lblEjerciciosDelCliente.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblEjerciciosDelCliente.setBounds(33, 437, 231, 32);
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
		btnBorrarRutina.setBounds(1442, 228, 81, 170);
		frame.getContentPane().add(btnBorrarRutina);
		
		
		
		
		JLabel lblNombre = new JLabel("nombre:");
		lblNombre.setBounds(751, 245, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblRepeticiones = new JLabel("repeticiones:");
		lblRepeticiones.setBounds(751, 286, 94, 24);
		frame.getContentPane().add(lblRepeticiones);
		
		JLabel lblSeries = new JLabel("series:");
		lblSeries.setBounds(895, 290, 53, 15);
		frame.getContentPane().add(lblSeries);
		
		textField_series = new JTextField();
		textField_series.setBounds(943, 286, 37, 24);
		frame.getContentPane().add(textField_series);
		textField_series.setColumns(10);
		
		textField_repeticiones = new JTextField();
		textField_repeticiones.setColumns(10);
		textField_repeticiones.setBounds(849, 286, 37, 25);
		frame.getContentPane().add(textField_repeticiones);
		
		JLabel lblCargaEnKg = new JLabel("carga en KG:");
		lblCargaEnKg.setBounds(998, 286, 98, 20);
		frame.getContentPane().add(lblCargaEnKg);
		
		textField_cargaEnKg = new JTextField();
		textField_cargaEnKg.setColumns(10);
		textField_cargaEnKg.setBounds(1090, 284, 37, 25);
		frame.getContentPane().add(textField_cargaEnKg);
		
		textField_nombreEjercicio = new JTextField();
		textField_nombreEjercicio.setColumns(10);
		textField_nombreEjercicio.setBounds(824, 238, 223, 25);
		frame.getContentPane().add(textField_nombreEjercicio);
		
		JLabel lblNombre_1 = new JLabel("nombre:");
		lblNombre_1.setBounds(177, 244, 70, 15);
		frame.getContentPane().add(lblNombre_1);
		
		textField_nombreCliente = new JTextField();
		textField_nombreCliente.setColumns(10);
		textField_nombreCliente.setBounds(249, 238, 147, 25);
		frame.getContentPane().add(textField_nombreCliente);
		
		JLabel lblClientes_1_1 = new JLabel("AFEGIR RUTINA");
		lblClientes_1_1.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes_1_1.setBounds(1161, 12, 161, 32);
		frame.getContentPane().add(lblClientes_1_1);
		
		JLabel lblCliente = new JLabel("Cliente:id");
		lblCliente.setBounds(1162, 44, 70, 15);
		frame.getContentPane().add(lblCliente);
		
		JLabel lblCliente_1 = new JLabel("Ejercicio:id");
		lblCliente_1.setBounds(1299, 44, 99, 15);
		frame.getContentPane().add(lblCliente_1);
		
		textField_añadirClienteRutina = new JTextField();
		textField_añadirClienteRutina.setBounds(1162, 59, 123, 19);
		frame.getContentPane().add(textField_añadirClienteRutina);
		textField_añadirClienteRutina.setColumns(10);
		
		textField_añadirEjercicioCliente = new JTextField();
		textField_añadirEjercicioCliente.setColumns(10);
		textField_añadirEjercicioCliente.setBounds(1297, 59, 123, 19);
		frame.getContentPane().add(textField_añadirEjercicioCliente);
		
		JLabel lblClientes_1_1_1 = new JLabel("BORRAR RUTINA");
		lblClientes_1_1_1.setFont(new Font("Dhurjati", Font.BOLD, 24));
		lblClientes_1_1_1.setBounds(1161, 194, 161, 53);
		frame.getContentPane().add(lblClientes_1_1_1);
		
		
		textField_apellidosCliente = new JTextField();
		textField_apellidosCliente.setColumns(10);
		textField_apellidosCliente.setBounds(249, 267, 320, 25);
		frame.getContentPane().add(textField_apellidosCliente);
		
		JLabel lblNombre_1_1 = new JLabel("apellidos:");
		lblNombre_1_1.setBounds(177, 270, 70, 24);
		frame.getContentPane().add(lblNombre_1_1);
		
		textField_edad = new JTextField();
		textField_edad.setColumns(10);
		textField_edad.setBounds(381, 299, 53, 25);
		frame.getContentPane().add(textField_edad);
		
		JLabel lblEdad = new JLabel("edad:");
		lblEdad.setBounds(337, 302, 62, 21);
		frame.getContentPane().add(lblEdad);
		
		JLabel lblAltura = new JLabel("altura:");
		lblAltura.setBounds(177, 300, 87, 25);
		frame.getContentPane().add(lblAltura);
		
		textField_altura = new JTextField();
		textField_altura.setColumns(10);
		textField_altura.setBounds(249, 299, 62, 25);
		frame.getContentPane().add(textField_altura);
		
		JLabel lblPeso = new JLabel("peso:");
		lblPeso.setBounds(482, 302, 59, 21);
		frame.getContentPane().add(lblPeso);
		
		textField_peso = new JTextField();
		textField_peso.setColumns(10);
		textField_peso.setBounds(526, 298, 43, 25);
		frame.getContentPane().add(textField_peso);
		
		JLabel lblNombre_1_2 = new JLabel("id:");
		lblNombre_1_2.setBounds(504, 244, 31, 15);
		frame.getContentPane().add(lblNombre_1_2);
		
		textField_idCliente = new JTextField();
		textField_idCliente.setColumns(10);
		textField_idCliente.setBounds(526, 239, 43, 25);
		frame.getContentPane().add(textField_idCliente);
		textField_idCliente.setEnabled(false);
		
		JLabel lblNombre_1_2_1 = new JLabel("id:");
		lblNombre_1_2_1.setBounds(1065, 243, 31, 15);
		frame.getContentPane().add(lblNombre_1_2_1);
		
		textField_idEjercicio = new JTextField();
		textField_idEjercicio.setEnabled(false);
		textField_idEjercicio.setColumns(10);
		textField_idEjercicio.setBounds(1090, 238, 37, 25);
		frame.getContentPane().add(textField_idEjercicio);
		
		JButton btnAñadirRutina= new JButton("Añadir");
		btnAñadirRutina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cliente_id = textField_añadirClienteRutina.getText();
				String[] clienting = cliente_id.split(":");
				String idClienteExtraido = clienting[1];
				String ejercicio_id = textField_añadirEjercicioCliente.getText();
				String[] ejercicing = ejercicio_id.split(":");
				String idEjercicioExtraido = ejercicing[1];
				CE ce = new CE(Integer.parseInt(idClienteExtraido), Integer.parseInt(idEjercicioExtraido));
				ClienteEjercicioDAO.insertClienteEjercicio(ce);
				btnActualizarTabla.doClick();
			}
		});
		btnAñadirRutina.setBounds(1443, 59, 80, 135);
		frame.getContentPane().add(btnAñadirRutina);
		
		tablaCE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablaCE.getSelectedRow();
				TableModel model = tablaCE.getModel();
				
				int idCliente =  Integer.parseInt( model.getValueAt(index, 0).toString());
				int idEjercicio =  Integer.parseInt( model.getValueAt(index, 1).toString());
	
				Cliente c = ClienteDAO.selectClienteByID(idCliente);
				Ejercicio ej = EjercicioDAO.selectEjercicioByID(idEjercicio);
				
				
				
				comboBox_clienteId.setSelectedItem(idCliente + ":" + ClienteDAO.selectClienteByID(idCliente).getNombre());
				comboBox_MostrarEjercicios.setSelectedItem(idEjercicio + ":" + EjercicioDAO.selectEjercicioByID(idEjercicio).getNombre());
			}
		});
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 410, 1539, 15);
		frame.getContentPane().add(separator);
		
		JTextArea textArea_EjerciciosCliente = new JTextArea();
		textArea_EjerciciosCliente.setBounds(32, 466, 470, 274);
		frame.getContentPane().add(textArea_EjerciciosCliente);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cliente_id = comboBox_MostrarClientes.getSelectedItem().toString();
				String[] clienting = cliente_id.split(":");
				String idClienteExtraido = clienting[0];
				
				textArea_EjerciciosCliente.setText("LISTA DE EJERCICIOS PARA EL CLIENTE\n------------------------------------------------------------------------------------------");
				
				List<CE> listaEjerciciosCliente = ClienteEjercicioDAO.selectEjerciciosByClienteID(Integer.parseInt(idClienteExtraido));
				Cliente c = ClienteDAO.selectClienteByID(Integer.parseInt(idClienteExtraido));
				textArea_EjerciciosCliente.setText("          " + textArea_EjerciciosCliente.getText() + ": \n\n	" + c.getNombre() + " " + c.getApellidos() + "\n\n Edad: " + c.getEdad() + "	Altura: " + c.getAltura() + " metros	Peso: " + c.getPeso() + " Kg\n\n------------------------------------------------------------------------------------------");
				textArea_EjerciciosCliente.setText(textArea_EjerciciosCliente.getText() + "\nEjercicio		" + "Nº de Series	" + "Repeticiones	" + "Carga en KG\n------------------------------------------------------------------------------------------");
				
				for(CE ce : listaEjerciciosCliente) {
					Ejercicio ej = EjercicioDAO.selectEjercicioByID(ce.getEjercicio_id());
					textArea_EjerciciosCliente.setText(textArea_EjerciciosCliente.getText() + "\n" + ej.getNombre() + "		" + ej.getNumeroDeSeries() + "	" + ej.getRepeticiones() + "	" + ej.getCargaEnKg());
				}
				
				textArea_EjerciciosCliente.setText(textArea_EjerciciosCliente.getText() + "\n\nÁNIMO CON EL ENTRENE!");
			}
		});
		btnMostrar.setBounds(400, 437, 102, 25);
		frame.getContentPane().add(btnMostrar);
		
		JLabel lblImagenCliente = new JLabel("Imagen del cliente");
		lblImagenCliente.setBounds(179, 339, 146, 15);
		frame.getContentPane().add(lblImagenCliente);
		
		JLabel labelImagenCliente = new JLabel("");
		labelImagenCliente.setBounds(33, 238, 132, 123);
		frame.getContentPane().add(labelImagenCliente);
		
		labelImagenCliente.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lblImagenDelEjercicio = new JLabel("Imagen del ejercicio");
		lblImagenDelEjercicio.setBounds(751, 339, 146, 15);
		frame.getContentPane().add(lblImagenDelEjercicio);
		
		picExerciceTextPath = new JTextField();
		picExerciceTextPath.setColumns(10);
		picExerciceTextPath.setBounds(902, 337, 160, 24);
		frame.getContentPane().add(picExerciceTextPath);
		
		JLabel labelImagenEjercicio = new JLabel("");
		labelImagenEjercicio.setBorder(BorderFactory.createLineBorder(Color.black));
		labelImagenEjercicio.setBounds(591, 236, 132, 123);
		frame.getContentPane().add(labelImagenEjercicio);
		
		JLabel labelMostrarImgCliente = new JLabel("");
		labelMostrarImgCliente.setBorder(BorderFactory.createLineBorder(Color.black));
		labelMostrarImgCliente.setBounds(1161, 82, 123, 112);
		frame.getContentPane().add(labelMostrarImgCliente);
		
		JLabel labelMostrarImgEjercicio = new JLabel("");
		labelMostrarImgEjercicio.setBorder(BorderFactory.createLineBorder(Color.black));
		labelMostrarImgEjercicio.setBounds(1299, 82, 123, 112);
		frame.getContentPane().add(labelMostrarImgEjercicio);
		
		
		buttonAñadirImagenCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        chooser.showOpenDialog(null);
		        File f= chooser.getSelectedFile();
		        String fileName= f.getAbsolutePath();
		        picClientTextPath.setText(fileName);
		        
		        mostrarImagen(picClientTextPath.getText(),labelImagenCliente,33, 238, 132, 123); 	
		        
			}
		});
		
		buttonAñadirImagenEjercicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
		        chooser.showOpenDialog(null);
		        File f= chooser.getSelectedFile();
		        String fileName= f.getAbsolutePath();
		        picExerciceTextPath.setText(fileName);
		        
		        mostrarImagen(picExerciceTextPath.getText(),labelImagenEjercicio,591, 236, 132, 123);
		        
			}
		});
		
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
				textField_añadirClienteRutina.setText(model.getValueAt(index, 1).toString() + ":" +  model.getValueAt(index, 0).toString());
				picClientTextPath.setText(ClienteDAO.selectClienteByID(Integer.parseInt(textField_idCliente.getText())).getPicPath());
				
				Cliente c1 = ClienteDAO.selectClienteByID(Integer.parseInt(textField_idCliente.getText()));
				if(c1.getPicPath()!=null) {
					picClientTextPath.setText(c1.getPicPath());
					mostrarImagen(ClienteDAO.selectClienteByID(Integer.parseInt(textField_idCliente.getText())).getPicPath(), labelMostrarImgCliente , 1161, 82, 123, 112);
					mostrarImagen(picClientTextPath.getText(),labelImagenCliente,33, 238, 132, 123); 
				} else {
					labelImagenCliente.setIcon(null);
					labelMostrarImgCliente.setIcon(null);
				}
				
				
				
			}
		});
		
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
				textField_añadirEjercicioCliente.setText(model.getValueAt(index, 1).toString() + ":" + model.getValueAt(index, 0).toString());
				picExerciceTextPath.setText(EjercicioDAO.selectEjercicioByID(Integer.parseInt(textField_idEjercicio.getText())).getPicPath());
				
				Ejercicio e1 = EjercicioDAO.selectEjercicioByID(Integer.parseInt(textField_idEjercicio.getText()));
				if(e1.getPicPath()!=null) {
					picExerciceTextPath.setText(e1.getPicPath());
					mostrarImagen(EjercicioDAO.selectEjercicioByID(Integer.parseInt(textField_idEjercicio.getText())).getPicPath(),labelImagenEjercicio,591, 236, 132, 123);
					mostrarImagen(picExerciceTextPath.getText(),labelMostrarImgEjercicio,1299, 82, 123, 112);
				} else {
					labelImagenEjercicio.setIcon(null);
					labelMostrarImgEjercicio.setIcon(null);
				}
			}
		});
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					try {
						boolean valido = comprobarValidezCamposCliente(textField_altura, textField_edad, textField_peso, textField_nombreCliente, textField_apellidosCliente, picClientTextPath);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Faltan campos por completar");
					
				
					}
					
					Cliente c = ClienteDAO.selectClienteByID(Integer.valueOf(textField_idCliente.getText()));
					c.setNombre(textField_nombreCliente.getText());
					c.setApellidos(textField_apellidosCliente.getText());
					c.setEdad(Integer.valueOf(Integer.parseInt(textField_edad.getText())));
					c.setAltura(Double.parseDouble(textField_altura.getText()));
					c.setPeso(Integer.parseInt(textField_peso.getText()));
					c.setPicPath(picClientTextPath.getText());
					ClienteDAO.updateCliente(c);
					btnActualizarTabla.doClick();
					
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		
		btnActualizar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Ejercicio ej = EjercicioDAO.selectEjercicioByID(Integer.valueOf(textField_idEjercicio.getText()));
				ej.setNombre(textField_nombreEjercicio.getText());
				ej.setNumeroDeSeries(Integer.parseInt(textField_series.getText()));
				ej.setRepeticiones(Integer.valueOf(Integer.parseInt(textField_repeticiones.getText())));
				ej.setCargaEnKg(Integer.parseInt(textField_cargaEnKg.getText()));
				ej.setPicPath(picExerciceTextPath.toString());
				EjercicioDAO.updateEjercicio(ej);
				btnActualizarTabla.doClick();
				
			}
		});
		
		
		
		
	}
	}
