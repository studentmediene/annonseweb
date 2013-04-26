package no.dusken.annonseweb.service;

import no.dusken.annonseweb.models.Ad;
import no.dusken.common.service.GenericService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdService extends GenericService<Ad> {

    @Query("select a from Ad a where a.active = true")
    public List<Ad> getActiveAds();

    @Query("select a from Ad a where a.active = false")
    public List<Ad> getNotActiveAds();
}
