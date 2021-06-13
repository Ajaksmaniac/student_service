package it.engineering.aleksandar.jovanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.engineering.aleksandar.jovanov.entity.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

}
