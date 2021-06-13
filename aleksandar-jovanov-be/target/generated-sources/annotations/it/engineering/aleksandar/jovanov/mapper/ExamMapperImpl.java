package it.engineering.aleksandar.jovanov.mapper;

import it.engineering.aleksandar.jovanov.dto.CityDto;
import it.engineering.aleksandar.jovanov.dto.ExamDto;
import it.engineering.aleksandar.jovanov.dto.ExaminationPeriodDto;
import it.engineering.aleksandar.jovanov.dto.ProfessorDto;
import it.engineering.aleksandar.jovanov.dto.StudentExamDto;
import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.dto.TitleDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;
import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;
import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;
import it.engineering.aleksandar.jovanov.entity.StudentExamEntity;
import it.engineering.aleksandar.jovanov.entity.SubjectEntity;
import it.engineering.aleksandar.jovanov.entity.TitleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-13T18:16:34+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class ExamMapperImpl implements ExamMapper {

    @Autowired
    private StudentExamMapper studentExamMapper;

    @Override
    public ExamDto toDto(ExamEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ExamDto examDto = new ExamDto();

        examDto.setExam_date( entity.getExam_date() );
        examDto.setExaminationPeriod( examinationPeriodEntityToExaminationPeriodDto( entity.getExaminationPeriod() ) );
        examDto.setId( entity.getId() );
        examDto.setProfessor( professorEntityToProfessorDto( entity.getProfessor() ) );
        examDto.setStudents( studentExamEntityListToStudentExamDtoList( entity.getStudents() ) );
        examDto.setSubject( subjectEntityToSubjectDto( entity.getSubject() ) );

        return examDto;
    }

    @Override
    public ExamEntity toEntity(ExamDto dto) {
        if ( dto == null ) {
            return null;
        }

        ExamEntity examEntity = new ExamEntity();

        examEntity.setExam_date( dto.getExam_date() );
        examEntity.setExaminationPeriod( examinationPeriodDtoToExaminationPeriodEntity( dto.getExaminationPeriod() ) );
        examEntity.setId( dto.getId() );
        examEntity.setProfessor( professorDtoToProfessorEntity( dto.getProfessor() ) );
        examEntity.setStudents( studentExamDtoListToStudentExamEntityList( dto.getStudents() ) );
        examEntity.setSubject( subjectDtoToSubjectEntity( dto.getSubject() ) );

        return examEntity;
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

    protected CityDto cityEntityToCityDto(CityEntity cityEntity) {
        if ( cityEntity == null ) {
            return null;
        }

        CityDto cityDto = new CityDto();

        return cityDto;
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

    protected List<StudentExamDto> studentExamEntityListToStudentExamDtoList(List<StudentExamEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentExamDto> list1 = new ArrayList<StudentExamDto>( list.size() );
        for ( StudentExamEntity studentExamEntity : list ) {
            list1.add( studentExamMapper.toDto( studentExamEntity ) );
        }

        return list1;
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

    protected CityEntity cityDtoToCityEntity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        CityEntity cityEntity = new CityEntity();

        return cityEntity;
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

    protected List<StudentExamEntity> studentExamDtoListToStudentExamEntityList(List<StudentExamDto> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentExamEntity> list1 = new ArrayList<StudentExamEntity>( list.size() );
        for ( StudentExamDto studentExamDto : list ) {
            list1.add( studentExamMapper.toEntity( studentExamDto ) );
        }

        return list1;
    }
}
