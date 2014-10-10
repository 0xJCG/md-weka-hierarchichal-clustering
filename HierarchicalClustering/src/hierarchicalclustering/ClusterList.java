package hierarchicalclustering;

import java.util.ArrayList;

public class ClusterList {
	private ArrayList<Cluster> list;
	
	public ClusterList() {
		this.list = new ArrayList<Cluster>();
	}
	
	public void add(Cluster cluster) {
		if (!this.isCluster(cluster))
			this.list.add(cluster);
	}
	
	private boolean isCluster(Cluster cluster) {
		return this.list.contains(cluster);
	}
}
