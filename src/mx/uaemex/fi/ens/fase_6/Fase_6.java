package mx.uaemex.fi.ens.fase_6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.uaemex.fi.ens.fase_2.Identificador;
import mx.uaemex.fi.ens.fase_3.SimboloManager;

public class Fase_6 {
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
	
	public Fase_6() throws Exception{
		this.id = new Identificador();
		this.sim = new SimboloManager();
	}
	
	private String getMod(String obj) throws Exception{
		if(this.sim.findSimbolo(obj)==1){
			return "00";
		}else if(this.id.esRegistro(obj)!=-1){
			return "11";
		}else{
			Pattern pat = Pattern.compile("^(\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\+(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\])$"); 
			Matcher mat = pat.matcher(obj);
			if(mat.find()){
				if(mat.group(7)!=null&&mat.group(7)!="BP"){
					return "00";
				}
				if(mat.group(5)!=null){
					if(this.id.esConsByte(mat.group(5))==1||this.id.esConsByte(mat.group(6))==1){
						return "01";
					}else if(this.id.esConsWord(mat.group(5))==1||this.id.esConsWord(mat.group(6))==1){
						return "10";
					}else{
						return "00";
					}
				}
				if(mat.group(2)!=null){
					if(this.id.esConsByte(mat.group(2))==1||this.id.esConsByte(mat.group(3))==1||this.id.esConsByte(mat.group(4))==1){
						return "01";
					}else if(this.id.esConsWord(mat.group(2))==1||this.id.esConsWord(mat.group(3))==1||this.id.esConsWord(mat.group(4))==1){
						return "10";
					}
				}
			}
		}
		return null;
	}
	
	private String getReg(String obj){
		switch(obj){
		case "AX":
		case "AL":
			return "000";
		case "CX":
		case "CL":
			return "001";
		case "DX":
		case "DL":
			return "010";
		case "BX":
		case "BL":
			return "011";
		case "SP":
		case "AH":
			return "100";
		case "BP":
		case "CH":
			return "101";
		case "SI":
		case "DH":
			return "110";
		case "DI":
		case "BH":
			return "111";
		}
		return null;
	}
	
	private String getW(String obj) throws Exception{
		if(this.id.esRegistro(obj)==1){
			return "0";
		}else if(this.id.esRegistro(obj)==2){
			return "1";
		}
		if(this.sim.getTamaño(obj)==1){
			return "0";
		}else if(this.sim.getTamaño(obj)==2){
			return "1";
		}
		return "0";
	}
	
