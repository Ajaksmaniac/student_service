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
import it.engineering.aleksandar.jovanov.dto.StudentDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;
import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;
import it.engineering.aleksandar.jovanov.entity.StudentEntity;
import it.engineering.aleksandar.jovanov.entity.StudentExamEntity;
import it.engineering.aleksandar.jovanov.entity.SubjectEntity;
import it.engineering.aleksandar.jovanov.entity.TitleEntity;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.mapper.StudentExamMapper;
import it.engineering.aleksandar.jovanov.mapper.StudentMapper;
import it.engineering.aleksandar.jovanov.repository.CityRepository;
import it.engineering.aleksandar.jovanov.repository.StudentRepository;
import it.engineering.aleksandar.jovanov.service.StudentService;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepository;
	private StudentMapper studentMapper;
	@Autowired
	private StudentExamMapper studentExamMapper;

	@Autowired
	private CityRepository cityRepository;
	
	
	
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
		super();
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
	}

	@Override
	public Optional<StudentDto> findById(Long id) {
		Optional<StudentEntity> students = studentRepository.findById(id);
		if (students.isPresent()) {
			//professor.get().getSubjects().size();
			return Optional.of(studentMapper.toDto(students.get()));
		}
		
		
		return Optional.empty();
	}

	@Override
	public List<StudentDto> getAll() {
		 List<StudentEntity> entities = studentRepository.findAll();
		 return entities.stream().map(entity->{
			 return studentMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}

	@Override
	public StudentDto save(StudentDto dto) throws MyEntityExistException, MyEntityNotPresentedException, Exception {
		// TODO Auto-generated method stub
		if(dto.getId() != null) throw new Exception("Invalid Object ");
		
		StudentEntity entity= studentRepository.FindByIndex(dto.getIndexNumber(),dto.getIndexYear());
		if(entity != null) {
			throw new MyEntityNotPresentedException("Student with index "+dto.getIndexNumber()+"/"+dto.getIndexYear()+" already exist!");
		}
		
		
		Optional<CityEntity> cityEntity= cityRepository.findById(dto.getCity().getCityCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code "+ dto.getCity().getCityCode()+" does not exist!");
		}
		
		//proveravanja da li je email ok
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		 Matcher matcher = pattern.matcher(dto.getEmail());
		 if(!matcher.matches()) throw new Exception("Invalid email ");
		 
		 StudentEntity student = studentMapper.toEntity(dto);
		 
		
		
		return studentMapper.toDto(studentRepository.save(student));
	}

	@Override
	public Optional<StudentDto> update(StudentDto dto)
			throws MyEntityNotPresentedException, MyEntityExistException, Exception {
		// TODO Auto-generated method stub
		
		if(dto.getId() == null) throw new Exception("Invalid Object ");
		Optional<StudentEntity> entity = studentRepository.findById(dto.getId());
		if (entity.isPresent()) {
			if(!dto.getIndexYear().equals(entity.get().getIndexYear())
					&& !dto.getIndexNumber().equals(entity.get().getIndexNumber())) {
				if(studentRepository.FindByIndex(dto.getIndexNumber(),dto.getIndexYear()) != null) {
					throw new MyEntityNotPresentedException("Student with index "+dto.getIndexNumber()+"/"+dto.getIndexYear()+" already exist!");
				}
			}
			
			
			
			Optional<CityEntity> cityEntity= cityRepository.findById(dto.getCity().getCityCode());
			if(!cityEntity.isPresent()) {
				throw new MyEntityNotPresentedException("City with code "+ dto.getCity().getCityCode()+" does not exist!");
			}
			
			//proveravanja da li je email ok
			Pattern pattern = Pattern.compile("^(.+)@(.+)$");
			 Matcher matcher = pattern.matcher(dto.getEmail());
			 if(!matcher.matches()) throw new Exception("Invalid email ");
			 
			 StudentEntity student = studentMapper.toEntity(dto);;
			 //UPDATE ISPITA
			  //ako lista prijavljenih ispita kod entiteta nije prazna (Radi se samo u slucaju kada student ima prijavljene ispite)
			 if(dto.getExams().size() > 0) {
				 // proveri listu prijavljenih ispita(proveri da li se podudaraju)
				 if(dto.getExams().stream().map(e->{
					 System.out.println(e);
					 return e.getId();
							 
				 }).collect(Collectors.toList()).equals(entity.get().getExams().stream().map(e->{
					 return e.getId();
				 }).collect(Collectors.toList()))){
					 System.out.println("Ispiti nisu menjani");
				 }else {
					 List<StudentExamEntity> exams = dto.getExams().stream().map(studentExamMapper::toEntity).collect(Collectors.toList());
					 student.setExams(exams);
				 }
			 }
			
			 
			 
			
			return Optional.of(studentMapper.toDto(studentRepository.save(student)));
		}
		//ili baciti izuzetak: pogledati save() metodu
		return Optional.empty();
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<StudentEntity> entity= studentRepository.findById(id);
		if(!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Student with id "+ id+" does not exist!");
		}
		
		studentRepository.delete(entity.get());
		
	}

	@Override
	public Page<StudentDto> findByPage(Pageable pageable) {
		Page<StudentDto> entites = studentRepository.findAll(pageable).map(studentMapper::toDto);
		return entites;
	}

	@Override
	public StudentDto findByIndex(Long indexYear, Long indexNumber) {
		StudentEntity entity= studentRepository.FindByIndex(indexNumber,indexYear);
		if(entity!= null) return studentMapper.toDto(entity);
		return null;
	}

}
