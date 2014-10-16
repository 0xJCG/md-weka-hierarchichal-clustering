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
	public String run() {
		Cluster c = new Cluster();
		double minDistance, daux = 0;
		int c1 = 0, c2 = 0, numIteracionesQueFaltan = this.instances.numInstances();
		int iteraciones = numIteracionesQueFaltan;
		
		System.out.println("Iteracion 1 de " + iteraciones + ".");
		ClusterList updatingClusterList = this.beginClustering();
		
		String resultado = "A distancia 0, " + updatingClusterList.toString() + "\n";
		
		while (numIteracionesQueFaltan > 1) { // Sabemos que empezamos desde el numero total de clusters iniciales hasta quedarnos con uno unico.
			System.out.println("Iteracion " + (iteraciones - numIteracionesQueFaltan + 2) + " de " + iteraciones + ".");
			
			minDistance = 1.0/0.0; // Infinito.
			for (int i = 0; i < updatingClusterList.size(); i++) { // Comprobamos cada cluster primer cluster...
				c = updatingClusterList.get(i);
				for (int j = i + 1; j < updatingClusterList.size(); j++) { // ...con el resto de clusters que quedan por comprobar.
					daux = this.link.calculateClusterDistance(c, updatingClusterList.get(j)); // Calculamos la distancia entre clusters.
					if (daux < minDistance) { // Comprobamos si es la menor. De serlo, la guardamos.
						minDistance = daux;
						c1 = i; // Guardamos la posicion del primer cluster.
						c2 = j; // Guardamos la posicion del segundo cluster.
					}
				}
			}
			updatingClusterList.get(c1).merge(updatingClusterList.get(c2)); // Unimos los dos clusters mas cercanos entre si.
			updatingClusterList.remove(c2); // Eliminamos el cluster que hemos introducido en el otro.
			numIteracionesQueFaltan--; // Un cluster menos en la lista.
			//ClusterTree.getClusterTree().add((float) minDistance, updatingClusterList);
			
			resultado += "A distancia " + minDistance + ", " + updatingClusterList.toString() + "\n";
		}
		//ClusterTree.getClusterTree().print();
		return resultado;
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
