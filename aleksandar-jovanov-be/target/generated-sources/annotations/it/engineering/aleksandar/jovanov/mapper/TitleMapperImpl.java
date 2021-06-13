package it.engineering.aleksandar.jovanov.mapper;

import it.engineering.aleksandar.jovanov.dto.TitleDto;
import it.engineering.aleksandar.jovanov.entity.TitleEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-04T16:40:25+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class TitleMapperImpl implements TitleMapper {

    @Override
    public TitleDto toDto(TitleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TitleDto titleDto = new TitleDto();

        titleDto.setId( entity.getId() );
        titleDto.setName( entity.getName() );

        return titleDto;
    }

    @Override
    public TitleEntity toEntity(TitleDto dto) {
        if ( dto == null ) {
            return null;
        }

        TitleEntity titleEntity = new TitleEntity();

        titleEntity.setId( dto.getId() );
        titleEntity.setName( dto.getName() );

        return titleEntity;
    }
}
