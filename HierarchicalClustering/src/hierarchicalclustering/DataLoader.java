package hierarchicalclustering;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class DataLoader {
	private FileReader fr;
	private String path;
	static final int classPos=1;
	
	/**
	 * pre: Se recibe como parametro el path del fichero.
	 * La constructora comprueba que el path que le pasamos como parametro es correcto antes de 
	 * construir, tratando la excepcion.
	 * @param path
	 * @param data
	 */
	public DataLoader(String path) {
		try {
			this.path = path;
			this.fr = new FileReader(path); 
		} 
		catch (FileNotFoundException e)	{
			System.out.println("ERROR: Revisar path del fichero de datos: " + path);
		}
		
	}
	
	private FileReader getFR() {
		return fr;
	}
		
	/**
	 * pre:
	 * pos:carga como instancias el fichero instanciado previamente en la constructora de la clase.
	 * 
	 */
	public Instances instancesLoader() {	
		Instances data=null;
		try {
			data = new Instances(this.getFR());
		} catch (IOException e) {
			System.out.println("ERROR: Revisar contenido del fichero de datos: "+this.path);
		}
		// Close the file
		this.closeFR();
		// Se aplicará siempre que se carguen las instancias.
		//this.instanceShuffle(data);
		return data;
	}
	
	/**
	 * Close the file
	 */
	private void closeFR(){
		try {
			this.getFR().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
