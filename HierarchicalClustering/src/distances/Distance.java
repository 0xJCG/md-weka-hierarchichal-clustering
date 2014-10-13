package distances;

public class Distance {
	private static Distance miDistancia = null;
	private DistancesInterface distance;
	
	private Distance() { // Por defecto, Eucl�dea.
		this.distance = new MinkowskiDistance(2);
	}
	
	public static Distance getMiDistance() {
		if (miDistancia == null)
			miDistancia = new Distance();
		return miDistancia;
	}
	
	public DistancesInterface getDistance() {
		return this.distance;
	}
	
	public void setDistance(int k) {
		if (k == 0)
			this.distance = new ChebyshevDistance();
		else
			this.distance = new MinkowskiDistance(k);
	}
}
