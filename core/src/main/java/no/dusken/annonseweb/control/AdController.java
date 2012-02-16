package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Ad;
import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.AdService;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ads")
public class AdController {

    @Autowired
    private AdService adService;

    @RequestMapping()
    public String viewAdHome(){
        return "ads/home";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String viewNewAdGeneral(){
        return "ads/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveNewAdGeneral(@ModelAttribute Ad ad){
        Ad save = adService.save(ad);
        return "ads/new";
    }

    @RequestMapping("/overview")
    public String overview(){
        return "ads/overview";
    }

    @RequestMapping("/all")
    public String viewActiveAds(){
        return "ads/all";
    }

    @RequestMapping("/search")
    public String searchAd(){
        return "ads/search";
    }

     @InitBinder
    public void initbinder(WebDataBinder binder){
        binder.registerCustomEditor(Sale.class, new BindByIdEditor(adService));
    }
}

