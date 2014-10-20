package distances;

import weka.core.Instance;

public interface DistancesInterface {
	/**
	 * Calula la distancia entre 2 instancias.
	 * @param instance1
	 * @param instance2
	 * @return distance
	 */
	public double distance(Instance instance1, Instance instance2);
}
