package hierarchicalclustering;

import weka.core.Instances;
import distances.AverageLink;
import distances.CompleteLink;
import distances.LinksInterface;
import distances.SingleLink;

public class HierarchicalClustering {
	private Instances instances;
	private LinksInterface link;
	
	public HierarchicalClustering(int link, Instances data) {
		if (link == 0)
			this.link = new CompleteLink();
		else if (link == 1)
			this.link = new SingleLink();
		else
			this.link = new AverageLink();
		this.instances = data;
	}
	
	public void setInstances(Instances data){
		this.instances = data;
	}
	
	/**
	 * Realiza el algoritmo bottom-up de clustering jerarquico.
	 */
	public void run() {
		ClusterList updatingClusterList = this.beginClustering();
		Cluster c = new Cluster(), nearestCluster = new Cluster();
		double minDistance, daux = 0;
		int c1 = 0, numCiclos = this.instances.numInstances();
		
		System.out.println("Distancia: 0");
		updatingClusterList.print();
		
		while (numCiclos > 1) { // Sabemos que empezamos desde el numero total de clusters iniciales hasta quedarnos con uno unico.
			minDistance = 1.0/0.0; // Infinito.
			for (int i = 0; i < updatingClusterList.size(); i++) { // Comprobamos cada cluster primer cluster...
				c = updatingClusterList.get(i);
				for (int j = i + 1; j < updatingClusterList.size(); j++) { // ...con el resto de clusters que quedan por comprobar.
					daux = this.link.calculateClusterDistance(c, updatingClusterList.get(j)); // Calculamos la distancia entre clusters.
					if (daux < minDistance) { // Comprobamos si es la menor. De serlo, la guardamos.
						minDistance = daux;
						nearestCluster = updatingClusterList.get(j); // Guardamos el cluster más cercano.
						c1 = i; // Y también guardaremos el cluster por el que hemos empezado a comprobar.
					}
				}
			}
			numCiclos--;
			nearestCluster.merge(updatingClusterList.get(c1)); // Unimos los dos clusters mas cercanos entre si.
			updatingClusterList.remove(c1); // Eliminamos el cluster que hemos introducido en el otro.
			updatingClusterList.add(nearestCluster); // Anadimos el nuevo cluster a la lista.
			//ClusterTree.getClusterTree().add((float) minDistance, updatingClusterList);
			System.out.println("Distancia: " + minDistance);
			updatingClusterList.print();
		}
		//ClusterTree.getClusterTree().print();
	}
	
	/**
	 * Realiza la primera iteracion del metodo bottom-up de cluster jerarquico, es decir, una instancia por cluster.
	 * @return Devuelve la lista inicial con los clusters.
	 */
	private ClusterList beginClustering() {
		Cluster newCluster;
		ClusterList firstClusterList = new ClusterList();
		for (int i = 0; i < this.instances.numInstances(); i++) {
			newCluster = new Cluster(this.instances.instance(i));
			firstClusterList.add(newCluster);
		}
		//ClusterTree.getClusterTree().add(0f, firstClusterList);
		return firstClusterList;
	}
}
