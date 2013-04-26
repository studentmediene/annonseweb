package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.annonseweb.models.ContactPerson;
import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.service.ContactPersonService;
import no.dusken.annonseweb.service.CustomerService;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Controller
@RequestMapping("/contactperson")
public class ContactPersonController {

    @Autowired
    private ContactPersonService contactPersonService;

    @Autowired
    private AnnonsePersonController annonsePersonController;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("")
    public String viewContactsHome(){
        return "contactperson/home";
    }

    @RequestMapping("/active")
    public String allActive(Model model){
        model.addAttribute("contactPersonList", contactPersonService.getActiveContactPersons());
        return "contactperson/all";
    }

    @RequestMapping("/all")
    public String all(Model model){
        model.addAttribute("contactPersonList", contactPersonService.findAll());
        return "contactperson/all";
    }

    @RequestMapping(value="/{contactPerson}")
    public String viewContactPerson(@PathVariable ContactPerson contactPerson, Model model){
        model.addAttribute("contact", contactPerson);
        return "contactperson/person";
    }

    @RequestMapping("/new")
    public String newContactPerson(Model model){
        return viewEdit(new ContactPerson(), model);
    }

    @RequestMapping("/new/{customer}")
    public String newContactPersonCustomer(@PathVariable Customer customer, Model model) {
        ContactPerson p = new ContactPerson();
        p.setCustomer(customer);
        return viewEdit(p, model);
    }

    @RequestMapping("/edit/{contactPerson}")
    public String viewEdit(@PathVariable ContactPerson contactPerson, Model model) {
        model.addAttribute("contactPerson", contactPerson);
        model.addAttribute("customerList", customerService.findAll());
        return "contactperson/edit";
    }

    @RequestMapping(value = "/save",  method = RequestMethod.POST)
    public String storeNewContactPerson(@Valid @ModelAttribute ContactPerson contactPerson) {
        AnnonsePerson usr = annonsePersonController.getLoggedInUser();
        contactPerson.setCreatedDate(Calendar.getInstance());
        contactPerson.setLastEditedUser(usr);
        contactPerson.setCreatedUser(usr);
        contactPerson.setLastEditedTime(Calendar.getInstance());
        contactPersonService.saveAndFlush(contactPerson);
        return "redirect:/annonseweb/contactperson/" + contactPerson.getId();
    }

    @RequestMapping(value = "/save/{pathContactPerson}",  method = RequestMethod.POST)
    public String editContactPerson(@Valid @ModelAttribute ContactPerson contactPerson,
                                    @PathVariable ContactPerson pathContactPerson) {
        pathContactPerson.copyInformationFrom(contactPerson);
        pathContactPerson.setLastEditedTime(Calendar.getInstance());
        pathContactPerson.setLastEditedUser(annonsePersonController.getLoggedInUser());
        contactPersonService.saveAndFlush(pathContactPerson);
        return "redirect:/annonseweb/contactperson/" + pathContactPerson.getId();
    }

    @RequestMapping("/emailList")
    public String viewEmailsForCustomersContactPersons(Model model){
        List<String> emailList = new ArrayList<String>();
        for(ContactPerson contactPerson: contactPersonService.findAll()){
            emailList.add(contactPerson.getEmail());
        }
        model.addAttribute("emailList", emailList);
        return "contactperson/emailList";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(ContactPerson.class, new BindByIdEditor(contactPersonService));
        binder.registerCustomEditor(Customer.class, new BindByIdEditor(customerService));
    }
}
