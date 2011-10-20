package no.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Created by IntelliJ IDEA.
 * User: kiro
 * Date: Nov 8, 2010
 * Time: 10:24:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/customers")
public class CustomerController{

    @RequestMapping("/customerHome")
    public String viewCustomerHome(){
        return "no/dusken/annonseweb/web/customers/customerHome";
    }


    @RequestMapping("/newCustomer")
    public String viewNewCustomer(){
        return "no/dusken/annonseweb/web/customers/newCustomer";
    }


    @RequestMapping("/viewCustomers")
    public String viewCustomerList(){
        return "no/dusken/annonseweb/web/customers/viewCustomers";
    }

    @RequestMapping("/searchCustomer")
    public String viewSearchCustomer(){
        return "no/dusken/annonseweb/web/customers/searchCustomer";
    }

    @RequestMapping("/emailCustomers")
    public String viewEmailsCustomers(){
        return "no/dusken/annonseweb/web/customers/emailCustomers";
    }

}

