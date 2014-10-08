package hierarchicalclustering;

import java.util.ArrayList;

public class ClusterList {
	private ArrayList<Cluster> list;
	
	public ClusterList() {
		this.list = new ArrayList<Cluster>();
	}
	
	public void add(Cluster cluster) {
		this.list.add(cluster);
	}
	
	public boolean isCluster(Cluster cluster) {
		return true;
	}
	
	public String toString() {
		return this.list.toString();
	}
}
