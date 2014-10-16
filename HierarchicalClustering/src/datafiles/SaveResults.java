package datafiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SaveResults {
	private static SaveResults miSaveResults = null;
	
	private SaveResults() {}
	
	public static SaveResults getSaveResults() {
		if (miSaveResults == null)
			miSaveResults = new SaveResults();
		return miSaveResults;
	}
	
	/**
	 * pre:
	 * @param FilePath = String, indica la ruta del archivo.
	 * @param FileContent = String, indica el contenido del archivo
	 * @param CleanFileContent = boolean.Si tue y existe el archivo borra el contenido, 
	 * 			si false, añade el contenido al final del archivo.
	 * @return true si se guarda con éxito, false en caso contrario.
	 * 
	 * fuente : http://www.creatusoftware.com/index.php?option=com_content&view=article&id=142:funcion-para-guardar-un-archivo-en-java&catid=62:fuentes-java&Itemid=41
	 */
	public boolean SaveFile(String path, String FileContent, boolean CleanFileContent) {
		FileWriter file;
		BufferedWriter writer;
		
	    Calendar calendar = new GregorianCalendar(); // Fecha y hora actuales.
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm"); // Formato de la fecha.
		String dateS = dateFormat.format(calendar.getTime()); // Fecha y hora actuales formateadas.
		
		// Variable para captura ranking y variable para guardar el contenido en un archivo.
		String newPath = path.substring(0, path.length() - 5) + "-resutado-" + dateS + ".txt";
	     
	    try {
	        file = new FileWriter(newPath, !CleanFileContent);
	        writer = new BufferedWriter(file);
	        writer.write(FileContent, 0, FileContent.length());
	         
	        writer.close();
	        file.close();
	 
	        return true;
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return false;
	   }
	}
}
