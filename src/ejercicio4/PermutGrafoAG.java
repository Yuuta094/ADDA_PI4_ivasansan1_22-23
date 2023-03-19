package ejercicio4;

import java.util.List;

import _datos.DatosClientes;
import _soluciones.SolucionClientes;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class PermutGrafoAG implements SeqNormalData<SolucionClientes> {
	public PermutGrafoAG(String fichero) {
		DatosClientes.iniDatos(fichero);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		double goal = 0, error = 0, k = 0, suma = 0;
		for (int i = 0; i < value.size(); i++) {
			if (i == 0) {
// Suma de beneficio - penalización por "tiempo"
// PENALIZACIÓN: (1km/min) / (1cent/min) = 1cent/km
// ES DECIR: restamos la suma de distancias al beneficio
				if (DatosClientes.existeArista(0, value.get(i))) {
					suma += DatosClientes.getPeso(0, value.get(i));
					goal += DatosClientes.getBeneficio(value.get(i)) - suma;
				}
// Penalización por no existir la arista que estudiamos
				else {
					error++;
				}
			} else {
				if (DatosClientes.existeArista(value.get(i - 1), value.get(i))) {
					suma += DatosClientes.getPeso(value.get(i - 1), value.get(i));
					goal += DatosClientes.getBeneficio(value.get(i)) - suma;
				} else {
					error++;
				}
			}
		}
// Doblamos la penalización (o la hacemos =2 si es 0)
// en el caso en el que el último vértice no sea el 0
		if (value.get(value.size() - 1) != 0) {
			if (error == 0) {
				error += 2;
			} else {
				error = error * 2;
			}
		}
		suma = 0.;
		for (int i = 0; i < value.size(); i++) {
			suma += DatosClientes.getBeneficio(value.get(i));
		}
		k = Math.pow(suma, 2);
		return goal - k * error;
	}

	@Override
	public SolucionClientes solucion(List<Integer> value) {
		return SolucionClientes.of_Range(value);
	}

	@Override
	public Integer itemsNumber() {
		return DatosClientes.getNumVertices();
	}
}