package no.dusken.annonseweb.control;

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

    @RequestMapping("")
    public String viewCustomerHome(){
        return "customers/customerHome";
    }


    @RequestMapping("/new")
    public String viewNewCustomer(){
        return "customers/newCustomer";
    }


    @RequestMapping("/all")
    public String viewCustomerList(){
        return "customers/viewCustomers";
    }

    @RequestMapping("/search")
    public String viewSearchCustomer(){
        return "customers/searchCustomer";
    }

    @RequestMapping("/emailList")
    public String viewEmailsCustomers(){
        return "customers/emailCustomers";
    }

}

