package it.engineering.aleksandar.jovanov.mapper;

import org.mapstruct.Mapper;


import it.engineering.aleksandar.jovanov.dto.ExaminationPeriodDto;

import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;

@Mapper(componentModel = "spring", uses = {ExamMapper.class,SubjectMapper.class})
public interface ExaminationPeriodMapper {
	ExaminationPeriodDto toDto(ExaminationPeriodEntity entity);
	ExaminationPeriodEntity toEntity(ExaminationPeriodDto dto);
}
