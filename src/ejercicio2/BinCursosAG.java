package ejercicio2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import _datos.DatosCursos;
import _soluciones.SolucionCursos;
import us.lsi.ag.BinaryData;

public class BinCursosAG implements BinaryData<SolucionCursos> {
	public BinCursosAG(String fichero) {
		DatosCursos.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		return DatosCursos.getNumCursos();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
// TODO Auto-generated method stub
		double goal = 0, error = 0, k = 0, suma = 0;
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) > 0) {
				goal += DatosCursos.getPrecioCurso(i);
			}
		}
		Set<Integer> ts = new HashSet<>();
		Set<Integer> cs = new HashSet<>();
		for (int i = 0; i < value.size(); i++) {
			if (value.get(i) > 0) {
				ts.addAll(DatosCursos.getTematicasCurso(i));
				cs.add(DatosCursos.getCentroCurso(i));
			}
		}
		Integer m = DatosCursos.getNumTematicas();
		Integer nc = DatosCursos.getMaxCentros();
// Restricción de selección de temáticas
		if (ts.size() < m) {
			error += m - ts.size();
		}
// Restricción de selección de centros
		if (cs.size() > nc) {
			error += cs.size() - nc;
		}
// Cálculo de k
		for (int i = 0; i < value.size(); i++) {
			suma += DatosCursos.getPrecioCurso(i);
		}
		k += Math.pow(suma, 2);
		return -goal - k * error;
	}

	@Override
	public SolucionCursos solucion(List<Integer> value) {
		return SolucionCursos.of_Range(value);
	}
}