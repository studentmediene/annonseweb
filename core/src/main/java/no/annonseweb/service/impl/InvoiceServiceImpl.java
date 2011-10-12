package no.annonseweb.service.impl;

import no.dusken.annonseweb.models.Invoice;
import no.dusken.annonseweb.service.InvoiceService;
import no.dusken.common.service.impl.GenericServiceImpl;
import org.springframework.dao.DataAccessException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Under Dusken - Computer Department
 * User: Magnus Kirø - magnuskiro@underdusken.no
 * Date: 9/21/11
 * Time: 10:00 PM
 */
public class InvoiceServiceImpl extends GenericServiceImpl<Invoice> implements InvoiceService {

    public InvoiceServiceImpl() {
        super(Invoice.class);
    }

    public List<Invoice> getInvoices() {
       List<Invoice> list = Collections.emptyList();
        try {
            Map m = new HashMap();
            m.put("hidden", false);
            list = genericDao.getByNamedQuery("invoice_all", m);
        } catch (DataAccessException dataAccessException) {
            log.error("error getting Invoices", dataAccessException);
        }
        return list;
    }

    @Override
    public Invoice getInvoice(Long id) {
        return new Invoice(1L);
    }
}