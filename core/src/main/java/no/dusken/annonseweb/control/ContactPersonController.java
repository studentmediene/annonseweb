package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.ContactPerson;
import no.dusken.annonseweb.service.ContactPersonService;
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


@Controller
@RequestMapping("/contact/person")
public class ContactPersonController {

    @Autowired
    private ContactPersonService contactPersonService;

    @RequestMapping("")
    public String viewContactsHome(){
        return "contact/home";
    }

    @RequestMapping("/active")
    public String allActive(Model model){
        List<ContactPerson> contactPersonList = contactPersonService.findAll();
        for(ContactPerson contactPerson: contactPersonList){
            if(!contactPerson.getActive()) contactPersonList.remove(contactPerson);
        }
        model.addAttribute("contactPersonList", contactPersonList);
        return "contact/person/all";
    }

    @RequestMapping("/all")
    public String all(Model model){
        List<ContactPerson> contactPersonList = contactPersonService.findAll();
        model.addAttribute("contactPersonList", contactPersonList);
        return "contact/person/all";
    }

    @RequestMapping(value="/{Id}")
    public String viewContactPerson(@PathVariable Long Id, Model model){
        ContactPerson contactPerson = contactPersonService.findOne(Id);
        model.addAttribute("contact", contactPerson);
        return "contact/person/person";
    }

    @RequestMapping("/new")
    public String newContactPerson(){
        return "contact/person/new";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addContactPerson(@Valid @ModelAttribute("newContactPerson")ContactPerson contactPerson){
        contactPersonService.save(contactPerson);
        return "contact/person/person";
    }

    @RequestMapping("/emailList")
    public String viewEmailsForCustomersContactPersons(Model model){
        List<String> emailList = new ArrayList<String>();
        for(ContactPerson contactPerson: contactPersonService.findAll()){
            emailList.add(contactPerson.getEmail());
        }
        model.addAttribute("emailList", emailList);
        return "contact/person/emailList";
    }

}
