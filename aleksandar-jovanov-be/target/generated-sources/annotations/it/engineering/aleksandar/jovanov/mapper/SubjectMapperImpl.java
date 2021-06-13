package it.engineering.aleksandar.jovanov.mapper;

import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.entity.SubjectEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-04T16:40:25+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public SubjectDto toDto(SubjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SubjectDto subjectDto = new SubjectDto();

        subjectDto.setId( entity.getId() );
        subjectDto.setName( entity.getName() );
        subjectDto.setDescription( entity.getDescription() );
        subjectDto.setNoOfEsp( entity.getNoOfEsp() );
        subjectDto.setYearOfStudy( entity.getYearOfStudy() );
        subjectDto.setSemester( entity.getSemester() );

        return subjectDto;
    }

    @Override
    public SubjectEntity toEntity(SubjectDto dto) {
        if ( dto == null ) {
            return null;
        }

        SubjectEntity subjectEntity = new SubjectEntity();

        subjectEntity.setId( dto.getId() );
        subjectEntity.setName( dto.getName() );
        subjectEntity.setDescription( dto.getDescription() );
        subjectEntity.setNoOfEsp( dto.getNoOfEsp() );
        subjectEntity.setYearOfStudy( dto.getYearOfStudy() );
        subjectEntity.setSemester( dto.getSemester() );

        return subjectEntity;
    }
}
