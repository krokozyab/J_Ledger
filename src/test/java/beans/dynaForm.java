/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import coreobjects.dynaFields;
import coreobjects.flexSegment;
import coreobjects.flexSegmentFacade;
import coreobjects.sobStruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;
import static beans.structBean.flexStruct;

/**
 *
 * @author facet
 */
@Named (value="dyanaForm")
@Stateless
public class dynaForm implements Serializable {
private static final long serialVersionUID = 20120423L;
private DynaFormModel model; 
private List<DynaFormLabel> dLabel;
private List<DynaFormControl> dControl;
private DynaFormLabel lb;
private int j=0;
private DynaFormRow frow;
private String defSegVal; // default seg value
//private flexSegment complSeg;
@EJB private  flexSegmentFacade segFac;

    public List<flexSegment> getSegList() {
        return segList;
    }

    public void setSegList(List<flexSegment> segList) {
        this.segList = segList;
    }

//@EJB private flexSegmentFacade bx;
private List<flexSegment> segList;

@PostConstruct  
    protected void initialize() {  
        model = new DynaFormModel(); 
        dLabel = new ArrayList<>();
        dControl = new ArrayList<>();
// 1. row
    /*   DynaFormRow row = model.createRegularRow();
    DynaFormLabel label11 = row.addLabel("Dyna field");
    DynaFormControl control12 = row.addControl(new dynaFields("Dyna",3, true), "input");
    label11.setForControl(control12);
    DynaFormLabel label12 = row.addLabel("Dyna field");
    DynaFormControl control13 = row.addControl(new dynaFields("Dyna",5, true), "input");
    label11.setForControl(control13);
    row = model.createRegularRow();
    DynaFormLabel label13 = row.addLabel("Dyna field");
    DynaFormControl control14 = row.addControl(new dynaFields("Dyna", 12, true), "input");
    label11.setForControl(control14);
     */
    flexStruct.stream().map((i) -> {
        frow = new DynaFormRow();
        return i;
    }).map((i) -> {
        DynaFormRow frow = model.createRegularRow();
        defSegVal= i.getDefaultType(); // default value for the segment
        if (defSegVal.equals("C")){
            defSegVal=i.getDefaultValue();
        }
        else defSegVal="null";
        //i.getFlexValueSetId().toString(),
        dLabel.add(frow.addLabel(i.getFormLeftPrompt()));
        //dControl.add(frow.addControl(new dynaFields( i.getSegmentName(),i.getDisplaySize(), defSegVal, true, "completeMethod"), "autoComplete"));
        dControl.add(frow.addControl(new dynaFields( i.getFlexValueSetId().toString(),i.getDisplaySize(), defSegVal, true, "completeMethod"), "autoComplete"));
        return i;
    }).map((_item) -> {
        dLabel.get(j).setForControl(dControl.get(j));
        return _item;
    }).forEach((_item) -> {
        j++;
    });
       
        
    }

 public DynaFormModel getModel() {  
        return model;  
    }  
  
    public List<dynaFields> getdynaFields() {  
        if (model == null) {  
            return null;  
        }  
  
        List<dynaFields> dynaFields = new ArrayList<>();  
        for (DynaFormControl dynaFormControl : model.getControls()) {  
            dynaFields.add((dynaFields) dynaFormControl.getData());  
        }  
  
        return dynaFields;  
    }  
    
    
 public String submitForm() {  
        FacesMessage.Severity sev = FacesContext.getCurrentInstance().getMaximumSeverity();  
        boolean hasErrors = (sev != null && (FacesMessage.SEVERITY_ERROR.compareTo(sev) >= 0));  
  
        RequestContext requestContext = RequestContext.getCurrentInstance();  
        requestContext.addCallbackParam("isValid", !hasErrors);  
  
        return null;  
    }      
    
 
 public void handleSelect(SelectEvent event) {
             Object item = event.getObject();
             FacesMessage msg = new FacesMessage("Selected", "Item:" + item);
}


public List <String> complete(String query) {  
       // List<<sgv>> results = new ArrayList<String>();
       List <String> ax = new ArrayList<>();
       List <String> bx = new ArrayList<>();
  FacesContext context = FacesContext.getCurrentInstance();
  String filo = (String) UIComponent.getCurrentComponent(context).getAttributes().get("filter");
        //char letter;  
        //for (letter = 'a'; letter <= 'm'; letter++) {  
        //    results.add(query + letter);  
       // }  
  System.out.println(filo +" --- "+  query);
  
    ax = segFac.segComplete(filo, query);  
 //   Iterator itr=ax.iterator();
 //    while (itr.hasNext()){
  //            sgv cx=(sgv)itr.next();
  //            System.out.println(cx.getFlex_value());
   //           bx.add((String)cx.getFlex_value());
 //}
    return ax; //bx;
    }  


}


  