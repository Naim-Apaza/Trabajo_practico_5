package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;

public interface IAlumnoService {

	List<AlumnoDTO> findAll();

	AlumnoDTO findByDni(String dni);

	boolean save(AlumnoDTO alumnoDTO);

	void deleteByDni(String dni);

	void edit(AlumnoDTO alumnoDTO);
}