	private String getRM(String mod,String obj) throws Exception{
		Pattern pat = Pattern.compile("^(\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\+(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\])$"); 
		Matcher mat = pat.matcher(obj);
		mat.find();
		switch(mod){
		case "00":
			if(this.sim.findSimbolo(obj)==1){
				return "110";
			}
			if(mat.group(7)!=null){
				if(mat.group(7).equalsIgnoreCase("SI")){
					return "100";
				}
				if(mat.group(7).equalsIgnoreCase("DI")){
					return "101";
				}
				if(mat.group(7).equalsIgnoreCase("BX")){
					return "111";
				}
			}else{
				if(mat.group(5).equalsIgnoreCase("BX")){
					if(mat.group(6).equalsIgnoreCase("SI")){
						return "000";
					}
					if(mat.group(6).equalsIgnoreCase("DI")){
						return "001";
					}
				}else if(mat.group(5).equalsIgnoreCase("BP")){
					if(mat.group(6).equalsIgnoreCase("SI")){
						return "010";
					}
					if(mat.group(6).equalsIgnoreCase("DI")){
						return "011";
					}
				}else if(mat.group(5).equalsIgnoreCase("SI")){
					if(mat.group(6).equalsIgnoreCase("BX")){
						return "000";
					}
					if(mat.group(6).equalsIgnoreCase("BP")){
						return "010";
					}
				}else if(mat.group(5).equalsIgnoreCase("DI")){
					if(mat.group(6).equalsIgnoreCase("BX")){
						return "001";
					}
					if(mat.group(6).equalsIgnoreCase("BP")){
						return "011";
					}
				}
			}
			break;
		case "11":
			if(obj.equalsIgnoreCase("AL")||obj.equalsIgnoreCase("AX")){
				return "000";
			}else if(obj.equalsIgnoreCase("CL")||obj.equalsIgnoreCase("CX")){
				return "001";
			}else if(obj.equalsIgnoreCase("DL")||obj.equalsIgnoreCase("DX")){
				return "010";
			}else if(obj.equalsIgnoreCase("BL")||obj.equalsIgnoreCase("BX")){
				return "011";
			}else if(obj.equalsIgnoreCase("AH")||obj.equalsIgnoreCase("SP")){
				return "100";
			}else if(obj.equalsIgnoreCase("CH")||obj.equalsIgnoreCase("BP")){
				return "101";
			}else if(obj.equalsIgnoreCase("DH")||obj.equalsIgnoreCase("SI")){
				return "110";
			}else if(obj.equalsIgnoreCase("BH")||obj.equalsIgnoreCase("DI")){
				return "111";
			}
			break;
		case "01":
		case "10":
			if(mat.group(5)!=null){
				if(mat.group(5).equalsIgnoreCase("SI")||mat.group(6).equalsIgnoreCase("SI")){
					return "100";
				}else if(mat.group(5).equalsIgnoreCase("DI")||mat.group(6).equalsIgnoreCase("DI")){
					return "101";
				}else if(mat.group(5).equalsIgnoreCase("BP")||mat.group(6).equalsIgnoreCase("BP")){
					return "110";
				}else if(mat.group(5).equalsIgnoreCase("BX")||mat.group(6).equalsIgnoreCase("BX")){
					return "111";
				}
			}else{
				if(mat.group(2).equalsIgnoreCase("BX")){
					if(mat.group(3).equalsIgnoreCase("SI")||mat.group(4).equalsIgnoreCase("SI")){
						return "000";
					}
					if(mat.group(3).equalsIgnoreCase("DI")||mat.group(4).equalsIgnoreCase("DI")){
						return "001";
					}
				}else if(mat.group(2).equalsIgnoreCase("BP")){
					if(mat.group(3).equalsIgnoreCase("SI")||mat.group(4).equalsIgnoreCase("SI")){
						return "010";
					}
					if(mat.group(3).equalsIgnoreCase("DI")||mat.group(4).equalsIgnoreCase("DI")){
						return "011";
					}
				}else if(mat.group(2).equalsIgnoreCase("SI")){
					if(mat.group(3).equalsIgnoreCase("BX")||mat.group(4).equalsIgnoreCase("BX")){
						return "000";
					}
					if(mat.group(3).equalsIgnoreCase("BP")||mat.group(4).equalsIgnoreCase("BP")){
						return "010";
					}
				}else if(mat.group(2).equalsIgnoreCase("DI")){
					if(mat.group(3).equalsIgnoreCase("BX")||mat.group(4).equalsIgnoreCase("BX")){
						return "001";
					}
					if(mat.group(3).equalsIgnoreCase("BP")||mat.group(4).equalsIgnoreCase("BP")){
						return "011";
					}
				}else{
					if(mat.group(3).equalsIgnoreCase("BX")){
						if(mat.group(4).equalsIgnoreCase("SI")){
							return "000";
						}else if(mat.group(4).equalsIgnoreCase("DI")){
							return "001";
						}
					}else if(mat.group(3).equalsIgnoreCase("BP")){
						if(mat.group(4).equalsIgnoreCase("SI")){
							return "010";
						}else if(mat.group(4).equalsIgnoreCase("DI")){
							return "011";
						}
					}else if(mat.group(3).equalsIgnoreCase("SI")){
						if(mat.group(4).equalsIgnoreCase("BX")){
							return "000";
						}else if(mat.group(4).equalsIgnoreCase("BP")){
							return "010";
						}
					}if(mat.group(3).equalsIgnoreCase("DI")){
						if(mat.group(4).equalsIgnoreCase("BX")){
							return "001";
						}else if(mat.group(4).equalsIgnoreCase("BP")){
							return "011";
						}
					}
				}
			}
		}
		return null;
	}
	
