package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.*;
import no.dusken.annonseweb.service.*;
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

import static junit.framework.Assert.*;

/**
 * Testing of <code>ContactNoteController</code>
 * @author Inge Halsaunet
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/integrationtest-jpa.xml"})
@TransactionConfiguration
@Transactional
public class AnnonseNoteControllerTest {

    @Autowired
    private AnnonseNoteController annonseNoteController;

    @Autowired
    private AnnonseNoteService annonseNoteService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SalesService salesService;

    @Autowired
    private ContactPersonService contactPersonService;

    @Autowired
    private AnnonsePersonService annonsePersonService;

    private AnnonsePerson someone;
    private Model model;
    private AnnonseNote note;
    private Customer customer;
    private Sale sale;
    private ContactPerson contactPerson;


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
        model = new ExtendedModelMap();
        note = new AnnonseNote();
        note.setText("A short note");
        note.setActive(Boolean.TRUE);
        customer =  new Customer("customerName", "centralEmail", "centralTlf", "invoiceAddress");
        customerService.saveAndFlush(customer);
        sale =  new Sale("description", null, customer, null, false);
        salesService.saveAndFlush(sale);
        contactPerson = new ContactPerson("name", "email", "phone", "position");
        contactPerson.setCustomer(customer);
        contactPersonService.saveAndFlush(contactPerson);
    }


    @Test
    public void testViewHome() {
        assertEquals("View home returned wrong view address.", "note/home", annonseNoteController.viewHome());
        assertTrue(true);
    }

    @Test
    public void testViewArchivedNotes() {
        assertFalse("Model was populated before it should!", model.containsAttribute("annonseNoteList"));
        assertEquals("View archived returned wrong view address", "note/list",
                annonseNoteController.viewArchivedNotes(model));
        assertTrue("Model was not populated!", model.containsAttribute("annonseNoteList"));
    }

    @Test
    public void testViewAllArchivedNotes() {
        assertFalse("Model was populated before it should!", model.containsAttribute("annonseNoteList"));
        assertEquals("View all archived returned wrong view address", "note/list",
                annonseNoteController.viewAllArchivedNotes(model));
        assertTrue("Model was not populated!", model.containsAttribute("annonseNoteList"));
    }

    @Test
    public void testDoArchive() {
        note.setActive(Boolean.TRUE);
        annonseNoteController.saveNew(note);
        assertEquals("Do active returned wrong view name!", "redirect:/annonse/note/" + note.getId(),
                annonseNoteController.doArchive(note));
        assertFalse("Do archive did not make note passive!", note.getActive());
    }

    @Test
    public void testViewActiveNotes() {
        assertFalse("Model was populated before it should!", model.containsAttribute("annonseNoteList"));
        assertEquals("View active returned wrong view address", "note/list",
                annonseNoteController.viewActiveNotes(model));
        assertTrue("Model was not populated!", model.containsAttribute("annonseNoteList"));
    }

    @Test
    public void testViewAnnonseNote() {
        assertFalse("Model was populated before it should!", model.containsAttribute("annonseNote"));
        assertEquals("View annonse note returned wrong view address", "note/note",
                annonseNoteController.viewAnnonseNote(note, model));
        assertTrue("Model was not populated!", model.containsAttribute("annonseNote"));
    }

    @Test
    public void testViewNew() {
        assertFalse("Model was populated before it should!", model.containsAttribute("annonseNote"));
        assertEquals("View new returned wrong view address", "note/edit",
                annonseNoteController.viewNew(model));
        assertTrue("Model was not populated!", model.containsAttribute("annonseNote"));
    }

    @Test
    public void testViewNewWithSale() {
        assertFalse("Model was populated before it should!", model.containsAttribute("annonseNote"));
        assertEquals("View new with sale returned wrong view address", "note/edit",
                annonseNoteController.viewNewWithSale(sale, model));
        assertTrue("Model was not populated!", model.containsAttribute("annonseNote"));
        assertEquals("Sale was not connected to note!", sale,
                ((AnnonseNote)model.asMap().get("annonseNote")).getSale());
    }

    @Test
    public void testViewNewWithCustomer() {
        assertFalse("Model was populated before it should!", model.containsAttribute("annonseNote"));
        assertEquals("View new with customer returned wrong view address", "note/edit",
                annonseNoteController.viewNewWithCustomer(customer, model));
        assertTrue("Model was not populated!", model.containsAttribute("annonseNote"));
        assertEquals("Customer was not connected to note!", customer,
                ((AnnonseNote) model.asMap().get("annonseNote")).getCustomer());
    }

    @Test
    public void testViewNewWithContactPerson() {
        assertFalse("Model was populated before it should!", model.containsAttribute("annonseNote"));
        assertEquals("View new with contact person returned wrong view address", "note/edit",
                annonseNoteController.viewNewWithContactPerson(contactPerson, model));
        assertTrue("Model was not populated!", model.containsAttribute("annonseNote"));
        assertEquals("Customer was not connected to note!", customer,
                ((AnnonseNote) model.asMap().get("annonseNote")).getCustomer());
        assertEquals("Contact person was not connected to note!", contactPerson,
                ((AnnonseNote)model.asMap().get("annonseNote")).getContactPerson());
    }

    @Test
    public void testViewEdit() {
        assertFalse("Model was populated before it should!", model.containsAttribute("annonseNote"));
        assertEquals("View edit returned wrong view address", "note/edit",
                annonseNoteController.viewEdit(note, model));
        assertTrue("Model was not populated!", model.containsAttribute("annonseNote"));
    }

    @Test
    public void testSaveNew() {
        int noteCount = annonseNoteService.findAll().size();
        assertNull("Annonse note had ID before it should", note.getId());
        String retAdr = annonseNoteController.saveNew(note);
        assertEquals("Save new did not return correct view adress!", "redirect:/annonse/note/" + note.getId(), retAdr);
        assertNotNull("Note did not get ID!", note.getId());
        assertEquals("Note was stored 0 or multiple times!", noteCount + 1, annonseNoteService.findAll().size());
        note = annonseNoteService.findOne(note.getId());
        assertEquals("Note did not get correct AnnonsePerson as creator!", someone, note.getCreatedUser());
        assertEquals("Note text was not stored properly!", "A short note", note.getText());
    }

    @Test
    public void testSaveEdit() {
        int noteCount;
        AnnonseNote editSrc = new AnnonseNote();
        annonseNoteController.saveNew(note);
        Long id = note.getId();
        noteCount = annonseNoteService.findAll().size();
        editSrc.setText("edited short note");
        editSrc.setActive(Boolean.FALSE);
        String retAdr = annonseNoteController.saveEdit(note, editSrc);
        assertEquals("Id was changed", id, note.getId());
        assertEquals("Save Edit returned wrong view address!", "redirect:/annonse/note/" + id, retAdr);
        assertEquals("Note was stored again or deleted!", noteCount, annonseNoteService.findAll().size());
        note = annonseNoteService.findOne(note.getId());
        assertEquals("Notes text was not properly edited", "edited short note", note.getText());
    }
}
