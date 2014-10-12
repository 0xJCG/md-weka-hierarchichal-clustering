package distances;

import weka.core.Instance;

public class ChebyshevDistance implements DistancesInterface {
	public double distance(Instance instance1, Instance instance2) {
		double dist = 0;
        //int maxI = 0;
        /*Vector diff = instance1.sub(instance2);
       
        double abs;
        for(int i : diff){
                abs = Math.abs(diff.ithValue(i));
                if (abs > dist){
                        //maxI = i;
                	dist = abs;
                }
        }*/
        
        return dist;
	}
}
