package no.dusken.annonseweb.service;

import no.dusken.annonseweb.models.Ad;
import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.models.Sale;
import no.dusken.common.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/integrationtest-jpa.xml"})
@TransactionConfiguration
@Transactional
public class ServiceIntegrationTest {

    @Autowired
    private SaleService saleService;

    @Test
    public void testSave(){

        Ad ad = new Ad(new BigDecimal("100"), new BigDecimal("10"));
        Customer customer = new Customer("customer", "mail@mail.mail", "12345678", "address", "address", new BigInteger("10"), new LinkedList<String>(Collections.singleton("tag")));
        Person createdUser = getPerson();

        Sale s = new Sale("Appointment name", new LinkedList<Ad>(Collections.singleton(ad)), customer, createdUser, true);

        Sale sale = saleService.saveAndFlush(s);
        assertNotNull(sale);
        assertNotNull(sale.getId());
        assertEquals(ad, sale.getAds().get(0));
        assertEquals(customer, sale.getCustomer());

    }

    private Person getPerson() {
        Person createdUser = new Person();
        createdUser.setUsername("user");
        createdUser.setFirstname("first");
        createdUser.setSurname("surname");
        createdUser.setEmailAddress("email@email.com");
        return createdUser;
    }

}
