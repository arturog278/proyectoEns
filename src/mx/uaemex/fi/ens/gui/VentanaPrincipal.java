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
	private JPanel panel;

	public VentanaPrincipal() {
		super("Proyecto Ensamblador");
		this.btnEjecutar = new JButton("Ejecutar");
		this.btnSalir = new JButton("Salir");
		this.txtEntrada = new JTextField();
		this.lblIngrese = new JLabel("Ingrese el nombre del programa");
		this.panel = new JPanel(new GridLayout(2,2));
		
		this.btnEjecutar.addActionListener(this);
		this.btnEjecutar.setActionCommand("Ejecutar");
		this.btnSalir.addActionListener(this);
		this.btnSalir.setActionCommand("Salir");
		this.txtEntrada.setSize(50, 100);		
		
		this.panel.add(this.lblIngrese);
		this.panel.add(this.txtEntrada);
		this.panel.add(this.btnEjecutar);
		this.panel.add(this.btnSalir);		
		
		this.panel.setBorder(new EmptyBorder(10,10,10,10));
		
		this.getContentPane().add(this.panel, BorderLayout.CENTER);
				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,500,150);
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
				JOptionPane.showMessageDialog(null, "Ocurrio un error: "+ ex.getMessage());		   
			}			
			break;
		case "Salir":
			System.exit(0);
			break;
		}
	}
	

}
