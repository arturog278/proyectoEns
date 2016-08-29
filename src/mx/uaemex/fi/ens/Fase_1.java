package mx.uaemex.fi.ens;
import java.io.*;
import java.util.Scanner;

public class Fase_1 {

	public static void main(String args[]) {
        try{
        	File temp= File.createTempFile("ArchivoTemp",".tmp" );
        	temp.deleteOnExit();//Archivo temporal para guardar lo boenido
        	BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
        	
        	System.out.println ("Por favor introduzca el nombre del programa a traducir (con extension .asm):");
            String entradaTeclado = "";
            Scanner entradaEscaner = new Scanner (System.in); 
            entradaTeclado = entradaEscaner.nextLine (); 
            FileInputStream fstream = new FileInputStream(entradaTeclado);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            while ((strLinea = buffer.readLine()) != null)   {
            	if (!strLinea.isEmpty())
            	{

            			
            		if (strLinea.contains(";"))
                	{
                		
    					int i = 0;    				
    					int b = 0;
    					while(strLinea.charAt(i)!=';'){
                			i=i+1;
							if(strLinea.charAt(i)!=' '){
                				b=1;
                				break;
                		}
    					if(strLinea.charAt(0)!=';' && b==1){
    						strLinea=strLinea.substring(0, i);
    						if(strLinea.charAt(0)==' ')
                    		{
                    			while(strLinea.charAt(0)==' '){
                    				strLinea=strLinea.substring(1);
                    			}
                    		}
    						}
    						
    						
    						//bw.write (strLinea+"\n");
    					}
    					
    					
                			
                	}
                	else{
                		
                		if(strLinea.charAt(0)==' ')
                		{
                			while(strLinea.charAt(0)==' '){
                				strLinea=strLinea.substring(1);
                			}
                		}
                		
                		System.out.println(strLinea);
                	}
            	}
            	
                

            }
            entrada.close();
            /*
            bw.close();
            BufferedReader bufferTemp = new BufferedReader(new FileReader(temp));
            while ((strLinea = bufferTemp.readLine()) != null)   {
            	
            	if (strLinea.contains("data segment") || strLinea.contains("code segment")|| strLinea.contains("stack segment")){
            		System.out.println(strLinea);
            	}
            	else
            	{
            		
            	}
            			
            	}
            */
            
        }catch (Exception e){
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
    }

}
					