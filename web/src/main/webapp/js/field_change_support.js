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
