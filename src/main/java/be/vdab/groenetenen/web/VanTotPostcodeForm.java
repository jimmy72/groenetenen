package be.vdab.groenetenen.web;

import javax.validation.constraints.NotNull;

import be.vdab.groenetenen.constraints.Postcode;

public class VanTotPostcodeForm {

	@NotNull
	//@Range(min = 1000, max = 9999)
	@Postcode
	private Integer van;
	@NotNull
	//@Range(min = 1000, max = 9999)
	@Postcode
	private Integer tot;
	public Integer getVan() {
		return van;
	}
	public void setVan(Integer van) {
		this.van = van;
	}
	public Integer getTot() {
		return tot;
	}
	public void setTot(Integer tot) {
		this.tot = tot;
	}

}
