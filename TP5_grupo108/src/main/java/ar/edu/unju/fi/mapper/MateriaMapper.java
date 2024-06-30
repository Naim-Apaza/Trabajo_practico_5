package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

public interface MateriaMapper {

	@Mappings({ @Mapping(target = "codigo", source = "codigo"), @Mapping(target = "nombre", source = "nombre"),
			@Mapping(target = "curso", source = "curso"), @Mapping(target = "cantidadHoras", source = "cantidadHoras"),
			@Mapping(target = "modalidad", source = "modalidad"), @Mapping(target = "docente", source = "docente"),
			@Mapping(target = "carrera", source = "carrera") })
	MateriaDTO toMateriaDTO(Materia materia);

	@InheritInverseConfiguration
	Materia toMateria(MateriaDTO materiaDTO);
	
	List<MateriaDTO> toMateriaDTOs(List<Materia> materias);

    List<Materia> toMaterias(List<MateriaDTO> materiasDTO);
}
