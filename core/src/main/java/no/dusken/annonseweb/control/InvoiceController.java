package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Invoice;
import no.dusken.annonseweb.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class InvoiceController {

    private InvoiceService invoiceService;

    @RequestMapping("/invoiceList")
    public String listInvoices(Model model){
        List<Invoice> invoiceList = invoiceService.findAll();
        model.addAttribute("InvoiceList", invoiceList);
        return "invoices/invoiceList";
    }

    @RequestMapping("/invoiceHome")
    public String viewInvoiceHome(Model model){
        return "invoices/invoiceHome";
    }

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

}
