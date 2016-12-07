package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.nio.charset.Charset;

import utilidades.UtilidadesES;

/**
 * Prueba el uso de flujos para leer y escribir cadenas de texto
 * en archivos.
 * @author Eduardo A. Ponce
 * @version 1.0
 */
public class Ejemplo3 {

	UtilidadesES utilES = new UtilidadesES(new BufferedReader(new InputStreamReader(System.in)), System.out);
	
	File fArchivo1 = null;
	File fArchivo2 = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			(new Ejemplo3()).ejecutar();
		}
		catch(Exception excep) {
			System.out.println("Error:"+excep.getMessage());
			excep.getCause().printStackTrace();
		}
	}
	
	public void ejecutar() throws FileNotFoundException, IOException{
		fArchivo1 = new File("usuarios.txt");
		fArchivo2 = new File("usuarios.cop");
		BufferedReader br = null;
		PrintWriter pw = null;
	    System.out.println("Mapa caracteres por defecto: "+Charset.defaultCharset());
	    //System.setProperty("file.encoding", "ISO-8859-1");
	    System.out.println("file.encoding = "+ System.getProperty("file.encoding"));
		String cadena = null;
		
		if (fArchivo1.exists())
			if (fArchivo1.canRead()) {
				if (!(fArchivo2.exists()) || (fArchivo2.exists() && fArchivo2.canWrite())) {
					br = new BufferedReader (new FileReader(fArchivo1));
					// Machaca lo que ya hubiera en fArchivo2.
					pw = new PrintWriter(new FileWriter(fArchivo2));
					// Para a�adir datos al archivo si ya existiera
					// pw = new PrintWriter(new FileWriter(fArchivo2, true));
					while ((cadena=br.readLine())!=null) {
						this.utilES.mostrarln(cadena);
						pw.println(cadena);
					}
					pw.close();
					br.close();
                                        this.utilES.mostrarln("Adiós");
				}
			}
			else
				this.utilES.mostrarln("No puede leerse el archivo de origen.");
		else
			this.utilES.mostrarln("No existe el archivo de origen.");		
	}

}

