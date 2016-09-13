package mx.uaemex.fi.ens.fase_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class Fase_2 {
	private File archSalida;
	private BufferedWriter bwSalida;
	private Identificador id;

	public Fase_2(File arch) throws Exception{
		this.archSalida = new File("resultadoFase2.txt");
		this.bwSalida = new BufferedWriter(new FileWriter(this.archSalida));
		BufferedReader br = new BufferedReader(new FileReader(arch));
		this.id = new Identificador();
		String strLinea;
		while((strLinea = br.readLine()) != null){
			if(strLinea.length()>3 && strLinea.substring(0, 4).equalsIgnoreCase("dup(")){
				String strLineaDup = strLinea.substring(4,strLinea.length()-1);
				if(this.id.esInstruccion(strLineaDup)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion-Instruccion\""+"\n");
				}else if(this.id.esPseudo(strLineaDup)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion-Pseudoinstruccion\""+"\n");
				}else if(this.id.esRegistro(strLineaDup)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion-Registro\""+"\n");
				}else if(this.id.esConsString(strLineaDup)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion-Constante Cadena\""+"\n");
				}else if(this.id.esConsDec(strLineaDup)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion-Constante Decimal\""+"\n");
				}else if(this.id.esConsBin(strLineaDup)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion-Constante Binaria\""+"\n");
				}else if(this.id.esConsHexa(strLineaDup)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion-Constante Hexadecimal\""+"\n");
				}else if(this.id.esSimb(strLineaDup)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion-Simbolo\""+"\n");
				}else{
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion-Objeto no identificado\""+"\n");
				}
			}else if (strLinea.startsWith("[")&&strLinea.endsWith("]")||strLinea.startsWith("(")&&strLinea.endsWith(")")){
				String strLineaPar = strLinea.substring(1,strLinea.length()-1);
				if(this.id.esInstruccion(strLineaPar)){
					this.bwSalida.write(strLinea+" \"Instruccion\""+"\n");
				}else if(this.id.esPseudo(strLineaPar)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion\""+"\n");
				}else if(this.id.esRegistro(strLineaPar)){
					this.bwSalida.write(strLinea+" \"Registro\""+"\n");
				}else if(this.id.esConsString(strLineaPar)){
					this.bwSalida.write(strLinea+" \"Constante Cadena\""+"\n");
				}else if(this.id.esConsDec(strLineaPar)){
					this.bwSalida.write(strLinea+" \"Constante Decimal\""+"\n");
				}else if(this.id.esConsBin(strLineaPar)){
					this.bwSalida.write(strLinea+" \"Constante Binaria\""+"\n");
				}else if(this.id.esConsHexa(strLineaPar)){
					this.bwSalida.write(strLinea+" \"Constante Hexadecimal\""+"\n");
				}else if(this.id.esSimb(strLineaPar)){
					this.bwSalida.write(strLinea+" \"Simbolo\""+"\n");
				}else{
					this.bwSalida.write(strLinea+" \"Objeto no identificado\""+"\n");
				}
			}else{
				if(this.id.esInstruccion(strLinea)){
					this.bwSalida.write(strLinea+" \"Instruccion\""+"\n");
				}else if(this.id.esPseudo(strLinea)){
					this.bwSalida.write(strLinea+" \"Pseudoinstruccion\""+"\n");
				}else if(this.id.esRegistro(strLinea)){
					this.bwSalida.write(strLinea+" \"Registro\""+"\n");
				}else if(this.id.esConsString(strLinea)){
					this.bwSalida.write(strLinea+" \"Constante Cadena\""+"\n");
				}else if(this.id.esConsDec(strLinea)){
					this.bwSalida.write(strLinea+" \"Constante Decimal\""+"\n");
				}else if(this.id.esConsBin(strLinea)){
					this.bwSalida.write(strLinea+" \"Constante Binaria\""+"\n");
				}else if(this.id.esConsHexa(strLinea)){
					this.bwSalida.write(strLinea+" \"Constante Hexadecimal\""+"\n");
				}else if(this.id.esSimb(strLinea)){
					this.bwSalida.write(strLinea+" \"Simbolo\""+"\n");
				}else{
					this.bwSalida.write(strLinea+" \"Objeto no identificado\""+"\n");
				}
			}
		}
		this.bwSalida.close();
		br.close();
	}

	public File getArchSalida() {
		return archSalida;
	}

}
