package hierarchicalclustering;

import java.util.LinkedList;

import weka.core.Instance;

public class Cluster {
	private LinkedList<Instance> instances;
	
	public Cluster() {
		super();
	}
	
	/**
	 * 
	 * @param instance
	 */
	public Cluster(Instance instance) {
		this.instances = new LinkedList<Instance>();
		this.instances.add(instance);
	}

	/**
	 * 
	 * @return
	 */
	public LinkedList<Instance> getInstances() {
		return instances;
	}
	
	/**
	 * 
	 * @param instance
	 */
	public void addInstance(Instance instance) {
		this.instances.add(instance);
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public Instance get(int i) {
		return this.instances.get(i);
	}
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		return this.instances.size();
	}
	
	/**
	 * 
	 * @param cluster
	 */
	public void merge(Cluster cluster) {
		for (int i = 0; i < cluster.size(); i++)
			this.addInstance(cluster.get(i));
	}
}
