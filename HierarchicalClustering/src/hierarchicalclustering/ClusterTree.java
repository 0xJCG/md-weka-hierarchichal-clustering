package hierarchicalclustering;

import java.util.TreeMap;

public class ClusterTree {
	private TreeMap<Float, ClusterList> tree;
	
	public ClusterTree() {
		this.tree = new TreeMap<Float, ClusterList>();
	}
	
	public void add(Float distance, ClusterList clusters) {
		this.tree.put(distance, clusters);
	}
	
	public String toString() {
		return this.tree.toString();
	}
}
