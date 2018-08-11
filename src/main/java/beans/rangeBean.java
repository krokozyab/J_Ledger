/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import coreobjects.*;
import coreobjects.flexSegmentFacade;
import coreobjects.tableDrill;
import coreobjects.tableOut;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.openjpa.jdbc.kernel.exps.Math;
import org.jboss.weld.exceptions.IllegalArgumentException;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.el.ValueExpressionAnalyzer;
import org.primefaces.event.data.SortEvent;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
//import scala.collection.immutable.List;
//import scala.collection.mutable.StringBuilder;
import service.TableHeaders;
import service.results;
import service.selectParts;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ValueExpression;
import javax.el.ValueReference;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.resource.spi.SecurityException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.StrictMath.random;

//import coreoblects.glPeriodsFacade;
//import coreoblects.tableOutFacade;
//import javafx.scene.control.SortEvent;






/**
 *
 * @author facet
 */
//@ManagedBean (value = "rBean")
@Named(value = "rBean")
//@Asynchronous
@ViewScoped
public class rangeBean implements Serializable {

    private static final long serialVersionUID = 1L;


    private DynaFormModel model=new DynaFormModel();
    private List<DynaFormLabel> rangeLabel=new ArrayList<>();
    private List<DynaFormControl> rangeControl=new ArrayList<>();
    private DynaFormRow frow;
    private  ledgers selectedLedger;
    private glPeriods periodFrom;
    private glPeriods periodTo;
    private boolean periodDisabled=true;
    private boolean submitDisabled=false;
    //private String results;
    private String budget;
    private String pivotName ="None";
    private String pivotSegId;
    private String pivotSegName;
    private pivot selectedPivot;
    private tableOut selectedRange;
    private static AtomicBoolean cancelStatus= new AtomicBoolean(false);

    @EJB
    private flexSegmentFacade segFac;



    private static List<sobStruct> acStruct=new ArrayList<>();
    private static List<ledgers> ledgersList=new ArrayList<>();
    private static List<glPeriods> periodList=new ArrayList<>();
    //private tableCols colsOut= new tableCols();
    private List<String> colsOut =new ArrayList<>();
    private List<tableOut> tableBal=new ArrayList<>(); // =new ArrayList<>();
    private Future<List<tableOut>> fTableBal;
    private List<tableOut> filteredBal=new ArrayList<>();
    private List<ColumnModel> columns=new ArrayList<>();
    private static List<pivot> pivotList=new ArrayList<>();
    private List<String> pivotHeaders = new ArrayList<>();
    private List<String> colsHeaders= new ArrayList<>();
    private List<String> colsSortable= new ArrayList<>();
    private List<TableHeaders> colsUpHeaders= new ArrayList<>();
    private List<String> resultsOut =new ArrayList<>(); // только список резульатов
    private List<BigDecimal> sumRow =new ArrayList<>(); // суммирующая строка
    private String sortCol;
    private String sortVal;
    private int sortSpan;
    private selectParts zx = new selectParts(); // from and where sql clause
    private boolean hlp=false; // show descriptions for segments
    private Integer tabIndex; // active tab
    private boolean padj=false; // show adjustment periods


    // drill section
    private LazyDataModel<tableDrill> drillTab;
    private List<tableDrill> drillFilter;
    private static tableDrill selectedDrill;
    private List<drillColumnModel> drillColumns=new ArrayList<>();
    private List<String> drillColsOut =new ArrayList<>();
    private List<String> drillColsHeaders= new ArrayList<>();
    private results resType =new results();
    private String selectedResult="PTD";
    private String parentWhere; // parent where conditions
    private final List<String> slaFixSql=Arrays.asList("aeLineNum", "accountingClassCode", "transactionNumber",
            "party", "partySite","currencyCode","enteredDr", "enteredCr", "accountedDr", "accountedCr", "description",
            "batchName", "batchStatus", "journalName", "journalLine", "aeHeaderId", "jeHeaderId");
    private final List<String> slaFixHeaders=Arrays.asList("Line", "Accounting class", "Transaction Num", "Party", "Party site", "Currency",
            "Entered Dr", "Entered Cr", "Accounted Dr", "Accounted Cr", "Line Description", "Batch name", "Batch status", "Journal name",
            "Journal Line num", "ae_header_id", "ae_header_id", "je_header_id");


    //je section
    private jeHeader selectedJe=new jeHeader();
    private jeLines selectedLine;
    private List<String> jColsOut =new ArrayList<>();
    private List<String> jColHeaders = new ArrayList<>();
    private List<jeLines> jeTable= new ArrayList<>();
    private List<jeLines> fJeTable= new ArrayList<>();
    private List<jeColumnModel> jeColumns=new ArrayList<>();

    //sla section
    private slaLines selectedSlaLine;
    private List<String> slaColsOut=new ArrayList<>();
    private List<String> slaColHeaders=new ArrayList<>();
    private List<slaLines> slaTable = new ArrayList<>();
    private List<slaLines> fSlaTable = new ArrayList<>();
    private List<slaColumnModel> slaColumns=new ArrayList<>();



    //  public List<BigDecimal> getTotalRow() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    //      System.out.println("Get valie");
    //      return sumRowCalc();
    //  }



    @PostConstruct
    protected void init() {
        //System.out.println("Post Construct!");

        ledgersList.clear();
        rangeLabel.clear();
        rangeControl.clear();

        model = new DynaFormModel();
        ledgersList = segFac.getLedgers();//get all ledgers
        //setRangeLabel(new ArrayList<>());
        rangeLabel = new ArrayList<>();
        //setRangeControl(new ArrayList<>());
        rangeControl = new ArrayList<>();

        setTabIndex(0);



    }

    public void afterLedger(){
        periodList= segFac.getPeriods(selectedLedger.getPeriodSetName()); // get periods list
    }



    public void scopeChanged(){

        if (periodFrom!=null) {
            periodFrom.clear();
        }
        if (periodTo!=null) {
            periodTo.clear();
        }

        model = new DynaFormModel();
        rangeLabel = new ArrayList<>();
        rangeControl = new ArrayList<>();

        rangeLabel.clear();

        rangeControl.clear();
        periodList.clear();
        periodList= segFac.getPeriods(selectedLedger.getPeriodSetName()); // get periods list
        acStruct.clear();
        acStruct= segFac.allStruct(selectedLedger); // get coa structure


        //acStruct.stream().forEach(i-> System.out.println(i.getSegmentNum()+ "  "+ i.getFormLeftPrompt()));


        setPeriodDisabled(false);
        selectedPivot= new pivot("None", "", "");
        pivotList.clear();
        pivotList.add(selectedPivot);
        pivotList.add(new pivot("Periods","",""));
        pivotList.addAll(acStruct.stream()
                .map(i-> {return new pivot(i.getFormLeftPrompt(),i.getFlexValueSetId().toString(), i.getSegmentNum().toString());})
                .collect(Collectors.toList()));


        frow = new DynaFormRow();
        //int j = 0;
        //  for (Iterator<sobStruct> it = flexStruct.iterator(); it.hasNext();) {
        //      sobStruct i = it.next();
        //System.out.println("segs");
        acStruct.stream().forEach(i -> {
                    DynaFormRow formRow = model.createRegularRow();
                    rangeLabel.add(formRow.addLabel(i.getFormLeftPrompt()));
                    rangeControl.add(formRow.addControl(new rangeProperty(i.getFlexValueSetId().toString() , "", false, i.getDisplaySize(), "complete","lo",i.getSegmentNum()), "autoComplete"));
                    rangeControl.add(formRow.addControl(new rangeProperty(i.getFlexValueSetId().toString() , "", false, i.getDisplaySize(), "complete","hi",i.getSegmentNum()), "autoComplete"));
                    rangeControl.add(formRow.addControl(new dtButton(i.getFlexValueSetId().toString() , true, i.getSegmentNum(), i.getFormLeftPrompt()), "selectBooleanButton"));
                    rangeControl.add(formRow.addControl(new descripz(i.getFlexValueSetId().toString() , "Value", i.getSegmentNum()), "selectOneRadio"));
                    //System.out.println(i.getSegmentNum());
                    rangeControl.add(formRow.addControl(new helpSegProperty(i.getFlexValueSetId().toString() , "", false, i.getDisplaySize(), "completeHelp","di",i.getSegmentNum()), "autoCompleteH")); // display only
                }

        );

    }

