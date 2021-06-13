package it.engineering.aleksandar.jovanov.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import it.engineering.aleksandar.jovanov.dto.CityDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;

@Mapper(componentModel = "spring")
public interface CityMapper {
	@Mappings({
	      @Mapping(target="cityCode", source="entity.code"),
	      @Mapping(target="cityName", source="entity.name")
	    })
	CityDto toDto(CityEntity entity);
	
	  @Mappings({
	      @Mapping(target="code", source="dto.cityCode"),
	      @Mapping(target="name", source="dto.cityName")
	    })
	CityEntity toEntity(CityDto dto);
}
