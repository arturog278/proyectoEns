package mx.uaemex.fi.ens.gui;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import mx.uaemex.fi.ens.fase_3.Simbolo;

public class VentanaDesplegar extends JFrame implements ActionListener {
	private JPanel panelSup;
	private JPanel panelCent;
	private JPanel panelInf;
	private JLabel label1;
	private JLabel label2;
	private JTextPane txtSalida;
	private JScrollPane scrlPane;
	private JTable tableSalida;
	private JButton btnSalir;
	private JButton btnRegresar;

	public VentanaDesplegar(String nombre,String contenido,Vector<Simbolo> simbolos) {
		super(nombre);
		this.panelCent = new JPanel(new GridLayout(1,2));
		this.panelInf = new JPanel(new GridLayout(1,2));
		this.panelSup = new JPanel(new GridLayout(1,2));
		
		this.btnRegresar = new JButton("Regresar");
		this.btnRegresar.addActionListener(this);
		this.btnRegresar.setActionCommand("Regresar");
		this.btnSalir = new JButton("Salir");
		this.btnSalir.addActionListener(this);
		this.btnSalir.setActionCommand("Salir");
		this.panelInf.add(this.btnSalir);
		this.panelInf.add(this.btnRegresar);

		this.label1 = new JLabel("Resultado obtenido:");
		this.label2 = new JLabel("Tabla de simbolos:");
		this.panelSup.add(this.label1);
		this.panelSup.add(this.label2);
		
		
		this.txtSalida = new JTextPane();
		this.txtSalida.setEditable(false);
		this.scrlPane = new JScrollPane();
		this.txtSalida.setText(contenido);
		this.scrlPane.setViewportView(this.txtSalida);
		String[] columnNames = {"Nombre","Tipo","Valor","Tamaño"};
		String[][] rowData = obtenerSimbolos(simbolos);

		this.tableSalida = new JTable(rowData,columnNames);
		this.panelCent.add(this.scrlPane);
		this.panelCent.add(this.tableSalida);
		
		this.panelInf.setBorder(new EmptyBorder(10,10,10,10));
		this.panelSup.setBorder(new EmptyBorder(10,10,10,10));
		this.panelCent.setBorder(new EmptyBorder(10,10,10,10));
		
		this.getContentPane().add(this.panelInf, BorderLayout.SOUTH);
		this.getContentPane().add(this.panelSup, BorderLayout.NORTH);
		this.getContentPane().add(this.panelCent, BorderLayout.CENTER);
		
		this.setBounds(100,100,1000,700);
		
	}
	
	private String[][] obtenerSimbolos(Vector<Simbolo> sim){
		String[][] simbolos = new String[sim.size()][4];
		for(int i=0;i<sim.size();i++){
			simbolos[i][0]=sim.elementAt(i).getSimbolo();
			simbolos[i][1]=sim.elementAt(i).getTipo();
			simbolos[i][2]=sim.elementAt(i).getValor();
			simbolos[i][3]=sim.elementAt(i).getTamaño();
		}
		return simbolos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch(cmd){
		case "Regresar":
			this.setVisible(false);
			break;
		case "Salir":
			System.exit(0);
			break;
		}
	}

}
