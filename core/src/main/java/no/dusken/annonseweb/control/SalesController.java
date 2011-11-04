package no.dusken.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: kiro
 * Date: Nov 8, 2010
 * Time: 10:24:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/sales")
public class SalesController{

    @RequestMapping("/saleHome")
    public String viewSaleHome(){
        return "sales/saleHome";
    }

    @RequestMapping("/searchSale")
    public String viewSearchSales(){
        return "sales/searchSale";
    }


    @RequestMapping("/newSale")
    public String viewNewSales(){
        return "sales/newSale";
    }

    @RequestMapping("/viewSales")
    public String viewSales(){
        return "sales/viewSales";
    }


    @RequestMapping("/editSales")
    public String viewEditSales(){
        return "sales/editSales";
    }

    /*
    map.put(Sales, salesList);
    return new ModelAndView(ARTICLEVIEW, map);
    */
}
