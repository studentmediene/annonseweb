package no.annonseweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: maskinist
 * Date: 13/04/11
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class QuickChoiceController {

    @RequestMapping("/quickChoice")
    public String quickChoice(){
        return "no/dusken/annonseweb/web/quickChoice";
    }

}
