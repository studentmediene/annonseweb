package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.service.CustomerService;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController{

    @Autowired
    private CustomerService customerService;

    @RequestMapping()
    public String viewHome(){
        return "customer/home";
    }

    @RequestMapping("/{customer}")
    public String viewCustomer(@PathVariable Customer customer, Model model){
        model.addAttribute("customer", customer);
        return "customer/customer";
    }

    @RequestMapping("/all")
    public String viewCustomerList(Model model){
        model.addAttribute("customerList", customerService.findAll());
        return "customer/all";
    }

    @RequestMapping("/new")
    public String viewNewCustomer(Model model){
        return viewEdit(new Customer(), model);
    }

    /**
     * List all the email addresses of the customers.
     * This is meant to give the user a mailing list, so it is easier to send mail to all the customers at the same time.
     */
    @RequestMapping("/emailList")
    public String viewEmailsCustomersMain(Model model){
        List<String> emailList = new ArrayList<String>();
        for(Customer customer: customerService.findAll()){
            emailList.add(customer.getEmail());
        }
        model.addAttribute("emailList", emailList);
        return "customer/emailList";
    }

    @RequestMapping("/edit/{customer}")
    public String viewEdit(@PathVariable Customer customer, Model model){
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    /**
     * Saves a new <code>Customer</code>.
     * @param customer the <code>Customer</code> to save
     * @return redirect address
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNew(@Valid @ModelAttribute Customer customer) {
        customerService.saveAndFlush(customer);
        return "redirect:/annonse/customer/" + customer.getId();
    }

    /**
     * Saves an edited <code>Customer</code>.
     * @param pathCustomer <code>Customer</code> that should be edited taken from mapped path
     * @param customer <code>Customer</code> from model to get new information from
     * @return redirect address
     */
    @RequestMapping("save/{pathCustomer}")
    public String saveEdit(@PathVariable Customer pathCustomer, @Valid @ModelAttribute Customer customer) {
        pathCustomer.cloneFrom(customer);
        customerService.saveAndFlush(pathCustomer);
        return "redirect:/annonse/customer/" + pathCustomer.getId();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Customer.class, new BindByIdEditor(customerService));
    }
}

