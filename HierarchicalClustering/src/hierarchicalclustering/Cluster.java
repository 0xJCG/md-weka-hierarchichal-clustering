package hierarchicalclustering;

import weka.core.Instance;
import weka.core.Instances;

public class Cluster {
	private Instances instances;
	
	public Cluster() {
		super();
	}
	
	public Cluster(Instance instance) {
		this.instances.add(instance);
	}

	public Instances getInstances() {
		return instances;
	}
	
	public void addInstance(Instance instance) {
		this.instances.add(instance);
	}
	
	public double distanceWithAnotherCluster(Cluster cluster) throws Exception {
		Instance instance1, instance2;
		double distance = 0, count = 0, tempDist = 0;
		
		for (int i = 0; i < this.instances.numInstances(); i++) {
        	instance1 = this.instances.instance(i);
        	for (int j = 0; j < cluster.getInstances().numInstances(); j++) {
        		instance2 = cluster.getInstances().instance(i);
        		tempDist = Distance.getMiDistance().getDistance().distance(instance1, instance2);
        		distance += tempDist;
        		count++;
        	}
        }
		
        if (count == 0)
                throw new Exception();
        else
                return distance / count;
	}
}
