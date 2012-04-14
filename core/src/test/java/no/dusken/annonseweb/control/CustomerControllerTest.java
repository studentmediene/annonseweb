package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Magnus Kirø - magnuskiro@ gmail.com/underdusken.no - 12/04/12
 */
public class CustomerControllerTest {
    private CustomerController customerController;

    @Before
    public void setup(){
        customerController = new CustomerController();
    }

    @Test
    public void testViewCustomerHome() throws Exception {
        // TODO: create test.
    }

    @Test
    public void testViewCustomer() throws Exception {
        // TODO:
    }

    @Test
    public void testViewCustomerList() throws Exception {
        // TODO:
    }

    @Test
    public void testViewNewCustomer() throws Exception {
        // TODO:
    }

    @Test
    public void testViewEmailsCustomersMain() throws Exception {
        // TODO:
    }

    @Test
    public void testViewEdit() throws Exception {
        // TODO:
    }

    @Test
    public void testEdit() throws Exception {
        // TODO: complete test.
        Customer customer = new Customer("customerName", "centralEmail", "centralTlf", "invoiceAddress",
                "subscriberAddress", new BigInteger("2"), new LinkedList<String>(Collections.singleton("tag")));
        customerController.edit(customer);

    }

    @Test
    public void testInitbinder() throws Exception {
        // TODO:
    }
}
