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

import java.util.Scanner;

import utilidades.UtilidadesES;

public class Ejemplo3bis {
    UtilidadesES utilES = new UtilidadesES(new BufferedReader(new InputStreamReader(System.in)), System.out);
    
    File fArchivo1 = null;
    File fArchivo2 = null;
    public static void main(String[] args) {
        try {
                (new Ejemplo3bis()).ejecutar();
        }
        catch(Exception excep) {
                System.out.println("Error:"+excep.getMessage());
                excep.getCause().printStackTrace();
        }
        
    }
    public void ejecutar() throws FileNotFoundException, IOException{
            fArchivo1 = new File("usuarios.txt");
            fArchivo2 = new File("usuarios.cop");
            System.out.println("Mapa caracteres por defecto: "+Charset.defaultCharset());
            //System.setProperty("file.encoding", "ISO-8859-1");
            System.out.println("file.encoding = "+ System.getProperty("file.encoding"));
            Scanner flujoEntrada = null;
            Scanner procesaCadena = null;
            PrintWriter pw = null;
            String cadena = null;
            
            if (fArchivo1.exists())
                    if (fArchivo1.canRead()) {
                            if (!(fArchivo2.exists()) || (fArchivo2.exists() && fArchivo2.canWrite())) {
                                    flujoEntrada = new Scanner (fArchivo1);
                                    // Machaca lo que ya hubiera en fArchivo2.
                                    pw = new PrintWriter(new FileWriter(fArchivo2));
                                    // Para a�adir datos al archivo si ya existiera
                                    // pw = new PrintWriter(new FileWriter(fArchivo2, true));
                                    while (flujoEntrada.hasNextLine()) {
                                            cadena = flujoEntrada.nextLine();
                                        this.utilES.mostrarln("Cadena: "+cadena);
                                            procesaCadena = new Scanner(cadena);
                                            procesaCadena.useDelimiter(",");
                                            while (procesaCadena.hasNext()) {
                                                this.utilES.mostrarln(procesaCadena.next());
                                            }
                                            this.utilES.mostrarln("Cadena: "+cadena);
                                            pw.println(cadena);
                                    }
                                    pw.close();
                                    flujoEntrada.close();
                                    this.utilES.mostrar("Adios");
                            }
                    }
                    else
                            this.utilES.mostrarln("No puede leerse el archivo de origen.");
            else
                    this.utilES.mostrarln("No existe el archivo de origen Ñ.");               
    }
}
