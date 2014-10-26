package distances;

import hierarchicalclustering.Cluster;

public interface LinksInterface {
	/**
     * Calculate distance between two clusters represented as cluster objects
     * @param cluster1: Primer cluster del que se quiere calcular la distancia.
     * @param cluster2: Segundo cluster del que se quiere calcular la distancia.
     * @return distance: devuelve la distancia entre dos clusters.
     */
    public double calculateClusterDistance (Cluster cluster1, Cluster cluster2);
}
