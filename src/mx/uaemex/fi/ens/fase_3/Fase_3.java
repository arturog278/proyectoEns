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
import mx.uaemex.fi.ens.fase_4.Fase_4;
import mx.uaemex.fi.ens.fase_5.Contador;
import mx.uaemex.fi.ens.fase_5.Fase_5;

public class Fase_3 {
	private Identificador id;
	private BufferedReader br;
	private BufferedWriter bw;
	private File archSalida;
	private SimboloManager simMan;
	private int segActual;
	private Fase_4 fase4;
	private Fase_5 fase5;
	private Contador count;
	private String cp;

	public Fase_3(File archEntrada) throws Exception {
		this.simMan = new SimboloManager();
		this.simMan.delete();
		this.archSalida = new File("resultadoFase3y4.txt");
		this.bw = new BufferedWriter(new FileWriter(this.archSalida));
		this.br = new BufferedReader(new FileReader(archEntrada));
		this.segActual = 0;
		this.fase4 = new Fase_4();
		this.fase5 = new Fase_5();
		this.id = new Identificador();
		this.cp = "0000";
		this.count = new Contador();
		String strLinea ="";
		int aux = 0;
		while((strLinea = br.readLine()) != null){
			if((aux = this.id.idSegment(strLinea))!=-1){
				bw.write(this.cp+" "+strLinea+" Correcto\n");
				if(aux != 0){
					this.cp = "0000";
				}
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
					this.bw.write(this.fase4.CheckLineCode(strLinea,this.cp));
					this.cp = this.fase4.getCp();
					break;
				}
			}
		}
		bw.close();
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
				if(this.id.esConsNumBoW(vecGroup[2])!=-1||this.id.esConsString(vecGroup[2])){
					if(vecGroup[4]!=null){
						if(this.id.esConsByte(vecGroup[5])!=-1 && this.id.esConsNumBoW(vecGroup[2])!=-1){
							this.bw.write(this.cp+" "+strLinea+" Correcto\n");
							this.addSimbolo(vecGroup[0],"var" , vecGroup[5], "b",this.cp);
							this.cp = this.count.suma(this.cp,this.fase5.calcularTamañoDS(vecGroup));
						}else{
							this.bw.write(this.cp+" "+strLinea+" Incorrecto, se esperaba una constante tamaño byte en el dup\n");
						}
					}else{
						if(this.id.esConsByte(vecGroup[2])!=-1||this.id.esConsString(vecGroup[2])){
							this.bw.write(this.cp+" "+strLinea+" Correcto\n");
							this.addSimbolo(vecGroup[0],"var" , vecGroup[2], "b",this.cp);
							this.cp = this.count.suma(this.cp,this.fase5.calcularTamañoDS(vecGroup));
						}else{
							this.bw.write(this.cp+" "+strLinea+" Incorrecto, se esperaba una constante tamaño byte\n");
						}
					}
				}else{
					this.bw.write(this.cp+" "+strLinea+" Incorrecto, se esperaba una constante tamaño byte\n");
				}
			}else if(vecGroup[1].equalsIgnoreCase("dw")){
				if(this.id.esConsNumBoW(vecGroup[2])!=-1){
					if(vecGroup[4]!=null){
						if(this.id.esConsWord(vecGroup[5])==1 && this.id.esConsNumBoW(vecGroup[2])!=-1){
							this.bw.write(this.cp+" "+strLinea+" Correcto\n");
							this.addSimbolo(vecGroup[0],"var" , vecGroup[5], "w",this.cp);
							this.cp = this.count.suma(this.cp,this.fase5.calcularTamañoDS(vecGroup));
						}else{
							this.bw.write(this.cp+" "+strLinea+" Incorrecto, se esperaba una constante tamaño word en el dup\n");
						}
					}else{
						if(this.id.esConsWord(vecGroup[2])!=-1){
							this.bw.write(this.cp+" "+strLinea+" Correcto\n");
							this.addSimbolo(vecGroup[0],"var" , vecGroup[2], "w",this.cp);
							this.cp = this.count.suma(this.cp,this.fase5.calcularTamañoDS(vecGroup));
						}else{
							this.bw.write(this.cp+" "+strLinea+" Incorrecto, se esperaba una constante tamaño word\n");
						}
					}
				}else{
					this.bw.write(this.cp+" "+strLinea+" Incorrecto, se esperaba una constante numerica tamaño word\n");
				}
			}else if(vecGroup[1].equalsIgnoreCase("equ")){
				if(this.id.esConsWord(vecGroup[2])==1){
					if(vecGroup[3].equals("")){
						this.bw.write(this.cp+" "+strLinea+" Correcto\n");
						this.addSimbolo(vecGroup[0],"equ", vecGroup[2], "w",null);
						this.cp = this.count.suma(this.cp,this.fase5.calcularTamañoDS(vecGroup));
					}else{
						this.bw.write(this.cp+" "+strLinea+" Incorrecto, no se esperaba nada despues de la constante\n");
					}
				}else{
					this.bw.write(this.cp+" "+strLinea+" Incorrecto, se esperaba una constante numerica tamaño word\n");
				}
			}			
		}else{
			this.bw.write(this.cp+" "+strLinea+" Incorrecto, error de sintaxis\n");
		}
		
	}
	
	private void addSimbolo(String s,String ti,String v,String ta,String dir) throws Exception{
		Simbolo sim = new Simbolo(s,ti,v,ta,dir);
		this.simMan.addSimbolo(sim);
	}
	
	private void checkLineStack(String strLinea) throws Exception{
		Pattern pat = Pattern.compile("^[dD][wW]\\s+(\\w+)\\s+[dD][uU][pP]\\s*\\((\\w+)\\)$");
		Matcher mat = pat.matcher(strLinea);
		if(mat.find()){
			String cons1 = mat.group(1);
			String cons2 = mat.group(2);
			if(this.id.esConsWord(cons1)==1 && this.id.esConsWord(cons2)==1){
				bw.write(this.cp+" "+strLinea+" Correcto\n");
				this.cp = this.count.suma(this.cp,this.fase5.calcularTamañoSS(cons1));				
			}else{
				bw.write(this.cp+" "+strLinea+" Incorrecto, se esperaba una constante numerica palabra");
			}
		}else{
			bw.write(this.cp+" "+strLinea+" Incorrecto, error de sintaxis");
		}
	}
	
	public File getArchSalida() {
		return archSalida;
	}

}
