package datafiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class DataLoader {
	private FileReader fr;
	
	/**
	 * La constructora no comprueba que el path que le pasamos como parametro es correcto antes de 
	 * construir, es decir, lanza la excepcion.
	 * @param path: fichero a cargar.
	 * @throws FileNotFoundException Si no encuentra el fichero a leer, lanza excepcion.
	 */
	public DataLoader(String path) throws FileNotFoundException {
		this.fr = new FileReader(path);
	}
		
	/**
	 * Carga las instancias del fichero tratado en la constructora y elimina los atributos no deseados.
	 * @return Instances: instancias que tenia el fichero.
	 * @throws IOException Si no puede cargar las instancias, lanza excepcion.
	 * 
	 */
	public Instances instancesLoader() throws IOException {	
		Instances data = null;
		data = new Instances(this.fr);
		this.closeFR(); // Close the file
		return deleteUselessAtributes(data);
	}
	
	/**
	 * Cierra el fichero.
	 * @throws IOException Si no puede cerrar el fichero, lanza excepcion.
	 */
	private void closeFR() throws IOException {
		this.fr.close();
	}
	
	/**
	 * Elimina aquellos atributos que no nos sirven: Strings y la clase, de haberlas.
	 * @param instances: instancias a tratar.
	 * @return Las instancias con atributos que no nos sirven.
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
