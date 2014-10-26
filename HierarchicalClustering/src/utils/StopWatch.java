package utils;

/* For additional documentation, see
* <a href="http://introcs.cs.princeton.edu/32class">Section 3.2</a> of
* <i>Introduction to Programming in Java: An Interdisciplinary Approach</i>
* by Robert Sedgewick and Kevin Wayne. */

public class StopWatch {
	private final long start;
	
	/**
	* Crea un objeto de StopWatch.
	*/
	public StopWatch() {
		start = System.currentTimeMillis();
	}
	
	/**
	* @return El tiempo, en segundos, desde que se ha creado este objeto.
	*/
	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}
}
