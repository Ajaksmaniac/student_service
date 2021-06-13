package it.engineering.aleksandar.jovanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.engineering.aleksandar.jovanov.entity.TitleEntity;

public interface TitleRepository extends JpaRepository<TitleEntity, Long>{

}
