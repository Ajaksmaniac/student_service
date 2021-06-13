package it.engineering.aleksandar.jovanov.service;

import java.util.List;
import java.util.Optional;

import it.engineering.aleksandar.jovanov.dto.CityDto;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;


public interface CityService {
	Optional<CityDto> findById(Long cityCode);
	List<CityDto> getAll();
	CityDto save(CityDto cityDto) throws MyEntityExistException;
	Optional<CityDto> update(CityDto cityDto);
	void delete(Long cityCode) throws MyEntityNotPresentedException;
}
