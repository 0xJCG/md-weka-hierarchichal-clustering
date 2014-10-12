package hierarchicalclustering;

import java.util.Iterator;
import java.util.Set;
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
	
	public int size() {
		return this.tree.size();
	}
	
	public void print() {
		Set<Float> keys = this.tree.keySet();
		for (Iterator<Float> i = keys.iterator(); i.hasNext();) {
			Float key = (Float) i.next();
			ClusterList value = this.tree.get(key);
			System.out.println("Distancia: " + key);
			value.print();
		}
	}
}
