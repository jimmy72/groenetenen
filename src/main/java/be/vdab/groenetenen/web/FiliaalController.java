package be.vdab.groenetenen.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.groenetenen.entities.Filiaal;
import be.vdab.groenetenen.services.FiliaalService;

@Controller
@RequestMapping(path = "filialen", produces = MediaType.TEXT_HTML_VALUE)
class FiliaalController {
	
	private static final String VAN_TOT_POSCODE_VIEW = "filialen/vantotpostcode";
	private static final String FILIAAL_VIEW = "filialen/filiaal";
	private static final String REDIRECT_FILIAAL_NIET_GEVONDEN = "redirect:/";
	private final FiliaalService filiaalService;
	
	FiliaalController(FiliaalService filiaalService){
		this.filiaalService = filiaalService;
	}
	
	@GetMapping(value = "/vantotpostcode")
	ModelAndView vanTotPostcode() {
		return new ModelAndView(VAN_TOT_POSCODE_VIEW)
				.addObject("vanTotPostcodeForm", new VanTotPostcodeForm());
	}
	
	
	@GetMapping(params = { "van", "tot" })
	ModelAndView vanTotPostcode(@Valid VanTotPostcodeForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView(VAN_TOT_POSCODE_VIEW);
		}
		return new ModelAndView(VAN_TOT_POSCODE_VIEW)
				.addObject("filialen", filiaalService.findByPostcode(
						form.getVan(), form.getTot()));
	}
	
	
	@GetMapping(value = "{filiaal}") //filiaal is de naam van de var in de thymeleaf file
	ModelAndView read(@PathVariable Optional<Filiaal> filiaal, RedirectAttributes redirectAttributes) {
		if (filiaal.isPresent()) {
			return new ModelAndView(FILIAAL_VIEW).addObject(filiaal.get());
		}
		redirectAttributes.addAttribute("fout", "Filiaal niet gevonden");
		return new ModelAndView(REDIRECT_FILIAAL_NIET_GEVONDEN);
	}
}
