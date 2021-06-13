package it.engineering.aleksandar.jovanov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.engineering.aleksandar.jovanov.dto.ExamDto;

import it.engineering.aleksandar.jovanov.entity.ExamEntity;


@Mapper(componentModel = "spring",uses= {ExaminationPeriodMapper.class,StudentExamMapper.class})
public interface ExamMapper {
	@Mapping(target = "examinationPeriod.exams", ignore = true)
	
	ExamDto toDto(ExamEntity entity);
	@Mapping(target = "examinationPeriod.exams", ignore = true)
	
	ExamEntity toEntity(ExamDto dto);
	
}
