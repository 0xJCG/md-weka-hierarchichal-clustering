package hierarchicalclustering;

import java.util.ArrayList;
import java.util.Iterator;

public class ClusterList {
	private ArrayList<Cluster> list;
	
	/**
	 * Constructura de la clase. Se reserva memoria para la lista de clusters.
	 */
	public ClusterList() {
		this.list = new ArrayList<Cluster>();
	}
	
	/**
	 * Constructura de la clase. Se reserva memoria para la lista de clusters.
	 * @param instance: primer cluster de la lista.
	 */
	public ClusterList(Cluster cluster) {
		this.list = new ArrayList<Cluster>();
		this.add(cluster);
	}
	
	/**
	 * Anade un nuevo cluster a la lista. 
	 * @param instance: cluster a anadir a la lista.
	 */
	public void add(Cluster cluster) {
		if (!this.isCluster(cluster))
			this.list.add(cluster);
	}
	/**
	 * Comprueba si cierto claster esta en la lista.
	 * @param cluster: el cluster a mirar.
	 * @return false si no esta, true si esta.
	 */
	private boolean isCluster(Cluster cluster) {
		return this.list.contains(cluster);
	}
	/**
	 * Analiza el numero de clusters de la lista.
	 * @return Numero de clusters en la lista.
	 */
	public int size() {
		return this.list.size();
	}
	
	/**
	 * Devuelve el cluster de una cierta posicion.
	 * @param i: posicion del cluster a devolver.
	 * @return El cluster deseado.
	 */
	public Cluster get(int i) {
		return this.list.get(i);
	}
	
	/**
	 * Elimina el cluster de una cierta posicion.
	 * @param i: posicion del cluster a eliminar.
	 */
	public void remove(int i) {
		this.list.remove(i);
	}
	
	/**
	 * Elimina un cluster de la lista.
	 * @param c: cluster a eliminar.
	 */
	public void remove(Cluster c) {
		this.list.remove(c);
	}
	
	/**
	 * Convierte los datos de la lista de clusters a un String.
	 * @return String con los datos.
	 */
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
