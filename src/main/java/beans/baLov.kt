package beans


import java.io.Serializable
import javax.annotation.PostConstruct
import javax.faces.application.FacesMessage
import javax.faces.context.FacesContext
import javax.faces.view.ViewScoped
import javax.inject.Named


/**
 * Created by facet on 16/06/16.
 */
@Named
@ViewScoped
open class baLov : Serializable
{



  var asi ="hiuop"


@PostConstruct
fun pi() = print(asi)



 open fun message (msg: String) {
        val message = FacesMessage(FacesMessage.SEVERITY_INFO, msg, null)
        FacesContext.getCurrentInstance().addMessage(null, message)
      asi="her"
    }
}


