package no.dusken.annonseweb.service;


import no.dusken.annonseweb.models.Customer;
import no.dusken.common.service.GenericService;

import java.util.List;


public interface CustomerService extends GenericService<Customer>{

    public List<Customer> getCustomers();


}
