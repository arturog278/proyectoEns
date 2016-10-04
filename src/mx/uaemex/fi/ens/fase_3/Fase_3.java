package mx.uaemex.fi.ens.fase_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mx.uaemex.fi.ens.fase_2.Identificador;

public class Fase_3 {
	private Identificador id;
	private BufferedReader br;
	private BufferedWriter bw;
	private File archSalida;
	private int segActual;

	public Fase_3(File archEntrada) throws Exception {
		this.archSalida = new File("resultadoFase3.txt");
		this.bw = new BufferedWriter(new FileWriter(this.archSalida));
		this.br = new BufferedReader(new FileReader(archEntrada));
		this.segActual = 0;
		this.id = new Identificador();
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
	}
	
	private void checkLineData(String strLinea) throws Exception{
		String[] vecLinea = new String[5];
		int i=0;
		String substr = "";
		int y=0;
		for (int x=0;x<strLinea.length();x++){
			switch(strLinea.charAt(x)){
			case '\"':
				y=x+1;
				while(strLinea.charAt(y)!='\"'){
					y+=1;
				}
				substr = strLinea.substring(x, y+1);
				vecLinea[i] = substr.trim();
				i+=1;
				x=y;
				break;
			case '[':
				y=x+1;
				while(strLinea.charAt(y)!=']'){
					y+=1;
				}
				substr = strLinea.substring(x, y+1);
				vecLinea[i] = substr.trim();
				i+=1;
				x=y;
				break;
			case '\'':
				y=x+1;
				while(strLinea.charAt(y)!='\''){
					y+=1;
				}
				substr = strLinea.substring(x, y+1);
				vecLinea[i] = substr.trim();
				i+=1;
				x=y;
				break;
			default:
				if(strLinea.charAt(x)!=' ' & strLinea.charAt(x)!=','& strLinea.charAt(x)!=':'){
					if(strLinea.length()-x>3 && strLinea.substring(x, x+4)=="dup("){           				
						y=x+1;
           				while(strLinea.charAt(y)!=')'){
           					y+=1;
           				}
           				substr = strLinea.substring(x, y+1);
           				vecLinea[i] = substr.trim();
        				i+=1;
           				x=y;            						
					}else{
						y=x+1;
						if(y==strLinea.length()){
							vecLinea[i] = strLinea.charAt(x)+"";
							i+=1;
						}else{           							
							while(strLinea.charAt(y)!=' ' & strLinea.charAt(x)!=',' & strLinea.charAt(x)!=':'){
								y+=1;            								
								if(y==strLinea.length()){
									y=y-1;
									break;
								}
							}            							
							substr = strLinea.substring(x, y+1);
							vecLinea[i] = substr.trim();
							i+=1;
   							x=y;   
						}			
					}
				}
			}
		}
		boolean esCorrecto = true;
		for(int j=0;j<5;j++){
			if(vecLinea[j]!=null){
				switch(j){
				case 0:
					if(!this.id.esSimb(vecLinea[j])){
						bw.write(strLinea+" Incorrecto, Se esperaba un simbolo\n");
						esCorrecto = false;
						j=5;
					}
					break;
				case 1:
					if(this.id.esPseudoDS(vecLinea[j])==-1){
						bw.write(strLinea+" Incorrecto, Se esperaba una pseudoinstruccion db,dw o equ\n");
						esCorrecto = false;
						j=5;
					}
					break;
				case 2:
					switch(this.id.esPseudoDS(vecLinea[1])){
					case 1:
						if(this.id.esConsByte(vecLinea[2])==-1){							
							bw.write(strLinea+" Incorrecto, Se esperaba una constante de tamaño byte\n");
							esCorrecto = false;
							j=5;
						}
						break;
					case 2:
						if(this.id.esConsWord(vecLinea[2])!=1){
							bw.write(strLinea+" Incorrecto, Se esperaba una constante de tamaño word\n");
							esCorrecto = false;
							j=5;
						}
						break;
					case 3:
						if(this.id.esConsWord(vecLinea[2])!=1){
							bw.write(strLinea+" Incorrecto, Se esperaba una constante de tamaño word\n");
							esCorrecto = false;
							j=5;
						}
						break;
					}
					break;
				case 3:
					if(!this.id.esPseudo(vecLinea[j])){
						bw.write(strLinea+" Incorrecto, Se esperaba una pseudoinstruccion dup\n");
						esCorrecto = false;
						j=5;
					}
					break;
				case 4:
					vecLinea[4] = vecLinea[4].replace('(',' ');
					vecLinea[4] = vecLinea[4].replace(')', ' ');
					vecLinea[4] = vecLinea[4].trim();
					switch(this.id.esPseudoDS(vecLinea[1])){
					case 1:
						if(this.id.esConsByte(vecLinea[4])!=1){
							bw.write(strLinea+" Incorrecto, Se esperaba una constante tipo byte\n");
							esCorrecto = false;
							j=5;
						}
						break;
					case 2:
						if(this.id.esConsWord(vecLinea[4])!=1){
							bw.write(strLinea+" Incorrecto, Se esperaba una constante tipo word\n");
							esCorrecto = false;
							j=5;
						}
						break;
					}
				}
			}
		}
		if(esCorrecto){
			bw.write(strLinea+" Correcto\n");
		}
		/*
		if(this.id.esSimb(vecLinea[0])){
				switch(this.id.esPseudoDS(vecLinea[1])){
				case 1:
					if(this.id.esConsByte(vecLinea[2])!=-1){
						bw.write(strLinea+" Correcto\n");
					}else{
						bw.write(strLinea+" Incorrecto, Se esperaba una constante de tamaño byte\n");
					}
					break;
				case 2:
					if(this.id.esConsWord(vecLinea[2])==1){
						bw.write(strLinea+" Correcto\n");
					}else{
						bw.write(strLinea+" Incorrecto, Se esperaba una constante de tamaño word\n");
					}
					break;
				case 3:
					if(this.id.esConsWord(vecLinea[2])==1){
						bw.write(strLinea+" Correcto\n");
					}else{
						bw.write(strLinea+" Incorrecto, Se esperaba una constante de tamaño word\n");
					}
					break;
				case -1:
					bw.write(strLinea+" Incorrecto, Se esperaba una pseudoinstruccion db,dw o equ\n");
				}			
		}else{
			bw.write(strLinea+" Incorrecto, Se esperaba un simbolo\n");
		}*/
	}
	
