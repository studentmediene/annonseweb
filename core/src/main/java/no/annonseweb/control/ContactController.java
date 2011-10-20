package no.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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



    @RequestMapping("/contactsHome")
    public String viewContactsHome(){
        return "no/dusken/annonseweb/web/contacts/contactHome";
    }

    @RequestMapping("/viewContacts")
    public String viewContacts(){
        return "no/dusken/annonseweb/web/contacts/viewContact";
    }

    @RequestMapping("/newContact")
    public String viewNewContacts(){
        return "no/dusken/annonseweb/web/contacts/newContact";
    }

    @RequestMapping("/newContactPerson")
    public String viewNewContactPerson(){
        return "no/dusken/annonseweb/web/contacts/newContactPerson";
    }

    @RequestMapping("/searchContact")
    public String viewSearchContacts(){
        return "no/dusken/annonseweb/web/contacts/searchContact";
    }

    @RequestMapping("/viewContactPersons")
    public String viewContactPersons(){
        return "no/dusken/annonseweb/web/contacts/viewContactPersons";
    }
}
