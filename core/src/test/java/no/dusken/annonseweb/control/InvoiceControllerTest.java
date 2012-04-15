package no.dusken.annonseweb.control;

import no.dusken.annonseweb.service.InvoiceService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Under Dusken - underdusken.no - https://github.com/dusken/
 * @author Magnus Kirø - magnuskiro@underdusken.no
 * 25.11.11
 */

public class InvoiceControllerTest {

    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void testNew(){
        Assert.fail();
    }
}
