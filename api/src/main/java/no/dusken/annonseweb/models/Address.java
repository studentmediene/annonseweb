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

package no.dusken.annonseweb.models;

import no.dusken.common.model.DuskenObject;

import javax.persistence.Entity;

@Entity
public class Address extends DuskenObject{

    //TODO: se issue on github.
    private String personName;
    private String streetName;
    private String zipCode;
    private String zipCounty;

    public Address() {}

    public Address(String personName, String streetName, String zipCode, String zipCounty) {
        this.personName = personName;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.zipCounty = zipCounty;
    }

    public String getPersonName() {
        return personName;
    }

    @Override
    public String getTitle() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getStreetName() {
        return streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getZipCounty() {
        return zipCounty;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setZipCounty(String zipCounty) {
        this.zipCounty = zipCounty;
    }
}
