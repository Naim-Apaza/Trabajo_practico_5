package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Component
public class CollectionMateria {
	private static List<Materia> materias = new ArrayList<Materia>();

	/**
	 * Precarga de materias
	 * 
	 * @return materias
	 */
	public static List<Materia> getMateriasListado() {
		if (materias.isEmpty()) {

			materias.add(new Materia(1, "Programacion Visual", "1A", "2", true,
					new Docente("CD", "Ariel", "Vega", "vega@gmail.com", "333333333"),
					new Carrera(1, "APU", 3, true)));
			materias.add(new Materia(2, "Base de datos", "2C", "3", false,
					new Docente("BBC", "Hector", "Lebertadori", "Liber@gmail.com", "388555555"),
					new Carrera(2, "APU", 3, true)));
			materias.add(new Materia(3, "Programacion Estructurada", "5B", "4", true,
					new Docente("DFF", "Perez", "God", "pereaGod@gmail.com", "42244444"),
					new Carrera(3, "APU", 3, true)));
		}

		return materias;
	}

	/**
	 * Metodo para agregar materias
	 * 
	 * @param materia
	 */
	public static void agregarMateria(Materia materia) {
		materias.add(materia);
	}

	/**
	 * Eliminar una materia mediante su codigo
	 * 
	 * @param codigoMateria
	 */
	public static void eliminarMateria(long codigoMateria) {
		Iterator<Materia> iterator = materias.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getCodigo() == codigoMateria) {
				iterator.remove();
			}
		}
	}

	/**
	 * Modificar la materia mediante el codigo de la materia modificando solo
	 * nombre, modalidad, curso, cantidad de horas, docente y carrera
	 * 
	 * @param materia
	 */
	public static void modificarMateria(Materia materia) {
		for (Materia m : materias) {
			if (m.getCodigo() == materia.getCodigo()) {
				m.setNombre(materia.getNombre());
				m.setModalidad(materia.isModalidad());
				m.setCurso(materia.getCurso());
				m.setCantidadHoras(materia.getCantidadHoras());
				m.setDocente(materia.getDocente());
				m.setCarrera(materia.getCarrera());
			}
		}
	}

	/**
	 * Busca la materia mediante su codigo, si no en encuentra la materia devolvera
	 * null
	 * 
	 * @param codigo
	 * @return
	 */
	public static Materia buscarMateria(long codigo) {
		Predicate<Materia> predicate = m -> m.getCodigo() == codigo;
		Optional<Materia> materia = materias.stream().filter(predicate).findFirst();

		if (materia.isPresent())
			return materia.get();
		return null;
	}

}
