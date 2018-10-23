package be.vdab.groenetenen.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.groenetenen.entities.Offerte;
import be.vdab.groenetenen.services.OfferteService;

@Controller
@RequestMapping(path = "/offertes")
@SessionAttributes("offerte")
class OfferteController {
	private static final String STAP_1_VIEW="offertes/stap1";
	private static final String STAP_2_VIEW = "offertes/stap2";
	private static final String OFFERTE_VIEW = "offertes/offerte";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/";
	
	private final OfferteService offerteService;
	
	public OfferteController(OfferteService offerteService) {
		this.offerteService = offerteService;
	}
	
	@GetMapping("/{id}")
	ModelAndView read(@PathVariable(name = "id") Offerte offerte) {
		return new ModelAndView(OFFERTE_VIEW).addObject("offerte", offerte);
	}
	
	@GetMapping(path = "/toevoegen")
	ModelAndView stap1() {
		return new ModelAndView(STAP_1_VIEW).addObject(new Offerte());
	}
		
	@PostMapping(path = "/toevoegen", params = "stap2")
	String stap1NaarStap2(@Validated(Offerte.Stap1.class) Offerte offerte, BindingResult bindingResult) {
		return bindingResult.hasErrors() ? STAP_1_VIEW : STAP_2_VIEW; 
	}
	
	@PostMapping(path = "/toevoegen", params = "stap1") 
	String stap2NaarStap1(Offerte offerte) { 
		return STAP_1_VIEW;
	}
	
	
	@PostMapping(path = "/toevoegen", params = "opslaan")
	String create(@Validated(Offerte.Stap2.class) Offerte offerte,
			BindingResult bindingResult, SessionStatus sessionStatus, HttpServletRequest request) { 
		if (bindingResult.hasErrors()) {
			return STAP_2_VIEW;
		}
		String offertesURL = request.getRequestURL().toString().replace("toevoegen", "");
		offerteService.create(offerte, offertesURL);
		sessionStatus.setComplete(); 
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
}
