/**
 * Created by IntelliJ IDEA.
 * User: stine
 * Date: 1/19/12
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */

    function addNewElement(){
        var parent = document.getElementById('myDiv');
        var num = uniqueId();
        var newElement = document.createElement('addSelect');
        newElement.setAttribute('id', num);
        //alert(newElement.id);

        newElement.innerHTML = '<div><select><option>Valg 1</option><option>Valg 2</option><option>Valg 3</option><option>Valg 4</option></select><button id="num" onclick="removeElement(\';'+newElement.id+' \'); return false;">remove</button></select></div>';
        parent.appendChild(newElement);
}