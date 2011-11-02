package no.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
    @RequestMapping("/menu")
    public String ViewMenu(){
        return "no/dusken/annonseweb/web/dropmenu";
    }

}
