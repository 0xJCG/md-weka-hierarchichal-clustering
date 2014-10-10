package hierarchicalclustering;

import java.util.TreeMap;

public class ClusterTree {
	private static ClusterTree miClusterTree = null;
	private TreeMap<Float, ClusterList> tree;
	
	private ClusterTree() {
		this.tree = new TreeMap<Float, ClusterList>();
	}
	
	public static ClusterTree getClusterTree() {
		if (miClusterTree == null)
			miClusterTree = new ClusterTree();
		return miClusterTree;
	}
	
	public void add(Float distance, ClusterList clusters) {
		this.tree.put(distance, clusters);
	}
	
	public String toString() {
		return this.tree.toString();
	}
}
