package no.dusken.annonseweb.service;

import no.dusken.annonseweb.models.Sale;
import no.dusken.common.service.GenericService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesService extends GenericService<Sale>{

    @Query("select s from Sale s where s.active = true")
    public List<Sale> getActiveSales();

    @Query("select s from Sale s where s.active = false")
    public List<Sale> getNotActiveSales();
}
