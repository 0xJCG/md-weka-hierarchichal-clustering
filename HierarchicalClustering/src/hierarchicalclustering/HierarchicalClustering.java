package hierarchicalclustering;

import weka.core.Instances;
import distances.AverageLink;
import distances.CompleteLink;
import distances.LinksInterface;
import distances.SingleLink;

public class HierarchicalClustering {
	private Instances instances;
	private LinksInterface link;
	
	public HierarchicalClustering(int link) {
		if (link == 0)
			this.link = new CompleteLink();
		else if (link == 1)
			this.link = new SingleLink();
		else
			this.link = new AverageLink();
	}
	
	public void setInstances(Instances data){
		this.instances = data;
	}
	
	public void run() {
		ClusterList updatingClusterList = this.beginClustering();
		Cluster c = new Cluster(), nearestCluster = new Cluster();
		double minDistance, daux = 0;
		int c1 = 0;
		
		System.out.println("Distancia: 0. Numero de clusters: " + updatingClusterList.size());
		updatingClusterList.print();
		
		while (updatingClusterList.size() > 1) {
			minDistance = 1.0/0.0; // Infinito.
			for (int i = 0; i < updatingClusterList.size(); i++) {
				c = updatingClusterList.get(i);
				for (int j = i + 1; j < updatingClusterList.size(); j++) {
					try {
						daux = this.link.calculateClusterDistance(c, updatingClusterList.get(j));
						if (daux < minDistance) {
							minDistance = daux;
							nearestCluster = updatingClusterList.get(j);
							c1 = i;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			nearestCluster.merge(updatingClusterList.get(c1));
			updatingClusterList.remove(c1);
			updatingClusterList.add(nearestCluster);
			//ClusterTree.getClusterTree().add((float) minDistance, updatingClusterList);
			System.out.println("Distancia: " + minDistance + ". Numero de clusters: " + updatingClusterList.size());
			updatingClusterList.print();
		}
		//ClusterTree.getClusterTree().print();
	}
	
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
