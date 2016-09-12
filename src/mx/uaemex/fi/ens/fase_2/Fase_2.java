package mx.uaemex.fi.ens.fase_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import mx.uaemex.fi.ens.main.Main;

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
			}
			else if(this.id.esConsBin(strLinea)){
			this.bwSalida.write(strLinea+" \"Constante Binaria\""+"\n");
			}else if(this.id.esConsHexa(strLinea)){
				this.bwSalida.write(strLinea+" \"Constante Hexadecimal\""+"\n");
			}else if(this.id.esSimb(strLinea)){
					this.bwSalida.write(strLinea+" \"Símbolo\""+"\n");
			}else{
				this.bwSalida.write(strLinea+" \"Objeto no identificado\""+"\n");
			}
		}
		this.bwSalida.close();
		br.close();
	}

	public File getArchSalida() {
		return archSalida;
	}

}
