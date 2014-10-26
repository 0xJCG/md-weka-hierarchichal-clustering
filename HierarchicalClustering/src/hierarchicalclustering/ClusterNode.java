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

    /**
     * Devuelve el nodo padre.
     * @return Nodo padre.
     */
	public ClusterNode getParent() {
        return this.parent;
    }

	/**
	 * Asigna un nodo al nodo padre.
	 * @param parent: el nodo a asignar.
	 */
    public void setParent(ClusterNode parent) {
        this.parent = parent;
    }

    /**
     * Devuelve la rama izquierda.
     * @return el nodo raiz de la rama izquierda.
     */
    public ClusterNode getLeftBranch() {
        return this.leftBranch;
    }
    
    /**
     * Asigna un nodo la rama izquierda.
     * @param leftBranch: el nodo que se le va asignar a el nodo raiz de la rama izquierda.
     */
    public void setLeftBranch(ClusterNode leftBranch) {
        this.leftBranch = leftBranch;
    }

    /**
     * Devuelve la rama derecha.
     * @return el nodo raiz de la rama derecha.
     */
    public ClusterNode getRightBranch() {
        return this.rightBranch;
    }

    /**
     * Asigna un nodo la rama derecha.
     * @param rightBranch: el nodo que se le va asignar a el nodo raiz de la rama derecha.
     */
    public void setRightBranch(ClusterNode rightBranch) {
        this.rightBranch = rightBranch;
    }

}