 /*   public List<rangeProperty> getrangeProperty() {
        if (model == null) {
            return null;
        }

        List<rangeProperty> rangeProperty = new ArrayList<>();
        for (DynaFormControl dynaFormControl : model.getControls()) {
            rangeProperty.add((rangeProperty) dynaFormControl.getData());
        }

        return rangeProperty;
    }
*/


    public List<String> completeLedger (String query) {
        return getLedgersList().stream()
                .filter(i -> i.getLedgerName().startsWith(query))
                .map(i->i.getLedgerName())
                .collect(Collectors.toList());
    }

    public List<String> completePeriod (String query) {
        return getPeriodList().stream()
                .filter(i -> i.getPeriodName().startsWith(query))
                .map(i->i.getPeriodName())
                .collect(Collectors.toList());
    }

    public List<String> completeResults (String query){
        return resType.getRes();
    }

    public List<String> completeBudget (String query){
        List<String> ax = new ArrayList<>();
        ax.add(query);
        return ax;
    }

    public List<String> completePivot (String query){
        return getPivotList().stream()
                .map(i->i.getPivotName())
                .collect(Collectors.toList());
    }




    public List<String> complete(String query) {
        FacesContext context = FacesContext.getCurrentInstance();
        return segFac.segComplete((String) UIComponent.getCurrentComponent(context).getAttributes().get("filter").toString().toUpperCase(), query);
    }

    public List<String> completeHelp(String query) {
        FacesContext context = FacesContext.getCurrentInstance();
        return segFac.segHelpComplete((String) UIComponent.getCurrentComponent(context).getAttributes().get("filter").toString().toUpperCase(), query);
    }

