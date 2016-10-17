package mx.uaemex.fi.ens.fase_3;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.uaemex.fi.ens.fase_2.Identificador;

public class Fase_3 {
	private Identificador id;
	private BufferedReader br;
	private BufferedWriter bw;
	private File archSalida;
	private XMLEncoder encoder;
	private Vector<Simbolo> simbolos;
	private int segActual;

	public Fase_3(File archEntrada) throws Exception {
		this.archSalida = new File("resultadoFase3.txt");
		this.bw = new BufferedWriter(new FileWriter(this.archSalida));
		this.br = new BufferedReader(new FileReader(archEntrada));
		this.segActual = 0;
		this.id = new Identificador();
		this.simbolos = new Vector<Simbolo>();
		String strLinea ="";
		int aux = 0;
		while((strLinea = br.readLine()) != null){
			if((aux = this.id.idSegment(strLinea))!=-1){
				bw.write(strLinea+" Correcto\n");
				this.segActual=aux;
			}else{
				switch(this.segActual){
				case 1:
					this.checkLineData(strLinea);
					break;
				case 2:
					this.checkLineStack(strLinea);
					break;
				case 3:
					this.bw.write(strLinea+" \"No identificado\""+"\n");
					break;
				}
			}
		}
		bw.close();
		this.encoder =  new XMLEncoder(new BufferedOutputStream(new FileOutputStream("tablaSimbolos.xml")));
		this.encoder.writeObject(this.simbolos);
		this.encoder.close();
	}
	
	private void checkLineData(String strLinea) throws Exception{
		Pattern pat = Pattern.compile("^(\\p{Alpha}\\w{0,9})\\s+([dD][bBwW]|[eE][qQ][uU])\\s+(\\w+|\\x22.*\\x22|\\x27.*\\x27)(\\s+([dD][uU][pP])\\s*\\((\\w+|\\x22.*\\x22|\\x27.*\\x27)\\)$|$)");
		Matcher mat = pat.matcher(strLinea);
		if(mat.find()){
			String[] vecGroup = new String[6];
			for(int i=0;i<6;i++){
				vecGroup[i] = mat.group(i+1);
			}
			if(vecGroup[1].equalsIgnoreCase("db")){
				if(this.id.esConsByte(vecGroup[2])!=-1){
					if(vecGroup[3]==""){
						if(this.id.esConsByte(vecGroup[5])!=-1 && this.id.esConsByte(vecGroup[2])==1){
							this.bw.write(strLinea+" Correcto\n");
							this.addSimbolo(vecGroup[0],this.tipoCons(vecGroup[5]) , vecGroup[5], "b");
						}else{
							this.bw.write(strLinea+" Incorrecto, se esperaba una constante tamaño byte en el dup\n");
						}
					}else{
						this.bw.write(strLinea+" Correcto\n");
						this.addSimbolo(vecGroup[0],this.tipoCons(vecGroup[2]) , vecGroup[2], "b");
					}
				}else{
					this.bw.write(strLinea+" Incorrecto, se esperaba una constante tamaño byte\n");
				}
			}else if(vecGroup[1].equalsIgnoreCase("dw")){
				if(this.id.esConsWord(vecGroup[2])==1){
					if(vecGroup[3]==""){
						if(this.id.esConsWord(vecGroup[5])==1 && this.id.esConsWord(vecGroup[2])==1){
							this.bw.write(strLinea+" Correcto\n");
							this.addSimbolo(vecGroup[0],this.tipoCons(vecGroup[5]) , vecGroup[5], "w");
						}else{
							this.bw.write(strLinea+" Incorrecto, se esperaba una constante tamaño word en el dup\n");
						}
					}else{
						this.bw.write(strLinea+" Correcto\n");
						this.addSimbolo(vecGroup[0],this.tipoCons(vecGroup[2]) , vecGroup[2], "w");
					}
				}else{
					this.bw.write(strLinea+" Incorrecto, se esperaba una constante numerica tamaño word\n");
				}
			}else if(vecGroup[1].equalsIgnoreCase("equ")){
				if(this.id.esConsWord(vecGroup[2])==1){
					if(vecGroup[3].equals("")){
						this.bw.write(strLinea+" Correcto\n");
						this.addSimbolo(vecGroup[0],this.tipoCons(vecGroup[2]) , vecGroup[2], "w");
					}else{
						this.bw.write(strLinea+" Incorrecto, no se esperaba nada despues de la constante\n");
					}
				}else{
					this.bw.write(strLinea+" Incorrecto, se esperaba una constante numerica tamaño word\n");
				}
			}			
		}else{
			this.bw.write(strLinea+" Incorrecto, error de sintaxis\n");
		}
		
	}
	
	private String tipoCons(String obj){
		if(this.id.esConsBin(obj)){
			return "bin";
		}else if(this.id.esConsDec(obj)){
			return "dec";
		}else if(this.id.esConsHexa(obj)){
			return "hex";
		}
		return "str";
	}
	
	private void addSimbolo(String s,String ti,String v,String ta){
		Simbolo sim = new Simbolo(s,ti,v,ta);
		this.simbolos.add(sim);
	}
	
	private void checkLineStack(String strLinea) throws Exception{
		Pattern pat = Pattern.compile("^[dD][wW]\\s+(\\w+)\\s+[dD][uU][pP]\\s*\\((\\w+)\\)$");
		Matcher mat = pat.matcher(strLinea);
		if(mat.find()){
			String cons1 = mat.group(1);
			String cons2 = mat.group(2);
			if(this.id.esConsWord(cons1)==1 && this.id.esConsWord(cons2)==1){
				bw.write(strLinea+" Correcto\n");
			}else{
				bw.write(strLinea+" Incorrecto, se esperaba una constante numerica palabra");
			}
		}else{
			bw.write(strLinea+" Incorrecto, error de sintaxis");
		}
	}
	
	public File getArchSalida() {
		return archSalida;
	}

}
