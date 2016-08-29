package mx.uaemex.fi.ens;
import java.io.*;
import java.util.Scanner;

public class Fase_1 {

	public static void main(String args[]) {
        try{
        	System.out.println ("Por favor introduzca el nombre del programa a traducir (con extension .asm):");
            String entradaTeclado = "";
            Scanner entradaEscaner = new Scanner (System.in); 
            entradaTeclado = entradaEscaner.nextLine (); 
            FileInputStream fstream = new FileInputStream(entradaTeclado);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            while ((strLinea = buffer.readLine()) != null)   {
            	if (strLinea.contains(";"))
            	{
            		
					int i;
					i=0;
					while(strLinea.charAt(i)==';'){
            			i=i+1;
            		}
					if(strLinea.charAt(0)==';'){
						strLinea="";
					}
					else{
						strLinea=strLinea.substring(0, i);
					}
					
					
            			
            	}

                System.out.println (strLinea);

            }
            entrada.close();
        }catch (Exception e){
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
    }

}
					