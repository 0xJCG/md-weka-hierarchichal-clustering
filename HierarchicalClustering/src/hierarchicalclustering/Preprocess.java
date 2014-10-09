package hierarchicalclustering;

import weka.core.Instances;

public class Preprocess {
	private static Preprocess miPreprocess = null;

	private Preprocess() {}
	
	public static Preprocess getMiPreprocess() {
		if (miPreprocess == null)
			miPreprocess = new Preprocess();
		return miPreprocess;
	}

	/**
	 * Función que elimina atributos que no nos sirven para esta tarea: strings y clase.
	 * @param instances: instancias de las que se desea eliminar atributos.
	 * @return el conjunto de instancias sin estos atributos "inútiles".
	 */
	public Instances deleteUselessAtributes(Instances instances) {
		if (instances.checkForStringAttributes())
			instances.deleteStringAttributes();
		int classPosition = instances.classIndex();
		if (classPosition >= 0)
			instances.deleteAttributeAt(classPosition);
		return instances;
	}
}
