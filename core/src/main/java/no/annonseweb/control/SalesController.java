package no.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Created by IntelliJ IDEA.
 * User: kiro
 * Date: Nov 8, 2010
 * Time: 10:24:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SalesController{

    @RequestMapping("/viewSales")
    public String viewSales(){
        return "no/dusken/annonseweb/web/sales/viewSales";
    }


    @RequestMapping("/saleHome")
    public String viewSaleHome(){
        return "no/dusken/annonseweb/web/sales/saleHome";
    }

    /*
    map.put(Sales, salesList);
    return new ModelAndView(ARTICLEVIEW, map);
    */
}
