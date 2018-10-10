package be.vdab.groenetenen.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.groenetenen.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
	@Override 
	@EntityGraph(Werknemer.MET_FILIAAL) 
	List<Werknemer> findAll(Sort sort);
}
