package ejercicio2;

import java.util.List;
import java.util.Locale;

import _soluciones.SolucionCursos;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestCursosBin {
	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		for (int i = 0; i < 3; i++) {
			BinCursosAG p = new BinCursosAG("ficheros/Ejercicio2DatosEntrada" + (i + 1) + ".txt");
			AlgoritmoAG<List<Integer>, SolucionCursos> ap = AlgoritmoAG.of(p);
			ap.ejecuta();
			System.out.println("================================");
			System.out.println(ap.bestSolution());
			System.out.println("================================\n");
		}
	}
}
