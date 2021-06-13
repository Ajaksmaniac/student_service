package it.engineering.aleksandar.jovanov.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.aleksandar.jovanov.dto.CityDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.mapper.CityMapper;
import it.engineering.aleksandar.jovanov.repository.CityRepository;
import it.engineering.aleksandar.jovanov.service.CityService;


@Service
@Transactional
public class CityServiceImpl implements CityService {
	private CityRepository cityRepository;
	private CityMapper cityMapper;
	
	@Autowired
	public CityServiceImpl(CityRepository cityRepository,CityMapper cityMapper ) {
		this.cityRepository = cityRepository;
		this.cityMapper = cityMapper;
	}
	
	public Optional<CityDto> findById(Long cityCode) {
		Optional<CityEntity> city = cityRepository.findById(cityCode);
		if (city.isPresent()) {
			return Optional.of(cityMapper.toDto(city.get()));
		}
		return Optional.empty();
	}
	
	public List<CityDto> getAll(){
		 List<CityEntity> entities = cityRepository.findAll();
		 return entities.stream().map(entity->{
			 return cityMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}
	
	public CityDto save(CityDto cityDto) throws MyEntityExistException {
		Optional<CityEntity> entity= cityRepository.findById(cityDto.getCityCode());
		if(entity.isPresent()) {
			throw new MyEntityExistException("City already exists!", cityMapper.toDto(entity.get()));
		}
		
		CityEntity city =  cityRepository.save(cityMapper.toEntity(cityDto));
		return cityMapper.toDto(city);
	}
	
	public Optional<CityDto> update(CityDto cityDto) {
		Optional<CityEntity> entity= cityRepository.findById(cityDto.getCityCode());
		if (entity.isPresent()) {
			CityEntity cityEntity = cityRepository.save(cityMapper.toEntity(cityDto));
			return Optional.of(cityMapper.toDto(cityEntity));
		}
		//ili baciti izuzetak: pogledati save() metodu
		return Optional.empty();
	}
	
	public void delete(Long cityCode) throws MyEntityNotPresentedException{
		Optional<CityEntity> entity= cityRepository.findById(cityCode);
		if(!entity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code "+ cityCode+" does not exist!");
		}
		cityRepository.delete(entity.get());
	}
	
}
