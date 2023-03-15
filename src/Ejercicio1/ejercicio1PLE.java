package Ejercicio1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import _datos.DatosCafe;
import _datos.DatosCafe.Variedad;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class ejercicio1PLE {

	public static List<Integer> tipos;
	public static List<Variedad> variedades;

	public static Integer getNumTipos() {
		return tipos.size();
	}

	public static Integer getNumVariedades() {
		return variedades.size();
	}

	public static Integer getKgTipo(Integer j) {
		return tipos.get(j);
	}

	public static Integer getBeneficioVariedad(Integer i) {
		return variedades.get(i).beneficio();
	}

	public static Double getKgTipoVariedad(Integer j, Integer i) {
		return variedades.get(i).composicion().get(j);
	}

	public static void Ejercicio1_model() throws IOException {
		for (int i = 0; i < 3; i++) {
			DatosCafe.iniDatos("ficheros/Ejercicio1DatosEntrada" + (i + 1) + ".txt");
			tipos = DatosCafe.tipos;
			variedades = DatosCafe.variedades;

			// si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para
			// no sobreescribirlo
			AuxGrammar.generate(ejercicio1PLE.class, "lsi_models/Ejercicio1.lsi", "gurobi_models/ejercicio1.lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_models/ejercicio1.lp");
			Locale.setDefault(new Locale("en", "US"));
			System.out.println(solution.toString((s, d) -> d > 0.));
		}
	}

	public static void main(String[] args) throws IOException {
		Ejercicio1_model();

	}

}
