package beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Created by facet on 17/06/16.
 */
@Named
@ViewScoped
public class jbaLov implements Serializable {

    Consumer<String> print = System.out::println;


    private String asi = "Jbalov";

    public String getAsi() {
        return asi;
    }

    public void setAsi(String asi) {
        this.asi = asi;
    }

    public void message(String summary) {
        System.out.println(getAsi());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
