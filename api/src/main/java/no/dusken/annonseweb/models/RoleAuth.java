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

import org.springframework.security.core.GrantedAuthority;

/**
 * The <code>RoleAuth</code> enum is thought to be a temporary way to authorize users in annonseweb until
 * a common db is created for mediastud
 *
 * @author Sitron Te
 */
public enum RoleAuth implements GrantedAuthority {
    MASKINIST, ANNONSE_ANSVARLIG, ANNONSE_ARBEIDER
    ;

    @Override
    public String getAuthority() {
        switch (this) {
            case MASKINIST: return "ROLE_MASKINIST";
            case ANNONSE_ANSVARLIG: return "ROLE_ANNONSE_ANSVARLIG";
            case ANNONSE_ARBEIDER: return "ROLE_ANNONSE_ARBEIDER";
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
