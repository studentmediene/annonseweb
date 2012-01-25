/**
 * Created by IntelliJ IDEA.
 * User: stine
 * Date: 1/19/12
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */




    function addNewAdElement(){
        //alert('hei');
        var parent = document.getElementById('myDiv');
        var number = document.getElementById('theValue');
        var num = (document.getElementById('theValue').value -1) +2;
        number.value = num;

        var newElement = document.createElement('adSelect');
        var newElementId = 'new'+num+'element';
        newElement.setAttribute('id', newElementId);

        newElement.innerHTML = '<table></table><select><option>Valg 1</option><option>Valg 2</option><option>Valg 3</option><option>Valg 4</option></select><button onclick=\'removeAdElement(); return false;\'>remove</button></table>';
        parent.appendChild(newElement);
    }