package it.engineering.aleksandar.jovanov.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;
import it.engineering.aleksandar.jovanov.entity.SubjectEntity;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.mapper.SubjectMapper;
import it.engineering.aleksandar.jovanov.repository.SubjectRepository;
import it.engineering.aleksandar.jovanov.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	private SubjectRepository subjectRepository;
	private SubjectMapper subjectMapper;
	
	
	
	@Autowired
	public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
	
		this.subjectRepository = subjectRepository;
		this.subjectMapper = subjectMapper;
	}

	@Override
	public Optional<SubjectDto> findById(Long id) {
		Optional<SubjectEntity> subject = subjectRepository.findById(id);
		if (subject.isPresent()) {
			return Optional.of(subjectMapper.toDto(subject.get()));
		}
		return Optional.empty();
		
	}

	@Override
	public List<SubjectDto> getAll() {
		 List<SubjectEntity> entities = subjectRepository.findAll();
		 return entities.stream().map(entity->{
			 return subjectMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}

	@Override
	public SubjectDto save(SubjectDto dto) throws MyEntityExistException {
//		Optional<SubjectEntity> entity= subjectRepository.findById(dto.getId());
//		if(entity.isPresent()) {
//			throw new MyEntityExistException("Subject already exists!", subjectMapper.toDto(entity.get()));
//		}
		if(dto.getId() != null)throw new MyEntityExistException("Invalid Object!",dto);
		SubjectEntity subject =  subjectRepository.save(subjectMapper.toEntity(dto));
		return subjectMapper.toDto(subject);
	}

	@Override
	public Optional<SubjectDto> update(SubjectDto dto) {
		Optional<SubjectEntity> entity= subjectRepository.findById(dto.getId());
		if (entity.isPresent()) {
			SubjectEntity subjectEntity = subjectRepository.save(subjectMapper.toEntity(dto));
			return Optional.of(subjectMapper.toDto(subjectEntity));
		}
		//ili baciti izuzetak: pogledati save() metodu
		return Optional.empty();
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<SubjectEntity> entity= subjectRepository.findById(id);
		if(!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Subject with id "+ id+" does not exist!");
		}
		subjectRepository.delete(entity.get());
		
	}

	@Override
	public Page<SubjectDto> findByPage(Pageable pageable) {
		Page<SubjectDto> entites = subjectRepository.findAll(pageable).map(subjectMapper::toDto);
		return entites;
	}

}
