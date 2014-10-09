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
	
	public double distanceWithAnotherCluster(Cluster cluster) {
		double minDistance = 1.0 / 0.0, auxDistance = 0.0;
		for (int i = 0; i < this.instances.numInstances(); i++) {
			for (int j = 0; j < cluster.getInstances().numInstances(); j++) {
				auxDistance = Math.abs(this.instances.instance(i).weight() - cluster.getInstances().instance(j).weight());
				if (auxDistance < minDistance)
					minDistance = auxDistance;
				auxDistance = 0.0;
			}
		}
		return minDistance;
	}
}
