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
public class AdController {

    @RequestMapping("/adHome")
    public String viewAdHome(){
        return "no/dusken/annonseweb/web/ads/adHome";
    }
}
