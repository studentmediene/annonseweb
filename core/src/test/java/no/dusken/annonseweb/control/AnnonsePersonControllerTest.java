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
 * This should be made serious after actual log in is made.
 *
 *@author Inge Halsaunet
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/integrationtest-jpa.xml"})
@TransactionConfiguration
@Transactional
public class AnnonsePersonControllerTest {
    // TODO Make this test
    @Before
    public void setUp() {
        //
    }

    @Test
    public void test() {
        assertTrue(true);
    }
}
