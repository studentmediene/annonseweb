package no.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "customers/customerHome";
    }


    @RequestMapping("/newCustomer")
    public String viewNewCustomer(){
        return "customers/newCustomer";
    }


    @RequestMapping("/viewCustomers")
    public String viewCustomerList(){
        return "customers/viewCustomers";
    }

    @RequestMapping("/searchCustomer")
    public String viewSearchCustomer(){
        return "customers/searchCustomer";
    }

    @RequestMapping("/emailCustomers")
    public String viewEmailsCustomers(){
        return "customers/emailCustomers";
    }

}

