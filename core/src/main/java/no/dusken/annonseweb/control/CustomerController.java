package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
        model.addAttribute("customerList", customerService.findAll());
        return "customers/all";
    }

    @RequestMapping("/new")
    public String viewNewCustomer(){
        return "customers/new";
    }

    //TODO: consider and find out whether or not edit and add can become the same method.
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addCustomer(@Valid @ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        return "customers/customer";
    }

    @RequestMapping("/emailList")
    public String viewEmailsCustomersMain(Model model){
        List<String> emailList = new ArrayList<String>();
        for(Customer customer: customerService.findAll()){
            emailList.add(customer.getCentralEmail());
        }
        model.addAttribute("emailList", emailList);
        return "customers/emailList";
    }
    
    @RequestMapping("/edit/{Id}")
    public String viewEdit(@PathVariable Long Id, Model model){
        model.addAttribute("customer", customerService.findOne(Id));
        return "customers/edit";
    }

    //TODO: consider and find out whether or not edit and add can become the same method.
    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute Customer customer){
        customerService.save(customer);
        return "customers/edit";
    }

    @RequestMapping("/search")
    public String viewSearchCustomer(){
        return "customers/new_tobe";     // to be changed back to the line beneath.
        //return "customers/search";
    }
}

