package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import utilidades.UtilidadesES;

/**
 * Ejemplo de cómo leer y escribir datos binarios empleando
 * las clases DataInputStream y DataOutputStream
 * @author Eduardo A. Ponce
 * @version 1.0
 */
public class Ejemplo4 {
UtilidadesES utilES = new UtilidadesES(new BufferedReader(new InputStreamReader(System.in)), System.out);
	
	File fArchivo = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			(new Ejemplo4()).ejecutar();
		}
		catch(Exception excep) {
			System.out.println("Error:"+excep.getMessage());
			excep.getCause().printStackTrace();
		}

	}
	public void ejecutar() throws Exception {
		fArchivo = new File("datos.dat");
		if (escribeDatosBinarios(fArchivo))
			leeDatosBinarios(fArchivo);
	}

	public boolean escribeDatosBinarios(File fArchivo) throws FileNotFoundException, IOException {
		boolean ok = true;
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(fArchivo));
		int num = 33;
		float decimal1 = 3.5f;
		double decimal2 = 5.22;
		
		dos.writeInt(num);
		dos.writeFloat(decimal1);
		dos.writeDouble(decimal2);
		dos.close();
		return ok;
	}
	
	public boolean leeDatosBinarios(File fArchivo) throws FileNotFoundException, IOException {
		boolean ok = true;
		DataInputStream dis = new DataInputStream(new FileInputStream(fArchivo));
		
		this.utilES.mostrarln("Datos leídos:");
		this.utilES.mostrarln((dis.readInt()+","+dis.readFloat()+","+dis.readDouble()));
		dis.close();
		
		return ok;		
	}
}
