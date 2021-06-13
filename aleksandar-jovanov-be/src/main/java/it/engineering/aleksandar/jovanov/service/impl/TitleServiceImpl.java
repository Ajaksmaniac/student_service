package it.engineering.aleksandar.jovanov.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.aleksandar.jovanov.dto.TitleDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;
import it.engineering.aleksandar.jovanov.entity.TitleEntity;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.mapper.TitleMapper;
import it.engineering.aleksandar.jovanov.repository.TitleRepository;
import it.engineering.aleksandar.jovanov.service.TitleService;
@Service
@Transactional
public class TitleServiceImpl implements TitleService {

	private TitleRepository titleRepository;
	private TitleMapper titleMapper;
	
	
	@Autowired
	public TitleServiceImpl(TitleRepository titleRepository, TitleMapper titleMapper) {
		super();
		this.titleRepository = titleRepository;
		this.titleMapper = titleMapper;
	}

	@Override
	public Optional<TitleDto> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TitleDto> getAll() {
		 List<TitleEntity> entities = titleRepository.findAll();
		 return entities.stream().map(entity->{
			 return titleMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}

	@Override
	public TitleDto save(TitleDto dto) throws MyEntityExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TitleDto> update(TitleDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		// TODO Auto-generated method stub
		
	}

}
