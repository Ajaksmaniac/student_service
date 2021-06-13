package it.engineering.aleksandar.jovanov.mapper;

import it.engineering.aleksandar.jovanov.dto.CityDto;
import it.engineering.aleksandar.jovanov.dto.ExamDto;
import it.engineering.aleksandar.jovanov.dto.ExaminationPeriodDto;
import it.engineering.aleksandar.jovanov.dto.ProfessorDto;
import it.engineering.aleksandar.jovanov.dto.StudentDto;
import it.engineering.aleksandar.jovanov.dto.StudentExamDto;
import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.dto.TitleDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;
import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;
import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;
import it.engineering.aleksandar.jovanov.entity.StudentEntity;
import it.engineering.aleksandar.jovanov.entity.StudentExamEntity;
import it.engineering.aleksandar.jovanov.entity.SubjectEntity;
import it.engineering.aleksandar.jovanov.entity.TitleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-13T18:16:34+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class StudentExamMapperImpl implements StudentExamMapper {

    @Override
    public StudentExamDto toDto(StudentExamEntity entity) {
        if ( entity == null ) {
            return null;
        }

        StudentExamDto studentExamDto = new StudentExamDto();

        studentExamDto.setGrade( entity.getGrade() );
        studentExamDto.setId( entity.getId() );
        studentExamDto.setStudent( studentEntityToStudentDto( entity.getStudent() ) );
        studentExamDto.setExam( examEntityToExamDto( entity.getExam() ) );
        studentExamDto.setAppliedAt( entity.getAppliedAt() );

        return studentExamDto;
    }

    @Override
    public StudentExamEntity toEntity(StudentExamDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentExamEntity studentExamEntity = new StudentExamEntity();

        studentExamEntity.setGrade( dto.getGrade() );
        studentExamEntity.setId( dto.getId() );
        studentExamEntity.setStudent( studentDtoToStudentEntity( dto.getStudent() ) );
        studentExamEntity.setExam( examDtoToExamEntity( dto.getExam() ) );
        studentExamEntity.setAppliedAt( dto.getAppliedAt() );

        return studentExamEntity;
    }

    protected CityDto cityEntityToCityDto(CityEntity cityEntity) {
        if ( cityEntity == null ) {
            return null;
        }

        CityDto cityDto = new CityDto();

        return cityDto;
    }

    protected StudentDto studentEntityToStudentDto(StudentEntity studentEntity) {
        if ( studentEntity == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        studentDto.setAdress( studentEntity.getAdress() );
        studentDto.setCity( cityEntityToCityDto( studentEntity.getCity() ) );
        studentDto.setCurrentYearOfStudy( studentEntity.getCurrentYearOfStudy() );
        studentDto.setEmail( studentEntity.getEmail() );
        studentDto.setFirstName( studentEntity.getFirstName() );
        studentDto.setId( studentEntity.getId() );
        studentDto.setIndexNumber( studentEntity.getIndexNumber() );
        studentDto.setIndexYear( studentEntity.getIndexYear() );
        studentDto.setLastName( studentEntity.getLastName() );

        return studentDto;
    }

    protected ExaminationPeriodDto examinationPeriodEntityToExaminationPeriodDto(ExaminationPeriodEntity examinationPeriodEntity) {
        if ( examinationPeriodEntity == null ) {
            return null;
        }

        ExaminationPeriodDto examinationPeriodDto = new ExaminationPeriodDto();

        examinationPeriodDto.setId( examinationPeriodEntity.getId() );
        examinationPeriodDto.setName( examinationPeriodEntity.getName() );
        examinationPeriodDto.setStarting_period( examinationPeriodEntity.getStarting_period() );
        examinationPeriodDto.setEnding_period( examinationPeriodEntity.getEnding_period() );
        examinationPeriodDto.setActive( examinationPeriodEntity.isActive() );

        return examinationPeriodDto;
    }

    protected SubjectDto subjectEntityToSubjectDto(SubjectEntity subjectEntity) {
        if ( subjectEntity == null ) {
            return null;
        }

        SubjectDto subjectDto = new SubjectDto();

        subjectDto.setDescription( subjectEntity.getDescription() );
        subjectDto.setId( subjectEntity.getId() );
        subjectDto.setName( subjectEntity.getName() );
        subjectDto.setNoOfEsp( subjectEntity.getNoOfEsp() );
        subjectDto.setSemester( subjectEntity.getSemester() );
        subjectDto.setYearOfStudy( subjectEntity.getYearOfStudy() );

        return subjectDto;
    }

    protected List<SubjectDto> subjectEntityListToSubjectDtoList(List<SubjectEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectDto> list1 = new ArrayList<SubjectDto>( list.size() );
        for ( SubjectEntity subjectEntity : list ) {
            list1.add( subjectEntityToSubjectDto( subjectEntity ) );
        }

        return list1;
    }

    protected TitleDto titleEntityToTitleDto(TitleEntity titleEntity) {
        if ( titleEntity == null ) {
            return null;
        }

        TitleDto titleDto = new TitleDto();

        titleDto.setId( titleEntity.getId() );
        titleDto.setName( titleEntity.getName() );

        return titleDto;
    }

    protected ProfessorDto professorEntityToProfessorDto(ProfessorEntity professorEntity) {
        if ( professorEntity == null ) {
            return null;
        }

        ProfessorDto professorDto = new ProfessorDto();

        professorDto.setAdress( professorEntity.getAdress() );
        professorDto.setCity( cityEntityToCityDto( professorEntity.getCity() ) );
        professorDto.setEmail( professorEntity.getEmail() );
        professorDto.setFirstname( professorEntity.getFirstname() );
        professorDto.setId( professorEntity.getId() );
        professorDto.setLastname( professorEntity.getLastname() );
        professorDto.setPhone( professorEntity.getPhone() );
        professorDto.setReelection_date( professorEntity.getReelection_date() );
        professorDto.setSubjects( subjectEntityListToSubjectDtoList( professorEntity.getSubjects() ) );
        professorDto.setTitle( titleEntityToTitleDto( professorEntity.getTitle() ) );

        return professorDto;
    }

    protected ExamDto examEntityToExamDto(ExamEntity examEntity) {
        if ( examEntity == null ) {
            return null;
        }

        ExamDto examDto = new ExamDto();

        examDto.setExam_date( examEntity.getExam_date() );
        examDto.setExaminationPeriod( examinationPeriodEntityToExaminationPeriodDto( examEntity.getExaminationPeriod() ) );
        examDto.setId( examEntity.getId() );
        examDto.setProfessor( professorEntityToProfessorDto( examEntity.getProfessor() ) );
        examDto.setSubject( subjectEntityToSubjectDto( examEntity.getSubject() ) );

        return examDto;
    }

    protected CityEntity cityDtoToCityEntity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        CityEntity cityEntity = new CityEntity();

        return cityEntity;
    }

    protected StudentEntity studentDtoToStudentEntity(StudentDto studentDto) {
        if ( studentDto == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setAdress( studentDto.getAdress() );
        studentEntity.setCity( cityDtoToCityEntity( studentDto.getCity() ) );
        studentEntity.setCurrentYearOfStudy( studentDto.getCurrentYearOfStudy() );
        studentEntity.setEmail( studentDto.getEmail() );
        studentEntity.setFirstName( studentDto.getFirstName() );
        studentEntity.setId( studentDto.getId() );
        studentEntity.setIndexNumber( studentDto.getIndexNumber() );
        studentEntity.setIndexYear( studentDto.getIndexYear() );
        studentEntity.setLastName( studentDto.getLastName() );

        return studentEntity;
    }

    protected ExaminationPeriodEntity examinationPeriodDtoToExaminationPeriodEntity(ExaminationPeriodDto examinationPeriodDto) {
        if ( examinationPeriodDto == null ) {
            return null;
        }

        ExaminationPeriodEntity examinationPeriodEntity = new ExaminationPeriodEntity();

        examinationPeriodEntity.setId( examinationPeriodDto.getId() );
        examinationPeriodEntity.setName( examinationPeriodDto.getName() );
        examinationPeriodEntity.setStarting_period( examinationPeriodDto.getStarting_period() );
        examinationPeriodEntity.setEnding_period( examinationPeriodDto.getEnding_period() );
        examinationPeriodEntity.setActive( examinationPeriodDto.isActive() );

        return examinationPeriodEntity;
    }

    protected SubjectEntity subjectDtoToSubjectEntity(SubjectDto subjectDto) {
        if ( subjectDto == null ) {
            return null;
        }

        SubjectEntity subjectEntity = new SubjectEntity();

        subjectEntity.setDescription( subjectDto.getDescription() );
        subjectEntity.setId( subjectDto.getId() );
        subjectEntity.setName( subjectDto.getName() );
        subjectEntity.setNoOfEsp( subjectDto.getNoOfEsp() );
        subjectEntity.setSemester( subjectDto.getSemester() );
        subjectEntity.setYearOfStudy( subjectDto.getYearOfStudy() );

        return subjectEntity;
    }

    protected List<SubjectEntity> subjectDtoListToSubjectEntityList(List<SubjectDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectEntity> list1 = new ArrayList<SubjectEntity>( list.size() );
        for ( SubjectDto subjectDto : list ) {
            list1.add( subjectDtoToSubjectEntity( subjectDto ) );
        }

        return list1;
    }

    protected TitleEntity titleDtoToTitleEntity(TitleDto titleDto) {
        if ( titleDto == null ) {
            return null;
        }

        TitleEntity titleEntity = new TitleEntity();

        titleEntity.setId( titleDto.getId() );
        titleEntity.setName( titleDto.getName() );

        return titleEntity;
    }

    protected ProfessorEntity professorDtoToProfessorEntity(ProfessorDto professorDto) {
        if ( professorDto == null ) {
            return null;
        }

        ProfessorEntity professorEntity = new ProfessorEntity();

        professorEntity.setAdress( professorDto.getAdress() );
        professorEntity.setCity( cityDtoToCityEntity( professorDto.getCity() ) );
        professorEntity.setEmail( professorDto.getEmail() );
        professorEntity.setFirstname( professorDto.getFirstname() );
        professorEntity.setId( professorDto.getId() );
        professorEntity.setLastname( professorDto.getLastname() );
        professorEntity.setPhone( professorDto.getPhone() );
        professorEntity.setReelection_date( professorDto.getReelection_date() );
        professorEntity.setSubjects( subjectDtoListToSubjectEntityList( professorDto.getSubjects() ) );
        professorEntity.setTitle( titleDtoToTitleEntity( professorDto.getTitle() ) );

        return professorEntity;
    }

    protected ExamEntity examDtoToExamEntity(ExamDto examDto) {
        if ( examDto == null ) {
            return null;
        }

        ExamEntity examEntity = new ExamEntity();

        examEntity.setExam_date( examDto.getExam_date() );
        examEntity.setExaminationPeriod( examinationPeriodDtoToExaminationPeriodEntity( examDto.getExaminationPeriod() ) );
        examEntity.setId( examDto.getId() );
        examEntity.setProfessor( professorDtoToProfessorEntity( examDto.getProfessor() ) );
        examEntity.setSubject( subjectDtoToSubjectEntity( examDto.getSubject() ) );

        return examEntity;
    }
}
