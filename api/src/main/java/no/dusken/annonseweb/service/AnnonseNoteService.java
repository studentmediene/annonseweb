package no.dusken.annonseweb.service;

/**
 * Under Dusken - underdusken.no - https://github.com/dusken/
 * Magnus Kirø - magnuskiro@underdusken.no
 * 16.12.11
 */

import no.dusken.annonseweb.models.AnnonseNote;
import no.dusken.annonseweb.models.AnnonsePerson;
import no.dusken.common.service.GenericService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface AnnonseNoteService extends GenericService<AnnonseNote> {

    @Query("select an from AnnonseNote an where an.active = true and (an.createdUser = :user or an.delegatedUser = :user)")
    public List<AnnonseNote> getUserAndDelegatedActiveAnnonseNotes(@Param("user") AnnonsePerson user);

    @Query("select an from AnnonseNote an where an.active = false and (an.createdUser = :user or an.delegatedUser = :user)")
    public List<AnnonseNote> getUserAndDelegatedNotActiveAnnonseNotes(@Param("user") AnnonsePerson user);

    @Query("select an from AnnonseNote an where an.createdUser = :user and an.active = true and an.dueDate >= :after")
    public List<AnnonseNote> getUserActiveAnnonseNotesAfterDate(@Param("user")AnnonsePerson user, @Param("after")Calendar after);

    @Query("select an from AnnonseNote an where an.delegatedUser = :delegated and an.active = true and an.dueDate >= :after")
    public List<AnnonseNote> getDelegatedActiveAnnonseNotesAfterDate(@Param("delegated")AnnonsePerson user, @Param("after")Calendar after);

    @Query("select an from AnnonseNote an where (an.createdUser = :user or an.delegatedUser = :user) and an.active = true and an.dueDate < :before")
    public List<AnnonseNote> getUserAndDelegatedActiveAnnonseNotesBeforeDate(@Param("user")AnnonsePerson user, @Param("before")Calendar before);
}
