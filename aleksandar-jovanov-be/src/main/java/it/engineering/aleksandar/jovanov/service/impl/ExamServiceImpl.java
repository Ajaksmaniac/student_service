package it.engineering.aleksandar.jovanov.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.aleksandar.jovanov.dto.ExamDto;
import it.engineering.aleksandar.jovanov.dto.ExaminationPeriodDto;
import it.engineering.aleksandar.jovanov.dto.StudentExamDto;
import it.engineering.aleksandar.jovanov.entity.CityEntity;
import it.engineering.aleksandar.jovanov.entity.ExamEntity;
import it.engineering.aleksandar.jovanov.entity.ExaminationPeriodEntity;
import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;
import it.engineering.aleksandar.jovanov.entity.StudentExamEntity;
import it.engineering.aleksandar.jovanov.exception.ExamAlreadyExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;
import it.engineering.aleksandar.jovanov.mapper.ExamMapper;
import it.engineering.aleksandar.jovanov.mapper.ExaminationPeriodMapper;
import it.engineering.aleksandar.jovanov.mapper.ProfessorMapper;
import it.engineering.aleksandar.jovanov.mapper.StudentExamMapper;
import it.engineering.aleksandar.jovanov.repository.ExamRepository;
import it.engineering.aleksandar.jovanov.repository.ExaminationPeriodRepository;
import it.engineering.aleksandar.jovanov.repository.ProfessorRepository;
import it.engineering.aleksandar.jovanov.repository.StudentExamRepository;
import it.engineering.aleksandar.jovanov.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService{

	private ExamRepository examRepository;
	private ExamMapper examMapper;
	
	private ExaminationPeriodRepository examinationPeriodRepository;
	private ExaminationPeriodMapper examinationPeriodMapper;
	
	private ProfessorRepository professorRepository;
	private ProfessorMapper professorMapper;
	@Autowired
	private StudentExamRepository studentExamRepository;
	@Autowired
	private StudentExamMapper studentExamMapper;
	
	
	
	

	@Autowired
	public ExamServiceImpl(ExamRepository examRepository, ExamMapper examMapper,
			ExaminationPeriodRepository examinationPeriodRepository, ExaminationPeriodMapper examinationPeriodMapper,
			ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
		super();
		this.examRepository = examRepository;
		this.examMapper = examMapper;
		this.examinationPeriodRepository = examinationPeriodRepository;
		this.examinationPeriodMapper = examinationPeriodMapper;
		this.professorRepository = professorRepository;
		this.professorMapper = professorMapper;
	}

	@Override
	public Optional<ExamDto> findById(Long id) {
		Optional<ExamEntity> exam = examRepository.findById(id);
		if (exam.isPresent()) {
			//professor.get().getSubjects().size();
			return Optional.of(examMapper.toDto(exam.get()));
		}
		
		
		return Optional.empty();
	}

	@Override
	public List<ExamDto> getAll() {
		 List<ExamEntity> entities = examRepository.findAll();
		 return entities.stream().map(entity->{
			 return examMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}

	@Override
	public ExamDto save(ExamDto dto) throws Exception {
		if(dto.getId() != null)throw new MyEntityExistException("Invalid Object!",dto);
		
		//Proveri da li postoji ispitni rok, ako postoji baci exception
		Optional<ExaminationPeriodEntity> examinationPeriodEntity= examinationPeriodRepository.findById(dto.getExaminationPeriod().getId());
		if(!examinationPeriodEntity.isPresent()) {
			throw new MyEntityNotPresentedException("Examination period with code "+ dto.getExaminationPeriod().getId()+" does not exist!");
		}
	
		//proveri da li je taj ispit vec u tom ispitnom roku, ako jeste baci exception
		if(examinationPeriodEntity.get().getExams().stream().filter(e->e.getId() == dto.getId()).collect(Collectors.toList()).size()>0) {
			throw new MyEntityExistException("Exam already exists in this examination period", dto);
		}
		//proveri da li je datum odrzavanja ispita u okviru ispitnog roka(Ako je pre ispitnog roka baci exception)
		//(ako je posle ispitnog roka baci exception)
//		LocalDate dt1 = LocalDate.parse(dto.getExam_date().toString());
//		LocalDate dt2 = LocalDate.parse(examinationPeriodEntity.get().getStarting_period().toString());
//		LocalDate dt3 = LocalDate.parse(examinationPeriodEntity.get().getEnding_period().toString());
		if(dto.getExam_date().before(examinationPeriodEntity.get().getStarting_period()) || 
				dto.getExam_date().after(examinationPeriodEntity.get().getEnding_period())) {
			throw new ExamAlreadyExistException("Exam is not between examination period dates");
		}
//		if(dt1.isBefore(dt2) || dt1.isAfter(dt3)) { 
//				
//			throw new ExamAlreadyExistException("Exam is not between examination period dates");
//		}
		
		
		//proveri da li profesor postoji
		Optional<ProfessorEntity> professor= professorRepository.findById(dto.getProfessor().getId());
		if(!professor.isPresent()) {
			throw new MyEntityNotPresentedException("Professor  with code "+ dto.getProfessor().getId()+" does not exist!");
		}
		//proveri da li profesor predaje ovaj predmet
		
		if(professor.get().getSubjects().stream().filter(e->e.getId() == dto.getSubject().getId()).collect(Collectors.toList()).size()<1) {
			throw new MyEntityNotPresentedException("Subject  with code "+ dto.getSubject().getId()+" Is not in the list od subjects for this professor");
		}
		//sacuvaj ispit
		ExamEntity exam = examRepository.save(examMapper.toEntity(dto));
	
		//ubaci ispit u listu ispita tog ispitnog roka
			
		examinationPeriodEntity.get().addExam(exam);
		examinationPeriodRepository.save(examinationPeriodEntity.get());
		//vrati dto ispita
		
		
		return examMapper.toDto(exam);
	}

	@Override
	public Optional<ExamDto> update(ExamDto dto) throws MyEntityNotPresentedException, MyEntityExistException, ExamAlreadyExistException {
		if(dto.getId() == null)throw new MyEntityExistException("Invalid Object!",dto);
		Optional<ExamEntity> entity= examRepository.findById(dto.getId());
		if (!entity.isPresent()) return Optional.empty();
		//Proveri da li postoji ispitni rok, ako postoji baci exception
		Optional<ExaminationPeriodEntity> examinationPeriodEntity= examinationPeriodRepository.findById(dto.getExaminationPeriod().getId());
		if(!examinationPeriodEntity.isPresent()) {
			throw new MyEntityNotPresentedException("Examination period with code "+ dto.getExaminationPeriod().getId()+" does not exist!");
		}
	
		if(entity.get().getSubject().getId() != dto.getSubject().getId()) {
			//proveri da li je taj ispit vec u tom ispitnom roku, ako jeste baci exception
			if(examinationPeriodEntity.get().getExams().stream().filter(e->e.getSubject().getId() == dto.getSubject().getId()).collect(Collectors.toList()).size()>0) {
				throw new MyEntityExistException("Exam already exists in this examination period", dto);
			}
		}
		
		//proveri da li je datum odrzavanja ispita u okviru ispitnog roka(Ako je pre ispitnog roka baci exception)
		//(ako je posle ispitnog roka baci exception)
		
		if(dto.getExam_date().before(examinationPeriodEntity.get().getStarting_period()) || 
				dto.getExam_date().after(examinationPeriodEntity.get().getEnding_period())) {
			throw new ExamAlreadyExistException("Exam is not between examination period dates");
		}
//		LocalDate dt1 = LocalDate.parse(dto.getExam_date().toString());
//		LocalDate dt2 = LocalDate.parse(examinationPeriodEntity.get().getStarting_period().toString());
//		LocalDate dt3 = LocalDate.parse(examinationPeriodEntity.get().getEnding_period().toString());
//		if(dt1.isBefore(dt2) || dt1.isAfter(dt3)) { 
//			
//			throw new ExamAlreadyExistException("Exam is not between examination period dates");
//		}
		
		//proveri da li profesor postoji
		Optional<ProfessorEntity> professor= professorRepository.findById(dto.getProfessor().getId());
		if(!professor.isPresent()) {
			throw new MyEntityNotPresentedException("Professor  with code "+ dto.getProfessor().getId()+" does not exist!");
		}
		//proveri da li profesor predaje ovaj predmet
		
		if(professor.get().getSubjects().stream().filter(e->e.getId() == dto.getSubject().getId()).collect(Collectors.toList()).size()<1) {
			throw new MyEntityNotPresentedException("Subject  with code "+ dto.getSubject().getId()+" Is not in the list od subjects for this professor");
		}
		//sacuvaj ispit
		ExamEntity exam = examRepository.save(examMapper.toEntity(dto));
	
		//ubaci ispit u listu ispita tog ispitnog roka
			
		examinationPeriodEntity.get().addExam(exam);
		examinationPeriodRepository.save(examinationPeriodEntity.get());
		//vrati dto ispita
		
		
		return Optional.of(examMapper.toDto(exam));
		
		
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<ExamEntity> entity= examRepository.findById(id);
		if(!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Exam  with code "+ id+" does not exist!");
		}
		
		//Izbrisi iz liste za trenutni ispitni rok
		Optional<ExaminationPeriodEntity> examinationPeriodEntity= examinationPeriodRepository.findById(entity.get().getExaminationPeriod().getId());
		List<ExamEntity> exams = examinationPeriodEntity.get().getExams().stream().filter(e->e.getId() != id).collect(Collectors.toList());
		examinationPeriodEntity.get().setExams(exams);
		examinationPeriodRepository.save(examinationPeriodEntity.get());
		//izbrisi exam
		examRepository.delete(entity.get());
		
	}

	@Override
	public Page<ExamDto> findByPage(Pageable pageable) {
		Page<ExamDto> entites = examRepository.findAll(pageable).map(examMapper::toDto);
		return entites;
	}
	@Override
	public Page<StudentExamDto> registeredExamsfindByPage(Pageable pageable) {
		Page<StudentExamDto> entites = studentExamRepository.findAll(pageable).map(studentExamMapper::toDto);
		return entites;
	}
	
	

	@Override
	public List<ExamDto> findByExaminationPeriod(Long id) {
		Optional<ExaminationPeriodEntity> period = examinationPeriodRepository.findById(id);
		 List<ExamEntity> entities = examRepository.findByExaminationPeriod(period.get());
		 return entities.stream().map(entity->{
			 return examMapper.toDto(entity);
		 }).collect(Collectors.toList());
	}

	@Override
	public ExamDto applyStudentForExam(StudentExamDto studentExamDto) throws Exception {
		ExaminationPeriodEntity period = examinationPeriodRepository.findActiveExamPeriod();
		if(period == null) throw new MyEntityNotPresentedException("There is not active examination periods");
		
		//Proveri da li je applied at 7 ili manje dana od pocetka ispitnog roka
		Long diff = getDifferenceDays(convertToDateViaInstant(studentExamDto.getAppliedAt()), period.getStarting_period());
		if(diff < 0 || diff > 7) throw new Exception("Exams can be registered only 7 days before Examination Period");
		if(studentExamDto.getExam().getId() == null) throw new Exception("Invalid Exam id");
		ExamEntity exam =examRepository.findById(studentExamDto.getExam().getId()).get();
		//proveri da li postoji ispit
		if(exam == null) throw new Exception("There is no exam with id: " +studentExamDto.getExam().getId());
		if(studentExamRepository.findByExamAndStudent(studentExamDto.getExam().getId(), studentExamDto.getStudent().getId() ).size() > 0)  throw new Exception("Exam already registered");
		//proveri da li je vec prijavljen ovaj ispit
		
		
		//sacuvaj
		System.out.print(studentExamDto+"ADS");
		StudentExamEntity entity = 	studentExamRepository.save(studentExamMapper.toEntity(studentExamDto));
		return studentExamMapper.toDto(entity).getExam();
		// TODO Auto-generated method stub
		
	}
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public Date convertToDateViaInstant(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}

	@Override
	public Optional<StudentExamDto> findStudentExamById(Long id) {
		Optional<StudentExamEntity> studentExam = studentExamRepository.findById(id);
		if (studentExam.isPresent()) {
			//professor.get().getSubjects().size();
			return Optional.of(studentExamMapper.toDto(studentExam.get()));
		}
		
		
		return Optional.empty();
	}

	@Override
	public StudentExamDto updateStudentExam(StudentExamDto studentExamDto) throws Exception {
		// TODO Auto-generated method stub
		if(studentExamDto.getId() == null) throw new Exception("Invalid Object");
		Optional<StudentExamEntity> studentExam = studentExamRepository.findById(studentExamDto.getId());
		if (!studentExam.isPresent()) throw new MyEntityNotPresentedException("Student exam with id" + studentExamDto.getId() + " doesnt exist");
		
		StudentExamEntity entity = studentExamRepository.save(studentExamMapper.toEntity(studentExamDto));
		return studentExamMapper.toDto(entity);
			//professor.get().getSubjects().size();
			
		
		
	}
	

}
