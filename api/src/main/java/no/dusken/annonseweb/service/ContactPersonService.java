/*
 * Copyright 2013 Studentmediene i Trondheim AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.dusken.annonseweb.service;

import no.dusken.annonseweb.models.ContactPerson;
import no.dusken.annonseweb.models.Customer;
import no.dusken.common.service.GenericService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("select  cp from ContactPerson cp where cp.active = true and cp.customer = :customer")
    public List<ContactPerson> getActiveContactPersonsForCustomer(@Param("customer")Customer customer);
}
