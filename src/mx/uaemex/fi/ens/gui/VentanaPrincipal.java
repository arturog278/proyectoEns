package mx.uaemex.fi.ens.gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mx.uaemex.fi.ens.fase_1.Fase_1;
import mx.uaemex.fi.ens.fase_3.Fase_3;
import mx.uaemex.fi.ens.fase_3.SimboloManager;
import mx.uaemex.fi.ens.main.Desplegar;

public class VentanaPrincipal extends JFrame implements ActionListener {
	private JTextField txtEntrada;
	private JButton btnEjecutar;
	private JButton btnSalir;
	private JLabel lblIngrese;
	private JPanel panelSup;
	private JButton btnLimpiar;
	private JPanel panelInf;

	public VentanaPrincipal() {
		super("Proyecto Ensamblador");
		this.btnEjecutar = new JButton("Ejecutar");
		this.btnSalir = new JButton("Salir");
		this.txtEntrada = new JTextField();
		this.txtEntrada.addActionListener(this);
		this.txtEntrada.setActionCommand("Ejecutar");
		this.btnLimpiar = new JButton("Limpiar");
		this.btnLimpiar.addActionListener(this);
		this.btnLimpiar.setActionCommand("Limpiar");
		this.lblIngrese = new JLabel("Ingrese el nombre del programa");
		this.panelSup = new JPanel(new GridLayout(1,2));
		this.panelInf = new JPanel(new GridLayout(1,3));
		
		this.btnEjecutar.addActionListener(this);
		this.btnEjecutar.setActionCommand("Ejecutar");
		this.btnSalir.addActionListener(this);
		this.btnSalir.setActionCommand("Salir");
		this.txtEntrada.setSize(50, 100);		
		
		this.panelSup.add(this.lblIngrese);
		this.panelSup.add(this.txtEntrada);
		
		this.panelInf.add(this.btnEjecutar);
		this.panelInf.add(this.btnSalir);
		this.panelInf.add(this.btnLimpiar);
		
		this.panelSup.setBorder(new EmptyBorder(10,10,10,10));
		this.panelInf.setBorder(new EmptyBorder(10,10,10,10));

		
		this.getContentPane().add(this.panelSup, BorderLayout.NORTH);
		this.getContentPane().add(this.panelInf, BorderLayout.SOUTH);

				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,480,120);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch(cmd){
		case "Ejecutar":
			String nombre = this.txtEntrada.getText();
			try {
	        	Desplegar desp;
				Fase_1 fase1 = new Fase_1(nombre);
				//Fase_2 fase2 = new Fase_2(fase1.getArchSalida());
				Fase_3 fase3 = new Fase_3(fase1.getArchSalida());
				desp = new Desplegar();
				VentanaDesplegar v2 = new VentanaDesplegar(nombre,desp.DesplegarC(fase3.getArchSalida()),desp.DesplegarXML());
				v2.setVisible(true);
			} catch (Exception ex) {		
				JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);		   
			}			
			break;
		case "Salir":
			System.exit(0);
			break;
		case "Limpiar":
			this.txtEntrada.setText("");
			this.txtEntrada.requestFocus();
			break;
		}
	}
	

}