    public void buttonAction() throws NoSuchMethodException, InvocationTargetException, InterruptedException, ExecutionException {

        String lowPart="";
        String hiPart="";
        StringBuilder sqlConditions = new StringBuilder();
        StringBuilder sqlColumns = new StringBuilder();
        StringBuilder sqlGroupBy = new StringBuilder(); // just like sqlColumns without descriptions
        StringBuilder sqlPeriodList = new StringBuilder();
        StringBuilder pivotCondition = new StringBuilder();
        String sqlPeriods;
        String sqlPivot=null;
        String pivotLo;
        String pivotHi;
        Map<String, List<DynaFormControl>> byRange;
        List<String> selectedPeriodList=new ArrayList<>();
        List<String> formattedPeriodList=new ArrayList<>();
        List<String> pivotListLocal=new ArrayList<>();



              /* if no range selected return */
        if (rangeControl.stream().filter(p -> "autoComplete".equals(p.getType())).
                filter(p ->((rangeProperty) p.getData()).getValue()!= null).count()<2) {
            addMessage("No range selected!");
            return;
        }


        if(periodTo.getStartDate().before(periodFrom.getStartDate())){
            addMessage("Chose valid period range");
            return;
        }


        pivotHeaders.clear();
        colsOut.clear();
        colsHeaders.clear();
        colsUpHeaders.clear();
        colsSortable.clear();
        resultsOut.clear();
        parentWhere="";


        //System.out.println("Span : "+resType.getSpn(selectedResult));


      /* Columns & group by */

        zx.setWherePart(""); // clear sql clause parts
        zx.setFromPart("");
        sqlColumns.append(rangeControl.stream().filter(i->"selectBooleanButton".equals(i.getType())).
                filter(i->(Boolean)(((dtButton) i.getData()).getValue())==true). // only reenderablr row
                //filter(i->! ((dtButton) i.getData()).getSegOrder().toString().equals(selectedPivot.getPivotSegName())). // exclude pivot column
                map(i -> { String ax; // value description or both
            String bx="Error: headers  not assigned!"; // accumulator headers
            String cx=""; // accumulator from
            String dx=""; // accumulator where

            ax=rangeControl.stream()
                    .filter(p->"selectOneRadio".equals(p.getType())) // radio btn
                    .filter(p->((((descripz) p.getData()).getSegOrder()))==(((dtButton) i.getData()).getSegOrder()) )//same segment
                    .map(p->(((descripz) p.getData()).getValue()))
                    .findAny().get().toString();
            String pos = (((dtButton) i.getData()).getSegOrder()).toString();
            String prompt = ((dtButton) i.getData()).getPrompt();
            switch (ax){
                case "Value": {
                    bx = "cc.segment" + pos;
                    if(!selectedPivot.getPivotSegName().equals(pos.toString())) { // exclude pivot column
                        colsOut.add("segment" + pos);
                        colsHeaders.add(prompt);
                        colsSortable.add(prompt);
                    }
                }
                break;
                case "Description":{ bx=" ft"+pos+".description"+" desc"+pos;
                    zx.addFromPart(" fnd_flex_values fv"+pos+", fnd_flex_values_tl ft"+pos+",");
                    zx.addWherePart("and ft"+pos+".flex_value_id="+"fv"+pos+".flex_value_id \n"
                            +"and ft"+pos+".language=userenv('LANG')\n"
                            +"and fv"+pos+".flex_value_set_id="+(((dtButton) i.getData()).getName())+"\n"
                            +"and cc.segment"+pos+"=fv"+pos+".flex_value\n");
                    if(!selectedPivot.getPivotSegName().equals(pos.toString())) { // exclude pivot column
                        colsOut.add("desc"+pos);
                        colsHeaders.add(prompt);
                        colsSortable.add(prompt);
                    }
                }
                break;
                case "Both": {bx="cc.segment"+pos+", "+"ft"+pos+".description"+" desc"+pos;
                    zx.addFromPart(" fnd_flex_values fv"+pos+", fnd_flex_values_tl ft"+pos+",");
                    zx.addWherePart("and ft"+pos+".flex_value_id="+"fv"+pos+".flex_value_id \n"
                            +"and ft"+pos+".language=userenv('LANG')\n"
                            +"and fv"+pos+".flex_value_set_id="+(((dtButton) i.getData()).getName())+"\n"
                            +"and cc.segment"+pos+"=fv"+pos+".flex_value\n");
                    if(!selectedPivot.getPivotSegName().equals(pos.toString())) { // exclude pivot column
                        colsOut.add("segment" + pos);
                        colsOut.add("desc" + pos);
                        colsHeaders.add(prompt);
                        colsHeaders.add(prompt + "_desc");
                        colsSortable.add(prompt);
                        colsSortable.add(prompt + "_desc");
                    }
                }
            }
            return bx;})
                .collect(Collectors.joining(", ")));

        if (zx.getFromPart().length()>0)
            zx.setFromPart(zx.getFromPart().substring(0, (zx.getFromPart().toString()).length() - 1)); // get of last ,


        sqlGroupBy.append(rangeControl.stream().filter(i->"selectBooleanButton".equals(i.getType())).
                filter(i->(Boolean)(((dtButton) i.getData()).getValue())==true). // only reenderablr row
                map(i -> { String ax; // value description or both
            String bx="Error: headers  not assigned!"; // accumulator headers
            String cx=""; // accumulator from
            String dx=""; // accumulator where

            ax=rangeControl.stream()
                    .filter(p->"selectOneRadio".equals(p.getType())) // radio btn
                    .filter(p->((((descripz) p.getData()).getSegOrder()))==(((dtButton) i.getData()).getSegOrder()) )//same segment
                    .map(p->(((descripz) p.getData()).getValue()))
                    .findAny().get().toString();
            String pos = (((dtButton) i.getData()).getSegOrder()).toString();
            String prompt = ((dtButton) i.getData()).getPrompt();
            switch (ax){
                case "Value": {
                    bx = "cc.segment" + pos;
                }
                break;
                case "Description":{ bx=" ft"+pos+".description";
                }
                break;
                case "Both": {bx="cc.segment"+pos+", "+"ft"+pos+".description";
                }
            }
            return bx;})
                .collect(Collectors.joining(", ")));



       /* populate mapping classs by column names */

        if(rangeControl.stream().filter(i->"selectBooleanButton".equals(i.getType())) //unable pivot on summary segment
                .filter(i->(Boolean)(((dtButton) i.getData()).getValue())==true)
                .filter(i->(((dtButton)i.getData()).getSegOrder()).toString().equals(selectedPivot.getPivotSegName()))
                .collect(Collectors.toList()).isEmpty()){
            if(!selectedPivot.getPivotName().equals("Periods") && !selectedPivot.getPivotName().equals("None")) {
                addMessage("Unable pivot on summary segment");
                return;
            }
        }




//static columns

        if(!selectedPivot.getPivotName().equals("Periods")){
            colsOut.add("periodName");
            colsHeaders.add("periodName");
            colsSortable.add("periodName");
        }
        colsOut.add("currencyCode");
        colsHeaders.add("currencyCode");
        colsSortable.add("currencyCode");


      /* Where conditions */

        byRange = rangeControl.stream().filter(p -> "autoComplete".equals(p.getType())).
                filter(p ->((rangeProperty) p.getData()).getValue()!= null && !((rangeProperty) p.getData()).getValue().toString().isEmpty()).
                collect(Collectors.groupingBy(p -> ((rangeProperty) p.getData()).getHi_lo()));



        if (!byRange.isEmpty()) {

            //lowPart = byRange.get("lo").stream().filter(p ->((rangeProperty) p.getData()).getValue()!= null).
            //        map(p->("and cc.segment"+((rangeProperty) p.getData()).getSegNum())+">="+"\'"+((rangeProperty) p.getData()).getValue()+"\'\n")
            //        .reduce("",String::concat);

                lowPart = byRange.get("lo").stream().filter(p -> ((rangeProperty) p.getData()).getValue() != null && !((rangeProperty) p.getData()).getValue().toString().isEmpty())
                        .map(p -> rangeSeg(((rangeProperty) p.getData()).getSegNum().toString(), ((rangeProperty) p.getData()).getName(), ((rangeProperty) p.getData()).getValue().toString(), ">="))
                        .reduce("", String::concat);

            if (lowPart!=null && !lowPart.isEmpty()){
                sqlConditions.append(lowPart);
                sqlConditions.append("\n");
                parentWhere=parentWhere+lowPart+"\n"; // for drill
            }

            //hiPart = byRange.get("hi").stream().filter(p ->((rangeProperty) p.getData()).getValue()!= null).
            //        map(p->("and cc.segment"+((rangeProperty) p.getData()).getSegNum())+"<="+"\'"+((rangeProperty) p.getData()).getValue()+"\'\n")
            //        .reduce("",String::concat);



                 hiPart = byRange.get("hi").stream().filter(p -> ((rangeProperty) p.getData()).getValue() != null && !((rangeProperty) p.getData()).getValue().toString().isEmpty()).
                         map(p -> rangeSeg(((rangeProperty) p.getData()).getSegNum().toString(), ((rangeProperty) p.getData()).getName(), ((rangeProperty) p.getData()).getValue().toString(), "<="))
                         .reduce("", String::concat);


            if (hiPart != null && !hiPart.isEmpty()) {
                parentWhere=parentWhere+hiPart+"\n";
                sqlConditions.append(hiPart);
            }
        }

        sqlConditions.append("\n" + "and gb.ledger_id=").append(selectedLedger.getLedgerId());


        selectedPeriodList=periodList.stream().filter(i->(isPadj()?"YN":"N").contains(i.getAdjustmentPeriodFlag())).
                filter(i->i.getPeriodYear()*100+i.getPeriodNum()>=periodFrom.getPeriodYear()*100+periodFrom.getPeriodNum()).
                filter(i->i.getPeriodYear()*100+i.getPeriodNum()<=periodTo.getPeriodYear()*100+periodTo.getPeriodNum()).
                map(i->i.getPeriodName()).
                map(i->{return "\'"+i+"\',";}).collect(Collectors.toList());

        //formattedPeriodList=selectedPeriodList.stream().map(i->{return "\'"+i+"\',";}).collect(Collectors.toList());

        sqlPeriodList.append(selectedPeriodList.stream().reduce("",String::concat));
        sqlPeriods=sqlPeriodList.substring(0, sqlPeriodList.length()-1); // get of end ,

// seg pivot select build

        if (selectedPivot.getPivotName().equals("Periods") || selectedPivot.getPivotName().equals("None")) {
            // doing nothing

        } else if (byRange !=null && !byRange.isEmpty()) {

                pivotLo = byRange.get("lo").stream()
                        .filter(p -> (((rangeProperty) p.getData()).getName()).equals(selectedPivot.getPivotSegId()))
                        .map(p -> ((rangeProperty) p.getData()).getValue().toString())
                        .reduce("", String::concat);


                pivotHi = byRange.get("hi").stream()
                        .filter(p -> (((rangeProperty) p.getData()).getName()).equals(selectedPivot.getPivotSegId()))
                        .map(p -> ((rangeProperty) p.getData()).getValue().toString())
                        .reduce("", String::concat);


            if (pivotLo!=null && !pivotLo.isEmpty()){
                pivotCondition.append(" and fv.flex_value >= \'").append(pivotLo).append("\'\n");
            }
            if (pivotHi!=null && !pivotHi.isEmpty()){
                pivotCondition.append(" and fv.flex_value <= \'").append(pivotHi).append("\'\n");
            }
            // System.out.println(pivotCondition);
            //if(pivotCondition.toString()!=null && !pivotCondition.toString().isEmpty()){
            pivotHeaders=segFac.getPivot(selectedPivot.getPivotSegId(), pivotCondition.toString());
            pivotListLocal=segFac.getPivot(selectedPivot.getPivotSegId(), pivotCondition.toString()).stream()
                    .map(i->{return "\'"+i+"\',";})
                    .collect(Collectors.toList());
            sqlPivot=pivotListLocal.stream().reduce("segment"+selectedPivot.getPivotSegName() + " in (", String::concat);
            sqlPivot=sqlPivot.substring(0,sqlPivot.length()-1); // get of last ,
            //System.out.println(pivotListLocal);
            //}
        }



        colsUpHeaders.add(new TableHeaders(" ", colsOut.size())); // сдвиг верхнего заголовка на результаты
        sortSpan=colsOut.size(); // сдвиг на результаты при для суммирующей строки при сортировке

//pivot columns build
        AtomicInteger aI = new AtomicInteger(1);
        AtomicInteger bI = new AtomicInteger(1);
//periods
        if (selectedPivot.getPivotName().equals("Periods")){ //результаты
            for (int i=1; i<=resType.getSpn(selectedResult); i++){ // several results
                colsOut.addAll(selectedPeriodList.stream()
                        .map(p -> "a" + ((Integer)aI.getAndIncrement()).toString() )
                        .collect(Collectors.toList()));
                resultsOut.addAll(selectedPeriodList.stream()
                        .map(p -> "a" + ((Integer)bI.getAndIncrement()).toString() )
                        .collect(Collectors.toList()));
            }


            colsUpHeaders.addAll(periodList.stream().filter(i->(isPadj()?"YN":"N").contains(i.getAdjustmentPeriodFlag())). // заголовки
                    filter(i->i.getPeriodYear()*100+i.getPeriodNum()>=periodFrom.getPeriodYear()*100+periodFrom.getPeriodNum()).
                    filter(i->i.getPeriodYear()*100+i.getPeriodNum()<=periodTo.getPeriodYear()*100+periodTo.getPeriodNum()).
                    map(i->{return new TableHeaders(i.getPeriodName(),resType.getSpn(selectedResult));})//.peek(i->System.out.println(i))
                    .collect(Collectors.toList()));
            if (resType.getSpn(selectedResult) == 2) {   // 2 columns in result
                for (int i = 0; i < periodList.size(); i++) {
                    colsHeaders.add(selectedResult.substring(0, 3));
                    colsHeaders.add(selectedResult.substring(4, 7));
                }
            } else
            if (resType.getSpn(selectedResult) == 1) { // 1 columns in result
                for (int i = 0; i < periodList.size(); i++) {
                    colsHeaders.add(selectedResult);
                }
            }
            else
            if (resType.getSpn(selectedResult) == 3) { // 3 columns in result
                for (int i = 0; i < periodList.stream().filter(j->(isPadj()?"YN":"N").contains(j.getAdjustmentPeriodFlag())).collect(Collectors.toList()).size(); i++) {
                    colsHeaders.add(selectedResult.substring(0, 3));
                    colsHeaders.add(selectedResult.substring(4, 7));
                    colsHeaders.add(selectedResult.substring(8, 11));
                }
            }
        }
// segments pivot
        else if (!selectedPivot.getPivotName().equals("None")) { // segments pivot
            for (int i=1; i<=resType.getSpn(selectedResult); i++){ //результаты
                colsOut.addAll(pivotListLocal.stream()
                        .map(p -> "a" + ((Integer)aI.getAndIncrement()).toString() )
                        .collect(Collectors.toList()));
                resultsOut.addAll(pivotListLocal.stream()
                        .map(p -> "a" + ((Integer)bI.getAndIncrement()).toString() )
                        .collect(Collectors.toList()));
            }
            //заголовки
            for (int i=0; i<pivotHeaders.size(); i++){
                colsUpHeaders.add(new TableHeaders(pivotHeaders.get(i),resType.getSpn(selectedResult)));
            }
            if (resType.getSpn(selectedResult) == 2) {   // 2 columns in result
                for (int i = 0; i < periodList.size(); i++) {
                    colsHeaders.add(selectedResult.substring(0, 3));
                    colsHeaders.add(selectedResult.substring(4, 7));
                }
            } else
            if (resType.getSpn(selectedResult) == 1) { // 1 columns in result
                for (int i = 0; i < periodList.size(); i++) {
                    colsHeaders.add(selectedResult);
                }
            }
            else
            if (resType.getSpn(selectedResult) == 3) { // 3 columns in result
                for (int i = 0; i < periodList.size(); i++) {
                    colsHeaders.add(selectedResult.substring(0, 3));
                    colsHeaders.add(selectedResult.substring(4, 7));
                    colsHeaders.add(selectedResult.substring(8, 11));
                }
            }
        }
        else { // no pivot single results

            for (int i=1; i<=resType.getSpn(selectedResult); i++){ //результаты
                colsOut.add("a"+i);
                resultsOut.add("a"+i);
            }
            switch (resType.getSpn(selectedResult)){ //заголовки
                case 1: colsHeaders.add(selectedResult);
                    break;
                case 2:
                    colsHeaders.add(selectedResult.substring(0, 3));
                    colsHeaders.add(selectedResult.substring(4, 7));
                    break;
                case 3:
                    colsHeaders.add(selectedResult.substring(0, 3));
                    colsHeaders.add(selectedResult.substring(4, 7));
                    colsHeaders.add(selectedResult.substring(8, 11));
            }
        }

        // call select
        if (!byRange.isEmpty()){

                fTableBal =  segFac.getTabres(getSql(sqlColumns.toString(),sqlConditions.toString(), sqlGroupBy.toString(), sqlPeriods, sqlPivot ),
                        colsOut); // excliding pivot column


            while (!fTableBal.isDone()) {
                // Wait
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //check cancel status
                //System.out.println("piiii...");
                //System.out.println(cancelStatus.toString());
                if(cancelStatus.compareAndSet(true, false)){
                    //cancelStatus.set(false);
                    fTableBal.cancel(false);
                    //System.out.println("Pizdec!");
                }

            }
            if(!fTableBal.isCancelled()){
                try {
                    tableBal=fTableBal.get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else cancelStatus.compareAndSet(false, true); // back

            UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("rform:outabs:bals");
            table.setValueExpression("sortCol", null);


            // add id columns at 0 position
            colsOut.add(0,"rowid");
            colsHeaders.add(0,"rowid");

            createDynamicColumns();

        }


setTabIndex(0);
    }


    public String rangeSeg(String segNum, String segSetId, String segValue, String side){
        StringBuilder qry = new StringBuilder();
        sgv selectedSeg;
        List<String> sumList;
        String ax;
        selectedSeg = segFac.getSegValue(segSetId, segValue);
        if (selectedSeg.getSummary_flag().equals("Y")) {
            sumList=segFac.getSumSegList(segSetId, segValue);

            ax= sumList.stream().map(i-> "'"+i+"',").reduce("",String::concat);
            return "and cc.segment"+segNum+" in ("+ax.substring(0,ax.length()-1)+")\n";
        }
        else {
            return "and cc.segment"+segNum+side+"'"+segValue+"'\n";
        }
    }



    public String getSql(String cFrom, String cWhere, String gBy, String cPer, String pivot){
        StringBuilder bSql = new StringBuilder();
        bSql.append("select * from (\n")
                .append("select * from (\n")
                .append("select * from (\n")
                .append("select ")
                .append(cFrom)
                .append(StringUtils.isBlank(cFrom)?" ":",")
                .append("gb.period_name, gb.currency_code, ")
                .append(resType.getOut(selectedResult))
                .append( "from gl_balances gb, gl_code_combinations cc, gl_period_statuses ps, gl_ledger_relationships lr,  GL_LEDGERS LED\n");
        if(!zx.getFromPart().toString().isEmpty()){
            bSql.append(" , ")
                    .append(zx.getFromPart()
                            .append("\n"));
        }
        bSql.append("where 1=1\n")
                .append("and gb.code_combination_id=cc.code_combination_id\n")
                .append("and ps.ledger_id=gb.ledger_id \n")
                .append("and ps.period_name=gb.period_name \n")
                .append("and ps.application_id= 101 \n")
                .append("and lr.application_id = 101 \n")
                .append("and led.ledger_id= ps.ledger_id \n")
                .append("and lr.sla_ledger_id=gb.ledger_id \n")
                .append("AND LR.source_ledger_id = PS.ledger_id \n")
                .append("and lr.target_ledger_id= lr.source_ledger_id")
                .append(" and gb.actual_flag='A'\n")
        .append("and gb.template_id is null\n");
        /*if(isPadj()== true){
            bSql.append("and ps.adjustment_period_flag in ('Y','N')\n");
        }
        else {
            bSql.append("and ps.adjustment_period_flag='N'\n");
        }*/
        if(!zx.getFromPart().toString().isEmpty()){
            bSql.append(zx.getWherePart());
        }
        //bSql.append("and abs(nvl(gb.period_net_dr,0)+abs(nvl(gb.period_net_cr,0)))!=0\n");
        bSql.append(" and gb.period_name in (")
                .append(cPer)
                .append(")\n")
                .append(cWhere)
                .append("\n")
                .append("group by ")
                .append(gBy)
                .append(StringUtils.isBlank(gBy)?" ":",")
                .append(" gb.currency_code, gb.period_name\n")
                .append(") where 1=1 \n" )
                .append(resType.zeroSupress(selectedResult));
        if (selectedPivot.getPivotName().equals("Periods")){
            bSql.append("))\n"
                    + "pivot(\n"
                    + resType.getPivOut(selectedResult))//"sum(nvl(ptd,0)) \n")
                    .append("for period_name in (").append(cPer).append("))");
        }
        else if (!selectedPivot.getPivotName().equals("None")){
            bSql.append("))\n" + "pivot(\n")
                    .append(resType.getPivOut(selectedResult)) //"sum(nvl(ptd,0)) \n")
                    .append("for ")
                    .append(pivot)
                    .append("))");
        }
        else{
            bSql.append("))");
        }
        //System.out.println(bSql.toString());
        return bSql.toString();
    }


    public void showGL() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("width", "100%");
        options.put("height", 600);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        RequestContext.getCurrentInstance().openDialog("glJournal", options, null);
    }


    // any message
    public void addMessage(String summary) {
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        //FacesContext.getCurrentInstance().addMessage(null, message);


        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(summary) );
    }
    // cancel status button
    public static  void cancelSW(){
        // setCancelStatus((AtomicBoolean) true);
        cancelStatus.set(true);
        //System.out.println("Pizdec Set!");
    }


