package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

/**
 * Under Dusken - underdusken.no - https://github.com/dusken/
 * Magnus Kirø - magnuskiro@underdusken.no
 * 04.12.11
 */
@Controller
@RequestMapping("/editCustomer")
public class EditCustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/addCustomer")
    public String addCustoemr(@RequestParam String name, @RequestParam String email,
                              @RequestParam String tlf, @RequestParam String invoiceAddress,
                              @RequestParam String subscriberAddress, @RequestParam BigInteger discount,
                              @RequestParam List<String> tags, Model model){
        Customer customer = new Customer(name, email, tlf, invoiceAddress, subscriberAddress, discount, tags);
        customerService.save(customer);
        return "customer/" + customer.getId();
    }

}
