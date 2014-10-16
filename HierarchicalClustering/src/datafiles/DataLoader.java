package datafiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class DataLoader {
	private FileReader fr;
	
	/**
	 * pre: Se recibe como parametro el path del fichero.
	 * La constructora comprueba que el path que le pasamos como parametro es correcto antes de 
	 * construir, tratando la excepcion.
	 * @param path
	 * @throws FileNotFoundException 
	 */
	public DataLoader(String path) throws FileNotFoundException {
		this.fr = new FileReader(path);
	}
	
	/**
	 * 
	 * @return
	 */
	private FileReader getFR() {
		return fr;
	}
		
	/**
	 * pre:
	 * pos:carga como instancias el fichero instanciado previamente en la constructora de la clase.
	 * @throws IOException 
	 * 
	 */
	public Instances instancesLoader() throws IOException {	
		Instances data = null;
		data = new Instances(this.getFR());
		this.closeFR(); // Close the file
		return deleteUselessAtributes(data);
	}
	
	/**
	 * Close the file
	 * @throws IOException  
	 */
	private void closeFR() throws IOException {
		this.getFR().close();
	}
	
	/**
	 * 
	 * @param instances
	 * @return
	 */
	private Instances deleteUselessAtributes(Instances instances) {
		if (instances.checkForStringAttributes())
			instances.deleteStringAttributes();
		int classPosition = instances.classIndex();
		if (classPosition >= 0)
			instances.deleteAttributeAt(classPosition);
		return instances;
	}
}
