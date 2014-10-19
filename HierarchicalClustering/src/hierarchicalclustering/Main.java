package hierarchicalclustering;

import java.io.FileNotFoundException;
import java.io.IOException;

import weka.core.Instances;
import datafiles.DataLoader;
import datafiles.SaveResults;
import distances.Distance;

public class Main {
	/**
	 * Arranque del programa.
	 * @param args:
	 * 0. Path del fichero
	 * 1. Tipo de clustering jerarquico: si 0 top-down, sino bottom-up.
	 * 2. Tipo distancia intergrupal: si 0 complete-link, si 1 single-link, sino average-link.
	 * 3. Distancia: si 0 Chebyshev; sino Minkowski para k igual al parametro introducido.
	 * 4. Salida de resultados a fichero: si 0 desactivado, sino activado.
	 */
	public static void main(String[] args) {
		if (args.length == 5) {
			DataLoader dl;
			Instances data = null;
			try {
				dl = new DataLoader(args[0]);
				data = dl.instancesLoader();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String resultado = "Cluster jerarquico, por Yuriy Andzheyevskiy y Jonathan Castro.\n\n";
			resultado += args[0] + ".\n";
			resultado += " - Numero de instancias: " + data.numInstances() + ".\n";
			HierarchicalClustering hc;
			
			int algorithm = Integer.parseInt(args[1]);
			int linkage = Integer.parseInt(args[2]);
			int distance = Integer.parseInt(args[3]);
			int file = Integer.parseInt(args[4]);
			
			if (linkage == 0) { // Complete-link.
				hc = new HierarchicalClustering(0, data);
				resultado += " - Linkage utilizado: complete-link.\n";
			} else if (linkage == 1) { // Single-link.
				hc = new HierarchicalClustering(1, data);
				resultado += " - Linkage utilizado: single-link.\n";
			} else { // Por defecto, average-link.
				hc = new HierarchicalClustering(2, data);
				resultado += " - Linkage utilizado: average-link.\n";
			}
			
			if (distance == 0) { // Chebyshev.
				Distance.getMiDistance().setDistance(0);
				resultado += " - Distancia utilizada: Chebyshev.\n";
			} else { // Minkowski.
				Distance.getMiDistance().setDistance(Integer.parseInt(args[3]));
				resultado += " - Distancia utilizada: Minkowski, con k = " + distance + ".\n";
			}
			
			if (algorithm == 1) { // Top-down
				resultado += " - Algoritmo utilizado: top-down.\n\n";
				resultado += "=========== Resultado del algoritmo ===========\n\n";
				resultado += hc.run2();
			} else { // Por defecto, bottom-up.
				resultado += " - Algoritmo utilizado: bottom-up.\n\n";
				resultado += "=========== Resultado del algoritmo ===========\n\n";
				resultado += hc.run();
			}
			
			if (file != 0) // Si el usuario ha introducido un numero que no sea 0, sacara el resultado de las iteraciones en un fichero.
				SaveResults.getSaveResults().SaveFile(args[0], resultado, true);
		} else
			System.out.println("El numero de parametros no es el correcto.");
	}
}
