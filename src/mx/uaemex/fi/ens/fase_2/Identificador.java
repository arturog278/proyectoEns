package mx.uaemex.fi.ens.fase_2;

import java.util.Arrays;

public class Identificador {
	private final String[] instruc = {"CLC","INTO","PUSHA","PUSHF","IMUL","POP","OR","ADC","JGE","JMP","JNLE","LOOPE"};
	private final String[] reg = {"AL","AH","BL","BH","CL","CH","DL","DH","AX","BX","CX","DX","SI","DI","SP","BP","SS","CS","DS","ES"};
	private final String[] pseudo = {"Data Segment","Stack Segment","Code Segment","ENDS","DB","DW","EQU","DUP","BYTE PTR","WORD PTR"};
	
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

}
