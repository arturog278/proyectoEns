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
	
	public int esPseudoDS(String obj){
		if(obj.equalsIgnoreCase("db"))
			return 1;
		else if(obj.equalsIgnoreCase("dw"))
			return 2;
		else if(obj.equalsIgnoreCase("equ"))
			return 3;
		else
			return -1;
	}
	
	public int idSegment(String obj){
		if(obj.equalsIgnoreCase("Data Segment"))
			return 1;
		else if(obj.equalsIgnoreCase("Stack Segment"))
			return 2;
		else if(obj.equalsIgnoreCase("Code Segment"))
			return 3;
		else if(obj.equalsIgnoreCase("ends"))
			return 0;
		else
			return -1;
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
		if(Character.isLetter(obj.charAt(0)) && obj.length()<=10 && !obj.contains(" ")){
			return true;
		}
		return false;
	}
	
	 private static int hex2decimal(String s) {
         String digits = "0123456789ABCDEF";
         s = s.toUpperCase();
         int val = 0;
         for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
             int d = digits.indexOf(c);
             val = 16*val + d;
         }
         return val;
     }
	 
	 private static int bin2decimal(String s){
		 int num=Integer.parseInt(s,2);
		 return num;
	 }
	 
	 public int esConsByte(String obj){
		 if(this.esConsDec(obj)){
			 if(Integer.parseInt(obj)>-127 && Integer.parseInt(obj)<256){
				 return 1;
			 }
		 }else if(this.esConsHexa(obj)){
			 int deci = Identificador.hex2decimal(obj.substring(0, obj.length()-1));
			 if(deci>-127 && deci<256){
				 return 1;
			 }
		 }else if(this.esConsBin(obj)){
			 int deci = Identificador.bin2decimal(obj.substring(0,obj.length()-1));
			 if(deci>-127 && deci<256){
				 return 1;
			 }
		 }else if(this.esConsString(obj)){
			 return 2;
		 }
		 return -1;		 
	 }
	 
	 public int esConsWord(String obj){
		 if(this.esConsDec(obj)){
			 if(Integer.parseInt(obj)>-32769 && Integer.parseInt(obj)<65536){
				 return 1;
			 }
		 }else if(this.esConsHexa(obj)){
			 int deci = Identificador.hex2decimal(obj.substring(0, obj.length()-1));
			 if(deci>-32769 && deci<65536){
				 return 1;
			 }
		 }else if(this.esConsBin(obj)){
			 int deci = Identificador.bin2decimal(obj.substring(0,obj.length()-1));
			 if(deci>-32769 && deci<65536){
				 return 1;
			 }
		 }else if(this.esConsString(obj)){
			 return 2;
		 }
		 return -1;		 
	 }
	 
}