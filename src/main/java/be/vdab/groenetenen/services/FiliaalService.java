package be.vdab.groenetenen.services;

import java.util.List;
import java.util.Optional;

import be.vdab.groenetenen.entities.Filiaal;

public interface FiliaalService {
	public abstract List<Filiaal> findByPostcode(int van, int tot);
	public abstract void delete(long id);
	public abstract void create(Filiaal filiaal);
	public abstract void update(Filiaal filiaal);
	public abstract Optional<Filiaal> findById(long id);
	public abstract List<Filiaal> findAll();
}
