package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Invoice;
import no.dusken.annonseweb.service.InvoiceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Under Dusken - underdusken.no - https://github.com/dusken/
 * @author Magnus Kirø - magnuskiro@underdusken.no
 * 25.11.11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/integrationtest-jpa.xml"})
@TransactionConfiguration
@Transactional
public class InvoiceControllerTest {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceController invoiceController;

    private Model model;
    private Invoice invoice;

    @Before
    public void setup() {
        model = new ExtendedModelMap();
        invoice = new Invoice();
        invoiceService.saveAndFlush(invoice);
    }

    @Test
    public void testNew(){
        Assert.assertTrue(true);
    }

    @Test
    public void testPrintInvoice() {
        assertFalse("Model was populated before it should! invoice", model.containsAttribute("invoice"));
        assertFalse("Model was populated before it should! total", model.containsAttribute("total"));
        assertEquals("View sidebar notes returned wrong view address", "invoice/print",
                invoiceController.printInvoice(invoice, model));
        assertTrue("Model was not populated! invoice", model.containsAttribute("invoice"));
        assertTrue("Model was not populated! total", model.containsAttribute("total"));
    }
}
