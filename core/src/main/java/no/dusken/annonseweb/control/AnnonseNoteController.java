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
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
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
        List<Sale> sList = salesService.findAll();
        sList.add(null);
        List<Customer> cList = customerService.findAll();
        cList.add(null);
        if (annonseNote.getCustomer() != null) {
            List<ContactPerson> pList = annonseNote.getCustomer().getContactPersons();
            pList.add(null);
            model.addAttribute("contactPersonList", pList);
        }
        model.addAttribute("annonseNote", annonseNote);
        model.addAttribute("userList", uList);
        model.addAttribute("saleList", sList);
        model.addAttribute("customerList", cList);
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

    @RequestMapping("/sidebar_notes")
    public void viewSidebarNotes(Writer writer) {
        List<AnnonseNote> expiredNotes = new ArrayList<AnnonseNote>();

        // todo check this out.
        // can be improved with db queries. expired notes array can be removed.

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM.yyyy - HH:mm:");
        String response = "<ul class=\"tasks\">Mine påminnere:";
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        for (AnnonseNote note: annonsePersonController.getLoggedInUser().getMyNotes()) {
            if (note.getActive() && note.getDueDate() != null) {
                if (note.getDueDate().after(yesterday)) {
                    response += "<li class=\"task\"><a href=\"/annonse/note/" + note.getId() + "\">";
                    response += dateFormat.format(note.getDueDate().getTime()) + "</a>";
                    if (note.getCustomer() != null) {
                        response += "<br /><a href=\"/annonse/customer/" + note.getCustomer().getId() + "\">";
                        response += "Kunde: " + note.getCustomer().getName() + "</a>";
                    }
                    if (note.getContactPerson() != null) {
                        response += "<br /><a href=\"/annonse/contactperson/" + note.getContactPerson().getId() + "\">";
                        response += "Kontakt: " + note.getContactPerson().getPersonName() + "</a>";
                    }
                    if (note.getSale() != null) {
                        response += "<br /><a href=\"/annonse/sale/" + note.getSale().getId() + "\">";
                        response += "Salg: " + note.getSale().getDescription() + "</a>";
                    }
                    response += "<p class=\"guidelines\"><a href=\"/annonse/note/"+note.getId()+"\">Vis</a>";
                    response += "<br /><a href=\"/annonse/note/edit/" + note.getId() + "\">Endre</a>";
                    response += "<br /><a href=\"/annonse/note/doarchive/" + note.getId();
                    response += "\">Arkiver</a></p></li>";
                } else {
                    expiredNotes.add(note);
                }
            }
        }
        response += "<br />Tildelte påminnere:";
        for (AnnonseNote note: annonsePersonController.getLoggedInUser().getDelegatedNotes()) {
            if (note.getActive() && note.getDueDate() != null) {
                if (note.getDueDate().after(yesterday)) {
                    response += "<li class=\"task\"><a href=\"/annonse/note/" + note.getId() + "\">";
                    response += dateFormat.format(note.getDueDate().getTime());
                    response += "</a><br />Fra: "+note.getCreatedUser().getName();
                    if (note.getCustomer() != null) {
                        response += "<br /><a href=\"/annonse/customer/" + note.getCustomer().getId() + "\">";
                        response += "Kunde: " + note.getCustomer().getName() + "</a>";
                    }
                    if (note.getContactPerson() != null) {
                        response += "<br /><a href=\"/annonse/contactperson/" + note.getContactPerson().getId() + "\">";
                        response += "Kontakt: " + note.getContactPerson().getPersonName() + "</a>";
                    }
                    if (note.getSale() != null) {
                        response += "<br /><a href=\"/annonse/sale/" + note.getSale().getId() + "\">";
                        response += "Salg: " + note.getSale().getDescription() + "</a>";
                    }
                    response += "<p class=\"guidelines\"><a href=\"/annonse/note/"+note.getId()+"\">Vis</a>";
                    response += "<br /><a href=\"/annonse/note/edit/" + note.getId() + "\">Endre</a>";
                    response += "<br /><a href=\"/annonse/note/doarchive/" + note.getId();
                    response += "\">Arkiver</a></p></li>";
                } else {
                    expiredNotes.add(note);
                }
            }
        }
        response += "<br />Utgåtte påminnere:";
        for (AnnonseNote note: expiredNotes) {
            response += "<li class=\"task\"><a href=\"/annonse/note/" + note.getId() + "\">";
            response += dateFormat.format(note.getDueDate().getTime()) + "</a>";
            if (note.getCustomer() != null) {
                response += "<br /><a href=\"/annonse/customer/" + note.getCustomer().getId() + "\">";
                response += "Kunde: " + note.getCustomer().getName() + "</a>";
            }
            if (note.getContactPerson() != null) {
                response += "<br /><a href=\"/annonse/contactperson/" + note.getContactPerson().getId() + "\">";
                response += "Kontakt: " + note.getContactPerson().getPersonName() + "</a>";
            }
            if (note.getSale() != null) {
                response += "<br /><a href=\"/annonse/sale/" + note.getSale().getId() + "\">";
                response += "Salg: " + note.getSale().getDescription() + "</a>";
            }
            response += "<p class=\"guidelines\"><a href=\"/annonse/note/"+note.getId()+"\">Vis</a>";
            response += "<br /><a href=\"/annonse/note/edit/" + note.getId() + "\">Endre</a>";
            response += "<br /><a href=\"/annonse/note/doarchive/" + note.getId();
            response += "\">Arkiver</a></p></li>";
        }
        response += "</ul>";
        try {
            writer.write(response);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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
