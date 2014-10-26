package distances;

import weka.core.Instance;

public interface DistancesInterface {
	/**
	 * Calula la distancia entre 2 instancias.
	 * @param instance1: Primera instancia de la que se quiere calcular la distancia.
	 * @param instance2: Segunda instancia de la que se quiere calcular la distancia.
	 * @return distance: devuelve la distancia entre dos instancias.
	 */
	public double distance(Instance instance1, Instance instance2);
}
