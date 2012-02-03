package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.CustomerService;
import no.dusken.annonseweb.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Under Dusken - underdusken.no - https://github.com/dusken/
 * Magnus Kirø - magnuskiro@underdusken.no
 * 08.11.11
 */

@Controller
@RequestMapping("/sales")
public class SalesController{

    @Autowired
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping()
    public String viewSaleHome(){
        return "sales/home";
    }

    @RequestMapping("/new")
    public String newSales(Model model){
        model.addAttribute("customerList", customerService.findAll());
        //return a list fo all customers. The Id will be used to connect the sales and customers. 
        return "sales/new";
    }
    
    @RequestMapping(value="/addCustomer", method = RequestMethod.POST)
    public String addSale(@Valid @ModelAttribute("sale") Sale sale, Model model){
        salesService.save(sale);
            // take a sale model as input. Store it av change the customer of this sale to have this sale in its saleslist.
        customerService.findOne(sale.getCustomer().getId()).addSale(sale);
        return "sales/sale";                              
    }

    @RequestMapping("/sale/{Id}")
    public String sale(@PathVariable Long Id, Model model){
        model.addAttribute("sale", salesService.findOne(Id));        
        return "sales/sale";       
    }
    
    @RequestMapping("/all")
    public String viewSales(){
        return "sales/all";
    }

    @RequestMapping("/edit")
    public String viewEditSales(){
        return "sales/edit";
    }

    @RequestMapping("/search")
    public String viewSearchSales(){
        return "sales/search";
    }

}
