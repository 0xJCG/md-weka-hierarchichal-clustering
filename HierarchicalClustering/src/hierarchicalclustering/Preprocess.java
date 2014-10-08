package hierarchicalclustering;

import weka.core.Instances;

public class Preprocess {
	private static Preprocess miPreprocess = null;

	private Preprocess() {}
	
	public static Preprocess getMiPreprocess() {
		if (miPreprocess == null)
			miPreprocess = new Preprocess();
		return miPreprocess;
	}

	public Instances deleteUselessAtributes(Instances instances) {
		return instances;
	}
	
	public Instances deleteClass(Instances instances) {
		return instances;
	}
}
