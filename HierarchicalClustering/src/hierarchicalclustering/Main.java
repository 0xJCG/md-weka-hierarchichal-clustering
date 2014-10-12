package hierarchicalclustering;

import datafiles.DataLoader;
import datafiles.Preprocess;
import distances.Distance;
import weka.core.Instances;

/*Parámetros:
  1º.-path: el path del fichero
  2º.-k: Tipo de clustering jerárquico. Si k=0 bottom up; si k=1 top down(de momento solo bottom up)
  3º.-l: Tipo distancia intergrupal. Si k=0 single-link; si k=1 complete-link; si k=2 average-link(De momento solo single-link)
  4º.-d: Distancia.Si k=0 Chebyshev; si k=1 Manhattan; si k=2 Euclidea*/
public class Main {
	public static void main(String[] args) {
		if (args.length == 4) {
			DataLoader dl = new DataLoader(args[0]);
			Instances data = dl.instancesLoader();
			HierarchicalClustering hc;
			
			/*if (args[1].equals("0")) {
				//bottomo-up
			} else if (args[1].equals("1"))
				//top-down
			else
				System.out.println("El numero introducido tiene que ser o el 0 o el 1");*/
			
			if (args[2].equals("0")) //complete-link
				hc = new HierarchicalClustering(0);
			else if(args[2].equals("1")) //single-link
				hc = new HierarchicalClustering(1);
			else //average-link
				hc = new HierarchicalClustering(2);
			
			if (args[3].equals("0")) // Chebyshev.
				Distance.getMiDistance().setDistance(0);
			else if(args[3].equals("1")) // Manhattan.
				Distance.getMiDistance().setDistance(1);
			else  // Euclidean.
				Distance.getMiDistance().setDistance(2);
			
			hc.setInstances(Preprocess.getMiPreprocess().deleteUselessAtributes(data));
			hc.run();
		}
		else
			System.out.println("El numero de parametros no es el correcto.");
	}
}
