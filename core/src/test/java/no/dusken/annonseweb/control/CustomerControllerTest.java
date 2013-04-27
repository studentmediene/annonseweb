package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.*;

/**
 * @author Magnus Kirø - magnuskiro@ gmail.com/underdusken.no - 12/04/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/integrationtest-jpa.xml"})
@TransactionConfiguration
@Transactional
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerService customerService;

    @Before
    public void setup(){
    }

    @Test
    public void testViewHome(){
        assertEquals("customer/home", customerController.viewHome());
    }

    @Test
    public void testEdit() throws Exception {
        Customer customer = new Customer("customerName", "centralEmail", "centralTlf",
                "invoiceAddress");

        assertNull("has ID before it should", customer.getId());
        customerController.saveNew(customer);
        Long id = customer.getId();
        assertNotNull("customer is null", customer);
        assertNotNull("ID is null", customer.getId());
        
        customer.setEmail("edited@email");
        customer.setName("editedName");
        customer.setPhoneNumber("editedPhoneNumber");
        String s = customerController.saveEdit(customerService.findOne(customer.getId()), customer);

        // Check if customer object has changed id
        assertEquals(id, customer.getId());

        customer = customerService.findOne(id);
        assertEquals(id, customer.getId());
        assertEquals("edited@email", customer.getEmail());
        assertEquals("editedName", customer.getName());
        assertEquals("redirect:/annonseweb/customer/"+customer.getId(), s);
        assertEquals("editedPhoneNumber", customer.getPhoneNumber());

        // checks if size is correct
        assertEquals(customerService.findAll().size(), 1);
    }

    /*
    @Test
    public void testEditMultipleObjects() throws Exception {
        Customer customer = new Customer("customerName", "centralEmail", "centralTlf",
                "invoiceAddress", "subscriberAddress");
        customerController.saveNew(customer);
        Long id = customer.getId();
        customer = new Customer("customerName", "centralEmail", "centralTlf",
                "invoiceAddress", "subscriberAddress");
        customer.setName("2ndEdit");
        customer.setEditNumber(id.toString());
        customerController.edit(customer);
        customer = customerService.findOne(id);
        assertEquals("different object: customer name is not edited", "2ndEdit", customer.getName());
        assertEquals("different object: customer email is wrong", "centralEmail", customer.getEmail());
        assertEquals("customer list is too long", 1, customerService.findAll().size());
        assertFalse("Contains id + 1", customerService.exists(id + 1));
        assertTrue("Does not contain id", customerService.exists(id));

        Customer customer2 = new Customer("customerName", "centralEmail", "centralTlf",
                "invoiceAddress", "subscriberAddress");
        customerController.edit(customer2);

        // check if customerlist now has length 2
        assertEquals("customer list is not 2", 2, customerService.findAll().size());

        customer2.setEditNumber(id.toString());
        customerController.edit(customer2);
        // check if customerlist now has length 2
        assertEquals("customer list is not 2", 2, customerService.findAll().size());
    }
    */
}
