package it.engineering.aleksandar.jovanov.mapper;

import it.engineering.aleksandar.jovanov.dto.CityDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-04T16:40:25+0200",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class CityMapperImpl implements CityMapper {

    @Override
    public CityDto toDto(CityEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CityDto cityDto = new CityDto();

        cityDto.setCityName( entity.getName() );
        cityDto.setCityCode( entity.getCode() );

        return cityDto;
    }

    @Override
    public CityEntity toEntity(CityDto dto) {
        if ( dto == null ) {
            return null;
        }

        CityEntity cityEntity = new CityEntity();

        cityEntity.setName( dto.getCityName() );
        cityEntity.setCode( dto.getCityCode() );

        return cityEntity;
    }
}
