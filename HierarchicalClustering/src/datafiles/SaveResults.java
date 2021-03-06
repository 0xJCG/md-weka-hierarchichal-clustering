package datafiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SaveResults {
	private static SaveResults miSaveResults = null;
	
	/**
	 * Constructora privada ya que se trata de una MAE
	 */
	private SaveResults() {} // Es una MAE.
	
	/**
	 * Inicializa el atributo en caso de que no tenga valor.
	 * @return SaveResults: atributo de la MAE 
	 */
	public static SaveResults getSaveResults() {
		if (miSaveResults == null)
			miSaveResults = new SaveResults();
		return miSaveResults;
	}
	
	/**
	 * Guarda el contenido de un String en un fichero de texto.
	 * @param path = String: indica la ruta del archivo.
	 * @param FileContent = String: indica el contenido del archivo.
	 * @param CleanFileContent = boolean: si true y existe el archivo, borra el contenido del mismo; si false, anade el contenido al final del archivo.
	 * @param type = String: tipo de fichero, se le pondra ese nombre al fichero (resultado, arbol...).
	 */
	public void SaveFile(String path, String FileContent, boolean CleanFileContent, String type) {
		FileWriter file;
		BufferedWriter writer;
		
		Calendar calendar = new GregorianCalendar(); // Fecha y hora actuales.
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss"); // Formato de la fecha.
		String dateS = dateFormat.format(calendar.getTime()); // Fecha y hora actuales formateadas.
			
		// Variable para captura ranking y variable para guardar el contenido en un archivo.
		path = path.substring(0, path.length() - 5) + "-" + type + "-" + dateS + ".txt";
		
	    try {
	        file = new FileWriter(path, CleanFileContent);
	        writer = new BufferedWriter(file);
	        writer.write(FileContent, 0, FileContent.length());
	         
	        writer.close();
	        file.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	   }
	}
}
