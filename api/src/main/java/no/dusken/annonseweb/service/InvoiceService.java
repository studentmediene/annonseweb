package no.dusken.annonseweb.service;

import no.dusken.annonseweb.models.Invoice;
import no.dusken.common.service.GenericService;

import java.util.List;

public interface InvoiceService extends GenericService<Invoice> {

    /**
     *
     * @return List<Invoice> - a list of all the invoices.
     */
    public List<Invoice> getInvoices();

}
