package no.dusken.annonseweb.service;

import no.dusken.annonseweb.models.Invoice;
import no.dusken.common.service.GenericService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: maskinist
 * Date: 14/04/11
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public interface InvoiceService{

    /**
     *
     * @return List<Invoice> - a list of all the invoices.
     */
    public List<Invoice> getInvoices();

    /**
     *
     * @param id - the id of the invoice to get.
     * @return an invoice
     */
    public Invoice getInvoice(Long id);

}
