package no.dusken.annonseweb.spring;

import no.dusken.annonseweb.models.*;
import no.dusken.annonseweb.service.*;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class ModelEditorConfigurer implements WebBindingInitializer {

    @Autowired
    private AdService adService;
    @Autowired
    private ContactNoteService contactNoteService;
    @Autowired
    private ContactPersonService contactPersonSerive;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private SalesService salesService;

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Ad.class, new BindByIdEditor(adService));
        binder.registerCustomEditor(ContactNote.class, new BindByIdEditor(contactNoteService));
        binder.registerCustomEditor(ContactPerson.class, new BindByIdEditor(contactPersonSerive));
        binder.registerCustomEditor(Customer.class, new BindByIdEditor(customerService));
        binder.registerCustomEditor(Invoice.class, new BindByIdEditor(invoiceService));
        binder.registerCustomEditor(Sale.class, new BindByIdEditor(salesService));
    }
}
