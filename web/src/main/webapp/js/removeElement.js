/**
 * Created by IntelliJ IDEA.
 * User: stine
 * Date: 1/25/12
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates.
 */



    function removeElement(elementId){
        //alert(elementId);
        var parent = document.getElementById('myDiv');
        var element = document.getElementById(elementId);
        parent.removeChild(element);
}