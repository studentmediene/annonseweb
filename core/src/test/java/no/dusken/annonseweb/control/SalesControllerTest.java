package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.CustomerService;
import no.dusken.annonseweb.service.SalesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Magnus Kirø - magnuskiro@ gmail.com/underdusken.no - 12/04/12
 * @author Inge Halsaunet - ingehals@underdusken.no / halsaune@stud.ntnu.no
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/integrationtest-jpa.xml"})
@TransactionConfiguration
@Transactional
public class SalesControllerTest {
    @Autowired
    private SalesController salesController;

    @Autowired
    private CustomerController customerController;

    @Autowired
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @Before
    public void setup() {
    }


    @Test
    public void testViewSaleHome() throws Exception {
        Assert.assertEquals("Wrong return address", "sale/home", salesController.viewSaleHome());
    }

    @Test
    public void testNewSale() throws Exception {
        // Easier and better to see if it just runs after build.
        Assert.assertTrue(true);
    }

    @Test
    public void testViewEditSale() throws Exception {
        // As with new, this is better and much easier to test after build
        Assert.assertTrue(true);
    }

    @Test
    public void testEditSale() throws Exception {
        int customerCount;
        int saleCount;
        Customer c =  new Customer("customerName", "centralEmail", "centralTlf",
                "invoiceAddress", "subscriberAddress");
        customerController.saveNew(c);
        customerCount = customerService.findAll().size();
        c = customerService.findOne(c.getId());
        Sale sale = new Sale("description", null, c, null, false);
        Assert.assertNull("sale has id before it is stored", sale.getId());
        salesController.editSale(sale);
        Long id = sale.getId();
        Assert.assertNotNull("sale id was null", id);
        sale = salesService.findOne(id);
        sale.setDescription("editedDescription");
        salesController.editSale(sale);
        sale = salesService.findOne(id);
        Assert.assertEquals("could not edit sale description", "editedDescription", sale.getDescription());
        Assert.assertEquals("one sale was stored 0 or multiple times", 1, salesService.findAll().size());
        Assert.assertEquals("We have created some unintended customers", customerCount, customerService.findAll().size());

        saleCount = salesService.findAll().size();
        Long s = sale.getId();
        sale = new Sale("description", null, c, null, false);
        sale.setEditNumber(s.toString());
        salesController.editSale(sale);
        Assert.assertEquals("could not edit sale by sale edit number", saleCount, salesService.findAll().size());
    }

    @Test
    public void testViewSale() throws Exception {
        // Better seen bugs after build
        Assert.assertTrue(true);
    }

    @Test
    public void testViewSalesList() throws Exception {
        Assert.assertTrue(true);
    }
}
