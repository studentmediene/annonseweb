package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.annonseweb.service.AnnonsePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The <code>AnnonsePersonController</code> is a class created for management of <code>AnnonsePerson</code>s.
 * It is thought only to be temporary, until a common db for MediaStud is created.
 *
 * @author Sitron Te
 */
@Controller
@RequestMapping("/")
public class AnnonsePersonController {
    @Autowired
    private AnnonsePersonService annonsePersonService;

    public AnnonsePerson getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnnonsePerson)
            return (AnnonsePerson) auth;
        return null;
    }
}
