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
            		if(strLinea.charAt(0)==' '){
            			int a = 0;
            			while(strLinea.charAt(a)==' '){
                			a+=1;
            			}
            			strLinea=strLinea.substring(a);
            		}

            			
            		if (strLinea.contains(";"))
                	{
                		
    					int i = 0;    				
    					while(strLinea.charAt(i)!=';'){
                			i=i+1;
                		}
    					if(strLinea.charAt(0)!=';'){
    						strLinea=strLinea.substring(0, i);
    						bw.write (strLinea+"\n");
    					}
    						
    					}else{
    						bw.write (strLinea+"\n");
                	}
    					
    					
                			
                	}
                	
            	}         
            entrada.close();
            bw.close();
            BufferedReader bufferTemp = new BufferedReader(new FileReader(temp));
            while ((strLinea = bufferTemp.readLine()) != null)   {           	
            	if (strLinea.contains("data segment") || strLinea.contains("code segment")|| strLinea.contains("stack segment")){
            		System.out.println(strLinea);
            	}else{
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
            				System.out.println(substr);
            				x=y;
            				break;
            			case '[':
            				y=x+1;
            				while(strLinea.charAt(y)!=']'){
            					y+=1;
            				}
            				substr = strLinea.substring(x, y+1);
            				System.out.println(substr);
            				x=y;
            				break;
            			case '\'':
            				y=x+1;
            				while(strLinea.charAt(y)!='\''){
            					y+=1;
            				}
            				substr = strLinea.substring(x, y+1);
            				System.out.println(substr);
            				x=y;
            				break;
            			default:
            				if(strLinea.charAt(x)!=' ' & strLinea.charAt(x)!=','& strLinea.charAt(x)!=':'){
            					if(strLinea.length()-x>3 && strLinea.substring(x, x+3)=="dub("){           				
            						y=x+1;
                       				while(strLinea.charAt(y)!=')'){
                       					y+=1;
                       				}
                       				substr = strLinea.substring(x, y+1);
                       				System.out.println(substr);
                       				x=y;            						
            					}else{
            						y=x+1;
            						if(y==strLinea.length()){
            							System.out.println(strLinea.charAt(x));
            						}else{
            							while(strLinea.charAt(y)!=' ' & strLinea.charAt(x)!=',' & strLinea.charAt(x)!=':'){
            								y+=1;
            								if(y==strLinea.length()){
            									y=y-1;
            									break;
            								}
            							}
            							substr = strLinea.substring(x, y+1);
               							System.out.println(substr);
               							x=y;   
            						}			
            					}
            				}
            				
            			
            				
            			}
            			
            		}
            	
            	
            		
            	}
            			
            	}
            
        }catch (Exception e){
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
    }

}
					