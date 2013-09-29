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
    var location = document.getElementById("sidebar-tasks-fetch-url").value;
    request.open("GET",location,true);
    request.send();
}
