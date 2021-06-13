package it.engineering.aleksandar.jovanov.mapper;


import org.mapstruct.Mapper;


import it.engineering.aleksandar.jovanov.dto.ProfessorDto;
import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;

@Mapper(componentModel = "spring",uses = {CityMapper.class,SubjectMapper.class})
public interface ProfessorMapper {
	ProfessorDto toDto(ProfessorEntity entity);
	ProfessorEntity toEntity(ProfessorDto dto);
}