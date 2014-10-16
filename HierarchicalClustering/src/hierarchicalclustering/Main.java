package hierarchicalclustering;

import java.io.FileNotFoundException;
import java.io.IOException;

import weka.core.Instances;
import datafiles.DataLoader;
import datafiles.SaveResults;
import distances.Distance;

/*Parámetros:
  1º.-path: el path del fichero
  2º.-k: Tipo de clustering jerárquico. Si k=0 bottom up; si k=1 top down(de momento solo bottom up)
  3º.-l: Tipo distancia intergrupal. Si k=0 single-link; si k=1 complete-link; si k=2 average-link(De momento solo single-link)
  4º.-d: Distancia.Si k=0 Chebyshev; si k=1 Manhattan; si k=2 Euclidea*/
public class Main {
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
			
			String resultado = "Cluster jer�rquico, por Yuriy Andzheyevskiy y Jonathan Castro.\n\n";
			resultado += args[0] + ".\n";
			resultado += " - N�mero de instancias: " + data.numInstances() + ".\n";
			HierarchicalClustering hc;
			
			/*if (args[1].equals("0")) {
				//bottomo-up
			} else if (args[1].equals("1"))
				//top-down
			else
				System.out.println("El numero introducido tiene que ser o el 0 o el 1");*/
			
			if (args[2].equals("0")) { // Complete-link.
				hc = new HierarchicalClustering(0, data);
				resultado += " - Linkage utilizado: complete-link.\n";
			} else if(args[2].equals("1")) { // Single-link.
				hc = new HierarchicalClustering(1, data);
				resultado += " - Linkage utilizado: single-link.\n";
			} else { // Por defecto, average-link.
				hc = new HierarchicalClustering(2, data);
				resultado += " - Linkage utilizado: average-link.\n";
			}
			
			if (args[3].equals("0")) { // Chebyshev.
				Distance.getMiDistance().setDistance(0);
				resultado += " - Distancia utilizada: Chebyshev.\n";
			} else { // Minkowski.
				Distance.getMiDistance().setDistance(Integer.parseInt(args[3]));
				resultado += " - Distancia utilizada: Minkowski, con k = " + args[3] + ".\n\n";
			}
			
			resultado += "=========== Resultado del algoritmo ===========\n\n";
			resultado += hc.run();
			
			if (!args[4].equals("0")) // Si el usuario ha introducido un numero que no sea 0, sacara el resultado de las iteraciones en un fichero.
				SaveResults.getSaveResults().SaveFile(args[0], resultado, true);
		}
		else
			System.out.println("El numero de parametros no es el correcto.");
	}
}
