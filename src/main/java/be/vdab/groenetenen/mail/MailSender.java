package be.vdab.groenetenen.mail;

import be.vdab.groenetenen.entities.Offerte;

public interface MailSender {
	public abstract void nieuweOfferte(Offerte offerte);
	public abstract void aantalOffertesMail(long aantal);
}
