package it.engineering.aleksandar.jovanov.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.aleksandar.jovanov.dto.ExaminationPeriodDto;

import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.mapper.ExaminationPeriodMapper;
import it.engineering.aleksandar.jovanov.repository.ExaminationPeriodRepository;
import it.engineering.aleksandar.jovanov.service.ExaminationPeriodService;
@Service
@Transactional
public class ExaminationPeriodServiceImpl implements ExaminationPeriodService {

	private ExaminationPeriodRepository examinationPeriodRepository;
	private ExaminationPeriodMapper examinationPeriodMapper;
	
	
	@Autowired
	public ExaminationPeriodServiceImpl(ExaminationPeriodRepository examinationPeriodRepository,
			ExaminationPeriodMapper examinationPeriodMapper) {
		super();
		this.examinationPeriodRepository = examinationPeriodRepository;
		this.examinationPeriodMapper = examinationPeriodMapper;
	}

	@Override
	public Optional<ExaminationPeriodDto> findById(Long id) {
		Optional<ExaminationPeriodEntity> period = examinationPeriodRepository.findById(id);
		if (period.isPresent()) {
			return Optional.of(examinationPeriodMapper.toDto(period.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ExaminationPeriodDto> getAll() {
		 List<ExaminationPeriodEntity> periods = examinationPeriodRepository.findAll();
		 return periods.stream().map(entity->{
			 return examinationPeriodMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}

	@Override
	public ExaminationPeriodDto save(ExaminationPeriodDto dto)
			throws Exception {
		
		if(dto.getId() != null)throw new MyEntityExistException("Invalid Object!",dto);
		
//		LocalDate dt1 = LocalDate.parse(dto.getStarting_period().toString());
//		LocalDate dt2 = LocalDate.parse(dto.getEnding_period().toString());
		if(dto.getStarting_period().after(dto.getEnding_period()))throw new Exception("Starting period cant be after ending period!");
//		if(dt1.isAfter(dt2))throw new Exception("Starting period cant be after ending period!");
//		if(dto.isActive() == true) examinationPeriodRepository.setAllToInActive();
		if(examinationPeriodRepository.getByDatesBetween(dto.getStarting_period(), dto.getEnding_period()).isEmpty() == false) throw new Exception("Period dates are overlaping!");
		ExaminationPeriodEntity period =  examinationPeriodRepository.save(examinationPeriodMapper.toEntity(dto));
		return examinationPeriodMapper.toDto(period);
	}

	@Override
	public Optional<ExaminationPeriodDto> update(ExaminationPeriodDto dto)
			throws MyEntityNotPresentedException, MyEntityExistException, Exception {
		if(dto.getId() == null)throw new MyEntityExistException("Invalid Object!",dto);
		Optional<ExaminationPeriodEntity> entity= examinationPeriodRepository.findById(dto.getId());
		if (entity.isPresent()) {
			//if(dto.getId() != null)throw new MyEntityExistException("Invalid Object!",dto);
//			LocalDate dt1 = LocalDate.parse(dto.getStarting_period().toString());
//			LocalDate dt2 = LocalDate.parse(dto.getEnding_period().toString());
			if(dto.getStarting_period().after(dto.getEnding_period()))throw new Exception("Starting period cant be after ending period!");
//			if(dt1.isAfter(dt2))throw new Exception("Starting period cant be after ending period!");
			//if(!dto.getStarting_period().equals(entity.get().getStarting_period()) && dto)
			if(examinationPeriodRepository.getByDatesBetween(dto.getStarting_period(), dto.getEnding_period(),dto.getId()).isEmpty() == false) throw new Exception("Period dates are overlaping!");
			if(dto.isActive() == true) examinationPeriodRepository.setAllToInActive();
			
			
			ExaminationPeriodEntity period =  examinationPeriodRepository.save(examinationPeriodMapper.toEntity(dto));
			return Optional.of(examinationPeriodMapper.toDto(period));
		}
		//ili baciti izuzetak: pogledati save() metodu
		return Optional.empty();
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<ExaminationPeriodEntity> entity= examinationPeriodRepository.findById(id);
		if(!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Exam period with code "+ id+" does not exist!");
		}
		examinationPeriodRepository.delete(entity.get());
		
	}

	@Override
	public Page<ExaminationPeriodDto> findByPage(Pageable pageable) {
		Page<ExaminationPeriodDto> entites = examinationPeriodRepository.findAll(pageable).map(examinationPeriodMapper::toDto);
		return entites;
	}

	@Override
	public ExaminationPeriodDto findActiveExaminationPeriod() {
		ExaminationPeriodEntity period = examinationPeriodRepository.findActiveExamPeriod();
		
		return examinationPeriodMapper.toDto(period);
	}

}
