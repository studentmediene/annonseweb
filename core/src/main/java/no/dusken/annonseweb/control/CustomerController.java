package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController{

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/home")
    public String viewCustomerHome(){
        return "customers/home";
    }

    @RequestMapping("/new")
    public String viewNewCustomer(){
        return "customers/new";
    }

    @RequestMapping("/customer")
    public String viewCustomer(@RequestParam Long id, Model model){
        Customer customer = customerService.findOne(id);
        model.addAttribute("customer", customer);
        return "customers/customer";
    }

    @RequestMapping("/all")
    public String viewCustomerList(Model model){
        List<Customer> customerList = customerService.findAll();
        /* only for testing
        List<Customer> customerList = new ArrayList<Customer>();
        BigInteger num = new BigInteger(String.valueOf(10000));
        List<String> tags = new ArrayList<String>();
        tags.add("kake");
        tags.add("elefant");
        customerList.add(new Customer("test1", "email", "tlf", "invoicead", "subscadr", num, tags));
        customerList.add(new Customer("test2", "email", "tlf", "invoicead2", "subscadr2", num, tags));
        customerList.add(new Customer("test3", "email", "tlf", "invoicead3", "subscadr3", num, tags));
        */
        model.addAttribute("customerList", customerList);
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

