package _soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import _datos.DatosCafe;
import _datos.DatosCafe.Variedad;

public class SolucionCafe {

	public static SolucionCafe of_Range(List<Integer> value) {
		return new SolucionCafe(value);
	}

	private Double beneficio;
	private List<Variedad> soluciones;
	private List<Integer> solucion;

	private SolucionCafe() {
		beneficio = 0.;
		soluciones = new ArrayList<>();
		solucion = new ArrayList<>();
	}
  
	private SolucionCafe(List<Integer> ls) {
		beneficio = 0.;
		soluciones = new ArrayList<>();
		solucion = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) > 0) {
				Integer kg = ls.get(i);
				Integer bv = DatosCafe.getBeneficioVariedad(i) * kg;
				soluciones.add(DatosCafe.getVariedades().get(i));
				solucion.add(ls.get(i));
				beneficio += bv;
			}
		}
	}

	public static SolucionCafe empty() {
		return new SolucionCafe();
	}

	public String toString() {
		String s = soluciones.stream().map(v -> "P" + (v.id() + 1) + ": " + solucion.get(soluciones.indexOf(v)))
				.collect(Collectors.joining(" Kg\n", "Kgs producidos de cada variedad:\n", " Kg\n"));
		return String.format("%sBeneficio obtenido: %.1f", s, beneficio);
	}
}
