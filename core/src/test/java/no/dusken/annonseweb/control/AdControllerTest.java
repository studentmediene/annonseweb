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

package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.Ad;
import no.dusken.annonseweb.models.Customer;
import no.dusken.annonseweb.models.Sale;
import no.dusken.annonseweb.service.CustomerService;
import no.dusken.annonseweb.service.SalesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 * @author Magnus Kirø - magnuskiro@ gmail.com/underdusken.no - 12/04/12
 * @author Inge Halsaunet - halsaune@stud.ntnu.no
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/integrationtest-jpa.xml"})
@TransactionConfiguration
@Transactional
public class AdControllerTest {

    @Autowired
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdController adController;

    private Model model;
    private Sale sale;
    private Customer customer;

    @Before
    public void setup() {
        customer =  new Customer("customerName", "centralEmail", "centralTlf", "invoiceAddress");
        customerService.saveAndFlush(customer);
        sale =  new Sale("description", null, customer, null, false, false);
        salesService.saveAndFlush(sale);
        model = new ExtendedModelMap();
    }

    @Test
    public void testViewAdHome() throws Exception {
        // TODO This should not be only assertTrue(true)!
        Assert.assertTrue(true);
    }

    @Test
    public void testViewNewAdGeneral() throws Exception {
        // TODO This should not be only assertTrue(true)!
        Assert.assertTrue(true);
    }

    @Test
    public void testSaveNewAdGeneral() throws Exception {
        // TODO This should not be only assertTrue(true)!
        Assert.assertTrue(true);
    }

    @Test
    public void testViewNewPrintedAdForSale() {
        String returnVal = adController.viewNewPrintedAdForSale(model, sale);
        Ad ad = null;
        Assert.assertEquals("viewNewPrintedAd did not return proper address", "ad/edit/printed", returnVal);
        try {
            ad = (Ad) model.asMap().get("ad");
        } catch (ClassCastException e) {
            Assert.assertTrue("Model was not populated with an Ad", false);
        }
        Assert.assertNotNull("Model was not populated with an Ad", ad);
        Assert.assertEquals("Model did not contain proper sale", sale, ad.getSale());
    }

    @Test
    public void testViewNewWebAdForSale() {
        String returnVal = adController.viewNewWebAdForSale(model, sale);
        Ad ad = null;
        Assert.assertEquals("viewNewWebAd did not return proper address", "ad/edit/web", returnVal);
        try {
            ad = (Ad) model.asMap().get("ad");
        } catch (ClassCastException e) {
            Assert.assertTrue("Model was not populated with an Ad", false);
        }
        Assert.assertNotNull("Model was not populated with an Ad", ad);
        Assert.assertEquals("Model did not contain proper sale", sale, ad.getSale());
    }

    @Test
    public void testViewNewRadioAdForSale() {
        String returnVal = adController.viewNewRadioAdForSale(model, sale);
        Ad ad = null;
        Assert.assertEquals("viewNewRadioAd did not return proper address", "ad/edit/radio", returnVal);
        try {
            ad = (Ad) model.asMap().get("ad");
        } catch (ClassCastException e) {
            Assert.assertTrue("Model was not populated with an Ad", false);
        }
        Assert.assertNotNull("Model was not populated with an Ad", ad);
        Assert.assertEquals("Model did not contain proper sale", sale, ad.getSale());
    }
}
