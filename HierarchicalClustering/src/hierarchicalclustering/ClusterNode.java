package hierarchicalclustering;

public class ClusterNode {
    private double distance;
    private Cluster cluster;

    private ClusterNode parent;
    private ClusterNode leftBranch;
    private ClusterNode rightBranch;

    public ClusterNode(double distance, Cluster cluster) {
        this.distance = distance;
        this.cluster = cluster;
    }

    /**
	 * Obtiene la distancia del cluster con el principal.
	 * @return Distancia con el cluster principal.
	 */
	public double getDistance() {
		return this.distance;
	}

	/**
	 * Establece la distancia a la que esta este cluster con el principal.
	 * @param distance: distancia con el cluster principal.
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	/**
	 * Devuelve el cluster del nodo.
	 * @return Cluster del nodo.
	 */
    public Cluster getCluster() {
		return cluster;
	}

	public ClusterNode getParent() {
        return this.parent;
    }

    public void setParent(ClusterNode parent) {
        this.parent = parent;
    }

    public ClusterNode getLeftBranch() {
        return this.leftBranch;
    }

    public void setLeftBranch(ClusterNode leftBranch) {
        this.leftBranch = leftBranch;
    }

    public ClusterNode getRightBranch() {
        return this.rightBranch;
    }

    public void setRightBranch(ClusterNode rightBranch) {
        this.rightBranch = rightBranch;
    }

}
