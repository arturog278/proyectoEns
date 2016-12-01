package mx.uaemex.fi.ens.fase_3;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Vector;


public class SimboloManager {
	private XMLEncoder encoder;
	private XMLDecoder decoder;
	private Vector<Simbolo> simbolos;

	public SimboloManager() throws Exception {
		this.encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("tablaSimbolos.xml")));
		this.encoder.close();
		this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("tablaSimbolos.xml")));
		this.simbolos = new Vector<Simbolo>();
		try{
			this.simbolos = (Vector<Simbolo>) this.decoder.readObject();
		}catch(Exception e){
			Simbolo sim = new Simbolo("Nombre","Tipo","Valor","Tamaño","Direccion");
			this.simbolos.addElement(sim);
			this.salva();
		}
		this.decoder.close();
	}
	
	public void addSimbolo(Simbolo sim) throws Exception{
		this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("tablaSimbolos.xml")));
		this.simbolos = (Vector<Simbolo>) this.decoder.readObject();
		this.decoder.close();
		this.simbolos.addElement(sim);
		this.salva();
	}
	
	public void salva() throws Exception{
		this.encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("tablaSimbolos.xml")));
		this.encoder.writeObject(this.simbolos);
		this.encoder.close();
	}

	public Vector<Simbolo> getSimbolos() {
		return simbolos;
	}
	
	public void delete() throws Exception{
		this.encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("tablaSimbolos.xml")));
		Vector<Simbolo> vacio = new Vector<Simbolo>();
		this.simbolos = vacio;
		this.encoder.writeObject(vacio);
		this.encoder.close();
	}
	
	public int findSimbolo(String obj) throws Exception{
		this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("tablaSimbolos.xml")));
		this.simbolos = (Vector<Simbolo>) this.decoder.readObject();
		this.decoder.close();
		for(Simbolo s:this.simbolos){
			if(s.getSimbolo().equalsIgnoreCase(obj)){
				if(s.getTipo().contentEquals("var")){
					return 1;
				}else if(s.getTipo().contentEquals("equ")){
					return 2;
				}else if(s.getTipo().contentEquals("label")){
					return 3;
				}
			}
		}
		return -1;
	}
	
	public int getTamaño(String obj)throws Exception{
		this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("tablaSimbolos.xml")));
		this.simbolos = (Vector<Simbolo>) this.decoder.readObject();
		this.decoder.close();
		for(Simbolo s:this.simbolos){
			if(s.getSimbolo().equalsIgnoreCase(obj)){
				if(s.getTamaño().contentEquals("b")){
					return 1;
				}else if(s.getTamaño().contentEquals("w")){
					return 2;
				}
			}
		}
		return -1;
	}
	
	public String getDireccion(String obj)throws Exception{
		this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("tablaSimbolos.xml")));
		this.simbolos = (Vector<Simbolo>) this.decoder.readObject();
		this.decoder.close();
		for(Simbolo s:this.simbolos){
			if(s.getSimbolo().equalsIgnoreCase(obj)){
				return s.getDireccion();
			}
		}
		return null;
	}
	
	public String getValor(String obj)throws Exception{
		this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("tablaSimbolos.xml")));
		this.simbolos = (Vector<Simbolo>) this.decoder.readObject();
		this.decoder.close();
		for(Simbolo s:this.simbolos){
			if(s.getSimbolo().equalsIgnoreCase(obj)){
				return s.getValor();
			}
		}
		return null;
	}
	

}
