package beans;

import coreobjects.eApplication;
import coreobjects.flexSegmentFacade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by facet on 27/06/16.
 */
@Named(value = "applBean")
@ViewScoped
public class Applic implements Serializable{
    private static final long serialVersionUID = 1L;

    private static List<eApplication> eApplications;
    private static eApplication selectedApps;

    @EJB
    private flexSegmentFacade segFac;


    @PostConstruct
    protected void init() {
        eApplications=new ArrayList<>();
        eApplications=segFac.getApps();
    }

    public List<String> completeApps (String query) {
        return eApplications.stream().filter(i -> i.getApplicationName().startsWith(query))
                .map(eApplication::getApplicationName).peek(System.out::println)
                .collect(Collectors.toList());
    }

    public  static List<eApplication> getApplications() {
        return eApplications;
    }

    public static void setApplications(List<eApplication> applications) {
        applications = applications;
    }

    public  eApplication getSelectedApps() {
        return selectedApps;
    }
    public  static eApplication getSelectedStaticApps() {
        return selectedApps;
    }

    public void setSelectedApps(eApplication selectedApps) {
        this.selectedApps = selectedApps;
    }



}
