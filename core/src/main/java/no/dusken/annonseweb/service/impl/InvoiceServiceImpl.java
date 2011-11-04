package no.dusken.annonseweb.service.impl;

import no.dusken.annonseweb.models.Invoice;
import no.dusken.annonseweb.service.InvoiceService;
import no.dusken.common.service.impl.GenericServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
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

        /* for testing purposes, to be removed later. WHen the database is up and working.
        ArrayList<Invoice> list = new ArrayList<Invoice>();
        for(int i=0; i<10;i++){
            Invoice invoice = new Invoice(i);
            list.add(invoice);
        }                        */
        return list;
    }

    @Override
    public Invoice getInvoice(Long id) {
        return new Invoice(1298L);
    }
}