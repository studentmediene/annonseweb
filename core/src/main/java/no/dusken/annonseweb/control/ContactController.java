package no.dusken.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/home")
    public String viewContactsHome(){
        return "contacts/home";
    }

    @RequestMapping("/all")
    public String all(Model model){

        return "contacts/all";
    }

    @RequestMapping("/search")
    public String search(){
        return "contacts/search";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contacts/contact";
    }

    @RequestMapping("/newContact")
    public String newContact(){
        return "contacts/newContact";
    }

    @RequestMapping("/person")
    public String viewContactPerson(){
        return "contacts/person";
    }

    @RequestMapping("/newPerson")
    public String newContactPerson(){
        return "contacts/newPerson";
    }
}
