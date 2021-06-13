package it.engineering.aleksandar.jovanov.service;

import java.util.List;
import java.util.Optional;



import it.engineering.aleksandar.jovanov.dto.TitleDto;
import it.engineering.aleksandar.jovanov.exception.MyEntityExistException;
import it.engineering.aleksandar.jovanov.exception.MyEntityNotPresentedException;

public interface TitleService {
	Optional<TitleDto> findById(Long id);
	List<TitleDto> getAll();
	TitleDto save(TitleDto dto) throws MyEntityExistException;
	Optional<TitleDto> update(TitleDto dto);
	void delete(Long id) throws MyEntityNotPresentedException;

}
