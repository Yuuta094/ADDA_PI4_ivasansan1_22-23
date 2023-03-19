package ejercicio3;

import java.util.List;

import _datos.DatosInvestigadores;
import _soluciones.SolucionInvestigadores;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeInvestigadoresAG implements ValuesInRangeData<Integer, SolucionInvestigadores> {
	public InRangeInvestigadoresAG(String fichero) {
		DatosInvestigadores.iniDatos(fichero);
	}

	@Override
	public Integer size() {
// Deberá haber un cromosoma por cada trabajador
// en cada trabajo, es decir n*m
		return DatosInvestigadores.getNumInvestigadores() * DatosInvestigadores.getNumTrabajos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		double goal = 0, error = 0, kk = 0, capacidad = 0;
// Definimos algunos valores por comodidad
		Integer numInv = DatosInvestigadores.getNumInvestigadores();
		Integer numTrab = DatosInvestigadores.getNumTrabajos();
		Integer numEsp = DatosInvestigadores.getNumEspecialidades();
		for (int j = 0; j < numTrab; j++) {
// Obtenemos un índice para dividir la lista de en
//trada en los distintos trabajos
			Integer jj = j * numInv;
			List<Integer> trab = value.subList(jj, jj + numInv);
			boolean realiza = true;
			for (int k = 0; k < numEsp; k++) {
				Integer suma = 0;
				for (int i = 0; i < numInv; i++) {
					suma += trab.get(i) * DatosInvestigadores.trabajadorEspecialidad(i, k);
				}
// Restricción de días necesarios
				if (suma != DatosInvestigadores.diasNecesarios(j, k)) {
					realiza = false;
					error += Math.abs(suma - DatosInvestigadores.diasNecesarios(j, k));
				}
			}
			if (realiza) {
// Si el trabajo se realiza, sumamos su calidad
				goal += DatosInvestigadores.getCalidad(j);
			}
		}
		for (int i = 0; i < numInv; i++) {
			capacidad = 0;
			for (int ii = i; ii < value.size(); ii += numInv) {
				capacidad += value.get(ii);
			}
// Restricción de días disponibles
			if (capacidad > DatosInvestigadores.diasDisponibles(i)) {
				error += capacidad - DatosInvestigadores.diasDisponibles(i);
			}
		} // Cálculo de k
		Integer suma = 0;
		for (int j = 0; j < numTrab; j++) {
			suma += DatosInvestigadores.getCalidad(j);
		}
		kk = Math.pow(suma, 2);
		return goal - kk * error;
	}

	@Override
	public SolucionInvestigadores solucion(List<Integer> value) {
		System.out.println(value);
		return SolucionInvestigadores.of_Range(value);
	}

	@Override
	public Integer max(Integer i) {
		// Para saber el max de un i más grande que n,
		// tomamos su valor en módulo n
		Integer l = i % DatosInvestigadores.getNumInvestigadores();
		return DatosInvestigadores.diasDisponibles(l) + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
}
