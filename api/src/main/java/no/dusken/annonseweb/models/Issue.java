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

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

/**
 * @author Marvin B. Lillehaug <lillehau@underdusken.no>
 */
@Entity
public class Issue extends DuskenObject {
    private Integer issueNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fromDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar toDate;

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "issue")
    private List<PrintedAd> printedAds;

    @Override
    public String getTitle() {
        return null;
    }

    public Integer getIssueNumber(){
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber){
        this.issueNumber = issueNumber;
    }
}
