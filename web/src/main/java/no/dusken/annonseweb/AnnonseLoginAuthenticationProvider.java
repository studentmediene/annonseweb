package no.dusken.annonseweb;

import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.annonseweb.models.RoleAuth;
import no.dusken.annonseweb.service.AnnonsePersonService;
import no.dusken.common.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>AnnonseLoginAuthenticationProvider</code> is a class created for user log in.
 * It is thought only to be temporary, until a common db for MediaStud is created.
 *
 * @author Sitron Te
 */
public class AnnonseLoginAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AnnonsePersonService annonsePersonService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass()))
            return null;
        if (annonsePersonService.findAll().size() == 0) {
            // TODO Clean up
            AnnonsePerson p = new AnnonsePerson();
            p.setPrincipal("SuperDuper");
            // TODO Well, this works while password is not encrypted
            p.setCredentials( "SuperPass");
            p.setAuthority(RoleAuth.MASKINIST.toString());
            annonsePersonService.saveAndFlush(p);
            p.setAuthenticated(true);
            annonsePersonService.saveAndFlush(p);
            return p;
        }
        String userName = null, passWord = null;
        if (authentication.getPrincipal() instanceof String)
            userName = (String) authentication.getPrincipal();
        if (authentication.getCredentials() instanceof String)
            passWord = (String) authentication.getCredentials();
        if (userName == null || passWord == null) {
            // TODO After testing, remove debug output
            System.err.println("!!! --- User name or password could not be deducted --- !!!");
            return null;
        }

        // TODO Look into better ways to look up persons
        for (AnnonsePerson p: annonsePersonService.findAll()) {
            if (((String)p.getPrincipal()).equalsIgnoreCase(userName)) {
                if (p.getCredentials().equals(passWord)) {
                    try {
                        p.setAuthenticated(true);
                        return p;
                    } catch (IllegalArgumentException e) {
                        throw new UserAuthenticationException("Something strange happened...", e);
                    }
                } else {
                    throw new UserAuthenticationException("Password is wrong for this user!");
                }
            }
        }
        throw new UserAuthenticationException("Could not locate user!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == AnnonsePerson.class || authentication == UsernamePasswordAuthenticationToken.class;
    }

    private class UserAuthenticationException extends AuthenticationException {

        public UserAuthenticationException(String msg, Throwable cause) {
             super(msg, cause);
        }

        public UserAuthenticationException(String msg) {
            super(msg);
        }
    }
}
