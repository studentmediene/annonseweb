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

package customeditors;

import no.dusken.annonseweb.service.AnnonsePersonService;
import no.dusken.common.model.Person;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Inge
 * Date: 28.03.13
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class ResolveByUsernameEditor extends PropertyEditorSupport {
    private AnnonsePersonService annonsePersonService;

    public ResolveByUsernameEditor(AnnonsePersonService annonsePersonService) {
        this.annonsePersonService = annonsePersonService;
    }
    @Override
    public void setAsText(String text) {
        setValue(annonsePersonService.getByUsername(text));
    }

    @Override
    public String getAsText() {
        Person value = (Person) getValue();
        if (value != null)
            return  value.getUsername();
        else
            return "";
    }
}
