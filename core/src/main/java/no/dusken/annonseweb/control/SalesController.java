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

    @RequestMapping("/home")
    public String viewSaleHome(){
        return "sales/home";
    }

    @RequestMapping("/new")
    public String viewNewSales(){
        return "sales/new";
    }

    @RequestMapping("/all")
    public String viewSales(){
        return "sales/all";
    }

    @RequestMapping("/edit")
    public String viewEditSales(){
        return "sales/edit";
    }

    @RequestMapping("/search")
    public String viewSearchSales(){
        return "sales/search";
    }

}
