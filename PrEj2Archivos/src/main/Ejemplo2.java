package main;

import java.io.*;

import utilidades.UtilidadesES;

/**
 * Prueba el uso de la clase File para crear y borrar
 * directorios y archivos. Tambi�n para renombrar archivos.
 * @author Eduardo A. Ponce
 * @version 1.0
 */
public class Ejemplo2 {

	UtilidadesES utilES = new UtilidadesES(new BufferedReader(new InputStreamReader(System.in)), System.out);
	
	File fDirectorio = null;
	File fArchivo = null;
	String nomDirectorio = null;
	String nomArchivo = null;
	/**
	 * Ejemplos de uso de la clase File
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			(new Ejemplo2()).ejecutar();
		}
		catch(IOException ioe) {
			System.out.println("Error:"+ioe.getMessage());
			ioe.getCause().printStackTrace();
		}
	}
	/**
	 * M�todo que procede a crear un directorio, y si lo
	 * crea correctamente, crea un archivo, y si lo crea
	 * correctamente, procede a renombrar el archivo y luego
	 * a borrarlo.
	 * @throws IOException En caso de que se produzca un error
	 * de lectura desde teclado.
	 */
	public void ejecutar() throws IOException {
		boolean ok = false;
		
		// Crear un directorio
		if (creaDirectorio()) {
			if (creaArchivo()) {
				renombraArchivo();
				borraArchivo();
			}
			borraDirectorio();
		}
	}
	/**
     * Crea un directorio si no existe.
     * @return true en caso de crearlo correctamente o bien de que ya
     * existiera. false en caso contrario.
     * @throws IOException Error de lectura desde teclado.
     */
        public boolean creaDirectorio() throws IOException {
            boolean ok = true;
    
            this.nomDirectorio = this.utilES.pideCadena("Introduzca nombre completo de directorio: ");
            this.fDirectorio = new File(this.nomDirectorio);
            if (!this.fDirectorio.exists())
                if (this.fDirectorio.mkdirs())
                    this.utilES.mostrarln("Directorio: " + this.nomDirectorio + " creado correctamente.");
                else {
                    ok = false;
                    this.utilES.mostrarln("Directorio: " + this.nomDirectorio + " no se ha podido crear.");
                }
            else {
                this.utilES.mostrarln("Directorio: " + this.nomDirectorio + " ya existe.");
                ok = false;
            }
                                    
                    return ok;
        }
	/**
	 * Crea un archivo si no existe.
	 * @return true en caso de crearlo correctamente o bien de que ya
	 * existiera. false en caso contrario.
	 * @throws IOException Error de lectura desde teclado.
	 */
	public boolean creaArchivo() throws IOException {
		boolean ok = true;
		
		this.nomArchivo = this.utilES.pideCadena("Introduzca el nombre del archivo: ");
		this.fArchivo = new File(this.fDirectorio, this.nomArchivo);
		if (!this.fArchivo.exists())
			if (this.fArchivo.createNewFile())
				this.utilES.mostrarln("El archivo "+this.nomArchivo+" se ha creado en "+this.fArchivo.getAbsolutePath());
			else {
				ok = false;
				this.utilES.mostrarln("El archivo "+this.nomArchivo+" NO se ha creado en "+this.fArchivo.getAbsolutePath());
			}
		else
			this.utilES.mostrarln("El archivo "+this.nomArchivo+" ya existe en "+this.fArchivo.getAbsolutePath());		
                System.out.println("Directorio absoluto: "+fArchivo.getAbsolutePath());
                System.out.println("Directorio relativo: "+fArchivo.getPath());
		return ok;
	}
	/**
	 * Renombra un archivo. Debemos asegurarnos de que la ruta del archivo a renombrar es la misma
	 * que la que se proporciona, de lo contrario se produce un "movimiento".
	 * @return true si lo renombra correctamente. false en caso contrario.
	 * @throws IOException Error de lectura desde teclado.
	 */
	public boolean renombraArchivo() throws IOException{
		boolean ok = true;
		String nuevoNombreArchivo = null;
		File fArchivoNuevo = null;

		nuevoNombreArchivo = this.utilES.pideCadena("Introduzca el nombre con el que renombrar� el archivo "+this.nomArchivo+": ");
		fArchivoNuevo = new File(this.fDirectorio,nuevoNombreArchivo);

		if (this.fArchivo.renameTo(fArchivoNuevo)) {
			this.fArchivo = fArchivoNuevo;
			this.utilES.mostrarln("Se ha renombrado correctamente el archivo. Ahora se ubica y denomina: "+this.fArchivo.getAbsolutePath());
		}
		else {
			ok = false;
			this.utilES.mostrarln("No se ha podido renombrar correctamente el archivo.");
		}
		return ok;
	}
	/**
	 * Borra el archivo.
	 * @return true si lo borra, false en caso contrario.
	 * @throws IOException Error de lectura desde teclado.
	 */
	public boolean borraArchivo() throws IOException{
		boolean ok = true;
		if (this.fArchivo.delete())
			this.utilES.mostrarln("El archivo se ha borrado correctamente.");
		else {
			ok = false;
			this.utilES.mostrarln("El archivo no ha podido borrarse.");
		}
		return ok;
	}
	/**
	 * Borra un directorio. Borrar un directorio es igual que borrar un archivo.
	 * @return true si lo borra, false en caso contrario.
	 * @throws IOException Error de lectura desde teclado.
	 */
	public boolean borraDirectorio() throws IOException{
		boolean ok = true;
		if (this.fDirectorio.delete())
			this.utilES.mostrarln("El directorio se ha borrado correctamente.");
		else {
			ok = false;
			this.utilES.mostrarln("El directorio no ha podido borrarse.");
		}		
		return ok;
	}
}
