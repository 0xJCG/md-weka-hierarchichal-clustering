package hierarchicalclustering;

import java.io.FileNotFoundException;
import java.io.IOException;

import utils.StopWatch;
import weka.core.Instances;
import datafiles.DataLoader;
import datafiles.SaveResults;
import distances.Distance;

public class Main {
	/**
	 * Arranque del programa.
	 * @param args:
	 * 0. Path del fichero.
	 * 1. Tipo de clustering jerarquico: si 0 top-down, sino bottom-up.
	 * 2. Tipo distancia intergrupal: si 0 complete-link, si 1 single-link, sino average-link.
	 * 3. Distancia: si 0 Chebyshev; sino Minkowski para k igual al parametro introducido.
	 * 4. Salida de resultados a fichero: si 0 desactivado, sino activado.
	 */
	public static void main(String[] args) {
		if (args.length == 5) {
			double totalElapsedTime;
			StopWatch programRunning = new StopWatch();
			
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
			ClusterTree tree = new ClusterTree(null);
			
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
				Distance.getMiDistance().setDistance(distance);
				resultado += " - Distancia utilizada: Minkowski, con k = " + distance + ".\n";
			}
			
			double elapsedTimeAlgorithm;
			StopWatch algorithmTime;
			
			if (algorithm == 0) { // Top-down
				resultado += " - Algoritmo utilizado: top-down.\n\n";
				System.out.println(resultado);
				resultado += "=========== Resultado del algoritmo ===========\n\n";
				algorithmTime = new StopWatch();
				tree = hc.topDown();
				elapsedTimeAlgorithm = algorithmTime.elapsedTime();
				resultado += tree.toString();
			} else { // Por defecto, bottom-up.
				resultado += " - Algoritmo utilizado: bottom-up.\n\n";
				System.out.println(resultado);
				resultado += "=========== Resultado del algoritmo ===========\n\n";
				algorithmTime = new StopWatch();
				resultado += hc.bottomUp();
				elapsedTimeAlgorithm = algorithmTime.elapsedTime();
			}
			
			System.out.println("\n\nTiempo que ha tardado en ejecutarse el algoritmo: " + elapsedTimeAlgorithm + "s.");
			
			if (file != 0) { // Si el usuario ha introducido un numero que no sea 0, sacara el resultado de las iteraciones en un fichero.
				SaveResults.getSaveResults().SaveFile(args[0], resultado, true, "resultado");
				if (algorithm == 0) // Top-down
					SaveResults.getSaveResults().SaveFile(args[0], tree.printTree(), false, "arbol");
			}
			
			totalElapsedTime = programRunning.elapsedTime();
			System.out.println("Tiempo que ha tardado en ejecutarse el prgrama completo: " + totalElapsedTime + "s.");
		} else {
			System.out.println("El numero de parametros no es el correcto.");
			String aviso = "Parametros a introducir:\n";
			aviso += " 1. Path del fichero.\n";
			aviso += " 2. Tipo de clustering jerarquico: si 0 top-down, sino bottom-up.\n";
			aviso += " 3. Tipo distancia intergrupal: si 0 complete-link, si 1 single-link, sino average-link.\n";
			aviso += " 4. Distancia: si 0 Chebyshev; sino Minkowski para k igual al parametro introducido.\n";
			aviso += " 5. Salida de resultados a fichero: si 0 desactivado, sino activado.";
			System.out.println(aviso);
		}
	}
}
