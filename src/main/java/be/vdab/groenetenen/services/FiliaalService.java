package be.vdab.groenetenen.services;

import java.util.List;

import be.vdab.groenetenen.entities.Filiaal;

public interface FiliaalService {
	public abstract List<Filiaal> findByPostcode(int van, int tot);
}
