package be.vdab.groenetenen.messaging;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import be.vdab.groenetenen.entities.Offerte;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferteEnOffertesURL implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Offerte offerte;
	private String offertesURL;
	
	protected OfferteEnOffertesURL() {}

	public OfferteEnOffertesURL(Offerte offerte, String offertesURL) {
		this.offerte = offerte;
		this.offertesURL = offertesURL;
	}

	public Offerte getOfferte() {
		return offerte;
	}

	public String getOffertesURL() {
		return offertesURL;
	}
	
}
