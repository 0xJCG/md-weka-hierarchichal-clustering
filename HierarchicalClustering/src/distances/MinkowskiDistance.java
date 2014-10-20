package distances;

import weka.core.Instance;

public class MinkowskiDistance implements DistancesInterface {
	private int k;
	
	/**
	 * Cosntructora de la clase.
	 * @param k: parametro que se utiliza para obtener la distancia de Minkowski.
	 */
	public MinkowskiDistance(int k) {
		this.k = k;
	}
	
	/**
	 * Calcula la distancia de Minkowski entre 2 instancias.
	 * @param instance1
	 * @param instance2
	 * @return distancela: la distancia obtenida.
	 */
	public double distance(Instance instance1, Instance instance2) {
		double dist = 0;

	    for (int i = 0; i < instance1.numAttributes(); i++) {
	        double x = instance1.value(i);
	        double y = instance2.value(i);

	        if (Double.isNaN(x) || Double.isNaN(y)) {
	            continue; // Mark missing attributes ('?') as NaN.
	        }
	        
	        dist += Math.pow(Math.abs(x-y), this.k);
	    }

	    return Math.pow(dist, 1.0/this.k);
	}
}
