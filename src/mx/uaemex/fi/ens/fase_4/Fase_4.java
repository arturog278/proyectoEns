package mx.uaemex.fi.ens.fase_4;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.uaemex.fi.ens.fase_2.Identificador;
import mx.uaemex.fi.ens.fase_3.Fase_3;
import mx.uaemex.fi.ens.fase_3.Simbolo;
import mx.uaemex.fi.ens.fase_3.SimboloManager;
import mx.uaemex.fi.ens.fase_5.Contador;
import mx.uaemex.fi.ens.fase_5.Fase_5;

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
	private Contador count;
	private Fase_5 fase5;
	private String cp;

	public Fase_4() throws Exception {
		pat1 = Pattern.compile("^[cC][lL][cC]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat2 = Pattern.compile("^[iI][nN][tT][oO]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat3 = Pattern.compile("^[pP][uU][sS][hH][aA]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat4 = Pattern.compile("^[pP][uU][sS][hH][fF]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat5 = Pattern.compile("^[iI][mM][uU][lL]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat6 = Pattern.compile("^[pP][oO][pP]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat7 = Pattern.compile("^[oO][rR]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat8 = Pattern.compile("^[aA][dD][cC]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat9 = Pattern.compile("^[jJ][gG][eE]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat10 = Pattern.compile("^[jJ][mM][pP]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat11 = Pattern.compile("^[jJ][nN][lL][eE]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat12 = Pattern.compile("^[lL][oO][oO][pP][eE]\\s*(([wW][oO][rR][dD]||[Bb][yY][tT][eE])\\s*[pP][tT][rR]\\s*){0,1}([\\w|\\+|\\[|\\]]*)\\x2C?(.*)$");
		pat13 = Pattern.compile("^(\\p{Alpha}\\w{0,9})\\x3A$");	
		this.simMan = new SimboloManager();
		this.id = new Identificador();
		this.count = new Contador();
		this.fase5 = new Fase_5();
	}
	
	public String CheckLineCode(String strLinea,String cpA) throws Exception{
		this.cp = cpA;
		Matcher mat = pat1.matcher(strLinea);
		int aux;
		if(mat.find()){
			if(mat.group(3).equals("")){
				this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(1, null, null));
				return cpA+" "+strLinea+" Correcto\n";
			}else{
				return cpA+" "+strLinea+" Incorrecto, no lleva operandos\n";
			}
		}else{
			mat = pat2.matcher(strLinea);
			if(mat.find()){
				if(mat.group(3).equals("")){
					this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(2, null, null));
					return cpA+" "+strLinea+" Correcto\n";
				}else{
					return cpA+" "+strLinea+" Incorrecto, no lleva operandos\n";
				}
			}else{
				mat = pat3.matcher(strLinea);
				if(mat.find()){
					if(mat.group(3).equals("")){
						this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(3, null, null));
						return cpA+" "+strLinea+" Correcto\n";
					}else{
						return cpA+" "+strLinea+" Incorrecto, no lleva operandos\n";
					}
				}else{
					mat = pat4.matcher(strLinea);
					if(mat.find()){
						if(mat.group(3).equals("")){
							this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(4, null, null));
							return cpA+" "+strLinea+" Correcto\n";
						}else{
							return cpA+" "+strLinea+" Incorrecto, no lleva operandos\n";
						}
					}else{
						mat = pat5.matcher(strLinea);
						if(mat.find()){							
							if(this.id.esRegistro(mat.group(3).trim())!=-1||this.id.esMemoria(mat.group(3).trim())){
								if(mat.group(4).equals("")){
									this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(5, mat.group(3), null));
									return cpA+" "+strLinea+" Correcto\n";
								}
								else{
									return cpA+" "+strLinea+" Incorrecto, se esperaba un solo operando\n";
								}
							}else{
								return cpA+" "+strLinea+" Incorrecto, se esperaba un registro o memoria\n";
							}
						}else{
							mat = pat6.matcher(strLinea);
							if(mat.find()){
								if(this.id.esRegistro(mat.group(3).trim())!=-1||this.id.esMemoria(mat.group(3).trim())||this.id.esRegistroS(mat.group(3).trim())){
									if(mat.group(4).equals("")){
										this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(6, mat.group(3), null));
										return cpA+" "+strLinea+" Correcto\n";
									}
									else{
										return cpA+" "+strLinea+" Incorrecto, se esperaba un solo operando\n";
									}
								}else{
									return cpA+" "+strLinea+" Incorrecto, se esperaba un registro o memoria\n";
								}
							}else{
								mat = pat7.matcher(strLinea);
								if(mat.find()){
									if((aux = this.id.esRegistro(mat.group(3).trim()))!=-1){
										if(aux==this.id.esRegistro(mat.group(4).trim())||this.id.esMemoria(mat.group(4).trim())||(this.id.esConsNumBoW(mat.group(4).trim())!=-1&&this.id.esConsNumBoW(mat.group(4).trim())<=aux)){			
											this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(7, mat.group(3), mat.group(4)));
											return cpA+" "+strLinea+" Correcto\n";
										}else{
											return cpA+" "+strLinea+" Incorrecto, se esperaba registro, memoria o inmediato del mismo tamaño en el segundo operando\n";
										}
									}else if(this.id.esMemoria(mat.group(3).trim())){
										if(this.id.esRegistro(mat.group(4).trim())!=-1||this.id.esConsNumBoW(mat.group(4).trim())!=-1){
											this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(7, mat.group(3), mat.group(4)));
											return cpA+" "+strLinea+" Correcto\n";
										}else{
											return cpA+" "+strLinea+" Incorrecto, se esperaba registro o inmediato del mismo tamaño en el segundo operando\n";
										}
									}else{
										return cpA+" "+strLinea+" Incorrecto, se esperaba registro o memoria en el primer operando\n";
									}
								}else{
									mat = pat8.matcher(strLinea);
									if(mat.find()){
										if((aux = this.id.esRegistro(mat.group(3).trim()))!=-1){
											if(aux==this.id.esRegistro(mat.group(4).trim())||this.id.esMemoria(mat.group(4).trim())||(this.id.esConsNumBoW(mat.group(4).trim())!=-1&&this.id.esConsNumBoW(mat.group(4).trim())<=aux)){
												this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(8, mat.group(3), mat.group(4)));
												return cpA+" "+strLinea+" Correcto\n";
											}else{
												return cpA+" "+strLinea+" Incorrecto, se esperaba registro, memoria o inmediato del mismo tamaño en el segundo operando\n";
											}
										}else if(this.id.esMemoria(mat.group(3).trim())){
											if(this.id.esRegistro(mat.group(4).trim())!=-1||this.id.esConsNumBoW(mat.group(4).trim())!=-1){
												this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(8, mat.group(3), mat.group(4)));
												return cpA+" "+strLinea+" Correcto\n";
											}else{
												return cpA+" "+strLinea+" Incorrecto, se esperaba registro o inmediato en el segundo operando\n";
											}
										}else{
											return cpA+" "+strLinea+" Incorrecto, se esperaba registro o memoria del mismo tamaño en el primer operando\n";
										}
									}else{
										mat = pat9.matcher(strLinea);
										if(mat.find()){
											if(this.simMan.findSimbolo(mat.group(3).trim())==3){
												this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(9, mat.group(3), null));
												return cpA+" "+strLinea+" Correcto\n";
											}else{
												return cpA+" "+strLinea+" Incorrecto, se esperaba etiqueta\n";
											}
										}else{
											mat = pat10.matcher(strLinea);
											if(mat.find()){
												if(this.simMan.findSimbolo(mat.group(3).trim())==3){
													this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(10, mat.group(3), null));
													return cpA+" "+strLinea+" Correcto\n";
												}else{
													return cpA+" "+strLinea+" Incorrecto, se esperaba etiqueta\n";
												}
											}else{
												mat = pat11.matcher(strLinea);
												if(mat.find()){
													if(this.simMan.findSimbolo(mat.group(3).trim())==3){
														this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(11, mat.group(3), null));
														return cpA+" "+strLinea+" Correcto\n";
													}else{
														return cpA+" "+strLinea+" Incorrecto, se esperaba etiqueta\n";
													}
												}else{
													mat = pat12.matcher(strLinea);
													if(mat.find()){
														if(this.simMan.findSimbolo(mat.group(3).trim())==3){
															this.cp = this.count.suma(this.cp, this.fase5.calcularTamañoCS(12, mat.group(3), null));
															return cpA+" "+strLinea+" Correcto\n";
														}else{
															return cpA+" "+strLinea+" Incorrecto, se esperaba etiqueta\n";
														}
													}else{
														mat = pat13.matcher(strLinea);
														if(mat.find()){
															Simbolo sim = new Simbolo(mat.group(1).trim(),"label",null,null,cpA);
															this.simMan.addSimbolo(sim);
															return cpA+" "+strLinea+" Correcto\n";
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
		return cpA+" "+strLinea+" Incorrecto, instruccion no identificada\n";
	}

	public String getCp() {
		return cp;
	}

}
