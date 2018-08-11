package beans;


import coreobjects.flexSegmentFacade;
import coreobjects.jeHeader;
import coreobjects.jeLines;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by facet on 30/06/16.
 */
@Named(value = "jeheader")
@ViewScoped
public class jeBean implements Serializable {
    private jeHeader selectedJe;
    private jeLines selectedLine;
    private List<String> jColsOut =new ArrayList<>();
    private List<String> jColHeaders = new ArrayList<>();
    private List<jeLines> jeTable= new ArrayList<>();
    private List<jeLines> fJeTable= new ArrayList<>();
    private List<jeColumnModel> jeColumns=new ArrayList<>();


    @EJB
    private flexSegmentFacade segFac;




 //   @PostConstruct
    public void jeAction()  {
        String sqlSegHeaders;
        selectedJe= getSegFac().getJeHeader(rangeBean.getSelectedStaticDrill().getJeHeaderId());

        sqlSegHeaders=(rangeBean.getStaticAcStruct().stream().map(i->"cc."+i.getColumnName().toLowerCase()+",").reduce("", String::concat)); // selected segments

        jColsOut.add("rowid");
        jColsOut.add("jeLineNum");

        jColsOut.addAll(rangeBean.getStaticAcStruct().stream().map(i->i.getColumnName().toLowerCase()).collect(Collectors.toList())); // seg columns

        jColsOut.add("enteredDr");
        jColsOut.add("enteredCr");
        jColsOut.add("accountedDr");
        jColsOut.add("accountedCr");
        jColsOut.add("lineDescription");

        jColHeaders.add("rowid");
        jColHeaders.add("Line num");

        jColHeaders.addAll(rangeBean.getStaticAcStruct().stream().map(i->i.getFormLeftPrompt()).collect(Collectors.toList())); // seg headers for drill table

        jColHeaders.add("Entered Dr");
        jColHeaders.add("Entered Cr");
        jColHeaders.add("Accounted Dr");
        jColHeaders.add("Accounted Cr");
        jColHeaders.add("Line Description");

        jeTable= getSegFac().getJlines(rangeBean.getSelectedStaticDrill().getJeHeaderId(), sqlSegHeaders);


        createJeColumns();

    }

    public List<jeLines> getfJeTable() {
        return fJeTable;
    }

    public void setfJeTable(List<jeLines> fJeTable) {
        this.fJeTable = fJeTable;
    }

    public List<jeColumnModel> getJeColumns() {
        return jeColumns;
    }

    public void setJeColumns(List<jeColumnModel> jeColumns) {
        this.jeColumns = jeColumns;
    }

    public List<String> getjColsOut() {
        return jColsOut;
    }

    public void setjColsOut(List<String> jColsOut) {
        this.jColsOut = jColsOut;
    }

    public List<String> getjColHeaders() {
        return jColHeaders;
    }

    public void setjColHeaders(List<String> jColHeaders) {
        this.jColHeaders = jColHeaders;
    }

    public flexSegmentFacade getSegFac() {
        return segFac;
    }

    public void setSegFac(flexSegmentFacade segFac) {
        this.segFac = segFac;
    }

    public jeLines getSelectedLine() {
        return selectedLine;
    }

    public void setSelectedLine(jeLines selectedLine) {
        this.selectedLine = selectedLine;
    }


    //je table
    static public class jeColumnModel implements Serializable {

        private String header;
        private String property;
        private Boolean visible;

        public jeColumnModel(String header, String property, Boolean visible) {
            this.header=header;
            this.property=property;
            this.visible=visible;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }


        public Boolean getVisible() {
            return visible;
        }

    }

    private void createJeColumns() {
        Boolean invis=false; // set only first id column to invisible
        for (int i = 0; i < jColsOut.size(); i++) {
            jeColumns.add(new jeColumnModel(jColHeaders.get(i), jColsOut.get(i), invis));
            invis=true;
        }
    }


    public jeHeader getSelectedJe() {
        return selectedJe;
    }

    public void setSelectedJe(jeHeader selectedJe) {
        this.selectedJe = selectedJe;
    }

    public List<jeLines> getJeTable() {
        return jeTable;
    }

    public void setJeTable(List<jeLines> jeTable) {
        this.jeTable = jeTable;
    }
}

