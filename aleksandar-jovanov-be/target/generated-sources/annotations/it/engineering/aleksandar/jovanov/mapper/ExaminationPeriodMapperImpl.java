package it.engineering.aleksandar.jovanov.mapper;

import it.engineering.aleksandar.jovanov.dto.ExamDto;
import it.engineering.aleksandar.jovanov.dto.ExaminationPeriodDto;
import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-13T18:50:40+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class ExaminationPeriodMapperImpl implements ExaminationPeriodMapper {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public ExaminationPeriodDto toDto(ExaminationPeriodEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ExaminationPeriodDto examinationPeriodDto = new ExaminationPeriodDto();

        examinationPeriodDto.setActive( entity.isActive() );
        examinationPeriodDto.setEnding_period( entity.getEnding_period() );
        examinationPeriodDto.setExams( examEntityListToExamDtoList( entity.getExams() ) );
        examinationPeriodDto.setId( entity.getId() );
        examinationPeriodDto.setName( entity.getName() );
        examinationPeriodDto.setStarting_period( entity.getStarting_period() );

        return examinationPeriodDto;
    }

    @Override
    public ExaminationPeriodEntity toEntity(ExaminationPeriodDto dto) {
        if ( dto == null ) {
            return null;
        }

        ExaminationPeriodEntity examinationPeriodEntity = new ExaminationPeriodEntity();

        examinationPeriodEntity.setActive( dto.isActive() );
        examinationPeriodEntity.setEnding_period( dto.getEnding_period() );
        examinationPeriodEntity.setExams( examDtoListToExamEntityList( dto.getExams() ) );
        examinationPeriodEntity.setId( dto.getId() );
        examinationPeriodEntity.setName( dto.getName() );
        examinationPeriodEntity.setStarting_period( dto.getStarting_period() );

        return examinationPeriodEntity;
    }

    protected List<ExamDto> examEntityListToExamDtoList(List<ExamEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<ExamDto> list1 = new ArrayList<ExamDto>( list.size() );
        for ( ExamEntity examEntity : list ) {
            list1.add( examMapper.toDto( examEntity ) );
        }

        return list1;
    }

    protected List<ExamEntity> examDtoListToExamEntityList(List<ExamDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ExamEntity> list1 = new ArrayList<ExamEntity>( list.size() );
        for ( ExamDto examDto : list ) {
            list1.add( examMapper.toEntity( examDto ) );
        }

        return list1;
    }
}
