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
public class ContactController {

    @RequestMapping("/viewContacts")
    public String viewContacts(){
        return "no/dusken/annonseweb/web/contacts/viewContact";
    }

    @RequestMapping("/ContactsHome")
    public String viewContactsHome(){
        return "no/dusken/annonseweb/web/contacts/ContactHome";
    }
}
