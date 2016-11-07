package mx.uaemex.fi.ens.fase_3;

import java.io.Serializable;

public class Simbolo implements Serializable {
	private String simbolo;
	private String tipo;
	private String valor;
	private String tamaño;
	private String direccion;
	
	public Simbolo(){
		
	}

	public Simbolo(String s, String ti, String v, String ta, String dir) {
		this.simbolo = s;
		this.tipo = ti;
		this.valor = v;
		this.tamaño = ta;
		this.direccion = dir;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getValor() {
		return valor;
	}

	public String getTamaño() {
		return tamaño;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
	}


}
