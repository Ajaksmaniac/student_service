package it.engineering.aleksandar.jovanov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.engineering.aleksandar.jovanov.dto.StudentDto;
import it.engineering.aleksandar.jovanov.entity.StudentEntity;


@Mapper(componentModel = "spring",uses= {CityMapper.class,StudentExamMapper.class})
public interface StudentMapper {
	
	
	StudentDto toDto(StudentEntity entity);

	StudentEntity toEntity(StudentDto dto);
}
