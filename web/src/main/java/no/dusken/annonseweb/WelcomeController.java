package no.dusken.annonseweb;

import no.dusken.annonseweb.control.AnnonsePersonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WelcomeController{
    @Autowired
    private AnnonsePersonController annonsePersonController;
    @RequestMapping("welcome")
    public String viewWelcome(Model model) {
        model.addAttribute("loggedIn", annonsePersonController.getLoggedInUser());
        return "/welcome";
    }
}
