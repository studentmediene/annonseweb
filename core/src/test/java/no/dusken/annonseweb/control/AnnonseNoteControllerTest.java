package no.dusken.annonseweb.control;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
    // TODO Make this test
    @Before
    public void setUp() {
        //
    }

    @Test
    public void testViewHome() {
        assertTrue(true);
    }

    @Test
    public void testViewArchivedNotes() {
        assertTrue(true);
    }

    @Test
    public void testViewAllArchivedNotes() {
        assertTrue(true);
    }

    @Test
    public void testViewActiveNotes() {
        assertTrue(true);
    }

    @Test
    public void testViewAnnonseNote() {
        assertTrue(true);
    }

    @Test
    public void testViewNew() {
        assertTrue(true);
    }

    @Test
    public void testViewNewWithSale() {
        assertTrue(true);
    }

    @Test
    public void testViewNewWithCustomer() {
        assertTrue(true);
    }

    @Test
    public void testViewNewWithContactPerson() {
        assertTrue(true);
    }

    @Test
    public void testViewEdit() {
        assertTrue(true);
    }

    @Test
    public void testSaveNew() {
        assertTrue(true);
    }

    @Test
    public void testSaveEdit() {
        assertTrue(true);
    }
}
