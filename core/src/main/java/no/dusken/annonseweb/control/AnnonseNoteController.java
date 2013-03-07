package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.AnnonseNote;
import no.dusken.annonseweb.service.AnnonseNoteService;
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
 * <code>ContactNoteController</code> keeps track of <code>AnnonseNote</code>s.
 * @author Inge Halsaunet
 */
@Controller
@RequestMapping("/note")
public class AnnonseNoteController {

    @Autowired
    private AnnonseNoteService annonseNoteService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping()
    public String viewHome() {
        return "note/home";
    }

    @RequestMapping("/all")
    public String allContacts(Model model){
        List<AnnonseNote> annonseNotes = annonseNoteService.findAll();
        model.addAttribute("contactPersonList", annonseNotes);
        return "note/all";
    }

    @RequestMapping(value="/{Id}")
    public String viewContactNote(@PathVariable Long Id, Model model){
        model.addAttribute("contact", annonseNoteService.findOne(Id));
        return "note/note";
    }

    @RequestMapping("/new")
    public String newContact(){
        return "note/new";
    }

    @RequestMapping("/new/{Id}")
    public String newContactWithCustomerID(@PathVariable Long Id,Model model){
        model.addAttribute("customer", customerService.findOne(Id));
        return "note/new";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addContactNote(@Valid @ModelAttribute("annonseNote")AnnonseNote annonseNote){
        annonseNoteService.save(annonseNote);
        return "note";
    }
}