	private void checkLineStack(String strLinea) throws Exception{
		String[] vecLinea = new String[3];
		int i=0;
		String substr = "";
		int y=0;
		for (int x=0;x<strLinea.length();x++){
			switch(strLinea.charAt(x)){
			case '\"':
				y=x+1;
				while(strLinea.charAt(y)!='\"'){
					y+=1;
				}
				substr = strLinea.substring(x, y+1);
				vecLinea[i] = substr.trim();
				i+=1;
				x=y;
				break;
			case '[':
				y=x+1;
				while(strLinea.charAt(y)!=']'){
					y+=1;
				}
				substr = strLinea.substring(x, y+1);
				vecLinea[i] = substr.trim();
				i+=1;
				x=y;
				break;
			case '\'':
				y=x+1;
				while(strLinea.charAt(y)!='\''){
					y+=1;
				}
				substr = strLinea.substring(x, y+1);
				vecLinea[i] = substr.trim();
				i+=1;
				x=y;
				break;
			default:
				if(strLinea.charAt(x)!=' ' & strLinea.charAt(x)!=','& strLinea.charAt(x)!=':'){
					if(strLinea.length()-x>3 && strLinea.substring(x, x+4)=="dup("){           				
						y=x+1;
           				while(strLinea.charAt(y)!=')'){
           					y+=1;
           				}
           				substr = strLinea.substring(x, y+1);
           				vecLinea[i] = substr.trim();
        				i+=1;
           				x=y;            						
					}else{
						y=x+1;
						if(y==strLinea.length()){
							vecLinea[i] = strLinea.charAt(x)+"";
							i+=1;
						}else{           							
							while(strLinea.charAt(y)!=' ' & strLinea.charAt(x)!=',' & strLinea.charAt(x)!=':'){
								y+=1;            								
								if(y==strLinea.length()){
									y=y-1;
									break;
								}
							}            							
							substr = strLinea.substring(x, y+1);
							vecLinea[i] = substr.trim();
							i+=1;
   							x=y;   
						}			
					}
				}
			}
		}
		if(vecLinea[0].equalsIgnoreCase("dw")){
			if(this.id.esConsWord(vecLinea[1])==1){
				if(this.id.esPseudo(vecLinea[2])){
					bw.write(strLinea+" Correcto\n");
				}else{
					bw.write(strLinea+" Incorrecto, se espereba un dup al final");
				}
			}else{
				bw.write(strLinea+" Incorrecto, se esperaba una constante numerica palabra");
			}
		}else{
			bw.write(strLinea+" Incorrecto, Se esperaba un simbolo\n");
		}
	}

	public File getArchSalida() {
		return archSalida;
	}

}
