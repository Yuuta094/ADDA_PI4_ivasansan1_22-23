package ejercicio3;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import _datos.DatosInvestigadores;
import _datos.DatosInvestigadores.Investigador;
import _datos.DatosInvestigadores.Trabajo;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {

	public static List<Investigador> investigadores;
	public static List<Trabajo> trabajos;

	// ------------------------------------------------------------------------------------------\\

	public static Integer getNumInvestigadores() {
		return investigadores.size();
	}

	public static Integer getNumEspecialidades() {
		return trabajos.get(0).dias().size();
	}

	public static Integer getNumTrabajos() {
		return trabajos.size();
	}

	public static Integer trabajadorEspecialidad(Integer i, Integer k) {
		return investigadores.get(i).especialdiad().equals(k) ? 1 : 0;
	}

	public static Integer diasDisponibles(Integer i) {
		return investigadores.get(i).capacidad();
	}

	public static Integer diasNecesarios(Integer j, Integer k) {
		return trabajos.get(j).dias().get(k);
	}

	public static Integer getCalidad(Integer j) {
		return trabajos.get(j).calidad();
	}

	public static Integer getMM() {
		return investigadores.stream().map(i -> i.capacidad()).max(Comparator.naturalOrder()).get() + 1;
	}

	// ------------------------------------------------------------------------------------------\\

	
	public static void Ejercicio3_model() throws IOException {
		for (int i = 0; i < 3; i++) {
			DatosInvestigadores.iniDatos("ficheros/Ejercicio3DatosEntrada" + (i + 1) + ".txt");
			investigadores= DatosInvestigadores.investigadores;
			trabajos=DatosInvestigadores.trabajos;

			// si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para
			// no sobreescribirlo
			AuxGrammar.generate(Ejercicio3PLE.class, "lsi_models/Ejercicio3.lsi", "gurobi_models/ejercicio3.lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/ejercicio3.lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
		}
	}

	public static void main(String[] args) throws IOException {
		Ejercicio3_model();
	}
}
