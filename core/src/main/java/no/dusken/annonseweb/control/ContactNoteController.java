package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.ContactNote;
import no.dusken.annonseweb.service.ContactNoteService;
import no.dusken.annonseweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * <code>ContactNoteController</code> keeps track of <code>ContactNote</code>s.
 * @author Inge Halsaunet
 */
@Controller
@RequestMapping("/contact/note")
public class ContactNoteController {

    @Autowired
    private ContactNoteService contactNoteService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/all")
    public String allContacts(Model model){
        List<ContactNote> contactNotes = contactNoteService.findAll();
        model.addAttribute("contactPersonList", contactNotes);
        return "contact/note/all";
    }

    @RequestMapping(value="/{Id}")
    public String viewContactNote(@PathVariable Long Id, Model model){
        model.addAttribute("contact", contactNoteService.findOne(Id));
        return "contact/note/note";
    }

    @RequestMapping("/new")
    public String newContact(){
        return "contact/note/new";
    }

    @RequestMapping("/new/{Id}")
    public String newContactWithCustomerID(@PathVariable Long Id,Model model){
        model.addAttribute("customer", customerService.findOne(Id));
        return "contact/note/new";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addContactNote(@Valid @ModelAttribute("contactNote")ContactNote contactNote){
        contactNoteService.save(contactNote);
        return "contact/note";
    }
}
