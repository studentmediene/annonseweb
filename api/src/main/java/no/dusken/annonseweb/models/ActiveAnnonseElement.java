package no.dusken.annonseweb.models;

/**
 * The <code>ActiveAnnonseElement</code> is an interface put on all annonseweb
 * models that can be set as active or inactive.
 *
 * @author Inge Halsaunet
 */
public interface ActiveAnnonseElement {

    /**
     * Gets the status of this annonseweb element.
     *
     * @return <code>Boolean.TRUE</code> if this element is active. <code>Boolean.FALSE</code> otherwise
     */
    public Boolean getActive();

    /**
     * Sets the status of this annonseweb element.
     *
     * @param active The new status of this element.
     */
    public void setActive(Boolean active);
}
