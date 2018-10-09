package be.vdab.groenetenen.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.groenetenen.services.FiliaalService;

@Controller
@RequestMapping(value = "filialen")
class FiliaalController {
	
	private static final String VAN_TOT_POSCODE_VIEW = "filialen/vantotpostcode";
	private final FiliaalService filiaalService;
	
	FiliaalController(FiliaalService filiaalService){
		this.filiaalService = filiaalService;
	}
	
	@GetMapping("vantotpostcode")
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
}
