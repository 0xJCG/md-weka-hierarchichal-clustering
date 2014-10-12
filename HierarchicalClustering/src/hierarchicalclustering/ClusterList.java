package hierarchicalclustering;

import java.util.ArrayList;
import java.util.Iterator;

public class ClusterList {
	private ArrayList<Cluster> list;
	
	public ClusterList() {
		this.list = new ArrayList<Cluster>();
	}
	
	public ClusterList(Cluster cluster) {
		this.list = new ArrayList<Cluster>();
		this.add(cluster);
	}
	
	public void add(Cluster cluster) {
		if (!this.isCluster(cluster))
			this.list.add(cluster);
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
		while (it.hasNext()) {
			c = it.next();
			System.out.println("   - " + c.getInstances());
		}
	}
}
