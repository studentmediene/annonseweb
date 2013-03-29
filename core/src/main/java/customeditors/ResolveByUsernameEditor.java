package customeditors;

import no.dusken.annonseweb.service.AnnonsePersonService;
import no.dusken.common.model.Person;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Inge
 * Date: 28.03.13
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class ResolveByUsernameEditor extends PropertyEditorSupport {
    private AnnonsePersonService annonsePersonService;

    public ResolveByUsernameEditor(AnnonsePersonService annonsePersonService) {
        this.annonsePersonService = annonsePersonService;
    }
    @Override
    public void setAsText(String text) {
        setValue(annonsePersonService.getByUsername(text));
    }

    @Override
    public String getAsText() {
        Person value = (Person) getValue();
        if (value != null)
            return  value.getUsername();
        else
            return "";
    }
}
