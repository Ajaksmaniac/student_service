package it.engineering.aleksandar.jovanov.mapper;

import org.mapstruct.Mapper;


import it.engineering.aleksandar.jovanov.dto.TitleDto;

import it.engineering.aleksandar.jovanov.entity.TitleEntity;

@Mapper(componentModel = "spring")
public interface TitleMapper {

	TitleDto toDto(TitleEntity entity);
	TitleEntity toEntity(TitleDto dto);
	
}
