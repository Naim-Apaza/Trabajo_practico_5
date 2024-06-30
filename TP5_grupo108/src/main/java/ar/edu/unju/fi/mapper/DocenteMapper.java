package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocenteMapper {

	@Mappings({ @Mapping(target = "legajo", source = "legajo"), @Mapping(target = "nombre", source = "nombre"),
			@Mapping(target = "apellido", source = "apellido"), @Mapping(target = "email", source = "email"),
			@Mapping(target = "telefono", source = "telefono") })
	DocenteDTO toDocenteDTO(Docente docente);

	@InheritInverseConfiguration
	Docente toDocente(DocenteDTO docenteDTO);
	
	List<DocenteDTO> toDocenteDTOs(List<Docente> docentes);

    List<Docente> toDocentes(List<DocenteDTO> docentesDTO);
}
