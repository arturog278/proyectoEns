package mx.uaemex.fi.ens.fase_5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.uaemex.fi.ens.fase_2.Identificador;
import mx.uaemex.fi.ens.fase_3.SimboloManager;

public class Fase_5 {
	private Identificador id;
	private SimboloManager sim;
 	public static final int CLC=1;
	public static final int INTO=2;
	public static final int PUSHA=3;
	public static final int PUSHF=4;
	public static final int IMUL=5;
	public static final int POP=6;
	public static final int OR=7;
	public static final int ADC=8;
	public static final int JGE=9;
	public static final int JMP=10;
	public static final int JNLE=11;
	public static final int LOOPE=12;

	public Fase_5() throws Exception {
		this.id = new Identificador();
		this.sim = new SimboloManager();
	}
		
	public int calcularTamañoCS(int ident,String str1,String str2) throws Exception{
		switch(ident){
		case CLC:
			return 1;
		case INTO:
			return 1;
		case PUSHA:
			return 1;
		case PUSHF:
			return 1;
		case IMUL:
			if(this.id.esRegistro(str1)!=1){
				return 2;
			}else{
				return 2+this.identificarDespMem(str1);
			}
		case POP:
			if(this.id.esRegistro(str1)!=1){
				return 2;
			}else if(this.id.esConsNumBoW(str1)!=-1){
				return 2+this.id.esConsNumBoW(str1);
			}else if(this.sim.findSimbolo(str1)==2){
				return 2+this.sim.getTamaño(str1);
			}else{
				return 2+this.identificarDespMem(str1);
			}
		case OR:
			if(this.id.esRegistro(str1)!=-1){
				if(this.id.esRegistro(str2)!=-1){
					return 2;
				}else if(this.id.esConsNumBoW(str2)!=-1){
					return 2+this.id.esConsNumBoW(str2);
				}else if(this.sim.findSimbolo(str2)==2){
					return 2+this.sim.getTamaño(str2);
				}else{
					return 2+this.identificarDespMem(str2);
				}
			}else{
				if(this.id.esRegistro(str2)!=-1){
					return 2+this.identificarDespMem(str1);
				}else if(this.id.esConsNumBoW(str2)!=-1){
					return 2+this.id.esConsNumBoW(str2);
				}else{
					return 2+this.sim.getTamaño(str2);
				}
			}
		case ADC:
			if(this.id.esRegistro(str1)!=-1){
				if(this.id.esRegistro(str2)!=-1){
					return 2;
				}else if(this.id.esConsNumBoW(str2)!=-1){
					return 2+this.id.esConsNumBoW(str2);
				}else if(this.sim.findSimbolo(str2)==2){
					return 2+this.sim.getTamaño(str2);
				}else{
					return 2+this.identificarDespMem(str2);
				}
			}else{
				if(this.id.esRegistro(str2)!=-1){
					return 2+this.identificarDespMem(str1);
				}else if(this.id.esConsNumBoW(str2)!=-1){
					return 2+this.id.esConsNumBoW(str2);
				}else{
					return 2+this.sim.getTamaño(str2);
				}
			}
		case JGE:
			return 4;
		case JMP:
			return 3;
		case JNLE:
			return 4;
		case LOOPE:
			return 2;			
		}
		return 0;
	}
	
	private int identificarDespMem(String obj) throws Exception{
		if(this.sim.findSimbolo(obj)==1){
			return 2;
		}else{
			Pattern pat = Pattern.compile("^(\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\+(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\])$"); 
			Matcher mat = pat.matcher(obj);
			int aux;
			if(mat.find()){
				if(mat.group(2)!=null){
					if((aux=this.id.esConsNumBoW(mat.group(2)))!=-1){
						return aux;
					}
					if((aux=this.id.esConsNumBoW(mat.group(3)))!=-1){
						return aux;
					}
					if((aux=this.id.esConsNumBoW(mat.group(4)))!=-1){
						return aux;
					}
				}else if(mat.group(5)!=null){
					if((aux=this.id.esConsNumBoW(mat.group(5)))!=-1){
						return aux;
					}
					if((aux=this.id.esConsNumBoW(mat.group(6)))!=-1){
						return aux;
					}
				}
			}
		}
		return 0;
	}
	
	public int calcularTamañoDS(String[] str1){
		if(!str1[1].equalsIgnoreCase("equ")){
			if(str1[1].equalsIgnoreCase("dw")){
				if(str1[4]==null){
					return 2;
				}else{
					int mult = this.id.consNumToDec(str1[2]);
					return mult*2;
				}
			}else{
				if(str1[4]==null){
					if(id.esConsString(str1[2])){
						return str1[2].length()-2;
					}else{
						return 1;
					}
				}else{
					int mult = this.id.consNumToDec(str1[2]);
					return mult;
				}
			}
		}
		return 0;
	}
	
	public int calcularTamañoSS(String str1){
		int tamaño =  this.id.consNumToDec(str1);		
		return tamaño*2;
	}

}
