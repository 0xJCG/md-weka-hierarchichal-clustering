package hierarchicalclustering;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class SaveResults {
	public SaveResults() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * pre:el directorio fichero_datos debe existir en el directorio del proyecto?(revisar)
	 * @param test
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public  void pushDataSets(Instances dataSet, String nomFile, String path) {
		Calendar calendar = new GregorianCalendar(); // Fecha y hora actuales.
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmm"); // Formato de la fecha.
		String dateS = dateFormat.format(calendar.getTime()); // Fecha y hora actuales formateadas.
		
		// Variable para captura ranking y variable para guardar el contenido en un archivo.
		String newPath = path.substring(0, path.length() - 5) + nomFile + dateS + ".arff";
		
		ArffSaver guardarARFF = new ArffSaver();        
		guardarARFF.setInstances(dataSet);
		try {
			guardarARFF.setDestination(new FileOutputStream(newPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			guardarARFF.writeBatch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//Escribe el lote de instancias
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
	public boolean SaveFile(String FilePath, String FileContent, boolean CleanFileContent) {
	    FileWriter file;
	    BufferedWriter writer;
	     
	    try {
	        file = new FileWriter(FilePath, !CleanFileContent);
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
