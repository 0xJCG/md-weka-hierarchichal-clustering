package hierarchicalclustering;

import weka.core.Instance;
import weka.core.Instances;

public class Cluster {
	private Instances instances;
	private ClusterList neighbours;
	private Cluster parent;
	
	public Cluster() {
		super();
	}
	
	public Cluster(Instance instance) {
		this.instances.add(instance);
	}

	public ClusterList getNeighbours() {
		return neighbours;
	}

	public void addNeighbour(Cluster neighbour) {
		this.neighbours.add(neighbour);
	}

	public Cluster getParent() {
		return parent;
	}

	public void setParent(Cluster parent) {
		this.parent = parent;
	}

	public Instances getInstances() {
		return instances;
	}
	
	public void addInstance(Instance instance) {
		this.instances.add(instance);
	}
}