	private String binToHex(String bin){
		int decimal = Integer.parseInt(bin,2);
		String hexStr = Integer.toString(decimal,16).toUpperCase();
		return hexStr;
	}
	
	private boolean tieneDespMem(String obj) throws Exception{
		if(this.sim.findSimbolo(obj)==1){
			return true;
		}else{
			Pattern pat = Pattern.compile("^(\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\+(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\])$"); 
			Matcher mat = pat.matcher(obj);
			if(mat.find()){
				if(mat.group(2)!=null){
					if(this.id.esConsNumBoW(mat.group(2))!=-1){
						return true;
					}
					if(this.id.esConsNumBoW(mat.group(3))!=-1){
						return true;
					}
					if(this.id.esConsNumBoW(mat.group(4))!=-1){
						return true;
					}
				}else if(mat.group(5)!=null){
					if(this.id.esConsNumBoW(mat.group(5))!=-1){
						return true;
					}
					if(this.id.esConsNumBoW(mat.group(6))!=-1){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private String getDespMem(String obj) throws Exception{
		if(this.sim.findSimbolo(obj)==1){
			return this.sim.getDireccion(obj);
		}else{
			Pattern pat = Pattern.compile("^(\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\+(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\+\\s*(\\w+)\\s*\\]|\\[\\s*(\\w+)\\s*\\])$"); 
			Matcher mat = pat.matcher(obj);
			int aux;
			if(mat.find()){
				if(mat.group(2)!=null){
					if((aux=this.id.esConsNumBoW(mat.group(2)))!=-1){
						if(aux==1){
							return this.consNumToHex(1,mat.group(2));
						}else if(aux==2){
							return this.consNumToHex(2, mat.group(2));
						}
					}
					if((aux=this.id.esConsNumBoW(mat.group(3)))!=-1){
						if(aux==1){
							return this.consNumToHex(1,mat.group(3));
						}else if(aux==2){
							return this.consNumToHex(2, mat.group(3));
						}
					}
					if((aux=this.id.esConsNumBoW(mat.group(4)))!=-1){
						if(aux==1){
							return this.consNumToHex(1,mat.group(4));
						}else if(aux==2){
							return this.consNumToHex(2, mat.group(4));
						}
					}
				}else if(mat.group(5)!=null){
					if((aux=this.id.esConsNumBoW(mat.group(5)))!=-1){
						if(aux==1){
							return this.consNumToHex(1,mat.group(5));
						}else if(aux==2){
							return this.consNumToHex(2, mat.group(5));
						}
					}
					if((aux=this.id.esConsNumBoW(mat.group(6)))!=-1){
						if(aux==1){
							return this.consNumToHex(1,mat.group(6));
						}else if(aux==2){
							return this.consNumToHex(2, mat.group(6));
						}
					}
				}
			}
		}
		return null;
	}
	
	private String consNumToHex(int tamaño,String obj){
		 if(this.id.esConsBin(obj)){
			 String number = obj.substring(0, obj.length()-1);
			 number = this.binToHex(number);
			 while(number.length()<tamaño*2){
				 number = "0"+number;
			 }
			 return number;
		 }else if(this.id.esConsDec(obj)){
			 int num = Integer.parseInt(obj);
			 String number = Integer.toString(num,16).toUpperCase();
			 while(number.length()<tamaño*2){
				 number = "0"+number;
			 }
			return number;
		 }
		 String number = obj.substring(0, obj.length()-1);
		 int decimal = Integer.parseInt(number,16);
		 number = Integer.toString(decimal,16).toUpperCase();
		 while(number.length()<tamaño*2){
			 number = "0"+number;
		 }
		 return number;
	 }
	
	private String getRegs2(String obj){
		switch(obj){
		case "ES":
			return "00";
		case "CS":
			return "01";
		case "SS":
			return "10";
		case "DS":
			return "11";
		}
		return null;
	}
	
	public String getCode(int id,String str1,String str2) throws Exception{
		switch(id){
		case CLC:
			return "F8";
		case INTO:
			return "CE";
		case PUSHA:
			return "60";
		case PUSHF:
			return "9C";
		case IMUL:
		{
			String w = this.getW(str1);
			String mod = this.getMod(str1);
			String rm = this.getRM(mod, str1);
			if(this.id.esRegistro(str1)!=-1){
				String code = "1111011"+w+mod+"101"+rm;
				String res = this.binToHex(code);
				return this.binToHex(code);
			}else{
				if(this.tieneDespMem(str1)){
					String desp = this.getDespMem(str1);
					String code = "1111011"+w+mod+"101"+rm;
					return this.binToHex(code)+desp;
				}else{
					String code = "1111011"+w+mod+"101"+rm;
					return this.binToHex(code);
				}
			}
		}
		case POP:
		{
			if(this.id.esRegistro(str1)!=-1){
				String reg = this.getReg(str1);
				String code = "01011"+reg;
				return this.binToHex(code);
			}else if(this.id.esRegistroS(str1)){
				String regs2 = this.getRegs2(str1);
				String code = "000"+regs2+"111";
				return this.binToHex(code);
			}else{
				String mod = this.getMod(str1);
				String rm = this.getRM(mod, str1);
				if(this.tieneDespMem(str1)){
					String desp = this.getDespMem(str1);
					String code = "10001111"+mod+"000"+rm;
					return this.binToHex(code)+desp;
				}else{
					String code = "10001111"+mod+"000"+rm;
					return this.binToHex(code);
				}				
			}
		}
		case OR:
		{
			if(this.id.esRegistro(str1)!=-1){
				String mod = this.getMod(str1);
				String rm = this.getRM(mod, str1);
				String w = this.getW(str1);
				int tamaño;
				if(this.id.esRegistro(str2)!=-1){
					String reg = this.getReg(str2);
					String code = "100"+w+mod+reg+rm;
					return "0"+this.binToHex(code);
				}else if((tamaño=this.id.esConsNumBoW(str2))!=-1){
					String inm = this.consNumToHex(tamaño, str2);
					String code = "1000000"+w+mod+"001"+rm;
					return this.binToHex(code)+inm;
				}else if(this.sim.findSimbolo(str2)==2){
					String inm = this.sim.getValor(str2);
					tamaño = this.id.esConsNumBoW(inm);
					inm = this.consNumToHex(tamaño, inm);
					String code = "1000000"+w+mod+"001"+rm;
					return this.binToHex(code)+inm;
				}else{
					mod = this.getMod(str2);
					rm = this.getRM(mod, str2);
					String reg = this.getReg(str1);
					if(this.tieneDespMem(str2)){
						String desp = this.getDespMem(str2);
						String code = "101"+w+mod+reg+rm;
						return "0"+this.binToHex(code)+desp;
					}else{
						String code = "101"+w+mod+reg+rm;
						return "0"+this.binToHex(code);
					}	
				}
			}else{
				String mod = this.getMod(str1);
				String rm = this.getRM(mod, str1);
				String w = this.getW(str1);
				int tamaño;
				if(this.id.esRegistro(str2)!=-1){
					String reg = this.getReg(str2);
					if(this.tieneDespMem(str1)){
						String desp = this.getDespMem(str1);						
						String code = "100"+w+mod+reg+rm;
						return "0"+this.binToHex(code)+desp;
					}else{
						String code = "100"+w+mod+reg+rm;
						return "0"+this.binToHex(code);
					}					
				}else if((tamaño=this.id.esConsNumBoW(str2))!=-1){
					String inm = this.consNumToHex(tamaño, str2);
					if(this.tieneDespMem(str1)){
						String desp = this.getDespMem(str1);
						String code = "1000000"+w+mod+"001"+rm;
						return this.binToHex(code)+desp+inm;
					}else{
						String code = "1000000"+w+mod+"001"+rm;
						return this.binToHex(code)+inm;
					}
				}else{
					String inm = this.sim.getValor(str2);
					tamaño = this.id.esConsNumBoW(inm);
					inm = this.consNumToHex(tamaño, inm);
					if(this.tieneDespMem(str1)){
						String desp = this.getDespMem(str1);
						String code = "1000000"+w+mod+"001"+rm;
						return this.binToHex(code)+desp+inm;
					}else{
						String code = "1000000"+w+mod+"001"+rm;
						return this.binToHex(code)+inm;
					}
				}
			}
		}
		case ADC:
		{
			if(this.id.esRegistro(str1)!=-1){
				String mod = this.getMod(str1);
				String rm = this.getRM(mod, str1);
				String w = this.getW(str1);
				int tamaño;
				if(this.id.esRegistro(str2)!=-1){
					String reg = this.getReg(str2);
					String code = "0001000"+w+mod+reg+rm;
					return this.binToHex(code);
				}else if((tamaño=this.id.esConsNumBoW(str2))!=-1){
					String inm = this.consNumToHex(Integer.parseInt(w)+1, str2);
					String code = "1000000"+w+mod+"010"+rm;
					return this.binToHex(code)+inm;
				}else if(this.sim.findSimbolo(str2)==2){
					String inm = this.sim.getValor(str2);
					tamaño = this.id.esConsNumBoW(inm);
					inm = this.consNumToHex(tamaño, inm);
					String code = "1000000"+w+mod+"010"+rm;
					return this.binToHex(code)+inm;
				}else{
					mod = this.getMod(str2);
					rm = this.getRM(mod, str2);
					String reg = this.getReg(str1);
					if(this.tieneDespMem(str2)){
						String desp = this.getDespMem(str2);
						String code = "0001001"+w+mod+reg+rm;
						return this.binToHex(code)+desp;
					}else{
						String code = "0001001"+w+mod+reg+rm;
						return this.binToHex(code);
					}	
				}
			}else{
				String mod = this.getMod(str1);
				String rm = this.getRM(mod, str1);
				String w = this.getW(str1);
				int tamaño;
				if(this.id.esRegistro(str2)!=-1){
					String reg = this.getReg(str2);
					if(this.tieneDespMem(str1)){
						String desp = this.getDespMem(str1);						
						String code = "0001000"+w+mod+reg+rm;
						return this.binToHex(code)+desp;
					}else{
						String code = "0001000"+w+mod+reg+rm;
						return this.binToHex(code);
					}					
				}else if((tamaño=this.id.esConsNumBoW(str2))!=-1){
					String inm = this.consNumToHex(tamaño, str2);
					if(this.tieneDespMem(str1)){
						String desp = this.getDespMem(str1);
						String code = "1000000"+w+mod+"010"+rm;
						return this.binToHex(code)+desp+inm;
					}else{
						String code = "1000000"+w+mod+"010"+rm;
						return this.binToHex(code)+inm;
					}
				}else{
					String inm = this.sim.getValor(str2);
					tamaño = this.id.esConsNumBoW(inm);
					inm = this.consNumToHex(tamaño, inm);
					if(this.tieneDespMem(str1)){
						String desp = this.getDespMem(str1);
						String code = "1000000"+w+mod+"010"+rm;
						return this.binToHex(code)+desp+inm;
					}else{
						String code = "1000000"+w+mod+"010"+rm;
						return this.binToHex(code)+inm;
					}
				}
			}
		}
		case JGE:
		{
			String desp = this.sim.getDireccion(str1);
			String code = "111110001101";
			return "0"+this.binToHex(code)+desp;
		}
		case JMP:
		{
			String desp = this.sim.getDireccion(str1);
			String code = "11101001";
			return this.binToHex(code)+desp;
		}
		case JNLE:
		{
			String desp = this.sim.getDireccion(str1);
			String code= "111110001111";
			return "0"+this.binToHex(code)+desp;
		}
		case LOOPE:
		{
			int posAct = Integer.parseInt(str1, 16);
			int posEtq = Integer.parseInt(this.sim.getDireccion(str2), 16);
			int dif = posAct-posEtq+2;
			String desp = this.consNumToHex(1, String.valueOf(dif));
			String code = "11100001";
			return this.binToHex(code)+desp;
		}
		}
		return null;
	}

}
