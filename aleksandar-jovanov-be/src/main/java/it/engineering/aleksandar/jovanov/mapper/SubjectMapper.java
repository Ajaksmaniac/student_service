package it.engineering.aleksandar.jovanov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.entity.SubjectEntity;
@Mapper(componentModel = "spring")
public interface SubjectMapper {

	
	
	SubjectDto toDto(SubjectEntity entity);
	SubjectEntity toEntity(SubjectDto dto);
	
}
