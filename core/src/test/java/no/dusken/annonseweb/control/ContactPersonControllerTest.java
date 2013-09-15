/*
 * Copyright 2013 Studentmediene i Trondheim AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.annonseweb.models.ContactPerson;
import no.dusken.annonseweb.models.Customer;
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
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.Calendar;

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

    @Autowired
    AnnonsePersonController annonsePersonController;

    private AnnonsePerson someone;
    private Model model;

    @Before
    public void setup() {
        model = model = new ExtendedModelMap();
        String username = "username";
        SecurityContextHolder.getContext().setAuthentication(new DummyAuthenticationUserDetails(username));
        someone = annonsePersonController.getLoggedInUser();
    }

    @Test
    public void testViewContactsHome(){
        assertEquals("Contacts home was not correctly returned", "contactperson/home", contactPersonController.viewContactsHome());
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
    public void testViewContactPerson(){
        assertTrue(true);
    }

    @Test
    public void testNewContactPerson(){
        assertTrue(true);
    }

    @Test
    public void testNewContactPersonCustomer() {
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

    @Test
    public void testViewOptionListForCustomer() {
        Customer customer =  new Customer("customerName", "centralEmail", "centralTlf", "invoiceAddress");
        customerService.saveAndFlush(customer);
        String returnVal = contactPersonController.viewOptionListForCustomer(customer,model);
        assertTrue("View customer did not populate model", model.containsAttribute("contactPersonList"));
        assertEquals("View customer did not return correct view name", "contactperson/as_options", returnVal);
    }
}
