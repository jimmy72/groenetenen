package be.vdab.groenetenen.services;

import java.util.Optional;

import be.vdab.groenetenen.entities.Offerte;

public interface OfferteService {
	public abstract void create(Offerte offerte, String offertesURL);
	public abstract Optional<Offerte> read(long id);
	public abstract void aantalOffertesMail();
}
