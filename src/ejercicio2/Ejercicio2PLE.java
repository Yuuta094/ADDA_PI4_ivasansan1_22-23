package ejercicio2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import _datos.DatosCafe;
import _datos.DatosCursos;
import _datos.DatosCursos.Curso;
import ejercicio1.Ejercicio1PLE;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {

	public static List<Curso> cursos;
	public static Integer maxCentros;
	

	// ------------------------------------------------------------------------------------------\\

	public static Integer getMaxCentros() {
		return maxCentros;
	}

	public static Integer getNumCursos() {
		return cursos.size();
	}

	public static List<Integer> getTematicas() {
		Set<Integer> s = new HashSet<>();
		for (Curso c : cursos) {
			s.addAll(c.tematicas());
		}
		return new ArrayList<>(s);
	}

	public static Integer getNumTematicas() {
		return getTematicas().size();
	}

	public static List<Integer> getTematicasCurso(Integer i) {
		return cursos.get(i).tematicas();
	}

	public static Integer getNumTematicasCurso(Integer i) {
		return getTematicasCurso(i).size();
	}

	public static Integer contieneTematica(Integer i, Integer j) {
		return cursos.get(i).tematicas().contains(getTematicas().get(j)) ? 1 : 0;
	}

	public static Double getPrecioCurso(Integer i) {
		return cursos.get(i).precio();
	}

	public static Integer getCentroCurso(Integer i) {
		return cursos.get(i).centro();
	}

	public static List<Integer> getCentros() {
		Set<Integer> s = new HashSet<>();
		for (Curso c : cursos) {
			s.add(c.centro());
		}
		return new ArrayList<>(s);
	}

	public static Integer getNumCentros() {
		return getCentros().size();
	}

	public static Integer ofreceCurso(Integer i, Integer k) {
		return cursos.get(i).centro().equals(getCentros().get(k)) ? 1 : 0;
	}

	// ------------------------------------------------------------------------------------------\\

	public static void Ejercicio2_model() throws IOException {
	
		for (int i = 0; i < 3; i++) {
			DatosCursos.iniDatos("ficheros/Ejercicio2DatosEntrada" + (i + 1) + ".txt");
			cursos = DatosCursos.cursos;
			maxCentros= DatosCursos.maxCentros;

			// si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para
			// no sobreescribirlo
			AuxGrammar.generate(Ejercicio2PLE.class, "lsi_models/Ejercicio2.lsi", "gurobi_models/ejercicio2.lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/ejercicio2.lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
		}

	}

	public static void main(String[] args) throws IOException {
		Ejercicio2_model();
	}
}
