package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Magnus Kirø - magnuskiro@ gmail.com/underdusken.no - 12/04/12
 */
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @Before
    public void setup(){
        customerController = new CustomerController();
    }

    @Test
    public void testViewCustomerHome() throws Exception {
        Assert.fail();
    }

    @Test
    public void testViewCustomer() throws Exception {
        Assert.fail();
    }

    @Test
    public void testViewCustomerList() throws Exception {
        Assert.fail();
    }

    @Test
    public void testViewNewCustomer() throws Exception {
        Assert.fail();
    }

    @Test
    public void testViewEmailsCustomersMain() throws Exception {
        Assert.fail();
    }

    @Test
    public void testViewEdit() throws Exception {
        Assert.fail();
    }

    @Test
    public void testEdit() throws Exception {
        // TODO: complete test.
        Customer customer = new Customer("customerName", "centralEmail", "centralTlf",
                "invoiceAddress", "subscriberAddress");
    }

    @Test
    public void testInitbinder() throws Exception {
        Assert.fail();
    }
}
