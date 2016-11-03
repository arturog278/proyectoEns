package mx.uaemex.fi.ens.main;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

import mx.uaemex.fi.ens.fase_3.Simbolo;

public class Desplegar {
	private XMLDecoder decoder;
	private Vector<Simbolo> simbolos;

	public Desplegar(){
		
	}
	
	public String DesplegarC(File arch) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(arch));
		String strLinea;
		String contenido = "";
		while((strLinea = br.readLine()) != null){
			contenido =contenido+strLinea+"\n";
		}
		br.close();
		return contenido;
	}
	
	public Vector<Simbolo> DesplegarXML() throws Exception{
		this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("tablaSimbolos.xml")));
		this.simbolos = (Vector<Simbolo>) this.decoder.readObject();
		this.decoder.close();
		return this.simbolos;
	}

}
