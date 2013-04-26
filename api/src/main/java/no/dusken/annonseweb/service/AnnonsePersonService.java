package no.dusken.annonseweb.service;

import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.common.service.GenericService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnonsePersonService extends GenericService<AnnonsePerson> {
    public AnnonsePerson getByUsername(String username);

    @Query("select ap from AnnonsePerson ap where ap.active = true")
    public List<AnnonsePerson> getActiveAnnonsePersons();

    @Query("select ap from AnnonsePerson ap where ap.active = false")
    public List<AnnonsePerson> getNotActiveAnnonsePersons();
}