    /**
     * @return the fTableBal
     */
    public Future<List<tableOut>> getfTableBal() {
        return fTableBal;
    }

    /**
     * @param fTableBal the fTableBal to set
     */
    public void setfTableBal(Future<List<tableOut>> fTableBal) {
        this.fTableBal = fTableBal;
    }

    /**
     * @return the sortSpan
     */
    public int getSortSpan() {
        return sortSpan;
    }

    /**
     * @param sortSpan the sortSpan to set
     */
    public void setSortSpan(int sortSpan) {
        this.sortSpan = sortSpan;
    }

    /**
     * @return the resultsOut
     */
    public List<String> getResultsOut() {
        return resultsOut;
    }

    /**
     * @param resultsOut the resultsOut to set
     */
    public void setResultsOut(List<String> resultsOut) {
        this.resultsOut = resultsOut;
    }

    /**
     * @return the sumRow
     */
    public List<BigDecimal> getSumRow() {
        return sumRow;
    }

    /**
     * @param sumRow the sumRow to set
     */
    public void setSumRow(List<BigDecimal> sumRow) {
        this.sumRow = sumRow;
    }

    /**
     * @return the sortCol
     */
    public String getSortCol() {
        return sortCol;
    }

    /**
     * @param sortCol the sortCol to set
     */
    public void setSortCol(String sortCol) {
        this.sortCol = sortCol;
    }

    /**
     * @return the sortVal
     */
    public String getSortVal() {
        return sortVal;
    }

    /**
     * @param sortVal the sortVal to set
     */
    public void setSortVal(String sortVal) {
        this.sortVal = sortVal;
    }

