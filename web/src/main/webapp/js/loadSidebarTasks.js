/**
 * Loads the tasks in the sidebar using AJAX.
 *
 * @author Inge Halsaunet
 */

function loadSidebarTasks() {
    var request;
    if (window.XMLHttpRequest) {                // code for modern browsers
        request=new XMLHttpRequest();
    }
    else {                                      // code for ancient browsers
        request=new ActiveXObject("Microsoft.XMLHTTP");
    }
    request.onreadystatechange=function() {
        if (request.readyState==4 && request.status==200) {
            document.getElementById("sidebar-tasks").innerHTML = request.responseText;
        }
    }
    request.open("GET","/annonse/note/sidebar_notes",true);
    request.send();
}
