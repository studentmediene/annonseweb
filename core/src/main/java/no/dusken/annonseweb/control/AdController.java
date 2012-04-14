package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Ad;
import no.dusken.annonseweb.models.WebAd;
import no.dusken.annonseweb.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @RequestMapping(value = "/{ad}", method = RequestMethod.GET)
    public String viewAdHome(@ModelAttribute Ad ad, Model model){
        model.addAttribute("ad", ad);
        return "ad/home";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String viewNewAdGeneral(Model model){
        return viewAdHome(new WebAd(), model);           //Get type of Ad and initialize the correct one
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveNewAdGeneral(@Valid @ModelAttribute Ad ad){
        ad = adService.save(ad);
        return "redirect:/ad/" + ad.getId();
    }
}

