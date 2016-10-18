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

	public Desplegar(File arch) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(arch));
		String strLinea;
		while((strLinea = br.readLine()) != null){
			System.out.println(strLinea);
		}
		br.close();
	}
	
	public void DesplegarXML() throws Exception{
		this.decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("tablaSimbolos.xml")));
		this.simbolos = (Vector<Simbolo>) this.decoder.readObject();
		this.decoder.close();
		System.out.println("\n");
		for(Simbolo s:this.simbolos){
			System.out.println(s.getSimbolo()+"\t"+s.getTamaño()+"\t"+s.getTipo()+"\t"+s.getValor()+"\n");
		}
	}

}
