package distances;

import weka.core.Instance;
import hierarchicalclustering.Cluster;

public class CompleteLink implements LinksInterface {
	public double calculateClusterDistance(Cluster cluster1, Cluster cluster2) {
		Instance instance1, instance2;
		double distance = 0, tempDist = 0;
		
		for (int i = 0; i < cluster1.size(); i++) {
        	instance1 = cluster1.get(i);
        	for (int j = 0; j < cluster2.size(); j++) {
        		instance2 = cluster2.get(j);
        		tempDist = Distance.getMiDistance().getDistance().distance(instance1, instance2);
        		if (tempDist > distance)
        			distance = tempDist;
        	}
        }
		
        return distance;
	}
}
