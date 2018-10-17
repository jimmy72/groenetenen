package be.vdab.groenetenen.restservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.groenetenen.services.FiliaalService;

@RestController
@RequestMapping("/filialen")
class FiliaalRestController {

	private final FiliaalService filiaalService;

	FiliaalRestController(FiliaalService filiaalService) {
		this.filiaalService = filiaalService;
	}
	
	
	
	
}
