package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.CustomerService;
import no.dusken.annonseweb.service.SalesService;
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

    @Autowired
    private SalesService salesService;

    private Model model;

    @Before
    public void setup(){
        model = new ExtendedModelMap();
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

    @Test
    public void testViewCustomerIdForSale() {
        Customer customer =  new Customer("customerName", "centralEmail", "centralTlf", "invoiceAddress");
        customerService.saveAndFlush(customer);
        Sale sale =  new Sale("description", null, customer, null, false, false);
        salesService.saveAndFlush(sale);
        String returnVal = customerController.viewCustomerIdForSale(sale, model);
        assertTrue("viewCustomerIdFforSale did not populate model", model.containsAttribute("customer"));
        assertEquals("viewCustomerIdForSale did not return correct view name", "customer/as_id", returnVal);
    }

}
