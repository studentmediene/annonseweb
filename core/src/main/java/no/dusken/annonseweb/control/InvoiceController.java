package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.*;
import no.dusken.annonseweb.service.InvoiceService;
import no.dusken.annonseweb.service.SalesService;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private AnnonsePersonController annonsePersonController;

    @RequestMapping("")
    public String viewInvoiceHome(){
        return "invoice/home";
    }

    @RequestMapping("/all")
    public String listInvoices(Model model){
        model.addAttribute("InvoiceList", invoiceService.findAll());
        return "invoice/all";
    }

    @RequestMapping("/{invoice}")
    public String viewInvoice(@PathVariable Invoice invoice, Model model){
        // todo does this work?
        model.addAttribute("invoice", invoice);
        return "invoice/invoice";
    }

    @RequestMapping("/new")
    public String newInvoice(Model model){
        return viewEditInvoice(new Invoice(), model);
    }

    @RequestMapping("/new/{sale}")
    public String newSaleInvoice(@PathVariable Sale sale, Model model){
        model.addAttribute("selectedSale", sale);
        return viewEditInvoice(new Invoice(), model);
    }

    @RequestMapping("/edit/{invoice}")
    public String viewEditInvoice(@PathVariable Invoice invoice, Model model){
        model.addAttribute("invoice", invoice);
        model.addAttribute("salesList", salesService.findAll());
        return "invoice/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNewInvoice(@Valid @ModelAttribute Invoice invoice) {
        // todo get getLoggedInUser, ain't that in commons ?
        invoice.setCreatedUser(annonsePersonController.getLoggedInUser());
        invoice.setLastEditedUser(annonsePersonController.getLoggedInUser());
        invoice.setCreatedDate(Calendar.getInstance());
        invoice.setLastEditedDate(Calendar.getInstance());
        invoiceService.saveAndFlush(invoice);
        for(Sale sale: invoice.getSales()){
            salesService.saveAndFlush(sale);
        }
        return "redirect:/annonse/invoice/" + invoice.getId();
    }

    @RequestMapping("/save/{pathInvoice}")
    public String saveEditedInvoice(@PathVariable Invoice pathInvoice, @Valid @ModelAttribute Invoice invoice) {
        pathInvoice.cloneFrom(invoice);
        pathInvoice.setLastEditedUser(annonsePersonController.getLoggedInUser());
        pathInvoice.setLastEditedDate(Calendar.getInstance());
        for(Sale sale: invoice.getSales()){
            salesService.saveAndFlush(sale);
        }
        invoiceService.saveAndFlush(pathInvoice);
        /* Should find some way to only remove connection for old saved sales */
        for(Sale sale: salesService.findAll()){
            if(sale.getInvoice() != null){
                if(sale.getInvoice().getId().equals(pathInvoice.getId())){
                    Boolean skip = false;
                    for(Sale selectedSale : invoice.getSales()){
                        if(sale.getId().equals(selectedSale.getId())){
                            skip = true;
                        }
                    }
                    if(!skip){
                        sale.setInvoice(null);
                        sale.setInvoiceGenerated(false);
                        salesService.saveAndFlush(sale);
                    }
                }
            }
        }
        return "redirect:/annonse/invoice/" + pathInvoice.getId();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Invoice.class, new BindByIdEditor(invoiceService));
        binder.registerCustomEditor(Sale.class, new BindByIdEditor(salesService));
    }

}
