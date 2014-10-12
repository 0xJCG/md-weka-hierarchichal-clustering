package distances;

import hierarchicalclustering.Cluster;

public interface Link {
	/**
     * Calculate distance between two clusters represented as cluster objects
     * @param cluster1
     * @param cluster2
     * @return distance
	 * @throws Exception 
     */
    public double calculateClusterDistance (Cluster cluster1, Cluster cluster2) throws Exception;
}
