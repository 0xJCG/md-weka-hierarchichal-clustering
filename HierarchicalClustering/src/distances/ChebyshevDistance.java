package distances;

import weka.core.Instance;

public class ChebyshevDistance implements DistancesInterface {
	public double distance(Instance instance1, Instance instance2) {
		double dist = 0, aux = 0;

	    for (int i = 0; i < instance1.numAttributes(); i++) {
	        double x = instance1.value(i);
	        double y = instance2.value(i);

	        if (Double.isNaN(x) || Double.isNaN(y)) {
	            continue; // Mark missing attributes ('?') as NaN.
	        }
	        
	        aux = Math.abs(x-y);
	        if (aux > dist)
	        	dist = aux;
	    }

	    return dist;
	}
}
