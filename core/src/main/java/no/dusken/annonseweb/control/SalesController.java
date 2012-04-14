package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.CustomerService;
import no.dusken.annonseweb.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Under Dusken - underdusken.no - https://github.com/dusken/
 * Magnus Kirø - magnuskiro@underdusken.no
 * 08.11.11
 */

@Controller
@RequestMapping("/sale")
public class SalesController{

    @Autowired
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping()
    public String viewSaleHome(){
        return "sale/home";
    }

    @RequestMapping("/new")
    public String newSales(Model model){
        model.addAttribute("customer", customerService.findAll());
        model.addAttribute("sale", new Sale());
        return "sale/new";
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public String editSale(@Valid @ModelAttribute Sale sale){
        sale = salesService.save(sale);
        return "redirect:/sale" + sale.getId();
    }

    @RequestMapping("/{sale}")
    public String sale(@ModelAttribute Sale sale, Model model){
        model.addAttribute("sale", sale);
        return "sale/sale";
    }
    
    @RequestMapping("/all")
    public String viewSales(){
        return "sale/all";
    }

    @RequestMapping("/edit")
    public String editSales(@Valid @ModelAttribute("sale") Sale sale, Model model){
        model.addAttribute("customerList", customerService.findAll());
        model.addAttribute("sale", new Sale());
        return "sale/edit";
    }
    
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addSale(@Valid @ModelAttribute("sale")Sale sale){
        salesService.save(sale);
        return "sale";
    }
}
