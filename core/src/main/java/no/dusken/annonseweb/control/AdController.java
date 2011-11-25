package no.dusken.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ads")
public class AdController {

    @RequestMapping("/home")
    public String viewAdHome(){
        return "ads/home";
    }

    @RequestMapping("/new")
    public String viewNewAdGeneral(){
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
}

