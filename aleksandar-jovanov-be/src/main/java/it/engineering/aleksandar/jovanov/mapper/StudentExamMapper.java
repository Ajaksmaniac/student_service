package it.engineering.aleksandar.jovanov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.engineering.aleksandar.jovanov.dto.ExamDto;
import it.engineering.aleksandar.jovanov.dto.StudentExamDto;
import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.StudentExamEntity;

@Mapper(componentModel = "spring")
public interface StudentExamMapper {

	@Mapping(target = "student.exams", ignore = true)
	@Mapping(target = "exam.students", ignore = true)
	@Mapping(target = "exam.examinationPeriod.exams", ignore = true)
	StudentExamDto toDto(StudentExamEntity entity);
	@Mapping(target = "student.exams", ignore = true)
	@Mapping(target = "exam.students", ignore = true)
	@Mapping(target = "exam.examinationPeriod.exams", ignore = true)
	StudentExamEntity toEntity(StudentExamDto dto);
	
}
