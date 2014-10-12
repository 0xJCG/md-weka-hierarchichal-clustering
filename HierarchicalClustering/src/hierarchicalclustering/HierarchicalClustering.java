package hierarchicalclustering;

import java.util.ArrayList;
import java.util.Iterator;

import weka.core.Instance;
import weka.core.Instances;
import distances.AverageLink;
import distances.CompleteLink;
import distances.Link;
import distances.SingleLink;

public class HierarchicalClustering {
	private Instances instances;
	private ArrayList<ClusterList> finalClusterList = new ArrayList<ClusterList>();
	private Link link;
	
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
		ClusterList cl, updatingClusterList = this.beginClustering();
		
		//ClusterList cl, updatingClusterList = this.finalClusterList.get(0);
		Cluster c = new Cluster(), nearestCluster = new Cluster();
		double minDistance, daux = 0;
		int c1 = 0;
		while (updatingClusterList.size() > 1) {
			cl = updatingClusterList;
			minDistance = 1.0/0.0;
			for (int i = 0; i < cl.size(); i++) {
				c = cl.get(i);
				for (int j = i + 1; j < cl.size(); j++) {
					try {
						daux = this.link.calculateClusterDistance(c, cl.get(j));
						if (daux < minDistance) {
							minDistance = daux;
							nearestCluster = cl.get(j);
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
			this.finalClusterList.add(updatingClusterList);
			ClusterTree.getClusterTree().add((float) minDistance, updatingClusterList);
			System.out.println("Distancia: " + minDistance);
			updatingClusterList.print();
			System.out.println(nearestCluster);
		}
		//this.printFinalList();
		//ClusterTree.getClusterTree().print();
	}
	
	private ClusterList beginClustering() {
		Cluster newCluster;
		ClusterList firstClusterList = new ClusterList();
		for (int i = 0; i < this.instances.numInstances(); i++) {
			newCluster = new Cluster(this.instances.instance(i));
			firstClusterList.add(newCluster);
		}
		ClusterTree.getClusterTree().add(0f, firstClusterList);
		this.finalClusterList.add(firstClusterList);
		return firstClusterList;
	}
	
	private void printFinalList() {
		Iterator<ClusterList> it = this.finalClusterList.iterator();
		ClusterList c;
		int cont = 1;
		while (it.hasNext()) {
			c = it.next();
			System.out.println("Iteracion " + cont);
			System.out.println("Numero de clusters: " + c.size());
			c.print();
			cont++;
		}
	}
}
