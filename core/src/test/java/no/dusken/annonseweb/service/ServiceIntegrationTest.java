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

import no.dusken.annonseweb.models.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/integrationtest-jpa.xml"})
@TransactionConfiguration
@Transactional
public class ServiceIntegrationTest {

    @Autowired
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AnnonsePersonService annonsePersonService;

    @Autowired
    private AdService adService;

    @Test
    public void testSave(){


        Ad ad = new PrintedAd(new BigDecimal("100"), new BigDecimal("10"));
        Customer customer = new Customer("customer", "mail@mail.mail", "12345678", "address");
        // I save customer first, since at least first version of this program should not save more than one entity at a time
        customerService.saveAndFlush(customer);
        AnnonsePerson createdUser = getPerson();

        Sale s = new Sale("description", null, customer, null, false, false);;
        ad.setSale(s);
        Sale sale = salesService.saveAndFlush(s);

        assertNotNull(sale);
        assertNotNull(sale.getId());
        //assertEquals(ad, sale.getAds().get(0));
        assertEquals(customer, sale.getCustomer());


    }

    private AnnonsePerson getPerson() {
        AnnonsePerson createdUser = new AnnonsePerson("Users Name");
        annonsePersonService.saveAndFlush(createdUser);
        return createdUser;
    }

}
