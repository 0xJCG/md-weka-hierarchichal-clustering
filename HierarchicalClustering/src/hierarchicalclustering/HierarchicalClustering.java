package hierarchicalclustering;

import weka.core.Instance;
import weka.core.Instances;
import distances.AverageLink;
import distances.CompleteLink;
import distances.LinksInterface;
import distances.SingleLink;

public class HierarchicalClustering {
	private Instances instances;
	private LinksInterface link;
	
	/**
	 * Constructura de la clase HierarchicalClustering.
	 * @param link: se trata de la opcion para activar la distancia intergrupal deseada.
	 * @param data: las instancias de datos a clasificar.
	 */
	public HierarchicalClustering(int link, Instances data) {
		if (link == 0)
			this.link = new CompleteLink();
		else if (link == 1)
			this.link = new SingleLink();
		else
			this.link = new AverageLink();
		this.instances = data;
	}
	
	/**
	 * Realiza el algoritmo bottom-up de clustering jerarquico.
	 */
	public String bottomUp() {
		Cluster c = new Cluster();
		double minDistance, daux = 0;
		int c1 = 0, c2 = 0, numIteracionesQueFaltan = this.instances.numInstances();
		int iteraciones = numIteracionesQueFaltan;
		
		System.out.println("Iteracion 1 de " + iteraciones + ".");
		ClusterList updatingClusterList = this.beginBottomUp();
		
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
	 * Realiza el algoritmo top-down de clustering jerarquico
	 * @return
	 */
	public String topDown(){
		Instance instance = null;
		Cluster c, caux, caux2 = new Cluster();
		double minDistance, daux = 0;
		int index = 0, numIteracionesQueFaltan = this.instances.numInstances();
		int iteraciones = numIteracionesQueFaltan;
		
		System.out.println("Iteracion 1 de " + iteraciones + ".");
		ClusterList updatingClusterList = this.beginTopDown();
		c = updatingClusterList.get(0);
		String resultado = "A distancia 0, " + updatingClusterList.toString() + "\n";
		
		while (numIteracionesQueFaltan > 1) { // Sabemos que empezamos con un unico cluster y acabaremos con el numero de instancias.
			System.out.println("Iteracion " + (iteraciones - numIteracionesQueFaltan + 2) + " de " + iteraciones + ".");
			
			minDistance = 1.0/0.0; // Infinito.
			for (int i = 0; i < c.size(); i++) { // Recorremos el cluster principal.
				instance = c.get(i); // Cogemos la instancia actual.
				caux = new Cluster(instance); // La metemos en un cluster temporal.
				caux2 = c.rest(i); // Guardamos en un cluster temporal, el resto del cluster principal quitando la instancia actual.
				daux = this.link.calculateClusterDistance(caux, caux2); // Comprobamos la distancia entre el nuevo cluster con la unica instancia, con el cluster principal sin dicha instancia.
				if (daux < minDistance) { // Si la distancia es la minima, guardamos los datos necesarios para saber cual es la instancia a quitar.
					minDistance = daux;
					index = i; // Posicion de la instancia a quitar.
				}
			}
			caux = new Cluster(c.get(index));
			c.remove(index);
			updatingClusterList.add(caux); // Un cluster mas en la lista.
			resultado += "A distancia " + minDistance + ", " + updatingClusterList.toString() + "\n";
			
			numIteracionesQueFaltan--; // Una iteracion menos.
		}
		return resultado;
	}
	
	/**
	 * Realiza la primera iteracion del metodo bottom-up de cluster jerarquico, es decir, una instancia por cluster.
	 * @return Devuelve la lista inicial con los clusters.
	 */
	private ClusterList beginBottomUp() {
		Cluster newCluster;
		ClusterList firstClusterList = new ClusterList();
		for (int i = 0; i < this.instances.numInstances(); i++) {
			newCluster = new Cluster(this.instances.instance(i));
			firstClusterList.add(newCluster);
		}
		//ClusterTree.getClusterTree().add(0f, firstClusterList);
		return firstClusterList;
	}
	
	/**
	 * Realiza la primera iteracion del metodo top-down de cluster jerarquico, es decir, todas las instancias en un cluster.
	 * @return Devuelve la lista con un solo cluster que tiene todas las instancias.
	 */
	private ClusterList beginTopDown(){
		Cluster newCluster = new Cluster();
		ClusterList firstClusterList = new ClusterList();
		for (int i = 0; i < this.instances.numInstances(); i++){
			newCluster.addInstance(this.instances.instance(i));
		}
		firstClusterList.add(newCluster);
		return firstClusterList;
	}
}
