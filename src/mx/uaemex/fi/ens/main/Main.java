package mx.uaemex.fi.ens.main;

import java.util.Scanner;

import mx.uaemex.fi.ens.fase_1.Fase_1;
import mx.uaemex.fi.ens.fase_2.Fase_2;

public class Main {

	public static void main(String args[])  {
		System.out.println ("Por favor introduzca el nombre del programa a traducir (con extension .asm):");
        String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); 
        entradaTeclado = entradaEscaner.nextLine (); 
        try {
        	Desplegar desp;
			Fase_1 fase1 = new Fase_1(entradaTeclado);
			Fase_2 fase2 = new Fase_2(fase1.getArchSalida());
			desp = new Desplegar(fase2.getArchSalida());
		} catch (Exception e) {			
	           System.err.println("Ocurrio un error: " + e.getMessage());
		}
	}

}
