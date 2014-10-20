package hierarchicalclustering;

import java.util.LinkedList;

import weka.core.Instance;

public class Cluster {
	private LinkedList<Instance> instances;
	
	/**
	 * Constructura de la clase. Se reserva memoria para la lista de instancias.
	 */
	public Cluster() {
		this.instances = new LinkedList<Instance>();
	}
	
	/**
	 * Constructura de la clase. Se reserva memoria para la lista de instancias.
	 * @param instance: primera instancia del cluster.
	 */
	public Cluster(Instance instance) {
		this.instances = new LinkedList<Instance>();
		this.instances.add(instance);
	}

	/**
	 * Devuelve la lista de instancias del cluster.
	 * @return Lista de instancias del cluster.
	 */
	public LinkedList<Instance> getInstances() {
		return instances;
	}
	
	/**
	 * Anade una nueva instancia al cluster. 
	 * @param instance: instancia a anadir al cluster.
	 */
	public void addInstance(Instance instance) {
		this.instances.add(instance);
	}
	/**
	 * Elimina una instancia del cluster.
	 * @param instance: instancia a eliminar.
	 */
	public void removeInstance(Instance instance) {
		this.instances.remove(instance);
	}
	
	/**
	 * Elimina la instancia de una cierta posicion.
	 * @param i: posicion de la instacia a eliminar.
	 */
	public void remove(int i) {
		this.instances.remove(i);
	}
	
	/**
	 * Devuelve el cluster eliminando la instancia deseado.
	 * @param i: posicion de la instancia a eliminar.
	 * @return El cluster sin la instancia eliminada.
	 */
	public Cluster rest(int i) {
		Cluster c = new Cluster();
		c.merge(this);
		c.remove(i);
		return c;
	}
	
	/**
	 * Devuelve la instancia de una cierta posicion.
	 * @param i: posicion de la instancia a devolver.
	 * @return La instancia deseada.
	 */
	public Instance get(int i) {
		return this.instances.get(i);
	}
	
	/**
	 * Analiza el numero de instancias del cluster.
	 * @return Numero de instancias del cluster.
	 */
	public int size() {
		return this.instances.size();
	}
	
	/**
	 * Junta el cluster que se le pasa por parametro con el nuestro.
	 * @param cluster: cluster a juntar.
	 */
	public void merge(Cluster cluster) {
		for (int i = 0; i < cluster.size(); i++)
			this.addInstance(cluster.get(i));
	}
}
