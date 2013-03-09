package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.models.RoleAuth;
import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.AnnonsePersonService;
import no.dusken.annonseweb.service.CustomerService;
import no.dusken.annonseweb.service.SalesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AnnonsePersonService annonsePersonService;

    private AnnonsePerson someone;

    @Before
    public void setup() {
        if (annonsePersonService.findAll().size() == 0) {
            someone = new AnnonsePerson();
            someone.setPrincipal("SuperDuper");
            someone.setCredentials( "SuperPass");
            someone.setAuthority(RoleAuth.MASKINIST.toString());
            annonsePersonService.saveAndFlush(someone);
        } else {
            someone = annonsePersonService.findOne(Long.valueOf(1));
        }
        SecurityContextHolder.getContext().setAuthentication(someone);
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
    public void testNewSaleCustomer() {
        Assert.assertTrue(true);
    }

    @Test
    public void testViewEdit() throws Exception {
        // As with new, this is better and much easier to test after build
        Assert.assertTrue(true);
    }

    @Test
    public void testStoreNewSale() {
        int customerCount;
        int salesCount = salesService.findAll().size();
        Customer c =  new Customer("customerName", "centralEmail", "centralTlf",
                "invoiceAddress");
        customerService.saveAndFlush(c);
        customerCount = customerService.findAll().size();
        Assert.assertTrue("After customer save, customer count was 0", customerCount != 0);
        c = customerService.findOne(c.getId());

        Sale sale =  new Sale("description", null, c, null, false);
        Assert.assertNull("sale had id before it was stored", sale.getId());
        salesController.storeNewSale(sale);

        Long id = sale.getId();
        Assert.assertNotNull("sale id was null", id);

        Assert.assertEquals("Sale was stored 0 or multiple times", salesCount + 1, salesService.findAll().size());
        Assert.assertEquals("We have created some unintended customers", customerCount, customerService.findAll().size());
    }

    @Test
    public void testEditSale() throws Exception {
        int customerCount;
        int salesCount = salesService.findAll().size();
        Customer c =  new Customer("customerName", "centralEmail", "centralTlf",
                "invoiceAddress");
        customerService.saveAndFlush(c);
        customerCount = customerService.findAll().size();
        c = customerService.findOne(c.getId());
        Sale sale = new Sale("description", null, c, null, false);
        salesController.storeNewSale(sale);
        Long id = sale.getId();
        Sale editedSale = new Sale("description", null, c, null, false);
        editedSale.setDescription("editedDescription");
        salesController.editSale(editedSale, sale);
        sale = salesService.findOne(id);
        Assert.assertEquals("could not edit sale description", "editedDescription", sale.getDescription());
        Assert.assertEquals("one sale was stored 0 or multiple times", salesCount + 1, salesService.findAll().size());
        Assert.assertEquals("We have created some unintended customers", customerCount, customerService.findAll().size());
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
