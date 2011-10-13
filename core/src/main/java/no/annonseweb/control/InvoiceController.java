package no.annonseweb.control;

import no.dusken.annonseweb.models.Invoice;
import no.dusken.annonseweb.service.InvoiceService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Created by IntelliJ IDEA.
 * User: kiro
 * Date: Nov 8, 2010
 * Time: 10:25:00 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class InvoiceController {

    private InvoiceService invoiceService;

    @RequestMapping("/invoiceList")
    public String listInvoices(Model model){
        /*Invoice invoice = new Invoice(253L);
        model.addAttribute("InvoiceList", invoice);
        System.out.println(invoice);        */
        return "no/dusken/annonseweb/web/invoiceList";
    }
    /*
    @Required
    public void setInvoiceService(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }  */

}
