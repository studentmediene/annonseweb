package no.dusken.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Emilie Brunsgaard Ek
 * Email: embrek@underdusken.no
 * Date: 13.10.11
 * Time: 20:13
 */
@Controller
public class StatisticsController {

    @RequestMapping("/statisticsHome")
    public String viewStatisticsHome(){
        return "statistics/statisticsHome";
    }
}
