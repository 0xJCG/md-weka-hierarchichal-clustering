package hierarchicalclustering;

public class ClusterTree {
	private ClusterNode root;
	private ClusterNode lastNode;
 
    public ClusterTree(double distance, Cluster cluster) {
        this.root = new ClusterNode(distance, cluster);
        this.lastNode = root;
    }
 
    public ClusterTree(ClusterNode root) {
        this.root = root;
        this.lastNode = root;
    }

    public ClusterNode getRoot() {
		return this.root;
	}

	public ClusterNode getLastNode() {
		return this.lastNode;
	}

	public void addClusterNode(ClusterNode clusterNode) {
        this.addClusterNode(clusterNode, this.root);
        this.lastNode = clusterNode;
    }
    
    public void addClusterNode(double distance, Cluster cluster) {
        ClusterNode clusterNode = new ClusterNode(distance, cluster);
    	this.addClusterNode(clusterNode, this.root);
        this.lastNode = clusterNode;
    }
    
    private void addClusterNode(ClusterNode node, ClusterNode root) {
    	/* Queremos siempre que los clusters solitarios queden como hojas en las ramas izquierdas. */
    	if (root.getLeftBranch() == null) // Si esta libre, anadimos el nodo.
        	root.setLeftBranch(node);
        else {
        	if (root.getRightBranch() == null) // Si el de la derecha esta libre, anadimos a la derecha.
            	root.setRightBranch(node);
        	else
        		this.addClusterNode(node, root.getRightBranch()); // Sino, anadimos en la rama de la derecha.
        }
    }
    
    public String toString() {
        return this.toString(this.root);
    }
    
    private String toString(ClusterNode node) {
        String string = "";
    	if (node != null) {
        	if ((node.getRightBranch() == null && node != this.lastNode) || node == this.root) { // Es hoja y no es el ultimo nodo del arbol o es la raiz.
        		string += "A distancia " + node.getDistance() + ":\n"; // Solo imprimimos la distancia una vez.
        	}
        	string += "  " + node.getCluster().getInstances() + "\n";
        	string += this.toString(node.getLeftBranch());
        	string += this.toString(node.getRightBranch());
        }
    	return string;
    }
    
    public String printTree() {
	    return this.printTree(this.root, 0);
	}
    
    private String printTree(ClusterNode node, int level) {
    	String string = "";
    	if (node == null)
	    	return "";
	    string += this.printTree(node.getRightBranch(), level + 1);
	    if (node.getRightBranch() == null) {
	    	string += node.getDistance() + "\t";
	    	for (int i = 0; i < level - 1; i++)
	    		string += "|\t";
	    	string += "|" + node.getCluster().getInstances() + "\n";
	    }
	    string += printTree(node.getLeftBranch(), level + 1);
	    return string;
    }
}
