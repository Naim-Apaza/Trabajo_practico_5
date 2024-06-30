package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionDocente {
	private static List<Docente> docentes = new ArrayList<Docente>();

	/**
	 * Pregarca de docentes
	 * 
	 * @return
	 */
	public static List<Docente> getDocentes() {
		if (docentes.isEmpty()) {
			docentes.add(new Docente("DC1", "Lucas", "Ramos", "Ramos@gmail.com", "3888888"));
			docentes.add(new Docente("DC2", "Roberto", "Llampa", "Robert@gmail.com", "3888888"));
			docentes.add(new Docente("DC3", "Daniel", "Rosas", "Rosas@gmail.com", "3888888"));
		}
		return docentes;
	}

	/**
	 * Agregar un docente
	 * 
	 * @param docente
	 */
	public static void agregarDocente(Docente docente) {
		docentes.add(docente);
	}

	/**
	 * Eliminar docente por medio de su legajo
	 * 
	 * @param legajo
	 */
	public static void eliminarDocente(String legajo) {
		Iterator<Docente> iterator = docentes.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getLegajo().equalsIgnoreCase(legajo))
				iterator.remove();
		}
	}

	/**
	 * Modifica un docente por medio de su legajo
	 * 
	 * @param docente
	 */
	public static void modificarDocente(Docente docente) {

		for (Docente d : docentes) {
			System.out.println(d.getLegajo());
			System.out.println(docente.getLegajo());

			if (d.getLegajo().equalsIgnoreCase(docente.getLegajo())) {
				System.out.println("ok");
				d.setNombre(docente.getNombre());
				d.setApellido(docente.getApellido());
				d.setEmail(docente.getEmail());
				d.setTelefono(docente.getTelefono());
			}
		}
	}

	/**
	 * Devuelve el docente que coincida con su legajo
	 * 
	 * @param legajo
	 * @return
	 */
	public static Docente buscarDocente(String legajo) {
		Predicate<Docente> filterLegajo = d -> d.getLegajo().equalsIgnoreCase(legajo);

		Optional<Docente> docente = docentes.stream().filter(filterLegajo).findFirst();

		if (docente.isPresent())
			return docente.get();
		return null;
	}

}
