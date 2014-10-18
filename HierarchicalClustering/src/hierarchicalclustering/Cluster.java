package hierarchicalclustering;

import java.util.LinkedList;

import weka.core.Instance;

public class Cluster {
	private LinkedList<Instance> instances;
	
	public Cluster() {
		this.instances = new LinkedList<Instance>();
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
	 * @param instance
	 */
	public void removeInstance(Instance instance) {
		this.instances.remove(instance);
	}
	
	/**
	 * 
	 * @param i
	 */
	public void remove(int i) {
		this.instances.remove(i);
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public Cluster rest(int i) {
		Cluster c = new Cluster();
		c.merge(this);
		c.remove(i);
		return c;
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
