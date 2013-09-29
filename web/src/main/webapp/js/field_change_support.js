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

/**
 * Makes it easier to update fields as one choose customer, or sale.
 *
 * @author Inge Edward Halsaunet
 */

/**
 * saleChange allows for dynamic update of selected customer in when editing notes
 */
function saleChange() {
    var saleId = $("#Sale option:selected").val();
    var fetchUrl = $("#update-customer-url").val() + saleId;
    $.post(fetchUrl, "", function(data, status) {
        if (status == "success") {
            $("#Customer").val(data);
            customerChange();
        }
    });
}

/**
 * customerChange allows for dynamic update of contact persons for currently selected customer
 */
function customerChange() {
    var customerId = $("#Customer option:selected").val();
    var fetchUrl = $("#fetch-contact-person-url").val() + customerId;
    $("#ContactPerson").load(fetchUrl);
}
