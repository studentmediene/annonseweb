package no.dusken.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ads")
public class AdController {

    @RequestMapping("/adHome")
    public String viewAdHome(){
        return "ads/adHome";
    }

    @RequestMapping("/newAdGeneral")
    public String viewNewAdGeneral(){
        return "ads/newAdGeneral";
    }

    @RequestMapping("/newAdDuskenPaper")
    public String viewNewAdDuskenPaper(){
        return "ads/newAdDuskenPaper";
    }

    @RequestMapping("/newAdDuskenNett")
    public String viewNewAdDuskenNett(){
        return "ads/newAdDuskenNett";
    }

    @RequestMapping("/newAdRadioRevolt")
    public String viewNewAdRadioRevolt(){
        return "ads/newAdRadioRevolt";
    }

    @RequestMapping("/newAdStudentTV")
    public String viewNewAdStudentTV(){
        return "ads/newAdStudentTV";
    }

    @RequestMapping("/viewActiveAds.d")
    public String viewActiveAds(){
        return "ads/viewActiveAds";
    }
}

