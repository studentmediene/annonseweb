package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Ad;
import no.dusken.annonseweb.models.Invoice;
import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.InvoiceService;
import no.dusken.annonseweb.service.SalesService;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Calendar;

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

    @RequestMapping("/print/{invoice}")
    public void printInvoice(@PathVariable Invoice invoice, Writer writer) {
        BigDecimal tot = new BigDecimal(0);
        String html = "<html><head><title>Print faktura</title></head><body>";
        html +="<h1>Faktura: " + invoice.getId() + "</h1>";
        html += "Fakturadato: " + invoice.getCreatedDate() + "<br />";
        html += "Uløpsdato: " + invoice.getInvoiceDate() + "<br /><hr><br />";
        html += "Kunde:<br />" + invoice.getSales().get(0).getCustomer().getName();
        html += "<br />" + invoice.getSales().get(0).getCustomer().getAddress();
        html += "<br /><hr /><br />";
        for (Sale s : invoice.getSales()) {
            html += s.getDescription() + "<br />";
            for (Ad a: s.getAds()) {
                tot.add(a.getFinalPrice());
                html += a.getTitle() + " - " + a.getFinalPrice() + "<br />";
            }
        }
        html += "<br /><hr /><br />";
        html += "<h2>Total: " + tot.toPlainString() + "</h2>";
        html += "</body></html>";
        try {
            writer.write(html);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Invoice.class, new BindByIdEditor(invoiceService));
        binder.registerCustomEditor(Sale.class, new BindByIdEditor(salesService));
    }

}
