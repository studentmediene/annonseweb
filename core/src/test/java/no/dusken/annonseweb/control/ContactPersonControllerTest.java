package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.annonseweb.models.ContactPerson;
import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.models.RoleAuth;
import no.dusken.annonseweb.service.AnnonsePersonService;
import no.dusken.annonseweb.service.ContactPersonService;
import no.dusken.annonseweb.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.jar.Pack200;

import static junit.framework.Assert.*;

/**
 * @author Magnus Kirø - magnuskiro@ gmail.com/underdusken.no - 12/04/12
 * @author Inge Edward Halsaunet
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/integrationtest-jpa.xml"})
@TransactionConfiguration
@Transactional
public class ContactPersonControllerTest  {
    @Autowired
    private ContactPersonController contactPersonController;
    @Autowired
    private ContactPersonService contactPersonService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    AnnonsePersonService annonsePersonService;
    private AnnonsePerson someone;

    @Before
    public void setup() {
        customerService.saveAndFlush(new Customer("name", "email", "phone", "address"));
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
    public void testViewContactsHome(){
        assertEquals("Contacts home was not correctly returned", "contact/home", contactPersonController.viewContactsHome());
    }

    @Test
    public void testAllActive(){
        assertTrue(true);
    }

    @Test
    public void testAll(){
        assertTrue(true);
    }

    @Test
    public void viewContactPerson(){
        assertTrue(true);
    }

    @Test
    public void testNewContactPerson(){
        assertTrue(true);
    }

    @Test
    public void testViewEdit() {
        assertTrue(true);
    }

    @Test
    public void testStoreNewContactPerson() {
        int contactCount = contactPersonService.findAll().size();
        Customer c = customerService.findOne(Long.valueOf(1));
        ContactPerson p = new ContactPerson("name", "email", "phone", "position");
        assertNull("Contact person had id before it should!", p.getId());

        p.setCustomer(c);
        contactPersonController.storeNewContactPerson(p);
        assertEquals("Contact person was stored 0 or multiple times!", contactCount + 1,
                contactPersonService.findAll().size());
        assertEquals("Creator of contact person was not correct annonse person", someone, p.getCreatedUser());
    }

    @Test
    public void testEditContactPerson() {
        int contactCount;
        Long pid;
        Customer c = customerService.findOne(Long.valueOf(1));
        ContactPerson p = new ContactPerson("name", "email", "phone", "position"), p2;
        p.setCustomer(c);
        contactPersonService.saveAndFlush(p);
        pid = p.getId();
        contactCount = contactPersonService.findAll().size();
        p2 = new ContactPerson("editedName", "editedEmail", "editedTelephone", "editedPosition");
        p2.setCreatedUser(someone);
        p2.setCreatedDate(Calendar.getInstance());
        contactPersonController.editContactPerson(p2, p);

        assertEquals("Unintentional contactperson creatd", contactCount, contactPersonService.findAll().size());
        p = contactPersonService.findOne(pid);
        assertNotNull("Could not locate stored contact person", p);
        assertEquals("Contact persons name was not correctly altered!", "editedName", p.getPersonName());
        assertEquals("Contact persons email was not correctly altered!", "editedEmail", p.getEmail());
    }

    @Test
    public void testViewEmailsForCustomersContactPersons(){
        assertTrue(true);
    }
}
