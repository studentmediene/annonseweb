package no.dusken.annonseweb.service;

import no.dusken.annonseweb.models.ContactPerson;
import no.dusken.common.service.GenericService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Under Dusken - underdusken.no - https://github.com/dusken/
 * Magnus Kirø - magnuskiro@underdusken.no
 * 04.12.11
 */
public interface ContactPersonService extends GenericService<ContactPerson>{
    @Query("select cp from ContactPerson cp where cp.active = true")
    public List<ContactPerson> getActiveContactPersons();

    @Query("select cp from ContactPerson cp where cp.active = false")
    public List<ContactPerson> getNotActiveContactPersons();
}
