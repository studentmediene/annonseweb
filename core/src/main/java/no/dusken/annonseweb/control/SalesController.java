package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.CustomerService;
import no.dusken.annonseweb.service.SalesService;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;

/**
 * Under Dusken - underdusken.no - https://github.com/dusken/
 * @author Magnus Kirø - magnuskiro@underdusken.no
 * @author Inge Edward Halsaunet - ingehals@underdusken.no
 * 08.11.11
 */

@Controller
@RequestMapping("/sale")
public class SalesController{

    @Autowired
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AnnonsePersonController annonsePersonController;

    @RequestMapping()
    public String viewSaleHome(){
        return "sale/home";
    }

    /**
     * creates a new sale to be viewed and managed through edit
     * @param model the model to view
     * @return the place to view it
     */
    @RequestMapping("/new")
    public String newSale(Model model){
        return viewEdit(new Sale(), model);
    }

    @RequestMapping("/new/{customer}")
    public String newSaleCustomer(@PathVariable Customer customer, Model model) {
        return viewEdit(new Sale(customer), model);
    }

    @RequestMapping("edit/{sale}")
    public String viewEdit(@PathVariable Sale sale, Model model){
        model.addAttribute("customerList", customerService.findAll());
        model.addAttribute("sale", sale);
        return "sale/edit";
    }

    /**
     * Stores a new <code>Sale</code>.
     * @param sale  the <code>Sale</code> to store
     * @return address to view the newly stored <code>Sale</code>
     */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String storeNewSale(@Valid @ModelAttribute Sale sale){
        AnnonsePerson usr = annonsePersonController.getLoggedInUser();
        Customer customer = customerService.findOne(sale.getCustomer().getId());
        sale.setCustomer(customer);
        sale.setCreatedUser(usr);
        sale.setLastEditedUser(usr);
        sale.setTimeCreated(Calendar.getInstance());
        sale.setLastEditedDate(Calendar.getInstance());
        salesService.saveAndFlush(sale);
        return "redirect:/annonseweb/sale/" + sale.getId();
    }

    /**
     * Edits a <code>Sale</code>.
     * @param sale the <code>Sale</code> held in the model to get new data from
     * @param pathSale the old <code>Sale</code> held by database
     * @return address to view the edited sale
     */
    @RequestMapping(value="/save/{pathSale}")
    public String editSale(@Valid @ModelAttribute Sale sale, @PathVariable Sale pathSale){
        pathSale.cloneFrom(sale);
        pathSale.setLastEditedUser(annonsePersonController.getLoggedInUser());
        pathSale.setLastEditedDate(Calendar.getInstance());
        salesService.saveAndFlush(pathSale);
        return "redirect:/annonseweb/sale/" + pathSale.getId();
    }

    @RequestMapping("/{sale}")
    public String viewSale(@PathVariable Sale sale, Model model){
        model.addAttribute("sale", sale);
        return "sale/sale";
    }

    @RequestMapping("/all_active")
    public String viewActiveSalesList(Model model){
        model.addAttribute("saleList", salesService.getActiveSales());
        return "sale/all";
    }

    @RequestMapping("/all")
    public String viewSalesList(Model model){
        model.addAttribute("saleList", salesService.findAll());
        return "sale/all";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Sale.class, new BindByIdEditor(salesService));
        binder.registerCustomEditor(Customer.class, new BindByIdEditor(customerService));
    }
}
