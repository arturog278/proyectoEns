package mx.uaemex.fi.ens.fase_2;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.uaemex.fi.ens.fase_3.SimboloManager;


public class Identificador {
	private final String[] instruc = {"CLC","INTO","PUSHA","PUSHF","IMUL","POP","OR","ADC","JGE","JMP","JNLE","LOOPE"};
	private final String[] reg16 = {"AX","BX","CX","DX","SI","DI","SP","BP"};
	private final String[] regS = {"SS","CS","DS","ES"};
	private final String[] reg8 = {"AL","AH","BL","BH","CL","CH","DL","DH",};
	private final String[] regMem = {"BX","BP","DI","SI"};
	private final String[] pseudo = {"Data Segment","Stack Segment","Code Segment","ENDS","DB","DW","EQU","DUP","BYTE PTR","WORD PTR"};
	private final char[] hexa = {'A','B','C','D','E','F'};
	private SimboloManager simMan;
	
	
	public Identificador() throws Exception {
		this.simMan = new SimboloManager();
	}
	
	public boolean esMemoria(String obj) throws Exception{
		Pattern pat = Pattern.compile("^(\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\+(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\])$"); 
		Matcher mat = pat.matcher(obj);
		if(mat.find()){
			if(mat.group(2)!=null){
				for(String s:this.regMem){
					if(s.equalsIgnoreCase(mat.group(2))||this.esConsByte(mat.group(2))==1||this.esConsWord(mat.group(2))==1){
						for(String s2:this.regMem){
							if(s2.equalsIgnoreCase(mat.group(3))||this.esConsByte(mat.group(3))==1||this.esConsWord(mat.group(3))==1){
								for(String s3:this.regMem){
									if(s3.equalsIgnoreCase(mat.group(4))||this.esConsByte(mat.group(4))==1||this.esConsWord(mat.group(4))==1){
										return true;
									}
								}
							}
						}
					}
				}
			}else if(mat.group(5)!=null){
				for(String s:this.regMem){
					if(s.equalsIgnoreCase(mat.group(5))||this.esConsByte(mat.group(5))==1||this.esConsWord(mat.group(5))==1){
						for(String s2:this.regMem){
							if(s2.equalsIgnoreCase(mat.group(6))||this.esConsByte(mat.group(6))==1||this.esConsWord(mat.group(6))==1){
								return true;
							}
						}
					}
				}
			}else{
				for(String s:this.regMem){
					if(s.equalsIgnoreCase(mat.group(7))||this.esConsByte(mat.group(7))==1||this.esConsWord(mat.group(7))==1){
						return true;
					}					
				}
			}
		}else if(this.simMan.findSimbolo(obj)==1){
			return true;
		}
		return false;
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
	
	public int esRegistro(String obj){
		for(int i=0;i<this.reg8.length;i++){
			if(this.reg8[i].equalsIgnoreCase(obj)){
				return 1;
			}
		}
		for(int i=0;i<this.reg16.length;i++){
			if(this.reg16[i].equalsIgnoreCase(obj)){
				return 2;
			}
		}
		return -1;
	}
	
	public boolean esRegistroS(String obj){
		for(int i=0;i<this.regS.length;i++){
			if(this.regS[i].equalsIgnoreCase(obj)){
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
		if(obj.charAt(obj.length()-1)=='H' && Character.isDigit(obj.charAt(0))){
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
         if(s.charAt(s.length()-1)=='H'){
			 s=s.substring(0, s.length()-1);
		 }
         int val = 0;
         for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
             int d = digits.indexOf(c);
             val = (int) (d*Math.pow(16,(s.length()-i-1)) + val);
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
			 if(obj.length()==3){			
				 if((obj.charAt(0)=='"' && obj.charAt(2)=='"') || (obj.charAt(0)=='\'' && obj.charAt(2)=='\'')){
					 return 2;
				 }
			 }
		 }
		 return -1;		 
	 }
	 
	 public int esConsNumBoW(String obj){
		 if(this.esConsByte(obj)==1){
			 return 1;
		 }else if(this.esConsWord(obj)==1){
			 return 2;
		 }
		 return -1;
	 }
	 
	 public int consNumToDec(String obj){
		 if(this.esConsBin(obj)){
			 return Identificador.bin2decimal(obj);
		 }else if(this.esConsHexa(obj)){
			 return Identificador.hex2decimal(obj);
		 }
		 return Integer.parseInt(obj);
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
		 }
		 return -1;		 
	 }
	 
}