package hierarchicalclustering;

import weka.core.Instances;

/*Parámetros:
  1º.-path: el path del fichero
  2º.-k: Tipo de clustering jerárquico. Si k=0 bottom up; si k=1 top down(de momento solo bottom up)
  3º.-l: Tipo distancia intergrupal. Si k=0 single-link; si k=1 complete-link; si k=2 average-link(De momento solo single-link)
  4º.-d: Distancia.Si k=0 Chebyshev; si k=1 Manhattan; si k=2 Euclidea*/
 //De momento solo 1 parámetro
public class Main {
	public static void main(String[] args) {
		if(args.length==2){
			DataLoader dl = new  DataLoader(args[0]);
			Instances data = dl.instancesLoader();
			HierarchicalClustering hc = new HierarchicalClustering();
			hc.setInstances(data);
			if(args[1].equals("0")){
				Distance.getMiDistance().setDistance(0);	
			}
			else if(args[1].equals("1")){
				Distance.getMiDistance().setDistance(1);	
				}
			else if(args[1].equals("2")){
				Distance.getMiDistance().setDistance(2);	
				}
			else{
				System.out.println("El numero introducido debe estar entre el 0 y el 2");
			}
			hc.run();
		
		}
		else{
			System.out.println("El numero de parametros no es el correcto.");
		}
	}
}
