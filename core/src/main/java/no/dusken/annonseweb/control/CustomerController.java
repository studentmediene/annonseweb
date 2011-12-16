package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController{

    @Autowired
    private CustomerService customerService;

    @RequestMapping()
    public String viewCustomerHome(){
        return "customers/home";
    }

    @RequestMapping(value="/customer/{Id}")
    public String viewCustomer(@PathVariable Long Id, Model model){
        model.addAttribute("customer", customerService.findOne(Id));
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

    @RequestMapping(value="/addCustomer", method = RequestMethod.POST)
    public String addCustomer(@Valid @ModelAttribute("customer") Customer customer, Model model){
        customerService.save(customer);
        return "customers/customer";
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

