package distances;

import weka.core.Instance;

public interface DistancesInterface {
	public double distance(Instance instance1, Instance instance2);
}
