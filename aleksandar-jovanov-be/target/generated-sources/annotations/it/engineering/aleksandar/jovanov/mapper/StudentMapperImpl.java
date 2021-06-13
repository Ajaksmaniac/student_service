package it.engineering.aleksandar.jovanov.mapper;

import it.engineering.aleksandar.jovanov.dto.StudentDto;
import it.engineering.aleksandar.jovanov.dto.StudentExamDto;
import it.engineering.aleksandar.jovanov.entity.StudentEntity;
import it.engineering.aleksandar.jovanov.entity.StudentExamEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-04T16:40:25+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private StudentExamMapper studentExamMapper;

    @Override
    public StudentDto toDto(StudentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        studentDto.setIndexYear( entity.getIndexYear() );
        studentDto.setIndexNumber( entity.getIndexNumber() );
        studentDto.setId( entity.getId() );
        studentDto.setFirstName( entity.getFirstName() );
        studentDto.setLastName( entity.getLastName() );
        studentDto.setEmail( entity.getEmail() );
        studentDto.setAdress( entity.getAdress() );
        studentDto.setCity( cityMapper.toDto( entity.getCity() ) );
        studentDto.setCurrentYearOfStudy( entity.getCurrentYearOfStudy() );
        studentDto.setExams( studentExamEntityListToStudentExamDtoList( entity.getExams() ) );

        return studentDto;
    }

    @Override
    public StudentEntity toEntity(StudentDto dto) {
        if ( dto == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setId( dto.getId() );
        studentEntity.setFirstName( dto.getFirstName() );
        studentEntity.setLastName( dto.getLastName() );
        studentEntity.setEmail( dto.getEmail() );
        studentEntity.setAdress( dto.getAdress() );
        studentEntity.setCity( cityMapper.toEntity( dto.getCity() ) );
        studentEntity.setCurrentYearOfStudy( dto.getCurrentYearOfStudy() );
        studentEntity.setExams( studentExamDtoListToStudentExamEntityList( dto.getExams() ) );
        studentEntity.setIndexYear( dto.getIndexYear() );
        studentEntity.setIndexNumber( dto.getIndexNumber() );

        return studentEntity;
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
