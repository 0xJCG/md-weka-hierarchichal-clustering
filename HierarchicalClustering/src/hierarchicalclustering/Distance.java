package hierarchicalclustering;

import weka.core.ChebyshevDistance;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.ManhattanDistance;

public class Distance {
	private static Distance miDistancia = null;
	private DistanceFunction distanceFunction;

	private Distance() { // Por defecto, Euclídea.
		this.distanceFunction = new EuclideanDistance();
	}
	
	public static Distance getMiDistance() {
		if (miDistancia == null)
			miDistancia = new Distance();
		return miDistancia;
	}
	
	public DistanceFunction getDistance() {
		return this.distanceFunction;
	}
	
	public void setDistance(int k) {
		if (k == 0)
			this.distanceFunction = new ChebyshevDistance();
		else if (k == 1)
			this.distanceFunction = new ManhattanDistance();
		else // Euclídea por defecto.
			this.distanceFunction = new EuclideanDistance();
	}
}
