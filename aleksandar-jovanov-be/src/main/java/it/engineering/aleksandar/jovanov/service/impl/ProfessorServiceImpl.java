package it.engineering.aleksandar.jovanov.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.aleksandar.jovanov.dto.ProfessorDto;
import it.engineering.aleksandar.jovanov.dto.SubjectDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;
import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;
import it.engineering.aleksandar.jovanov.entity.SubjectEntity;
import it.engineering.aleksandar.jovanov.entity.TitleEntity;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.mapper.ProfessorMapper;
import it.engineering.aleksandar.jovanov.mapper.SubjectMapper;
import it.engineering.aleksandar.jovanov.repository.CityRepository;
import it.engineering.aleksandar.jovanov.repository.ProfessorRepository;
import it.engineering.aleksandar.jovanov.repository.SubjectRepository;
import it.engineering.aleksandar.jovanov.repository.TitleRepository;
import it.engineering.aleksandar.jovanov.service.ProfessorService;
@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService{
	
	@Autowired
	private ProfessorMapper professorMapper;
	
	@Autowired
	private SubjectMapper subjectMapper;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private TitleRepository titleRepository;
	
	
	
	
	
	@Override
	public Optional<ProfessorDto> findById(Long id) {
		Optional<ProfessorEntity> professor = professorRepository.findById(id);
		if (professor.isPresent()) {
			//professor.get().getSubjects().size();
			return Optional.of(professorMapper.toDto(professor.get()));
		}
		
		
		return Optional.empty();
	}

	@Override
	public List<ProfessorDto> getAll() {
		 List<ProfessorEntity> entities = professorRepository.findAll();
		 return entities.stream().map(entity->{
			 return professorMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}

	@Override
	public ProfessorDto save(ProfessorDto dto) throws Exception {
		if(dto.getId() != null)throw new MyEntityExistException("Invalid Object!",dto);
		Optional<CityEntity> cityEntity= cityRepository.findById(dto.getCity().getCityCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code "+ dto.getCity().getCityCode()+" does not exist!");
		}
		Optional<TitleEntity> titleEntity= titleRepository.findById(dto.getTitle().getId());
		if(!titleEntity.isPresent()) {
			throw new MyEntityNotPresentedException("Title with id "+dto.getTitle().getId()+" does not exist!");
		}
		//proveravanja da li je email ok
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		 Matcher matcher = pattern.matcher(dto.getEmail());
		 if(!matcher.matches()) throw new Exception("Invalid email ");
		
		
		ProfessorEntity professor = professorMapper.toEntity(dto);
		
		if(!dto.getSubjects().isEmpty()) {
			List<SubjectEntity> subjects = dto.getSubjects().stream().map(subject->{
				Optional<SubjectEntity> e = subjectRepository.findById(subject.getId());
				
				return e.get();
			}).collect(Collectors.toList());
			professor.setSubjects(subjects);
		}
		ProfessorEntity subject =  professorRepository.save(professor);
		return professorMapper.toDto(subject);
	}

	@Override
	public Optional<ProfessorDto> update(ProfessorDto dto) throws Exception {
		if(dto.getId() == null) throw new Exception("Invalid Object ");
		
		Optional<ProfessorEntity> entity= professorRepository.findById(dto.getId());
		if (entity.isPresent()) {
			Optional<CityEntity> cityEntity= cityRepository.findById(dto.getCity().getCityCode());
			if(!cityEntity.isPresent()) {
				throw new MyEntityNotPresentedException("City with code "+ dto.getCity().getCityCode()+" does not exist!");
			}
			Optional<TitleEntity> titleEntity= titleRepository.findById(dto.getTitle().getId());
			if(!titleEntity.isPresent()) {
				throw new MyEntityNotPresentedException("Title with id "+dto.getTitle().getId()+" does not exist!");
			}
			Pattern pattern = Pattern.compile("^(.+)@(.+)$");
			 Matcher matcher = pattern.matcher(dto.getEmail());
			 if(!matcher.matches()) throw new Exception("Invalid email ");
			
			
			ProfessorEntity professor = professorMapper.toEntity(dto);
			
			if(!dto.getSubjects().isEmpty()) {
				List<SubjectEntity> subjects = dto.getSubjects().stream().map(subject->{
					Optional<SubjectEntity> e = subjectRepository.findById(subject.getId());
					
					return e.get();
				}).collect(Collectors.toList());
				professor.setSubjects(subjects);
			}
			ProfessorEntity subject =  professorRepository.save(professor);
			return Optional.of(professorMapper.toDto(subject));
		}
		//ili baciti izuzetak: pogledati save() metodu
		return Optional.empty();
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<ProfessorEntity> entity= professorRepository.findById(id);
		if(!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Professor with id "+ id+" does not exist!");
		}
		
		professorRepository.delete(entity.get());
	
	}

	@Override
	public Page<ProfessorDto> findByPage(Pageable pageable) {
		Page<ProfessorDto> entites = professorRepository.findAll(pageable).map(professorMapper::toDto);
		return entites;
	}

}
