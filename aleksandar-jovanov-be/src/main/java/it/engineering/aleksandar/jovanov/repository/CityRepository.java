package it.engineering.aleksandar.jovanov.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.engineering.aleksandar.jovanov.entity.CityEntity;



@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long>{

}
