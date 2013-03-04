package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Invoice;
import no.dusken.annonseweb.service.InvoiceService;
import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private SalesService salesService;

    @RequestMapping("/")
    public String viewHome(){
        return "invoice/home";
    }

    @RequestMapping("/all")
    public String listInvoices(Model model){
        List<Invoice> invoiceList = invoiceService.findAll();
        model.addAttribute("InvoiceList", invoiceList);
        return "invoice/all";
    }

    @RequestMapping("/invoice")
    public String viewInvoice(){
        return "invoice/invoice";
    }

    @RequestMapping("/new")
    public String newInvoice(Model model){
        model.addAttribute("SaleList", salesService.findAll());

        return "invoice/new";
    }

    @RequestMapping("/search")
    public String search(){
        return "invoice/search";
    }

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @Autowired
    public void setSalesService(SalesService salesService){
        this.salesService = salesService;
    }

}
