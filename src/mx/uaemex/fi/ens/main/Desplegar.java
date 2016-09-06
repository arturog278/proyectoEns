package mx.uaemex.fi.ens.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Desplegar {

	public Desplegar(File arch) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(arch));
		String strLinea;
		while((strLinea = br.readLine()) != null){
			System.out.println(strLinea);
		}
		br.close();
	}

}
