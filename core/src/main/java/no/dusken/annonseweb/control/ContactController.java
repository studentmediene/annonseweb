package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.ContactNote;
import no.dusken.annonseweb.models.ContactPerson;
import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.service.ContactNoteService;
import no.dusken.annonseweb.service.ContactPersonService;
import no.dusken.annonseweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Emilie Brunsgaard Ek
 * Email: embrek@underdusken.no
 * Date: 13.10.11
 * Time: 19:32
 */
@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactPersonService contactPersonService;

    @Autowired
    private ContactNoteService contactNoteService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("")
    public String viewContactsHome(){
        return "contacts/home";
    }

    @RequestMapping("/contactNote/all")
    public String allContacts(Model model){
        List<ContactNote> contactNotes = contactNoteService.findAll();
        model.addAttribute("contactPersonList", contactNotes);
        return "contacts/allContactNotes";
    }

    @RequestMapping("/contactPerson/active")
    public String allActive(Model model){
        List<ContactPerson> contactPersonList = contactPersonService.findAll();
        for(ContactPerson contactPerson: contactPersonList){
            if(!contactPerson.getActive()) contactPersonList.remove(contactPerson);
        }
        model.addAttribute("contactPersonList", contactPersonList);
        return "contacts/all";
    }

    @RequestMapping("/contactPerson/all")
    public String all(Model model){
        List<ContactPerson> contactPersonList = contactPersonService.findAll();
        model.addAttribute("contactPersonList", contactPersonList);
        return "contacts/allContactPersons";
    }

    @RequestMapping(value="/contactNote/{Id}")
    public String viewContactNote(@PathVariable Long Id, Model model){
        model.addAttribute("contact", contactNoteService.findOne(Id));
        return "contacts/contactNote";
    }

    @RequestMapping("/contactNote/new")
    public String newContact(){
        return "contacts/newContactNote";
    }

    @RequestMapping("/contactNote/new/{Id}")
    public String newContactWithCustomerID(@PathVariable Long Id,Model model){
        model.addAttribute("customer", customerService.findOne(Id));
        return "contacts/newContactNote";
    }

    @RequestMapping(value="/contactNote/add", method = RequestMethod.POST)
    public String addContactNote(@Valid @ModelAttribute("contactNote")ContactNote contactNote){
        contactNoteService.save(contactNote);
        return "contacts/contactNote";
    }

    @RequestMapping(value="/contactPerson/{Id}")
    public String viewContactPerson(@PathVariable Long Id, Model model){
        ContactPerson contactPerson = contactPersonService.findOne(Id);
        model.addAttribute("contact", contactPerson);
        return "contacts/contactPerson";
    }

    @RequestMapping("/contactPerson/new")
    public String newContactPerson(){
        return "contacts/newContactPerson";
    }

    @RequestMapping(value="/contactPerson/add", method = RequestMethod.POST)
    public String addContactPerson(@Valid @ModelAttribute("contactPerson")ContactPerson contactPerson){
        contactPersonService.save(contactPerson);
        return "contacts/contactPerson";
    }

    @RequestMapping("/emailList")
    public String viewEmailsCustomersContact(Model model){
        List<String> emailList = new ArrayList<String>();
        for(ContactPerson contactPerson: contactPersonService.findAll()){
            emailList.add(contactPerson.getEmail());
        }
        model.addAttribute("emailList", emailList);
        return "customers/emailList";
    }

}
