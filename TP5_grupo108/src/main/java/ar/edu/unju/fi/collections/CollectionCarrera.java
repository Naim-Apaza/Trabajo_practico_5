package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;

@Component
public class CollectionCarrera {
	private static List<Carrera> carreras = new ArrayList<Carrera>();

	/**
	 * Trae un listado de carreras precargadas
	 * 
	 * @return
	 */
	public static List<Carrera> getCarreras() {
		if (carreras.isEmpty()) {
			carreras.add(new Carrera(1, "APU", 3, true));
			carreras.add(new Carrera(2, "Ing. Industrial", 5, false));
			carreras.add(new Carrera(3, "Ing. Informatica", 5, true));
		}
		return carreras;
	}

	/**
	 * Agrega una carrera a la lista
	 * 
	 * @param carrera
	 */
	public static void agregarCarrera(Carrera carrera) {
		carreras.add(carrera);
	}

	/**
	 * Elimina una carrera por medio de su codigo
	 * 
	 * @param codigoCarrera
	 */
	public static void eliminaCarrera(long codigoCarrera) {
		Iterator<Carrera> iterator = carreras.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getCodigo() == codigoCarrera)
				iterator.remove();
		}
	}

	/**
	 * Modifica la carrera con los nuevos valores
	 * 
	 * @param carrera
	 */
	public static void modificarCarrera(Carrera carrera) {
		for (Carrera c : carreras) {
			if (c.getCodigo() != carrera.getCodigo()) {
				// hacer algo en la vista
			} else {
				c.setNombre(carrera.getNombre());
				c.setCantAnios(carrera.getCantAnios());
				c.setEstado(carrera.isEstado());
			}
		}
	}

	/**
	 * Busca la carrera mediante su codigo
	 * 
	 * @param codigo
	 * @return
	 */
	public static Carrera buscarCarrera(long codigo) {
		Predicate<Carrera> filterCodigo = c -> c.getCodigo() == codigo;

		Optional<Carrera> carrera = carreras.stream().filter(filterCodigo).findFirst();
		if (carrera.isPresent())
			return carrera.get();
		return null;
	}

}
