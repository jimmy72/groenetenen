package be.vdab.groenetenen.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import be.vdab.groenetenen.restclients.KoersClient;

@Service
class EuroServiceImpl implements EuroService{

	private final KoersClient client;
	
	EuroServiceImpl(KoersClient client){
		this.client = client;
	}
	
	@Override
	public BigDecimal naarDollar(BigDecimal euro) {
		return euro.multiply(client.getDollarKoers()).setScale(2, RoundingMode.HALF_UP);
	}

}
