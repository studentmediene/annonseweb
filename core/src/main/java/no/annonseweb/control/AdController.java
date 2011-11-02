package no.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Emilie Brunsgaard Ek
 * Email: embrek@underdusken.no
 * Date: 13.10.11
 * Time: 20:08
 */
@Controller
@RequestMapping("/ads")
public class AdController {

    @RequestMapping("/adHome")
    public String viewAdHome(){
        return "no/dusken/annonseweb/web/ads/adHome";
    }

    @RequestMapping("/newAdGeneral")
    public String viewNewAdGeneral(){
        return "no/dusken/annonseweb/web/ads/newAdGeneral";
    }

    @RequestMapping("/newAdDuskenPaper")
    public String viewNewAdDuskenPaper(){
        return "no/dusken/annonseweb/web/ads/newAdDuskenPaper";
    }

    @RequestMapping("/newAdDuskenNett")
    public String viewNewAdDuskenNett(){
        return "no/dusken/annonseweb/web/ads/newAdDuskenNett";
    }

    @RequestMapping("/newAdRadioRevolt")
    public String viewNewAdRadioRevolt(){
        return "no/dusken/annonseweb/web/ads/newAdRadioRevolt";
    }

    @RequestMapping("/newAdStudentTV")
    public String viewNewAdStudentTV(){
        return "no/dusken/annonseweb/web/ads/newAdStudentTV";
    }

    @RequestMapping("/viewActiveAds")
    public String viewActiveAds(){
        return "no/dusken/annonseweb/web/ads/viewActiveAds";
    }
}

