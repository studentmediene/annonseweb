package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value="/customer/{Id}")
    public String viewCustomer(@PathVariable Long Id, Model model){
        Customer customer = customerService.findOne(Id);
        customer.getId();
        model.addAttribute("customer", customer);
        return "customers/customer";
    }

    @RequestMapping("/all")
    public String viewCustomerList(Model model){
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customerList", customerList);
        return "customers/all";
    }

    @RequestMapping("/new")
    public String viewNewCustomer(){
        return "customers/new";
    }

    @RequestMapping("/addCustomer")
    public String addCustomer(@RequestParam String customerName, @RequestParam String centralEmail,
                              @RequestParam String centralTlf, @RequestParam String invoiceAddress,
                              @RequestParam String subscriberAddress, @RequestParam BigInteger discount,
                              @RequestParam List<String> industryTags, Model model){
        Customer customer = new Customer(customerName, centralEmail, centralTlf, invoiceAddress, subscriberAddress, discount, industryTags);
        customerService.save(customer);
        return "customers/customer/" + customer.getId();
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

