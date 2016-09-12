package mx.uaemex.fi.ens.fase_2;

import java.util.Arrays;

public class Identificador {
	private final String[] instruc = {"CLC","INTO","PUSHA","PUSHF","IMUL","POP","OR","ADC","JGE","JMP","JNLE","LOOPE"};
	private final String[] reg = {"AL","AH","BL","BH","CL","CH","DL","DH","AX","BX","CX","DX","SI","DI","SP","BP","SS","CS","DS","ES"};
	private final String[] pseudo = {"Data Segment","Stack Segment","Code Segment","ENDS","DB","DW","EQU","DUP","BYTE PTR","WORD PTR"};
	private final char[] hexa = {'A','B','C','D','E','F'};
	
	
	public Identificador() {
		
	}
	
	public boolean esInstruccion(String obj){
		for(int i=0;i<this.instruc.length;i++){
			if(this.instruc[i].equalsIgnoreCase(obj)){
				return true;
			}
		}
		return false;
	}
	
	public boolean esRegistro(String obj){
		for(int i=0;i<this.reg.length;i++){
			if(this.reg[i].equalsIgnoreCase(obj)){
				return true;
			}
		}
		return false;
	}
	
	public boolean esPseudo(String obj){
		for(int i=0;i<this.pseudo.length;i++){
			if(this.pseudo[i].equalsIgnoreCase(obj)){
				return true;
			}else if(obj.length()>3 && obj.substring(0, 4).equalsIgnoreCase("dup(")){
				return true;
			}
		}
		return false;
	}
	public boolean esConsString(String obj){
		if(obj.charAt(0)=='\"'||obj.charAt(0)=='\''){
			if(obj.charAt(obj.length()-1)=='\"'||obj.charAt(0)=='\''){
				return true;
			}
		}
		return false;
}
	public boolean esConsDec(String obj){
		
		return obj.matches("\\d+");
	}
	public boolean esConsHexa(String obj){
		obj=obj.toUpperCase();
		if(obj.charAt(obj.length()-1)=='H' && obj.charAt(0)=='0'){
			for(int i=1;i<obj.length()-2;i++){
				for(int j=0;j<6;j++){
				if(!Character.isDigit(obj.charAt(i)) && obj.charAt(i)!=this.hexa[j]){
					if(j==5){
						return false;
					}
				}
				else{
				
				j=6;
					}
			}
		}
		return true;
		}
		return false;
	}
	
	public boolean esConsBin(String obj){
		obj=obj.toUpperCase();
		if(obj.charAt(obj.length()-1)=='B'){
			for(int i=0;i<obj.length()-2;i++){				
				if(obj.charAt(i)!='1' && obj.charAt(i)!='0'){
					return false;
		}

			}
			return true;
	}
		else{
			return false;
		}
	
}
	public boolean esSimb(String obj){
		if(Character.isLetter(obj.charAt(0)) && obj.length()<=10){
			return true;
		}
		return false;
	}
}