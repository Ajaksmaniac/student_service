package it.engineering.aleksandar.jovanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.engineering.aleksandar.jovanov.entity.ProfessorEntity;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {

}
