package no.dusken.annonseweb.control;

import customeditors.ResolveCalendarEditor;
import no.dusken.annonseweb.models.*;
import no.dusken.annonseweb.service.*;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <code>ContactNoteController</code> keeps track of <code>AnnonseNote</code>s.
 *
 * @author Inge Halsaunet
 */
@Controller
@RequestMapping("/note")
public class AnnonseNoteController {

    @Autowired
    private AnnonseNoteService annonseNoteService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SalesService salesService;

    @Autowired
    private ContactPersonService contactPersonService;

    @Autowired
    private AnnonsePersonService annonsePersonService;

    @Autowired
    private AnnonsePersonController annonsePersonController;

    @RequestMapping()
    public String viewHome() {
        return "note/home";
    }

    @RequestMapping("/archive")
    public String viewArchivedNotes(Model model) {
        // TODO make comparator, sort list and remove if there are too many entries.
        ArrayList<AnnonseNote> aList = new ArrayList<AnnonseNote>();
        List<AnnonseNote> list = annonsePersonController.getLoggedInUser().getMyNotes();
        if (list!=null)
            for (AnnonseNote note:list)
                if (note != null && !note.getActive())
                    aList.add(note);
        list = annonsePersonController.getLoggedInUser().getDelegatedNotes();
        if (list!=null)
            for (AnnonseNote note:list)
                if (note != null && !note.getActive())
                    aList.add(note);
        model.addAttribute("annonseNoteList", aList);
        model.addAttribute("active", false);
        return "note/list";
    }

    @RequestMapping("/archive/all")
    public String viewAllArchivedNotes(Model model) {
        ArrayList<AnnonseNote> aList = new ArrayList<AnnonseNote>();
        List<AnnonseNote> list = annonsePersonController.getLoggedInUser().getMyNotes();
        if (list!=null)
            for (AnnonseNote note:list)
                if (note != null && !note.getActive())
                    aList.add(note);
        list = annonsePersonController.getLoggedInUser().getDelegatedNotes();
        if (list!=null)
            for (AnnonseNote note:list)
                if (note != null && !note.getActive())
                    aList.add(note);
        model.addAttribute("annonseNoteList", aList);
        model.addAttribute("active", false);
        return "note/list";
    }

    @RequestMapping("/doarchive/{annonseNote}")
    public String doArchive(@PathVariable AnnonseNote annonseNote) {
        annonseNote.setActive(Boolean.FALSE);
        annonseNoteService.saveAndFlush(annonseNote);
        return "redirect:/annonse/note/" + annonseNote.getId();
    }

    @RequestMapping("/active")
    public String viewActiveNotes(Model model) {
        ArrayList<AnnonseNote> aList = new ArrayList<AnnonseNote>();
        List<AnnonseNote> list = annonsePersonController.getLoggedInUser().getMyNotes();
        if (list!=null)
            for (AnnonseNote note:list)
                if (note != null && note.getActive())
                    aList.add(note);
        list = annonsePersonController.getLoggedInUser().getDelegatedNotes();
        if (list!=null)
            for (AnnonseNote note:list)
                if (note != null && note.getActive())
                    aList.add(note);
        model.addAttribute("annonseNoteList", aList);
        model.addAttribute("active", true);
        return "note/list";
    }

    @RequestMapping("/{annonseNote}")
    public String viewAnnonseNote(@PathVariable AnnonseNote annonseNote, Model model) {
        model.addAttribute("annonseNote", annonseNote);
        return "note/note";
    }

    @RequestMapping("/new")
    public String viewNew(Model model) {
        AnnonseNote note = new AnnonseNote();
        return viewEdit(note, model);
    }

    @RequestMapping("/new/sale/{sale}")
    public String viewNewWithSale(@PathVariable Sale sale, Model model) {
        AnnonseNote note = new AnnonseNote();
        note.setSale(sale);
        return viewEdit(note, model);
    }

    @RequestMapping("/new/customer/{customer}")
    public String viewNewWithCustomer(@PathVariable Customer customer, Model model) {
        AnnonseNote note = new AnnonseNote();
        note.setCustomer(customer);
        return viewEdit(note, model);
    }

    @RequestMapping("/new/contactperson/{contactPerson}")
    public String viewNewWithContactPerson(@PathVariable ContactPerson contactPerson, Model model) {
        AnnonseNote note = new AnnonseNote();
        note.setCustomer(contactPerson.getCustomer());
        note.setContactPerson(contactPerson);
        return viewEdit(note, model);
    }

    @RequestMapping("/edit/{annonseNote}")
    public String viewEdit(@PathVariable AnnonseNote annonseNote, Model model) {
        List<AnnonsePerson> uList = annonsePersonService.findAll();
        uList.add(null);
        model.addAttribute("annonseNote", annonseNote);
        model.addAttribute("userList", uList);
        return"note/edit";
    }

    @RequestMapping("/save")
    public String saveNew(@Valid @ModelAttribute AnnonseNote annonseNote) {
        annonseNote.setCreatedUser(annonsePersonController.getLoggedInUser());
        annonseNote.setCreatedDate(Calendar.getInstance());
        annonseNoteService.saveAndFlush(annonseNote);
        return "redirect:/annonse/note/" + annonseNote.getId();
    }

    @RequestMapping("/save/{pathAnnonseNote}")
    public String saveEdit(@PathVariable AnnonseNote pathAnnonseNote, @Valid @ModelAttribute AnnonseNote annonseNote) {
        pathAnnonseNote.setActive(annonseNote.getActive());
        pathAnnonseNote.setContactPerson(annonseNote.getContactPerson());
        pathAnnonseNote.setCustomer(annonseNote.getCustomer());
        pathAnnonseNote.setDelegatedUser(annonseNote.getDelegatedUser());
        pathAnnonseNote.setDueDate(annonseNote.getDueDate());
        pathAnnonseNote.setSale(annonseNote.getSale());
        pathAnnonseNote.setText(annonseNote.getText());
        annonseNoteService.saveAndFlush(pathAnnonseNote);
        return "redirect:/annonse/note/" + pathAnnonseNote.getId();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(AnnonseNote.class, new BindByIdEditor(annonseNoteService));
        binder.registerCustomEditor(Sale.class, new BindByIdEditor(salesService));
        binder.registerCustomEditor(Customer.class, new BindByIdEditor(customerService));
        binder.registerCustomEditor(ContactPerson.class, new BindByIdEditor(contactPersonService));
        binder.registerCustomEditor(AnnonsePerson.class, new BindByIdEditor(annonsePersonService));
        binder.registerCustomEditor(Calendar.class, new ResolveCalendarEditor());
    }
}
