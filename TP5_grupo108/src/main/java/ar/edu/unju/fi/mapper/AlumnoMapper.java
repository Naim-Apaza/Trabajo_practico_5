package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlumnoMapper {

	@Mappings({ @Mapping(target = "fechaNacimiento", dateFormat = "yyyy-MM-dd") })
	AlumnoDTO tAlumnoDTO(Alumno alumno);

	@InheritInverseConfiguration
	Alumno toAlumno(AlumnoDTO alumnoDTO);

	List<AlumnoDTO> toAlumnoDTOs(List<Alumno> alumnos);

	List<Alumno> ToAlumnos(List<AlumnoDTO> alumnosDTO);

}
