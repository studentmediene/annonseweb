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

var elapseTime=100;
var closing=0;
var menuOpen=0;


function openMenu(id){
    stayOpen();
    if(menuOpen){
        menuOpen.style.visibility = 'hidden';
    }
    menuOpen=document.getElementById(id);
    menuOpen.style.visibility = 'visible';
}


function closeMenu(){
    if(menuOpen){
        menuOpen.style.visibility = 'hidden';
    }
}

function setClosing(){
    closing = window.setTimeout(closeMenu, elapseTime);
}

function stayOpen(){
    if(closing){
        window.clearTimeout(closing);
        closing = null;
    }
}

document.onclick = closeMenu;