package hierarchicalclustering;

import java.util.LinkedList;

import weka.core.Instance;

public class Cluster {
	private LinkedList<Instance> instances;
	
	public Cluster() {
		super();
	}
	
	public Cluster(Instance instance) {
		this.instances = new LinkedList<Instance>();
		this.instances.add(instance);
	}

	public LinkedList<Instance> getInstances() {
		return instances;
	}
	
	public void addInstance(Instance instance) {
		this.instances.add(instance);
	}
	
	public Instance get(int i) {
		return this.instances.get(i);
	}
	
	public int size() {
		return this.instances.size();
	}
	
	public void merge(Cluster cluster) {
		for (int i = 0; i < cluster.size(); i++)
			this.addInstance(cluster.get(i));
	}
}
