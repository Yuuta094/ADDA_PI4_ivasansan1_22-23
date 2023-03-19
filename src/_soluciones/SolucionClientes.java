package _soluciones;

import java.util.ArrayList;
import java.util.List;

import _datos.DatosClientes;
import _datos.DatosClientes.Cliente;

public class SolucionClientes {
	public static SolucionClientes of_Range(List<Integer> value) {
		return new SolucionClientes(value);
	}

	private Double kms;
	private Double benef;
	private List<Cliente> clientes;

	private SolucionClientes() {
		kms = 0.;
		benef = 0.;
		clientes = new ArrayList<>();
		Cliente c0 = DatosClientes.getCliente(0);
		clientes.add(c0);
	}

	private SolucionClientes(List<Integer> value) {
		kms = 0.;
		benef = 0.;
		clientes = new ArrayList<>();
		Cliente c0 = DatosClientes.getCliente(0);
		clientes.add(c0);
		for (int i = 0; i < value.size(); i++) {
			Cliente c = DatosClientes.getCliente(value.get(i));
			clientes.add(c);
			if (i == 0) {
				if (DatosClientes.existeArista(0, value.get(i))) {
					kms += DatosClientes.getPeso(0, value.get(i));
					benef += DatosClientes.getBeneficio(value.get(i)) - kms;
				}
			} else {
				if (DatosClientes.existeArista(value.get(i - 1), value.get(i))) {
					kms += DatosClientes.getPeso(value.get(i - 1), value.get(i));
					benef += DatosClientes.getBeneficio(value.get(i)) - kms;
				}
			}
		}
	}

	public static SolucionClientes empty() {
		return new SolucionClientes();
	}

	public String toString() {
		List<Integer> ids = clientes.stream().map(c -> c.id()).toList();
		return "Camino a seguir:\n" + ids + "\nDistancia: " + kms + "\nBeneficio: " + benef;
	}
}