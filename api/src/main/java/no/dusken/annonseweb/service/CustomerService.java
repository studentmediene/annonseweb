package no.dusken.annonseweb.service;

import no.dusken.annonseweb.models.Customer;
import no.dusken.common.service.GenericService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerService extends GenericService<Customer>{

    @Query("select c from Customer c where c.active = true")
    public List<Customer> getActiveCustomers();

    @Query("select c from Customer c where c.active = false")
    public List<Customer> getNotActiveCustomers();
}
