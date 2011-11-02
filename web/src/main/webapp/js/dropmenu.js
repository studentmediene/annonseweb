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