    public jeHeader getSelectedJe() {
        return selectedJe;
    }

    public void setSelectedJe(jeHeader selectedJe) {
        this.selectedJe = selectedJe;
    }

    public jeLines getSelectedLine() {
        return selectedLine;
    }

    public void setSelectedLine(jeLines selectedLine) {
        this.selectedLine = selectedLine;
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

    public List<jeLines> getJeTable() {
        return jeTable;
    }

    public void setJeTable(List<jeLines> jeTable) {
        this.jeTable = jeTable;
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

    public slaLines getSelectedSlaLine() {
        return selectedSlaLine;
    }

    public void setSelectedSlaLine(slaLines selectedSlaLine) {
        this.selectedSlaLine = selectedSlaLine;
    }

    public List<String> getSlaColsOut() {
        return slaColsOut;
    }

    public void setSlaColsOut(List<String> slaColsOut) {
        this.slaColsOut = slaColsOut;
    }

    public List<String> getSlaColHeaders() {
        return slaColHeaders;
    }

    public void setSlaColHeaders(List<String> slaColHeaders) {
        this.slaColHeaders = slaColHeaders;
    }

    public List<slaLines> getSlaTable() {
        return slaTable;
    }

    public void setSlaTable(List<slaLines> slaTable) {
        this.slaTable = slaTable;
    }

    public List<slaLines> getfSlaTable() {
        return fSlaTable;
    }

    public void setfSlaTable(List<slaLines> fSlaTable) {
        this.fSlaTable = fSlaTable;
    }

    public List<slaColumnModel> getSlaColumns() {
        return slaColumns;
    }

    public void setSlaColumns(List<slaColumnModel> slaColumns) {
        this.slaColumns = slaColumns;
    }

    public boolean isHlp() {
        return hlp;
    }

    public void setHlp(boolean hlp) {
        this.hlp = hlp;
    }

    public Integer getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(Integer tabIndex) {
        this.tabIndex = tabIndex;
    }

    public boolean isPadj() {
        return padj;
    }

    public void setPadj(boolean padj) {
        this.padj = padj;
    }


    //main table
    static public class ColumnModel implements Serializable {

        private String header;
        private String property;
        private Boolean visible;
        private String sortable;

        public ColumnModel(String header, String property, Boolean visible, String sortable) {
            this.header = header;
            this.property = property;
            this.visible =visible;
            this.sortable=sortable;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }

        /**
         * @return the visible
         */
        public Boolean getVisible() {
            return visible;
        }

        /**
         * @param visible the visible to set
         */
        public void setVisible(Boolean visible) {
            this.visible = visible;
        }

        /**
         * @param property the property to set
         */
        public void setProperty(String property) {
            this.property = property;
        }

        /**
         * @return the sortable
         */
        public String getSortable() {
            return sortable;
        }

        /**
         * @param sortable the sortable to set
         */
        public void setSortable(String sortable) {
            this.sortable = sortable;
        }
    }


    private void createDynamicColumns() {
        //columns=colsHeaders.stream().map(i->{return new ColumnModel(i.toUpperCase(),i);})
        //       .collect(Collectors.toList());
        ColumnModel ax;
        columns.clear();
        Boolean invis=false; // set only first id column to invisible
        for (int i = 0; i < colsOut.size(); i++) {
            columns.add(new ColumnModel(colsHeaders.get(i),colsOut.get(i), invis, null));
            invis=true;
        }
        for (int i = 0; i < colsSortable.size(); i++) { // set sorted columns
            for (int j=0; j<columns.size(); j++) {
                if (colsSortable.get(i).equals(columns.get(j).getHeader())) {
                    ax=columns.get(j);
                    ax.setSortable(colsSortable.get(i));
                    ax.sortable=columns.get(j).property;
                    columns.set(j, ax);
                    //System.out.println(columns.get(j).property+"   -   "+columns.get(j).sortable);
                }
            }
        }
    }

    //Totalizer
    public void setSortProperty(Object o) { // filter value
        //System.out.println("Sum event!");
        if (o instanceof String) {
            // set group by valye for summing
            setSortVal((String) o);
            System.out.println("sortval :"+getSortVal());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            DataTable table = (DataTable) UIComponent.getCurrentComponent(facesContext);
            UIColumn sortColumn = table.getSortColumn();
            System.out.println("vvv: "+table.getSortBy().toString());
            // get header text, then find respective column name an set it
            setSortCol((columns.stream().filter(i->i.getHeader().equals ((String)table.getSortColumn().getHeaderText() )  ).map(i->i.property).findAny().get()));
            // call sum calculation
            try {
                sumRowCalc();
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            // refresh sum row
            RequestContext.getCurrentInstance().update("rform:outabs:bals");

        }
    }

    public void onsort(SortEvent event)  { //filter by
        System.out.println("Sort event!");
        if(event!=null){
            // get header text, then find respective column name an set it
            setSortCol((columns.stream().filter(i->i.getHeader().equals ((String)event.getSortColumn().getHeaderText() )  ).map(i->i.property).findAny().get()));
        }
    }

    public void sumRowCalc() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        sumRow.clear();
        List<BigDecimal> z = new ArrayList<>();
        System.out.println("Сортируем по "+ sortCol);
        if (StringUtils.isBlank(sortCol)) return;
        IntStream.range(0, resultsOut.size())
                .forEach(p -> {
                            z.add(
                                    IntStream.range(0, filteredBal.size()).filter(i -> {
                                        Method ax = null;
                                        tableOut bx;
                                        String cx = "";
                                        bx = filteredBal.get(i);

                                        try { // поле по которому сортируем reflection
                                            ax = bx.getClass().getMethod("get" + StringUtils.capitalize(sortCol));
                                        } catch (NoSuchMethodException ex) {
                                            Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        System.out.println("method: "+ax);

                                        try { /* получаем значение сортируемого поля reflection */
                                            cx = (String) ax.invoke(bx);

                                            System.out.println("method value: "+cx);

                                        } catch (IllegalAccessException ex) {
                                            Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IllegalArgumentException ex) {
                                            Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (InvocationTargetException ex) {
                                            Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        System.out.print("val "+cx +" - "+getSortVal());

                                        if (cx.equals(getSortVal())) {
                                            System.out.print("равно");
                                            return true;
                                        }
                                        System.out.print("не равно");
                                        return false;
                                    })
                                            .mapToObj(i -> filteredBal.get(i))

                                            .map(q -> {

                                                Method dx = null;
                                                BigDecimal ex = new BigDecimal("0");

                                                //fx=q;
                                                try {
                                                    dx = q.getClass().getMethod("getA" + (p + 1));
                                                } catch (NoSuchMethodException ex1) {
                                                    Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex1);
                                                }
                                                System.out.println("getA" + (p + 1));
                                                System.out.println("class: "+q.getClass());

                                                try {
                                                    ex = (BigDecimal) dx.invoke((tableOut) q);
                                                    System.out.println("val ex:"+ex);
                                                } catch (IllegalAccessException ex1) {
                                                    Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex1);
                                                } catch (IllegalArgumentException ex1) {
                                                    Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex1);
                                                } catch (InvocationTargetException ex1) {
                                                    Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex1);
                                                }

                                                if (ex == null) {
                                                    System.out.println("nullllll");
                                                    return BigDecimal.ZERO;
                                                }
                                                return ex;
                                            }).peek(q -> System.out.println(q.toString()))
                                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            );
                        }
                );
        System.out.println("sum: "+z);
        sumRow.addAll(z);
    }



    //Drill
    public void drillAction()  throws InvocationTargetException, NoSuchMethodException{
        drillConditions ax = new drillConditions();
        StringBuilder whereSegments= new StringBuilder();
        String sqlSegHeaders;
        String cx="";
        glPeriods dx;// = new glPeriods();

        //System.out.println(selectedPivot.getPivotName());

        if (selectedRange == null){
            addMessage("Pls. chose something");
            return;
        }
        if (!selectedPivot.getPivotName().equals("None")){
            addMessage("Can't drill in pivot mode!");
            return;
        }

        //drillTab.clear();
        RequestContext requestContext = RequestContext.getCurrentInstance(); // clear filters on table
        requestContext.execute("PF('drillTable').clearFilters()");
        //requestContext.execute("PF('jeTable').clearFilters()");
        //requestContext.update("outab:outabs:drilltab");
        //requestContext.update("outab:outabs:jtab");

        ax.setLedgerId(selectedLedger.getLedgerId().toString()); //added ledger Id
        sqlSegHeaders=acStruct.stream().map(i->"cc."+i.getColumnName().toLowerCase()+",").reduce("", String::concat);
        drillColsOut.clear();
        drillColsOut.add("rowid");
        drillColsOut.add("source");
        drillColsOut.add("entityCode");
        drillColsOut.add("eventTypeCode");

        drillColsHeaders.clear();
        drillColsHeaders.add("rowid");
        drillColsHeaders.add("Source Application");
        drillColsHeaders.add("Entity code");
        drillColsHeaders.add("Entity type code");


        if (selectedPivot.getPivotName().equals("None")) {
            //selectedRange
            for (int i = 1; i <= 25; i++) {
                try {
                    cx=BeanUtils.getProperty(selectedRange, "segment" + i);
                    if(cx!=null){
                        //drillColsOut.add("segment"+i);
                        whereSegments.append("and segment").append(i).append("=\'").append(cx).append("\'\n");
                    }
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(flexSegmentFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (StringUtils.isBlank(whereSegments.toString())){
                addMessage("No range selected!");
                return;
            }

            if (!selectedLedger.getCurrencyCode().equals(selectedRange.getCurrencyCode())){ // only for foreign currencies
                ax.setCurrencyCode("currency_code=\'"+selectedRange.getCurrencyCode()+"\'\n");
            }
            whereSegments.append(" and chart_of_accounts_id=").append(acStruct.get(0).getCoaId().toString()).append("\n");
            whereSegments.append(parentWhere); // add parent conditions
            ax.setSegments(whereSegments.toString()); // segments added

            drillColsOut.addAll(acStruct.stream().map(i->i.getColumnName().toLowerCase()).collect(Collectors.toList())); // seg columns
            drillColsHeaders.addAll(acStruct.stream().map(i->i.getFormLeftPrompt()).collect(Collectors.toList())); // seg headers for drill table
            drillColsOut.addAll(slaFixSql);

            /*drillColsOut.add("aeLineNum");
            drillColsOut.add("accountingClassCode");
            drillColsOut.add("transactionNumber");
            drillColsOut.add("party");
            drillColsOut.add("partySite");
            drillColsOut.add("currencyCode");
            drillColsOut.add("enteredDr");
            drillColsOut.add("enteredCr");
            drillColsOut.add("accountedDr");
            drillColsOut.add("accountedCr");
            drillColsOut.add("description");
            drillColsOut.add("batchName");
            drillColsOut.add("batchStatus");
            drillColsOut.add("journalName");
            drillColsOut.add("journalLine");
            drillColsOut.add("aeHeaderId");
            drillColsOut.add("jeHeaderId");*/

            drillColsHeaders.addAll(slaFixHeaders);
            /*
            drillColsHeaders.add("Line");
            drillColsHeaders.add("Accounting class");
            drillColsHeaders.add("Transaction Num");
            drillColsHeaders.add("Party");
            drillColsHeaders.add("Party site");
            drillColsHeaders.add("Currency");
            drillColsHeaders.add("Entered Dr");
            drillColsHeaders.add("Entered Cr");
            drillColsHeaders.add("Accounted Dr");
            drillColsHeaders.add("Accounted Cr");
            drillColsHeaders.add("Line Description");
            drillColsHeaders.add("Batch name");
            drillColsHeaders.add("Batch status");
            drillColsHeaders.add("Journal name");
            drillColsHeaders.add("Journal Line num");
            drillColsHeaders.add("ae_header_id");
            drillColsHeaders.add("je_header_id");
*/
            dx=periodList.stream()
                    .filter(i->i.getPeriodName().equals(selectedRange.getPeriodName()))
                    .findAny().get();
            ax.setAccountingDateFrom("\'"+DateFormatUtils.format(dx.getStartDate(),"dd.MM.yyyy")+"\'"); // strat accounting date added
            ax.setAccountingDateTo("\'"+DateFormatUtils.format(dx.getEndDate(),"dd.MM.yyyy")+"\'"); // end accounting date added
            ax.setSqlHeaders(sqlSegHeaders);



        }




        drillTab = new LazyDataModel<tableDrill>() {
            StringBuilder filter =new StringBuilder();
            @Override
            public List<tableDrill> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                                         Map<String, Object> filters) {

                //filters.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
                //filters.forEach((k,v)->System.out.println("ora : " + tableDrill.getOraJava().get(k)+" val:"+v));
                filter.setLength(0);
                filters.forEach((k,v)->filter.append("and ")
                        .append(tableDrill.getOraJava().get(k))
                        .append(" like ")
                        .append("\'%")
                        .append(v)
                        .append("%\'\n"));


                List<tableDrill> lazyDrill = new ArrayList<tableDrill>();
                //System.out.println("Outer: "+Thread.currentThread().getId());
                Future <List<tableDrill>> futLaz=segFac.drill(ax, filter.toString(), first, pageSize, Applic.getSelectedStaticApps());
                while (!futLaz.isDone()) {
                    // Wait
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //check cancel status
                    //System.out.println("piiii...");
                    //System.out.println(cancelStatus.toString());
                    if(cancelStatus.compareAndSet(true, false)){
                        //cancelStatus.set(false);
                        futLaz.cancel(false);
                        //System.out.println("Pizdec!");
                        return lazyDrill;
                    }

                }
                if(!futLaz.isCancelled()){
                    try {
                        lazyDrill=futLaz.get();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(rangeBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else cancelStatus.compareAndSet(false, true);
                if(lazyDrill.size()>0){
                    drillTab.setRowCount(lazyDrill.get(0).getResultCount());
                }

                //System.out.println("Yehooo!");
                return lazyDrill;
            }
            @Override
            public tableDrill getRowData(String playerId) {
                //Integer id = Integer.valueOf(playerId);
                for (tableDrill player : drillTab) {
                    if (playerId.equals(player.getRowid())) {
                        return player;
                    }
                }
                return null;
            }



        };
        //drillTab.setRowCount(segFac.drillCount(ax));




        //reset table state
        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent("rform:outabs:drillTab");
        table.setValueExpression("sortCol", null);
        table.setValueExpression("filterBy", null);

        createDrillColumns();

        //       pool.shutdown();

        //drillTab.stream().forEach(i->System.out.println(i.getSource()+i.getDescription()));
setTabIndex(1);
    }

    //drill table
    static public class drillColumnModel implements Serializable {

        private String header;
        private String property;
        private Boolean visible;

        public drillColumnModel(String header, String property, Boolean visible) {
            this.header = header;
            this.property = property;
            this.visible =visible;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }

        /**
         * @return the visible
         */
        public Boolean getVisible() {
            return visible;
        }

        /**
         * @param visible the visible to set
         */
        public void setVisible(Boolean visible) {
            this.visible = visible;
        }
    }

    private void createDrillColumns() {
        //columns=colsHeaders.stream().map(i->{return new ColumnModel(i.toUpperCase(),i);})
        //       .collect(Collectors.toList());
        drillColumns.clear();
        Boolean invis=false; // set only first id column to invisible
        for (int i = 0; i < drillColsOut.size(); i++) {
            drillColumns.add(new drillColumnModel(drillColsHeaders.get(i),drillColsOut.get(i), invis));
            invis=true;
        }
    }

// je
    public void jeAction()  {
        String sqlSegHeaders;

        if (getSelectedDrill()==null){
            addMessage("Pls. chose line by clicking on it.");
            return;
        }
        //jeTab.clear();
        RequestContext requestContext = RequestContext.getCurrentInstance(); // clear filters on table
        requestContext.execute("PF('jeTable').clearFilters()");
        //requestContext.update("outab:outabs:jtab");


        selectedJe= segFac.getJeHeader(getSelectedDrill().getJeHeaderId());

        sqlSegHeaders=(getAcStruct().stream().map(i->"cc."+i.getColumnName().toLowerCase()+",").reduce("", String::concat)); // selected segments

        jColsOut.clear();
        jColsOut.add("rowid");
        jColsOut.add("jeLineNum");

        jColsOut.addAll(getAcStruct().stream().map(i->i.getColumnName().toLowerCase()).collect(Collectors.toList())); // seg columns

        jColsOut.add("enteredDr");
        jColsOut.add("enteredCr");
        jColsOut.add("accountedDr");
        jColsOut.add("accountedCr");
        jColsOut.add("lineDescription");

        jColHeaders.clear();
        jColHeaders.add("rowid");
        jColHeaders.add("Line num");

        jColHeaders.addAll(getAcStruct().stream().map(i->i.getFormLeftPrompt()).collect(Collectors.toList())); // seg headers for drill table

        jColHeaders.add("Entered Dr");
        jColHeaders.add("Entered Cr");
        jColHeaders.add("Accounted Dr");
        jColHeaders.add("Accounted Cr");
        jColHeaders.add("Line Description");

        jeTable.clear();
        jeTable= segFac.getJlines(getSelectedDrill().getJeHeaderId(), sqlSegHeaders);


        createJeColumns();
setTabIndex(2);
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
        jeColumns.clear();
        for (int i = 0; i < jColsOut.size(); i++) {
            jeColumns.add(new jeColumnModel(jColHeaders.get(i), jColsOut.get(i), invis));
            invis=true;
        }
    }

// sla

    public void slaAction()  {
        String sqlSegHeaders;

        if (getSelectedDrill()==null){
            addMessage("Pls. chose line by clicking on it.");
            return;
        }

        RequestContext requestContext = RequestContext.getCurrentInstance(); // clear filters on table
        requestContext.execute("PF('slaTable').clearFilters()");


        //selectedJe= segFac.getJeHeader(getSelectedDrill().getAeHeaderId());

        sqlSegHeaders=(getAcStruct().stream().map(i->"cc."+i.getColumnName().toLowerCase()+",").reduce("", String::concat)); // selected segments

        slaColsOut.clear();
        slaColsOut.add("rowid");
        slaColsOut.add("eventTypeCode");
        slaColsOut.add("accountingDate");
        slaColsOut.add("glTransferStatusCode");
        slaColsOut.add("aeLineNum");
        slaColsOut.add("accountingClassCode");


        slaColsOut.addAll(getAcStruct().stream().map(i->i.getColumnName().toLowerCase()).collect(Collectors.toList())); // seg columns

        slaColsOut.add("enteredDr");
        slaColsOut.add("enteredCr");
        slaColsOut.add("accountedDr");
        slaColsOut.add("accountedCr");
        slaColsOut.add("currency");
        slaColsOut.add("currencyConversionDate");
        slaColsOut.add("currencyConversionRate");
        slaColsOut.add("currencyConversionType");
        slaColsOut.add("party");
        slaColsOut.add("partySite");
        slaColsOut.add("hDescription");
        slaColsOut.add("lDescription");

        slaColHeaders.clear();
        slaColHeaders.add("rowid");
        slaColHeaders.add("Event type");
        slaColHeaders.add("Accounting date");
        slaColHeaders.add("GL transfer");
        slaColHeaders.add("Line num");
        slaColHeaders.add("Accounting class");

        slaColHeaders.addAll(getAcStruct().stream().map(i->i.getFormLeftPrompt()).collect(Collectors.toList())); // seg headers for drill table

        slaColHeaders.add("Entered Dr");
        slaColHeaders.add("Entered Cr");
        slaColHeaders.add("Accounted Dr");
        slaColHeaders.add("Accounted Cr");
        slaColHeaders.add("Currency");
        slaColHeaders.add("Conversion date");
        slaColHeaders.add("Conversion rate");
        slaColHeaders.add("Conversion type");
        slaColHeaders.add("Party");
        slaColHeaders.add("Party site");
        slaColHeaders.add("Header description");
        slaColHeaders.add("Line description");


        slaTable.clear();
        slaTable=segFac.getSlaLines(getSelectedDrill().getAeHeaderId(), sqlSegHeaders, selectedLedger);


        createSlaColumns();
setTabIndex(3);
    }



    static public class slaColumnModel implements Serializable {

        private String header;
        private String property;
        private Boolean visible;

        public slaColumnModel(String header, String property, Boolean visible) {
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

    private void createSlaColumns() {
        Boolean invis=false; // set only first id column to invisible
        slaColumns.clear();
        for (int i = 0; i < slaColsOut.size(); i++) {
            slaColumns.add(new slaColumnModel(slaColHeaders.get(i), slaColsOut.get(i), invis));
            invis=true;
        }
    }


    public String getMyFormattedDate(Date myDate) {
        //Calendar c = Calendar.getInstance();
        //c.setTime(myDate);
        //c.add(Calendar.YEAR, 2000);
        //myDate=c.getTime();
        //return DateFormatUtils.ISO_DATE_FORMAT.format(c);
        //return new SimpleDateFormat("dd-MM-yyyy").format(myDate);
        return DateFormatUtils.format(myDate, "dd-MM-yyyy");
    }



    public int getRandomPrice() {
        return (int) (random() * 100000);
    }
    public void onSummaryRow(Object filter)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ELResolver elResolver = elContext.getELResolver();

        DataTable table = (DataTable) UIComponent.getCurrentComponent(facesContext);

        UIColumn sortColumn = table.getSortColumn();
        ValueExpression expression = sortColumn.getValueExpression("sortBy");
        ValueReference reference = ValueExpressionAnalyzer.getReference(elContext, expression);
        String property = (String) reference.getProperty();

        int total = 0;
        List<?> rowList = (List<?>) table.getValue();
        for(Object row : rowList)
        {
            Object value = elResolver.getValue(elContext, row, property);
            if(filter.equals(value))
            {
                // THIS IS THE ONLY POINT TO CUSTOMIZE
                System.out.println(property);

            }
        }

        List<UIComponent> children = table.getSummaryRow().getChildren();
        UIComponent column = children.get(children.size() - 1);
        column.getAttributes().put("total", total);
    }


// Getters and setters
    /**
     * @return the fStruct
     */
    public flexSegmentFacade getfStruct() {
        return segFac;
    }

    /**
     * @param fStruct the fStruct to set
     */
    public void flexSegmentFacade(flexSegmentFacade fStruct) {
        this.segFac = segFac;
    }

    /**
     * @return the acStruct
     */
    public List<sobStruct> getAcStruct() {
        return acStruct;
    }
    public static List<sobStruct> getStaticAcStruct() {
        return acStruct;
    }

    /**
     * @param acStruct the acStruct to set
     */
    public void setAcStruct(List<sobStruct> acStruct) {
        this.acStruct = acStruct;
    }

    /**
     * @return the selectedLedger
     */
    public ledgers getSelectedLedger() {
        return selectedLedger;
    }



    /**
     * @param selectedLedger the selectedLedger to set
     */
    public void setSelectedLedger(ledgers selectedLedger) {
        this.selectedLedger = selectedLedger;
    }

    /**
     * @return the ledgersList
     */
    public static List<ledgers> getLedgersList() {
        return ledgersList;
    }

    /**
     * @param aLedgersList the ledgersList to set
     */
    public static void setLedgersList(List<ledgers> aLedgersList) {
        ledgersList = aLedgersList;
    }

    /**
     * @return the periodFrom
     */
    public glPeriods getPeriodFrom() {
        return periodFrom;
    }

    /**
     * @param periodFrom the periodFrom to set
     */
    public void setPeriodFrom(glPeriods periodFrom) {
        this.periodFrom = periodFrom;
    }

    /**
     * @return the periodTo
     */
    public glPeriods getPeriodTo() {
        return periodTo;
    }

    /**
     * @param periodTo the periodTo to set
     */
    public void setPeriodTo(glPeriods periodTo) {
        this.periodTo = periodTo;
    }

    /**
     * @return the periodList
     */
    public static List<glPeriods> getPeriodList() {
        return periodList;
    }

    /**
     * @param aPeriodList the periodList to set
     */
    public static void setPeriodList(List<glPeriods> aPeriodList) {
        periodList = aPeriodList;
    }

    /**
     * @return the periodDisabled
     */
    public boolean isPeriodDisabled() {
        return periodDisabled;
    }

    /**
     * @param periodDisabled the periodDisabled to set
     */
    public void setPeriodDisabled(boolean periodDisabled) {
        this.periodDisabled = periodDisabled;
    }

    /**
     * @return the submitDisabled
     */
    public boolean isSubmitDisabled() {
        return submitDisabled;
    }

    /**
     * @param submitDisabled the submitDisabled to set
     */
    public void setSubmitDisabled(boolean submitDisabled) {
        this.submitDisabled = submitDisabled;
    }

    /**
     * @return the columns
     */
    public List<ColumnModel> getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    /**
     * @return the tableBal
     */
    public List<tableOut> getTableBal() {
        return tableBal;
    }

    /**
     * @param tableBal the tableBal to set
     */
    public void setTableBal(List<tableOut> tableBal) {
        this.tableBal =  tableBal;
    }

    /**
     * @return the filteredBal
     */
    public List<tableOut> getFilteredBal() {
        return filteredBal;
    }

    /**
     * @param filteredBal the filteredBal to set
     */
    public void setFilteredBal(List<tableOut> filteredBal) {
        this.filteredBal = filteredBal;
    }

    /**
     * @return the segFac
     */
    public flexSegmentFacade getSegFac() {
        return segFac;
    }

    /**
     * @param segFac the segFac to set
     */
    public void setSegFac(flexSegmentFacade segFac) {
        this.segFac = segFac;
    }

    /**
     * @return the ledgersFac
     */
    public flexSegmentFacade getLedgersFac() {
        return segFac;
    }

    /**
     * @param segFac the ledgersFac to set
     */
    public void setLedgersFac(flexSegmentFacade segFac) {
        this.segFac = segFac;
    }

    /**
     * @return the periodsFac
     */
    public flexSegmentFacade getPeriodsFac() {
        return segFac;
    }

    /**
     * @param segFac the periodsFac to set
     */
    public void setPeriodsFac(flexSegmentFacade segFac) {
        this.segFac = segFac;
    }

    /**
     * @return the tableFac
     */
    public flexSegmentFacade getTableFac() {
        return segFac;
    }

    /**
     * @param segFac the tableFac to set
     */
    public void setTableFac(flexSegmentFacade segFac) {
        this.segFac = segFac;
    }

    /**
     * @return the colsOut
     */
    public List<String> getColsOut() {
        return colsOut;
    }

    /**
     * @param colsOut the colsOut to set
     */
    public void setColsOut(List<String> colsOut) {
        this.colsOut = colsOut;
    }

    /**
     * @return the results
     */
    // public String getResults() {
    //     return results;
    // }

    /**
     * @param results the results to set
     */
    // public void setResults(String results) {
    //     this.results = results;
    // }

    /**
     * @return the budget
     */
    public String getBudget() {
        return budget;
    }

    /**
     * @param budget the budget to set
     */
    public void setBudget(String budget) {
        this.budget = budget;
    }

    /**
     * @return the pivotListLocal
     */
    public static List<pivot> getPivotList() {
        return pivotList;
    }

    /**
     * @param pivotList the pivotListLocal to set
     */
    public void setPivotList(List<pivot> pivotList) {
        rangeBean.pivotList = pivotList;
    }

    /**
     * @return the selectedPivot
     */
    public pivot getSelectedPivot() {
        return selectedPivot;
    }

    /**
     * @param selectedPivot the selectedPivot to set
     */
    public void setSelectedPivot(pivot selectedPivot) {
        this.selectedPivot = selectedPivot;
    }


    /**
     * @return the pivotName
     */
    public String getPivotName() {
        return pivotName;
    }

    /**
     * @param pivotName the pivotName to set
     */
    public void setPivotName(String pivotName) {
        this.pivotName = pivotName;
    }

    /**
     * @return the model
     */
    public DynaFormModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(DynaFormModel model) {
        this.model = model;
    }

    /**
     * @return the rangeLabel
     */
    public List<DynaFormLabel> getRangeLabel() {
        return rangeLabel;
    }

    /**
     * @param rangeLabel the rangeLabel to set
     */
    public void setRangeLabel(List<DynaFormLabel> rangeLabel) {
        this.rangeLabel = rangeLabel;
    }

    /**
     * @return the rangeControl
     */
    public List<DynaFormControl> getRangeControl() {
        return rangeControl;
    }

    /**
     * @param rangeControl the rangeControl to set
     */
    public void setRangeControl(List<DynaFormControl> rangeControl) {
        this.rangeControl = rangeControl;
    }

    /**
     * @return the frow
     */
    public DynaFormRow getFrow() {
        return frow;
    }

    /**
     * @param frow the frow to set
     */
    public void setFrow(DynaFormRow frow) {
        this.frow = frow;
    }

    /**
     * @return the selectedRange
     */
    public tableOut getSelectedRange() {
        return selectedRange;
    }

    /**
     * @param selectedRange the selectedRange to set
     */
    public void setSelectedRange(tableOut selectedRange) {
        this.selectedRange = selectedRange;
    }

    /**
     * @return the pivotHeaders
     */
    public List<String> getPivotHeaders() {
        return pivotHeaders;
    }

    /**
     * @param pivotHeaders the pivotHeaders to set
     */
    public void setPivotHeaders(List<String> pivotHeaders) {
        this.pivotHeaders = pivotHeaders;
    }

    /**
     * @return the colsHeaders
     */
    public List<String> getColsHeaders() {
        return colsHeaders;
    }

    /**
     * @param colsHeaders the colsHeaders to set
     */
    public void setColsHeaders(List<String> colsHeaders) {
        this.colsHeaders = colsHeaders;
    }



    /**
     * @return the drillFilter
     */
    public List<tableDrill> getDrillFilter() {
        return drillFilter;
    }

    /**
     * @param drillFilter the drillFilter to set
     */
    public void setDrillFilter(List<tableDrill> drillFilter) {
        this.drillFilter = drillFilter;
    }

    /**
     * @return the selectedDrill
     */
    public static tableDrill getSelectedStaticDrill() {
        return selectedDrill;
    }

    public  tableDrill getSelectedDrill() {
        return selectedDrill;
    }

    /**
     * @param selectedDrill the selectedDrill to set
     */
    public void setSelectedDrill(tableDrill selectedDrill) {
        this.selectedDrill = selectedDrill;
    }

    /**
     * @return the pivotSegId
     */
    public String getPivotSegId() {
        return pivotSegId;
    }

    /**
     * @param pivotSegId the pivotSegId to set
     */
    public void setPivotSegId(String pivotSegId) {
        this.pivotSegId = pivotSegId;
    }

    /**
     * @return the pivotSegName
     */
    public String getPivotSegName() {
        return pivotSegName;
    }

    /**
     * @param pivotSegName the pivotSegName to set
     */
    public void setPivotSegName(String pivotSegName) {
        this.pivotSegName = pivotSegName;
    }

    /**
     * @return the drillColumns
     */
    public List<drillColumnModel> getDrillColumns() {
        return drillColumns;
    }

    /**
     * @param drillColumns the drillColumns to set
     */
    public void setDrillColumns(List<drillColumnModel> drillColumns) {
        this.drillColumns = drillColumns;
    }

    /**
     * @return the drillColsOut
     */
    public List<String> getDrillColsOut() {
        return drillColsOut;
    }

    /**
     * @param drillColsOut the drillColsOut to set
     */
    public void setDrillColsOut(List<String> drillColsOut) {
        this.drillColsOut = drillColsOut;
    }

    /**
     * @return the drillColsHeaders
     */
    public List<String> getDrillColsHeaders() {
        return drillColsHeaders;
    }

    /**
     * @param drillColsHeaders the drillColsHeaders to set
     */
    public void setDrillColsHeaders(List<String> drillColsHeaders) {
        this.drillColsHeaders = drillColsHeaders;
    }

    /**
     * @param drillTab the drillTab to set
     */
    public void setDrillTab(LazyDataModel drillTab) {
        this.drillTab = drillTab;
    }

    /**
     * @return the drillTab
     */
    public LazyDataModel<tableDrill> getDrillTab() {
        return drillTab;
    }

    /**
     * @return the cancelStatus
     */
    public AtomicBoolean getCancelStatus() {
        return cancelStatus;
    }

    /**
     * @param cancelStatus the cancelStatus to set
     */
    public void setCancelStatus(AtomicBoolean cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    /**
     * @return the resType
     */
    public results getResType() {
        return resType;
    }

    /**
     * @param resType the resType to set
     */
    public void setResType(results resType) {
        this.resType = resType;
    }

    /**
     * @return the selectedResult
     */
    public String getSelectedResult() {
        return selectedResult;
    }

    /**
     * @param selectedResult the selectedResult to set
     */
    public void setSelectedResult(String selectedResult) {
        this.selectedResult = selectedResult;
    }

    /**
     * @return the colsUpHeaders
     */
    public List<TableHeaders> getColsUpHeaders() {
        return colsUpHeaders;
    }

    /**
     * @param colsUpHeaders the colsUpHeaders to set
     */
    public void setColsUpHeaders(List<TableHeaders> colsUpHeaders) {
        this.colsUpHeaders = colsUpHeaders;
    }

    /**
     * @return the colsSortable
     */
    public List<String> getColsSortable() {
        return colsSortable;
    }

    /**
     * @param colsSortable the colsSortable to set
     */
    public void setColsSortable(List<String> colsSortable) {
        this.colsSortable = colsSortable;
    }


}


