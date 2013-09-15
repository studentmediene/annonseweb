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

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The <code>ResolveCalendarEditor</code> is a <code>PropertyEditor</code> made for resolving <code>Calendar</code>
 * objects from strings. Used in connection with html5 tag input, type: datetime-local.
 *
 * @author Inge Halsaunet
 *
 */
public class ResolveCalendarEditor extends PropertyEditorSupport {
    private SimpleDateFormat dateFormat;

    public ResolveCalendarEditor() {
       dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    }

    public ResolveCalendarEditor(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public void setAsText(String text) {
        Calendar time = Calendar.getInstance();
        try {
            time.setTime(dateFormat.parse(text));
        } catch (ParseException e) {
        }
        setValue(time);
    }

    @Override
    public String getAsText() {
        Calendar value = (Calendar) getValue();
        if (value != null) {
            return dateFormat.format(value.getTime());
        } else {
            return "";
        }
    }
}
