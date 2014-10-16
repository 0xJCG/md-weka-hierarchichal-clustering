package hierarchicalclustering;

import java.util.Iterator;
import java.util.LinkedList;

public class ClusterList {
	private LinkedList<Cluster> list;
	
	public ClusterList() {
		this.list = new LinkedList<Cluster>();
	}
	
	public ClusterList(Cluster cluster) {
		this.list = new LinkedList<Cluster>();
		this.add(cluster);
	}
	
	public void add(Cluster cluster) {
		if (!this.isCluster(cluster))
			this.list.addLast(cluster);
	}
	
	private boolean isCluster(Cluster cluster) {
		return this.list.contains(cluster);
	}
	
	public int size() {
		return this.list.size();
	}
	
	public Cluster get(int i) {
		return this.list.get(i);
	}
	
	public void remove(int i) {
		this.list.remove(i);
	}
	
	public void print() {
		Iterator<Cluster> it = this.list.iterator();
		Cluster c;
		System.out.println("  - " + this.size() + " cluster/s:");
		while (it.hasNext()) {
			c = it.next();
			System.out.println("    " + c.getInstances());
		}
	}
	
	public String toString() {
		Iterator<Cluster> it = this.list.iterator();
		Cluster c;
		String string = this.size() + " cluster/s:\n";
		while (it.hasNext()) {
			c = it.next();
			string += "  " + c.getInstances() + "\n";
		}
		return string;
	}
}
