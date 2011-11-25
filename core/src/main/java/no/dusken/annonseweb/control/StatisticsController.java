package no.dusken.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Emilie Brunsgaard Ek
 * embrek@underdusken.no
 * Date: 13.10.11
 */

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @RequestMapping("/home")
    public String viewStatisticsHome(){
        return "statistics/Home";
    }
}
