package be.vdab.groenetenen.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.groenetenen.entities.Filiaal;
import be.vdab.groenetenen.services.FiliaalService;

@Controller
@RequestMapping(path = "/filialen", produces = MediaType.TEXT_HTML_VALUE)
class FiliaalController {
	
	private static final String VAN_TOT_POSCODE_VIEW = "filialen/vantotpostcode";
	private static final String FILIAAL_VIEW = "filialen/filiaal";
	private static final String REDIRECT_FILIAAL_NIET_GEVONDEN = "redirect:/";
	private static final String PER_ID_VIEW = "filialen/perid";
	private static final String REDIRECT_NA_AFSCHRIJVEN = "redirect:/filialen/{id}";
	private final FiliaalService filiaalService;
	
	FiliaalController(FiliaalService filiaalService){
		this.filiaalService = filiaalService;
	}
	
	@GetMapping(path = "/vantotpostcode")
	ModelAndView vanTotPostcode() {
		return new ModelAndView(VAN_TOT_POSCODE_VIEW)
				.addObject("vanTotPostcodeForm", new VanTotPostcodeForm());
	}
	
	
	@GetMapping(params = {"van", "tot"})
	ModelAndView vanTotPostcode(@Valid VanTotPostcodeForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView(VAN_TOT_POSCODE_VIEW);
		}
		return new ModelAndView(VAN_TOT_POSCODE_VIEW)
				.addObject("filialen", filiaalService.findByPostcode(
						form.getVan(), form.getTot()));
	}
	
	//3.1.1 Automatische String naar Entity converters
//	@GetMapping(path = "/{id}") //filiaal is de naam van de var in de thymeleaf file vantotpostcode.html
//	ModelAndView read(@PathVariable(name = "id") Optional<Filiaal> filiaal, RedirectAttributes redirectAttributes) {
//		if (filiaal.isPresent()) {
//			return new ModelAndView(FILIAAL_VIEW).addObject(filiaal.get());
//		}
//		redirectAttributes.addAttribute("fout", "Filiaal niet gevonden");
//		return new ModelAndView(REDIRECT_FILIAAL_NIET_GEVONDEN);
//	}
	
	//Manuele read method die 
	@GetMapping(path = "/{id}") //filiaal is de naam van de var in de thymeleaf file vantotpostcode.html
	ModelAndView read(@PathVariable(name = "id") long filiaalId, RedirectAttributes redirectAttributes) {
		Optional<Filiaal> filiaal = this.filiaalService.findById(filiaalId);
		if (filiaal.isPresent()) {
			return new ModelAndView(FILIAAL_VIEW).addObject("filiaal", filiaal.get());
		}
		redirectAttributes.addAttribute("fout", "Filiaal niet gevonden");
		return new ModelAndView(REDIRECT_FILIAAL_NIET_GEVONDEN);
	}
	
	@GetMapping(path = "/perid")
	String findById() {
		return PER_ID_VIEW;
	}
	
	@PostMapping(path = "/{id}/afschrijven")
	String afschrijven(@PathVariable(name="id") long id, RedirectAttributes redirectAttributes ) {
		this.filiaalService.afschrijven(id);
		redirectAttributes.addAttribute("id", id);
		return REDIRECT_NA_AFSCHRIJVEN;
	}
	
}
