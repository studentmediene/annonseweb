package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Invoice;
import no.dusken.annonseweb.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @RequestMapping()
    public String viewInvoiceHome(){
        return "invoices/home";
    }

    @RequestMapping("/all")
    public String listInvoices(Model model){
        List<Invoice> invoiceList = invoiceService.findAll();
        model.addAttribute("InvoiceList", invoiceList);
        return "invoices/all";
    }

    @RequestMapping("/invoice")
    public String viewInvoice(){
        return "invoices/invoice";
    }

    @RequestMapping("/new")
    public String newInvoice(){
        return "invoices/new";
    }

    @RequestMapping("/search")
    public String search(){
        return "invoices/search";
    }

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

}
