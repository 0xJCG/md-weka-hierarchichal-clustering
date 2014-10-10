package hierarchicalclustering;

import java.util.ArrayList;
import java.util.Iterator;

import weka.core.Instances;

public class HierarchicalClustering {
	private Instances instances;
	private ArrayList<ClusterList> clusterList = new ArrayList<ClusterList>();
	
	public void run() {
		Iterator<ClusterList> it = this.clusterList.iterator();
		ClusterList cl, claux;
		Cluster c;
		double minDistance, daux; 
		
		this.beginClustering();
		
		while (it.hasNext()) {
			cl = it.next();
			claux = new ClusterList();
			for (int i = 0; i < cl.size(); i++) {
				c = cl.get(i);
				minDistance = 1.0/0.0;
				claux.add(c);
				for (int j = i + 1; j < cl.size(); j++) {
					try {
						daux = c.distanceWithAnotherCluster(cl.get(j));
						if (daux < minDistance)
							claux.add(cl.get(j));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			this.clusterList.add(claux);
		}
	}
	
	private void beginClustering() {
		Cluster newCluster;
		ClusterList firstClusterList = new ClusterList();
		for (int i = 0; i < this.instances.numInstances(); i++) {
			newCluster = new Cluster(this.instances.instance(i));
			firstClusterList.add(newCluster);
		}
		/*ClusterTree.getClusterTree().add(0f, firstClusterList);*/
		this.clusterList.add(firstClusterList);
	}
}
