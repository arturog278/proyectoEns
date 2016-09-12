package mx.uaemex.fi.ens.fase_1;
import java.io.*;
import java.util.Scanner;

import mx.uaemex.fi.ens.main.Main;

public class Fase_1 {
	private File archSalida;
	private BufferedWriter bwSalida;

	public Fase_1(String entradaTeclado) throws Exception{
        	File temp= File.createTempFile("ArchivoTemp",".tmp" );
        	temp.deleteOnExit();
        	BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
        	
            FileInputStream fstream = new FileInputStream(entradaTeclado);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            while ((strLinea = buffer.readLine()) != null)   {
            	if (!strLinea.isEmpty())
            	{           		
            		strLinea = strLinea.trim();           			
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
            File temp2= File.createTempFile("ArchivoTemp2",".tmp" );
        	temp2.deleteOnExit();
        	bw = new BufferedWriter(new FileWriter(temp2));
            BufferedReader bufferTemp = new BufferedReader(new FileReader(temp));
            while ((strLinea = bufferTemp.readLine()) != null)   {           	
            	if (strLinea.equalsIgnoreCase("data segment") || strLinea.equalsIgnoreCase("code segment")|| strLinea.equalsIgnoreCase("stack segment")){
            		bw.write(strLinea+"\n");
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
            				bw.write(substr+"\n");
            				x=y;
            				break;
            			case '[':
            				y=x+1;
            				while(strLinea.charAt(y)!=']'){
            					y+=1;
            				}
            				substr = strLinea.substring(x, y+1);
            				bw.write(substr+"\n");
            				x=y;
            				break;
            			case '\'':
            				y=x+1;
            				while(strLinea.charAt(y)!='\''){
            					y+=1;
            				}
            				substr = strLinea.substring(x, y+1);
            				bw.write(substr+"\n");
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
                       				bw.write(substr+"\n");
                       				x=y;            						
            					}else{
            						y=x+1;
            						if(y==strLinea.length()){
            							bw.write(strLinea.charAt(x)+"\n");
            						}else{           							
            							while(strLinea.charAt(y)!=' ' & strLinea.charAt(x)!=',' & strLinea.charAt(x)!=':'){
            								y+=1;            								
            								if(y==strLinea.length()){
            									y=y-1;
            									break;
            								}
            							}            							
            							substr = strLinea.substring(x, y+1);
            							substr = substr.replaceAll(",", "\n");
            							substr = substr.replaceAll(" ", "\n");
            							substr = substr.replaceAll(":", "\n");
            							bw.write(substr+"\n");
               							x=y;   
            						}			
            					}
            				}
            				
            			
            				
            			}
            			
            		}
      	
            		
            	}
            			
            	}  
            bw.close();
            this.creacionArchivo(temp2);
            
    }
	
	private void creacionArchivo(File archTemp) throws Exception{
		this.archSalida = new File("resultadoFase1.txt");
		this.bwSalida = new BufferedWriter(new FileWriter(this.archSalida));
		BufferedReader br = new BufferedReader(new FileReader(archTemp));
		String strLinea;
		while ((strLinea = br.readLine()) != null){				
			strLinea = strLinea.trim();
			if(!strLinea.isEmpty()){
				this.bwSalida.write(strLinea+"\n");
			}
		}
		this.bwSalida.close();
		br.close();
		
	}

	public File getArchSalida() {
		return archSalida;
	}

}
					