package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.annonseweb.models.RoleAuth;
import no.dusken.annonseweb.service.AnnonsePersonService;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The <code>AnnonsePersonController</code> is a class created for management of <code>AnnonsePerson</code>s.
 * It is thought only to be temporary, until a common db for MediaStud is created.
 *
 * @author Sitron Te
 */
@Controller
@RequestMapping("/user")
public class AnnonsePersonController {
    @Autowired
    private AnnonsePersonService annonsePersonService;

    public AnnonsePerson getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnnonsePerson)
            return (AnnonsePerson) auth;
        return null;
    }

    @RequestMapping("/addUser")
    public String addUser(Model model) {
        AnnonsePerson usr = new AnnonsePerson();
        usr.setPrincipal("");
        usr.setCredentials("");
        usr.setAuthority(RoleAuth.ANNONSE_ARBEIDER.toString());
        model.addAttribute("user", usr);
        return edit(model);
    }

    @RequestMapping("/edit/{user}")
    public String editUser(Model model, @PathVariable AnnonsePerson user) {
        model.addAttribute("user", user);
        return edit(model);
    }

    private String edit(Model model) {
        ArrayList<String> aList = new ArrayList<String>();
        for (RoleAuth r:RoleAuth.values())
            aList.add(r.toString());
        model.addAttribute("authorityList", aList);
        model.addAttribute("activeList", Arrays.asList(true, false));
        return "/user/edit";
    }

    @RequestMapping("/me")
    public String viewMe(Model model) {
        model.addAttribute("loggedIn", getLoggedInUser());
        return "/user/me";
    }

    @RequestMapping("/all")
    public String viewAll(Model model) {
        model.addAttribute("userList", annonsePersonService.findAll());
        return "/user/all";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNew(@Valid @ModelAttribute AnnonsePerson user) {
        annonsePersonService.saveAndFlush(user);
        return "redirect:/annonse/user/" + user.getId();
    }

    @RequestMapping("/save/{pathUser}")
    public String saveEdit(@PathVariable AnnonsePerson pathUser, @Valid @ModelAttribute AnnonsePerson user) {
        pathUser.setAuthority(user.getAuthority());
        // TODO Should password change be possible?
        pathUser.setActive(user.getActive());
        annonsePersonService.saveAndFlush(pathUser);
        return "redirect:/annonse/user/" + pathUser.getId();
    }

    @RequestMapping("/{user}")
    public String viewUser(Model model, @PathVariable AnnonsePerson user) {
        model.addAttribute("user", user);
        return "/user/viewUser";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(AnnonsePerson.class, new BindByIdEditor(annonsePersonService));
    }
}
