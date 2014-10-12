package distances;

import weka.core.Instance;
import weka.core.NormalizableDistance;

public class EuclideanDistance extends NormalizableDistance {
	public double distance(Instance instance1, Instance instance2) {
	    double dist = 0.0;

	    for (int i = 0; i < instance1.numAttributes(); i++) {
	        double x = instance1.value(i);
	        double y = instance2.value(i);

	        if (Double.isNaN(x) || Double.isNaN(y)) {
	            continue; // Mark missing attributes ('?') as NaN.
	        }

	        dist += (x-y)*(x-y);
	    }

	    return Math.sqrt(dist);
	}

	public String getRevision() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String globalInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected double updateDistance(double currDist, double diff) {
		// TODO Auto-generated method stub
		return 0;
	}
}
