package ar.edu.unju.fi.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;

@Component
public class CollectionAlumno {
	private static List<Alumno> alumnos = new ArrayList<Alumno>();

	public static List<Alumno> getAlumnos() {
		if (alumnos.isEmpty()) {
			alumnos.add(new Alumno("445435", "Jose", "Hernandez", "he@gmail", "33434343", "lozano", "APU23424",
					LocalDate.of(2003, 3, 23)));
			alumnos.add(new Alumno("3232", "Ivan", "Centinela", "Centi@gmail", "388433222", "Jujuy", "APU003221",
					LocalDate.of(2008, 5, 3)));
			alumnos.add(new Alumno("233323", "Jose", "Condori", "condor@gmail", "38848422233", "Jujuy", "APU0023332",
					LocalDate.of(1999, 6, 11)));
		}

		return alumnos;
	}

	public static boolean agregarAlumno(Alumno alumno) {
		return alumnos.add(alumno);
	}

	public static void eliminarAlumno(String dni) {
		Iterator<Alumno> iterator = alumnos.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().getDni().equals(dni)) {
				iterator.remove();
			}
		}
	}

	public static void modificarAlumno(Alumno alumno) {
		for (Alumno a : alumnos) {
			if (a.getDni().equalsIgnoreCase(alumno.getDni())) {
				a.setNombre(alumno.getNombre());
				a.setApellido(alumno.getApellido());
				a.setTelefono(alumno.getTelefono());
				a.setDomicilio(alumno.getDomicilio());
				a.setEmail(alumno.getEmail());
				a.setLu(alumno.getLu());
				a.setFechaNacimiento(alumno.getFechaNacimiento());
			}
		}
	}

	public static Alumno buscarAlumno(String dni) {
		Predicate<Alumno> predicate = a -> a.getDni().equalsIgnoreCase(dni);
		Optional<Alumno> alumno = alumnos.stream().filter(predicate).findFirst();

		return alumno.isPresent() ? alumno.get() : null;
	}
}
