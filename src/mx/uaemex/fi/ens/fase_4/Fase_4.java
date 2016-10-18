package mx.uaemex.fi.ens.fase_4;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.uaemex.fi.ens.fase_2.Identificador;
import mx.uaemex.fi.ens.fase_3.Simbolo;
import mx.uaemex.fi.ens.fase_3.SimboloManager;

public class Fase_4 {
	private Pattern pat1;
	private Pattern pat2;
	private Pattern pat3;
	private Pattern pat4;
	private Pattern pat5;
	private Pattern pat6;
	private Pattern pat7;
	private Pattern pat8;
	private Pattern pat9;
	private Pattern pat10;
	private Pattern pat11;
	private Pattern pat12;
	private Pattern pat13;
	private SimboloManager simMan;
	private Identificador id;

	public Fase_4() throws Exception {
		pat1 = Pattern.compile("^[cC][lL][cC]$");
		pat2 = Pattern.compile("^[iI][nN][tT][oO]$");
		pat3 = Pattern.compile("^[pP][uU][sS][hH][aA]$");
		pat4 = Pattern.compile("^[pP][uU][sS][hH][fF]$");
		pat5 = Pattern.compile("^[iI][mM][uU][lL]\\s+(.+)$");
		pat6 = Pattern.compile("^[pP][oO][pP]\\s+(.+)$");
		pat7 = Pattern.compile("^[oO][rR]\\s+(.+)\\x2C(.+)$");
		pat8 = Pattern.compile("^[aA][dD][cC]\\s+(.+)\\x2C(.+)$");
		pat9 = Pattern.compile("^[jJ][gG][eE]\\s+(.+)$");
		pat10 = Pattern.compile("^[jJ][mM][pP]\\s+(.+)$");
		pat11 = Pattern.compile("^[jJ][nN][lL][eE]\\s+(.+)$");
		pat12 = Pattern.compile("^[lL][oO][oO][pP][eE]\\s+(.+)$");
		pat13 = Pattern.compile("^(\\p{Alpha}\\w{0,9})\\x3A$");	
		this.simMan = new SimboloManager();
		this.id = new Identificador();
	}
	
	public String CheckLineCode(String strLinea) throws Exception{
		Matcher mat = pat1.matcher(strLinea);
		int aux;
		if(mat.find()){
			return strLinea+" Correcto\n";
		}else{
			mat = pat2.matcher(strLinea);
			if(mat.find()){
				return strLinea+" Correcto\n";
			}else{
				mat = pat3.matcher(strLinea);
				if(mat.find()){
					return strLinea+" Correcto\n";
				}else{
					mat = pat4.matcher(strLinea);
					if(mat.find()){
						return strLinea+" Correcto\n";
					}else{
						mat = pat5.matcher(strLinea);
						if(mat.find()){							
							if(this.id.esRegistro(mat.group(1).trim())!=-1||this.id.esMemoria(mat.group(1).trim())){
								return strLinea+" Correcto\n";
							}
						}else{
							mat = pat6.matcher(strLinea);
							if(mat.find()){
								if(this.id.esRegistro(mat.group(1).trim())!=-1||this.id.esMemoria(mat.group(1).trim())||this.id.esRegistroS(mat.group(1).trim())){
									return strLinea+" Correcto\n";
								}
							}else{
								mat = pat7.matcher(strLinea);
								if(mat.find()){
									if((aux = this.id.esRegistro(mat.group(1).trim()))!=-1){
										if(aux==this.id.esRegistro(mat.group(2).trim())||this.id.esMemoria(mat.group(2).trim())||(this.id.esConsNumBoW(mat.group(2).trim())!=-1&&this.id.esConsNumBoW(mat.group(2).trim())<=aux)){
											return strLinea+" Correcto\n";
										}else{
											return strLinea+" Incorrecto\n";
										}
									}else if(this.id.esMemoria(mat.group(1).trim())){
										if(this.id.esRegistro(mat.group(2).trim())!=-1||this.id.esConsNumBoW(mat.group(2).trim())!=-1){
											return strLinea+" Correcto\n";
										}else{
											return strLinea+" Incorrecto\n";
										}
									}
								}else{
									mat = pat8.matcher(strLinea);
									if(mat.find()){
										if((aux = this.id.esRegistro(mat.group(1).trim()))!=-1){
											if(aux==this.id.esRegistro(mat.group(2).trim())||this.id.esMemoria(mat.group(2).trim())||(this.id.esConsNumBoW(mat.group(2).trim())!=-1&&this.id.esConsNumBoW(mat.group(2).trim())<=aux)){
												return strLinea+" Correcto\n";
											}else{
												return strLinea+" Incorrecto\n";
											}
										}else if(this.id.esMemoria(mat.group(1).trim())){
											if(this.id.esRegistro(mat.group(2).trim())!=-1||this.id.esConsNumBoW(mat.group(2).trim())!=-1){
												return strLinea+" Correcto\n";
											}else{
												return strLinea+" Incorrecto\n";
											}
										}
									}else{
										mat = pat9.matcher(strLinea);
										if(mat.find()){
											if(this.simMan.findSimbolo(mat.group(1).trim())==3){
												return strLinea+" Correcto\n";
											}else{
												return strLinea+" Incorrecto, la etiqueta no existe\n";
											}
										}else{
											mat = pat10.matcher(strLinea);
											if(mat.find()){
												if(this.simMan.findSimbolo(mat.group(1).trim())==3){
													return strLinea+" Correcto\n";
												}else{
													return strLinea+" Incorrecto, la etiqueta no existe\n";
												}
											}else{
												mat = pat11.matcher(strLinea);
												if(mat.find()){
													if(this.simMan.findSimbolo(mat.group(1).trim())==3){
														return strLinea+" Correcto\n";
													}else{
														return strLinea+" Incorrecto, la etiqueta no existe\n";
													}
												}else{
													mat = pat12.matcher(strLinea);
													if(mat.find()){
														if(this.simMan.findSimbolo(mat.group(1).trim())==3){
															return strLinea+" Correcto\n";
														}else{
															return strLinea+" Incorrecto, la etiqueta no existe\n";
														}
													}else{
														mat = pat13.matcher(strLinea);
														if(mat.find()){
															Simbolo sim = new Simbolo(mat.group(1).trim(),"label",null,null);
															this.simMan.addSimbolo(sim);
															return strLinea+" Correcto\n";
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return strLinea+" Incorrecto, instruccion no identificada\n";
	}

}