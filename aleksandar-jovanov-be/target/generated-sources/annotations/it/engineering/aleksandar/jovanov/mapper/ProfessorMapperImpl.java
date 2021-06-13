package it.engineering.aleksandar.jovanov.mapper;

import it.engineering.aleksandar.jovanov.dto.ProfessorDto;
import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.dto.TitleDto;
import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;
import it.engineering.aleksandar.jovanov.entity.SubjectEntity;
import it.engineering.aleksandar.jovanov.entity.TitleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-13T19:34:39+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class ProfessorMapperImpl implements ProfessorMapper {

    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public ProfessorDto toDto(ProfessorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ProfessorDto professorDto = new ProfessorDto();

        professorDto.setAdress( entity.getAdress() );
        professorDto.setCity( cityMapper.toDto( entity.getCity() ) );
        professorDto.setEmail( entity.getEmail() );
        professorDto.setFirstname( entity.getFirstname() );
        professorDto.setId( entity.getId() );
        professorDto.setLastname( entity.getLastname() );
        professorDto.setPhone( entity.getPhone() );
        professorDto.setReelection_date( entity.getReelection_date() );
        professorDto.setSubjects( subjectEntityListToSubjectDtoList( entity.getSubjects() ) );
        professorDto.setTitle( titleEntityToTitleDto( entity.getTitle() ) );

        return professorDto;
    }

    @Override
    public ProfessorEntity toEntity(ProfessorDto dto) {
        if ( dto == null ) {
            return null;
        }

        ProfessorEntity professorEntity = new ProfessorEntity();

        professorEntity.setAdress( dto.getAdress() );
        professorEntity.setCity( cityMapper.toEntity( dto.getCity() ) );
        professorEntity.setEmail( dto.getEmail() );
        professorEntity.setFirstname( dto.getFirstname() );
        professorEntity.setId( dto.getId() );
        professorEntity.setLastname( dto.getLastname() );
        professorEntity.setPhone( dto.getPhone() );
        professorEntity.setReelection_date( dto.getReelection_date() );
        professorEntity.setSubjects( subjectDtoListToSubjectEntityList( dto.getSubjects() ) );
        professorEntity.setTitle( titleDtoToTitleEntity( dto.getTitle() ) );

        return professorEntity;
    }

    protected List<SubjectDto> subjectEntityListToSubjectDtoList(List<SubjectEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectDto> list1 = new ArrayList<SubjectDto>( list.size() );
        for ( SubjectEntity subjectEntity : list ) {
            list1.add( subjectMapper.toDto( subjectEntity ) );
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

    protected List<SubjectEntity> subjectDtoListToSubjectEntityList(List<SubjectDto> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectEntity> list1 = new ArrayList<SubjectEntity>( list.size() );
        for ( SubjectDto subjectDto : list ) {
            list1.add( subjectMapper.toEntity( subjectDto ) );
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
}
