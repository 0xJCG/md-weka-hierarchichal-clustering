package distances;

import weka.core.Instance;
import hierarchicalclustering.Cluster;

public class CompleteLink implements LinksInterface {
	 /**
	 * Pre: los clusters no deben estar vacios.
	 * Calcula la distancia intergrupal entre 2 clusters por complete-link (la distancia mayor entre los clusters).
	 * @param cluster1: Primer cluster del que se quiere calcular la distancia.
	 * @param cluster2: Segundo cluster del que se quiere calcular la distancia.
	 * @return distance: la distancia entre dos clusters.
	 */
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
