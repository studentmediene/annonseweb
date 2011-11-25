package no.dusken.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController{

    @RequestMapping("/home")
    public String viewCustomerHome(){
        return "customers/home";
    }

    @RequestMapping("/new")
    public String viewNewCustomer(){
        return "customers/new";
    }

    @RequestMapping("/customer")
    public String viewCustomer(){
        return "customers/customer";
    }

    @RequestMapping("/all")
    public String viewCustomerList(){
        return "customers/all";
    }

    @RequestMapping("/search")
    public String viewSearchCustomer(){
        return "customers/search";
    }

    @RequestMapping("/emailList")
    public String viewEmailsCustomers(){
        return "customers/emailList";
    }

}

