package distances;

public class Distance {
	private static Distance miDistancia = null;
	private DistancesInterface distance;
	
	/**
	 * Constructora de la clase privada porque es una MAE. Por defecto, Euclidea. 
	 */
	private Distance() {
		this.distance = new MinkowskiDistance(2); 
	}
	
	/**
	 * Inicializa el atributo en caso de que no tenga valor.
	 * @return el atributo de la MAE.
	 */
	public static Distance getMiDistance() {
		if (miDistancia == null)
			miDistancia = new Distance();
		return miDistancia;
	}
	
	/**
	 * Devuelve el tipo de Distancia.
	 * @return el tipo de distancia.
	 */
	public DistancesInterface getDistance() {
		return this.distance;
	}
	
	/**
	 * Actualiza el tipo de distancia dependiendo del valor que le pasemos.
	 * @param k el valor que influye en el tipo de distancia.
	 */
	public void setDistance(int k) {
		if (k == 0)
			this.distance = new ChebyshevDistance();
		else
			this.distance = new MinkowskiDistance(k);
	}
}
