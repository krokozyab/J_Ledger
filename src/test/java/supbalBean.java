/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import coreobjects.suppliers;
import coreobjects.suppliersBalFacade;
import coreobjects.suppliersBal;
import coreobjects.suppliersFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author facet
 */
@Named (value="saupbalBean")
@SessionScoped
//@Stateless
public class supbalBean implements Serializable{

@EJB private suppliersBalFacade ejbFc;
@EJB private  suppliersFacade ejbsupFc;

private suppliersBal supBal;
private List<suppliersBal> supBalList;

private suppliers currentSup; // = new suppliers();
private List<suppliers> suppliersList;

private String comparatorType ="gt";

private String sup_list;

private BigDecimal tBal;


  
public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Bal Selected", supBal.getSupplierName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

/**
     *
     * @return
     */
    public BigDecimal totalBal() {
        BigDecimal sum;

        sum= new BigDecimal("0");
         
        for(suppliersBal s : supBalList) {
            sum=sum.add(s.getAccountedBalEnd());
        }
        return sum;
 }
 
public void populateBal(){
        this.setSupBalList(getEjbFc().findAll());
        //System.out.println("Blya! supp");
        
     //   for (int i = 0; i < supBalList.size(); i++) {
//			System.out.println(supBalList.get(i).getSupplierName());
//		}
        //return "/admin/ledgers";
    }

    /**
     * @return the ejbFacade
     */
    public suppliersBalFacade getEjbFc() {
        return ejbFc;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFc(suppliersBalFacade ejbFc) {
        this.ejbFc = ejbFc;
    }

    /**
     * @return the supBal
     */
    public suppliersBal getSupBal() {
        return supBal;
    }

    /**
     * @param supBal the supBal to set
     */
    public void setSupBal(suppliersBal supBal) {
        this.supBal = supBal;
    }

    /**
     * @return the supBalList
     */
    public List<suppliersBal> getSupBalList() {
        return supBalList;
    }

    /**
     * @param supBalList the supBalList to set
     */
    public void setSupBalList(List<suppliersBal> supBalList) {
        this.supBalList = supBalList;
    }

    /**
     * @return the ejbsupFc
     */
    public suppliersFacade getEjbsupFc() {
        return ejbsupFc;
    }

    /**
     * @param ejbsupFc the ejbsupFc to set
     */
    public void setEjbsupFc(suppliersFacade ejbsupFc) {
        this.ejbsupFc = ejbsupFc;
    }

    /**
     * @return the currentSup
     */
    public suppliers getCurrentSup() {
        if (currentSup == null){
            currentSup=new suppliers();
        }
        return currentSup;
    }

    /**
     * @param currentSup the currentSup to set
     */
    public void setCurrentSup(suppliers currentSup) {
        this.currentSup = currentSup;
    }

    /**
     * @return the suppliersList
     */
    public List<suppliers> getSuppliersList() {
        return suppliersList;
    }

    /**
     * @param suppliersList the suppliersList to set
     */
    public void setSuppliersList(List<suppliers> suppliersList) {
        this.suppliersList = suppliersList;
    }


public  List autoCompleteSupplier(String text){
 System.out.println("Bly  "+text);

  return ejbsupFc.CompleteSupplier(text); 
  
  
  
}

public boolean filterByEndBal(Object value, Object filter, Locale locale) {
    BigDecimal ax;    
    String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
    ax= new BigDecimal(filterText);     
        return ((Comparable) value).compareTo(ax) > 0;
    }

    /**
     * @return the comparatorType
     */
    public String getComparatorType() {
        return comparatorType;
    }

    /**
     * @param comparatorType the comparatorType to set
     */
    public void setComparatorType(String comparatorType) {
        this.comparatorType = comparatorType;
    }

    public String getSup_list() {
        return sup_list;
    }

    public void setSup_list(String sup_list) {
        this.sup_list = sup_list;
    }

    public BigDecimal gettBal() {
        return totalBal();
    }

    public void settBal(BigDecimal tBal) {
        this.tBal = tBal;
    }

    
    public static Object getManagedBean(final String beanName) {
    FacesContext fc = FacesContext.getCurrentInstance();
    Object bean;
    try {        ELContext elContext = fc.getELContext();

        bean = elContext.getELResolver().getValue(elContext, null, beanName);

    } catch (RuntimeException e) {

        throw new FacesException(e.getMessage(), e);

    }
 

    if (bean == null) {

        throw new FacesException("Managed bean with name '" + beanName

            + "' was not found. Check your faces-config.xml or @ManagedBean annotation.");

    }

    return bean;
}

    
    
    
}
