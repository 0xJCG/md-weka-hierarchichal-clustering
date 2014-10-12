package distances;

import weka.core.Instance;

public class MinkowskiDistance implements DistancesInterface {
	private int k;
	
	public MinkowskiDistance(int k) {
		this.k = k;
	}
	
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
