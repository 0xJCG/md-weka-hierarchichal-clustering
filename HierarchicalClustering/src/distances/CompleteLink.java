package distances;

import weka.core.Instance;
import hierarchicalclustering.Cluster;

public class CompleteLink implements Link {
	public double calculateClusterDistance(Cluster cluster1, Cluster cluster2) {
		Instance instance1, instance2;
		double distance = 0, tempDist = 0;
		
		for (int i = 0; i < cluster1.getInstances().numInstances(); i++) {
        	instance1 = cluster1.getInstances().instance(i);
        	for (int j = 0; j < cluster2.getInstances().numInstances(); j++) {
        		instance2 = cluster2.getInstances().instance(i);
        		tempDist = Distance.getMiDistance().getDistance().distance(instance1, instance2);
        		if (tempDist > distance)
        			distance = tempDist;
        	}
        }
		
        return distance;
	}
}
