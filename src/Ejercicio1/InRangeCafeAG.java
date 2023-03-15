package Ejercicio1;

import java.util.List;

import _datos.DatosCafe;
import _datos.DatosSubconjunto;
import _soluciones.SolucionCafe;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeCafeAG implements ValuesInRangeData<Integer, SolucionCafe> {

	public InRangeCafeAG(String string) {
		DatosCafe.iniDatos(string);
	}
	
	@Override
	public Integer max(Integer i) {
		return DatosCafe.getMaxKgVariedad(i) + 1;
	}

	@Override
	public Integer min(Integer i) { 
		return 0;
	}
	
	@Override
	public Integer size() {
		return DatosCafe.getNumVariedades();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		double goal = 0, error = 0, dif = 0, k = 0;
		for (int i = 0; i < size(); i++) {
			if (value.get(i) > 0) {
				goal += value.get(i) * DatosCafe.getBeneficioVariedad(i);
			}
		}
		for (int j = 0; j < DatosCafe.getNumTipos(); j++) {
			dif = 0;
			// Restriccion de kg disponibles de cada tipo
			for (int i = 0; i < size(); i++) {
				dif += value.get(i) * DatosCafe.getKgTipoVariedad(j, i);
			}
			if (dif > DatosCafe.getKgTipo(j)) {
				error += dif - DatosCafe.getKgTipo(j);
			}
		}
		// Calculo de k
		for (int i = 0; i < size(); i++) {
			k += Math.pow((DatosCafe.getMaxKgVariedad(i) * DatosCafe.getBeneficioVariedad(i)), 2);
		}
		return goal - k * error;
	}

	@Override
	public SolucionCafe solucion(List<Integer> value) {
		return SolucionCafe.of_Range(value);
	}

}
