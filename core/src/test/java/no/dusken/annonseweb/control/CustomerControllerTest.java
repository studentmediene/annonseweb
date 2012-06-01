package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

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
    public void testEdit() throws Exception {
        // TODO: complete test.
        
        Customer customer = new Customer("customerName", "centralEmail", "centralTlf",
                "invoiceAddress", "subscriberAddress");

        assertNull("has ID before it should", customer.getId());
        customerController.edit(customer);
        Long id = customer.getId();
        assertNotNull("customer is null", customer);
        assertNotNull("ID is null", customer.getId());
        
        customer.setEmail("edited@email");
        customer.setName("editedName");
        String s = customerController.edit(customer);

        assertEquals(id, customer.getId());
        assertEquals("edited@email", customer.getEmail());
        assertEquals("editedName", customer.getName());
        assertEquals("redirect:/annonse/customer/"+customer.getId(), s);

        assertEquals(customerService.findAll().size(), 1);

    }
}
