package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejemplo1 {

	/**
	 * Ejemplo de lectura del contenido de un directorio
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			(new Ejemplo1()).ejecutar();
		}
		catch(IOException ioe) {
			System.out.println("Error:"+ioe.getMessage());
			ioe.getCause().printStackTrace();
		}
	}
	/**
	 * Muestra los nombres de archivos y subdirectorios contenidos en un directorio
	 * @throws IOException En caso de error en lectura desde teclado.
	 */
	public void ejecutar() throws IOException{
		// Nombre del directorio que se proporciona
		String nombreDirectorio = null;
		// Recoge los nombres de los archivos y subdirectorios
		String[] contenidoDir = null;
		// Objeto File que encapsula el directorio
		File rutaDirectorio = null;
		// Objeto File para determinar si es archivo o directorio 
		File elementoD = null;
		
		try {
			/* Se solicita la ruta del directorio */
			System.out.print("Introduce nombre o ruta de directorio:");
			nombreDirectorio = (new BufferedReader(new InputStreamReader(System.in))).readLine();
			// Se crea el objeto File
			rutaDirectorio = new File(nombreDirectorio);
			// Se obtiene la lista de componentes del directorio
			contenidoDir = rutaDirectorio.list();
			System.out.println("Archivos en directorio: "+nombreDirectorio);
			// Se recorre la lista
			for (String archivo: contenidoDir) {
				elementoD = new File(nombreDirectorio+archivo);
				// Si el elemento es directorio, se antecede el nombre con "<d>"
				if (elementoD.isDirectory())
					System.out.print("<d>");
				System.out.println(archivo);
			}
		}
		catch(IOException ioe) {
			throw new IOException("Error al leer de teclado",ioe);
		}
	}
}