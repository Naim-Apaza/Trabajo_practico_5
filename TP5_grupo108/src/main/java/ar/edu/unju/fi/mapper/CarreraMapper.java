package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapper {
	@Mappings({ @Mapping(target = "codigo", source = "codigo"), @Mapping(target = "nombre", source = "nombre"),
			@Mapping(target = "cantAnios", source = "cantAnios"), @Mapping(target = "estado", source = "estado") })
	CarreraDTO toCarreraDTO(Carrera carrera);

	@InheritInverseConfiguration
	Carrera toCarrera(CarreraDTO carreraDTO);
	
	List<CarreraDTO> toCarreraDTOs(List<Carrera> carreras);

    List<Carrera> toCarreras(List<CarreraDTO> carrerasDTO);
}
