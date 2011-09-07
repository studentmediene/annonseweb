package no.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Created by IntelliJ IDEA.
 * User: kiro
 * Date: Nov 8, 2010
 * Time: 10:25:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BillController{

    @RequestMapping("/viewBills")
    public String viewBills(){
        return "no/dusken/annonseweb/web/viewBills";
    }

}
