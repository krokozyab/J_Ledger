/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreobjects;

//import coreoblects.tableOutFacade;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.persistence.config.TargetServer;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.spi.PersistenceUnitTransactionType;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.eclipse.persistence.config.PersistenceUnitProperties.*;


/**
 *
 * @author facet
 */
@Stateless
public class flexSegmentFacade extends AbstractFacade<flexSegment>   {

    @PersistenceContext(unitName = "com.local_J_Ledger_war_1.0-SNAPSHOTPU")
    //@PersistenceContext(unitName = "pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    Map properties = new HashMap();
    public flexSegmentFacade() {
        super(flexSegment.class);
// Ensure RESOURCE_LOCAL transactions is used.
        properties.put(TRANSACTION_TYPE, PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
        // Configure the internal EclipseLink connection pool
        properties.put(JDBC_DRIVER, "oracle.jdbc.OracleDriver");
        properties.put(JDBC_URL, "jdbc:oracle:thin:@apps22h-dev.oebs.yandex.net:1531:CLONE22H");
        properties.put(JDBC_USER, "apps");
        properties.put(JDBC_PASSWORD, "apps");
        // Configure logging. FINE ensures all SQL is shown
        properties.put(LOGGING_LEVEL, "FINE");
        properties.put(LOGGING_TIMESTAMP, "false");
        properties.put(LOGGING_THREAD, "false");
        properties.put(LOGGING_SESSION, "false");
        // Ensure that no server-platform is configured
        properties.put(TARGET_SERVER, TargetServer.None);
        Persistence.createEntityManagerFactory("com.local_J_Ledger_war_1.0-SNAPSHOTPU", properties);
    }



     public List<flexSegment> segValues(long setId){
     List<flexSegment> segVals=new ArrayList<>();
     StringBuilder qry = new StringBuilder();
     String qpr;
     Long ax;
     ax=setId;
     qpr=ax.toString();
     qry.append("select fv.flex_value_set_id, fv.flex_value_id, fv.flex_value, ft.description \n"
    + " from fnd_flex_values fv, fnd_flex_values_tl ft \n"
    + " where 1=1 \n " 
    + " and fv.enabled_flag='Y' \n"
    + " and fv.summary_flag='N' \n"
    + " and ft.flex_value_id=fv.flex_value_id \n"
    + " and ft.language=USERENV('LANG') \n"
    + " and fv.flex_value_set_id=");
    qry.append(qpr);
    qry.append(" \n");
    qry.append(" order by flex_value");

    segVals=(List<flexSegment>) em.createNativeQuery(qry.toString(), flexSegment.class).getResultList();

    return segVals;
     }


public List <eApplication> getApps() {
        String qry="select application_id, application_name from fnd_application_tl\n" +
                "where language=userenv('LANG')\n" +
                "and application_name not like ('%Obsolete%')\n" +
                "and application_name not like ('%устарело%')\n" +
                "and application_id>99\n" +
                "order by application_id";
    return  (List<eApplication>) em.createNativeQuery(qry, eApplication.class).getResultList();
    }

public List<String> getPivot(String setId, String where){
    StringBuilder qry = new StringBuilder();
         qry.append("select fv.flex_value \n"
    + " from fnd_flex_values fv, fnd_flex_values_tl ft \n"
    + " where 1=1 \n " 
    + " and fv.enabled_flag='Y' \n"
    + " and fv.summary_flag='N' \n"
    + " and ft.flex_value_id=fv.flex_value_id \n"
    + " and ft.language=USERENV('LANG') \n"
    + " and fv.flex_value_set_id=")
    .append(setId)
    .append(" \n")
    .append(where)
    .append("and rownum<90")
    .append("order by flex_value");
        // System.out.println("pvn____________");
    //System.out.println(qry.toString());
    return  (List<String>)em.createNativeQuery(qry.toString()).getResultList();
}
     




public List<String> segComplete(String setId, String beginWith){
     List<sgv> result = new ArrayList<>();
     StringBuilder qry = new StringBuilder();
     List<String> rst = new ArrayList<>();
     //sgv ax;
     //qpr=ax.toString();
     qry.append("select  fv.flex_value_id, fv.flex_value, fv.summary_flag, ft.description \n"
    + " from fnd_flex_values fv, fnd_flex_values_tl ft \n"
    + " where 1=1 \n " 
    + " and fv.enabled_flag='Y' \n"
    //+ " and fv.summary_flag='N' \n"
    + " and ft.flex_value_id=fv.flex_value_id \n"
    + " and ft.language=USERENV('LANG') and rownum<90 \n"
    + " and fv.flex_value_set_id=");
    qry.append(setId);
    qry.append(" \n");
    qry.append(" and fv.flex_value like \'");
    qry.append(beginWith);
    qry.append("%\' \n");
    qry.append(" order by flex_value");
    result=  (List<sgv>)em.createNativeQuery(qry.toString(), sgv.class).getResultList();
    result.forEach(i->rst.add(i.getFlex_value()));
    return rst; 
     }

    public List<String> segHelpComplete(String setId, String beginWith){
        List<sgv> result = new ArrayList<>();
        StringBuilder qry = new StringBuilder();
        List<String> rst = new ArrayList<>();
        //sgv ax;
        //qpr=ax.toString();
        qry.append("select  fv.flex_value_id, fv.flex_value, fv.summary_flag, ft.description \n"
                + " from fnd_flex_values fv, fnd_flex_values_tl ft \n"
                + " where 1=1 \n "
                + " and fv.enabled_flag='Y' \n"
                //+ " and fv.summary_flag='N' \n"
                + " and ft.flex_value_id=fv.flex_value_id \n"
                + " and ft.language=USERENV('LANG') and rownum<200 \n"
                + " and fv.flex_value_set_id=");
        qry.append(setId);
        qry.append(" \n");
        qry.append(" and fv.flex_value||'  '||ft.description like \'%");
        qry.append(beginWith);
        qry.append("%\' \n");
        qry.append(" order by flex_value");
        result=  (List<sgv>)em.createNativeQuery(qry.toString(), sgv.class).getResultList();
        result.forEach(i->rst.add(i.getFlex_value()+"  "+i.getDescription()));
        return rst;
    }



    public sgv getSegValue(String setId, String segVal){
        StringBuilder qry = new StringBuilder();
        qry.append("select  fv.flex_value_id, fv.flex_value, fv.summary_flag \n"
                + " from fnd_flex_values fv, fnd_flex_values_tl ft \n"
                + " where 1=1 \n "
                + " and fv.enabled_flag='Y' \n"
                //+ " and fv.summary_flag='N' \n"
                + " and ft.flex_value_id=fv.flex_value_id \n"
                + " and ft.language=USERENV('LANG') and rownum<90 \n"
                + " and fv.flex_value_set_id=");
        qry.append(setId);
        qry.append(" \n");
        qry.append(" and fv.flex_value='");
        qry.append(segVal);
        qry.append("\' \n");

        return (sgv)em.createNativeQuery(qry.toString(), sgv.class).getSingleResult();
    }

public List<String> getSumSegList(String setId, String segVal){
    StringBuilder qry = new StringBuilder();
    qry.append("SELECT flex_value\n" +
            "FROM (\n" +
            "SELECT h.parent_flex_value,\n" +
            "v.flex_value\n" +
            "FROM fnd_flex_value_sets vs,\n" +
            "fnd_flex_values v,\n" +
            "fnd_flex_value_norm_hierarchy h\n" +
            "where 1=1\n" +
            "and vs.flex_value_set_id=")
            .append(setId)
            .append("\n")
            .append("AND vs.flex_value_set_id = v.flex_value_set_id\n" +
                    "AND v.flex_value_set_id = h.flex_value_set_id\n" +
                    "AND ((v.summary_flag = 'Y' AND h.range_attribute = 'P') OR (v.summary_flag = 'N' and h.range_attribute = 'C'))\n" +
                    "AND v.flex_value BETWEEN h.child_flex_value_low AND h.child_flex_value_high\n" +
                    ")\n" +
                    "connect by prior flex_value = parent_flex_value\n" +
                    "START WITH parent_flex_value = '")
            .append(segVal)
            .append("'");
    return (List<String>) em.createNativeQuery(qry.toString()).getResultList();
}

    public List<sobStruct> allStruct(ledgers ledger){
       // List<sobStruct> results=new ArrayList<>();
       // List<sobStruct> result= new ArrayList<>();
       // sobStruct ax;
        //ledgers ledger;
        StringBuilder qry = new StringBuilder();
        //ledger=rangeBean.getSelectedLedger();
        //+ifs.segment_num seg_id 
        qry.append("select sob.name \n" +
",ffvs.flex_value_set_id\n" +
", sob.ledger_id \n" +
", sob.chart_of_accounts_id \n" +
", fifst.id_flex_structure_name \n" +
", ifs.segment_name\n" +
", ifs.segment_num\n" +
", ifs.display_size \n" +
", ifs.default_type \n" +
", ifs.default_value \n" +                
", ifs.form_left_prompt\n" +
", ifs.application_column_name \n" +
", sav1.attribute_value balancing\n" +
", sav2.attribute_value costCenter\n" +
", sav3.attribute_value naturalAccount\n" +
", sav4.attribute_value intercompany\n" +
", sav5.attribute_value secondaryTracking\n" +
", sav6.attribute_value global\n" +
", ffvs.flex_value_set_name\n" +
", ffvs.flex_value_set_id\n" +
"from fnd_id_flex_structures fifs\n" +
", fnd_id_flex_structures_tl fifst\n" +
", fnd_segment_attribute_values sav1\n" +
", fnd_segment_attribute_values sav2\n" +
", fnd_segment_attribute_values sav3\n" +
", fnd_segment_attribute_values sav4\n" +
", fnd_segment_attribute_values sav5\n" +
", fnd_segment_attribute_values sav6\n" +
", fnd_id_flex_segments_vl ifs\n" +
", fnd_flex_value_sets ffvs\n" +
", gl_ledgers sob\n" +
"where 1=1\n" +
"and fifs.id_flex_code = 'GL#'\n" +
"and fifs.application_id = fifst.application_id\n" +
"and fifs.id_flex_code = fifst.id_flex_code\n" +
"and fifs.id_flex_num = fifst.id_flex_num\n" +
"and fifs.application_id = ifs.application_id\n" +
"and fifs.id_flex_code = ifs.id_flex_code\n" +
"and fifs.id_flex_num = ifs.id_flex_num\n" +
"and sav1.application_id = ifs.application_id\n" +
"and sav1.id_flex_code = ifs.id_flex_code\n" +
"and sav1.id_flex_num = ifs.id_flex_num\n" +
"and sav1.application_column_name = ifs.application_column_name\n" +
"and sav2.application_id = ifs.application_id\n" +
"and sav2.id_flex_code = ifs.id_flex_code\n" +
"and sav2.id_flex_num = ifs.id_flex_num\n" +
"and sav2.application_column_name = ifs.application_column_name\n" +
"and sav3.application_id = ifs.application_id\n" +
"and sav3.id_flex_code = ifs.id_flex_code\n" +
"and sav3.id_flex_num = ifs.id_flex_num\n" +
"and sav3.application_column_name = ifs.application_column_name\n" +
"and sav4.application_id = ifs.application_id\n" +
"and sav4.id_flex_code = ifs.id_flex_code\n" +
"and sav4.id_flex_num = ifs.id_flex_num\n" +
"and sav4.application_column_name = ifs.application_column_name\n" +
"and sav5.application_id = ifs.application_id\n" +
"and sav5.id_flex_code = ifs.id_flex_code\n" +
"and sav5.id_flex_num = ifs.id_flex_num\n" +
"and sav5.application_column_name = ifs.application_column_name\n" +
"and sav6.application_id = ifs.application_id\n" +
"and sav6.id_flex_code = ifs.id_flex_code\n" +
"and sav6.id_flex_num = ifs.id_flex_num\n" +
"and sav6.application_column_name = ifs.application_column_name\n" +
"and sav1.segment_attribute_type = 'GL_BALANCING'\n" +
"and sav2.segment_attribute_type = 'FA_COST_CTR'\n" +
"and sav3.segment_attribute_type = 'GL_ACCOUNT'\n" +
"and sav4.segment_attribute_type = 'GL_INTERCOMPANY'\n" +
"and sav5.segment_attribute_type = 'GL_SECONDARY_TRACKING'\n" +
"and sav6.segment_attribute_type = 'GL_GLOBAL'\n" +
"and ifs.id_flex_num = sob.chart_of_accounts_id\n" +
"and ifs.flex_value_set_id = ffvs.flex_value_set_id\n" +
"and sob.ledger_id =\n");
qry.append(ledger.idToString());
qry.append("\n" +
"and fifst.language=USERENV('LANG')\n" +
"order by sob.name, sob.chart_of_accounts_id, ifs.segment_num");

//System.out.println(qry.toString());

       // results =  
         return       em.createNativeQuery(qry.toString(), sobStruct.class).getResultList();




        //return results;
    }     
 
public List<ledgers> getLedgers(){
        String qry;
        qry="select ledger_id, name, period_set_name, currency_code from gl_ledgers where ledger_category_code!='NONE'";
        return (List<ledgers>) em.createNativeQuery(qry, ledgers.class).getResultList();
        
    }

    public List<glPeriods> getPeriods(String periodSetName) {
        StringBuilder qry = new StringBuilder();
        qry.append("select period_name, start_date, end_date, period_year, period_num, adjustment_period_flag\n"
                + "from gl_periods \n"
                + "where period_set_name=\'");
        qry.append(periodSetName).append("\'\n");
        //qry.append("and period_type ='Month'\n");
        qry.append("order by start_date");
        return (List<glPeriods>) em.createNativeQuery(qry.toString(), glPeriods.class).getResultList();
    }

    public jeHeader getJeHeader(Integer headerId){
        StringBuilder qry = new StringBuilder();
        qry.append("select \n" +
                "gh.je_header_id,\n" +
                "gb.name as batch_name, \n" +
                "gh.name as journal_name,\n" +
                "gh.description,\n" +
                "gh.period_name,\n" +
                "gh.actual_flag as balance_type,\n" +
                "gc.user_je_category_name as category,\n" +
                "gs.user_je_source_name as source,\n" +
                "gh.default_effective_date as effective_date,\n" +
                "gh.status as posted_flag,\n" +
                "gh.currency_code as currency,\n" +
                "gh.currency_conversion_date,\n" +
                "gh.currency_conversion_type,\n" +
                "gh.currency_conversion_rate,\n" +
                "gh.accrual_rev_effective_date as reverse_date,\n" +
                "gh.accrual_rev_period_name as reverse_period,\n" +
                "gh.accrual_rev_status as reverse_status,\n" +
                "fu.user_name as created_by,\n" +
                "ful.user_name as last_updated_by,\n" +
                "gh.last_update_date\n" +
                "from gl_je_headers gh,  gl_je_categories gc, gl_je_sources gs, gl_je_batches gb, fnd_user fu, fnd_user ful\n" +
                "where gh.je_category=gc.je_category_name\n" +
                "and gh.je_source=gs.je_source_name\n" +
                "and gh.je_batch_id=gb.je_batch_id\n" +
                "and gh.created_by=fu.user_id\n" +
                "and gh.last_updated_by=ful.user_id\n" +
                "and gh.je_header_id=")
                .append(headerId.toString());
        return (jeHeader)  em.createNativeQuery(qry.toString(),jeHeader.class).getSingleResult();
    }


    public List<jeLines> getJlines(Integer headerId, String sgs){
        StringBuilder qry = new StringBuilder();
        qry.append("select l.rowid,")
                .append(sgs)
                .append(" l.je_line_num, l.entered_dr, l.entered_cr, l.accounted_dr, l.accounted_cr, l.description  \n" +
                        "from gl_je_lines l, gl_code_combinations cc\n" +
                        "where l.code_combination_id=cc.code_combination_id\n" +
                        "and l.je_header_id=")
                .append(headerId.toString())
                .append("\n")
                .append("order by l.je_line_num");
        return  em.createNativeQuery(qry.toString(), jeLines.class).getResultList();
    }

    public List<slaLines> getSlaLines(Integer aeHeader, String sgs, ledgers lgr){
        StringBuilder qry = new StringBuilder();
        String entity;
        qry.append("select entity_id from xla_ae_headers where ae_header_id=")
                .append(aeHeader.toString())
                .append(" and rownum=1");
        entity= em.createNativeQuery(qry.toString()).getSingleResult().toString();
        qry.setLength(0);
        qry.append("select l.rowid, h.event_type_code, h.accounting_date, h.gl_transfer_status_code, l.ae_line_num, l.accounting_class_code,")
                .append(sgs)
                .append("l.entered_dr, l.entered_cr, l.accounted_dr, l.accounted_cr,\n" +
                        "l.currency_code, l.currency_conversion_date, l.currency_conversion_rate, l.currency_conversion_type,\n" +
                        "(\n" +
                                "CASE l.party_type_code\n" +
                                "WHEN 'S'\n" +
                                "THEN\n" +
                                "(SELECT vendor_name FROM ap_suppliers WHERE vendor_id=l.party_id)\n" +
                                "WHEN 'C'\n" +
                                "THEN\n" +
                                "(SELECT account_name\n" +
                                "FROM hz_cust_accounts\n" +
                                "WHERE cust_account_id(+)=l.party_id)\n" +
                                "ELSE ''\n" +
                                "END) party,\n" +
                                "(\n" +
                                "CASE l.party_type_code\n" +
                                "WHEN 'S'\n" +
                                "THEN\n" +
                                "(SELECT vendor_site_code\n" +
                                "FROM ap_supplier_sites_all\n" +
                                "WHERE vendor_site_id=l.party_site_id)\n" +
                                "WHEN 'C'\n" +
                                "THEN\n" +
                                "(SELECT orig_system_reference\n" +
                                "FROM hz_cust_site_uses_all\n" +
                                "WHERE cust_acct_site_id(+)=l.party_site_id)\n" +
                                "ELSE ''\n" +
                                "END) party_site,\n" +
                                "h.description h_description, l.description l_description\n"+
                        "from xla_ae_headers h, xla_ae_lines l, gl_code_combinations cc\n" +
                        "where 1=1\n" +
                        "and l.ae_header_id=h.ae_header_id\n" +
                        "AND cc.code_combination_id=l.code_combination_id\n" +
                        "and h.balance_type_code=\'A\'\n"+
                        "and h.entity_id=")
                .append(entity)
                .append("\n")
                .append("and h.ledger_id=").append(lgr.getLedgerId().toString()).append("\n")
                .append("order by h.accounting_date, h.event_type_code, l.ae_line_num");

        //System.out.println(qry.toString());

        return em.createNativeQuery(qry.toString(),slaLines.class).getResultList();
    }

    @Asynchronous
    public Future <List <tableOut>> getTabres (String qry, List<String> mp) throws NoSuchMethodException, InvocationTargetException {
        List<Object> rslt = new ArrayList<>();
        List<tableOut> rtab = new ArrayList<>();

        tableOut ax;
        
    
           rslt=  (List<Object>) em.createNativeQuery(qry).getResultList();

        
        Iterator<Object> rsltI = rslt.iterator();
        while (rsltI.hasNext()) {
            ax = new tableOut();
            Object[] obj = (Object[]) rsltI.next();
            //ax.setRowid(UUID.randomUUID().toString().substring(0, 8)); // set rowid
            ax.setRowid(UUID.randomUUID().toString()); // set rowid
            //System.out.println("*************************");
            //System.out.println(mp.size());
            for (int i = 0; i < mp.size(); i++) {
                if (obj[i] == null) {
                    obj[i] = 0;
                }
                try {
                   // System.out.println(i+ " - "+ mp.get(i) + " - " + obj[i].toString() );
                    BeanUtils.setProperty(ax, mp.get(i), obj[i]);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(flexSegmentFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            rtab.add(ax);
        }
   
        return new AsyncResult  <List<tableOut>>  (rtab);
        
    }
    
    @Asynchronous
    public Future <List<tableDrill>> drill (drillConditions cond, String filter, int first, int pageSize, eApplication selApps) {
        List result;
        StringBuilder sqlDrill = new StringBuilder();
        Integer nvl =0;
        
        //System.out.println("Inner: "+Thread.currentThread().getId());
        if(selApps!=null){
            nvl=selApps.getApplicationId();
        }

        
        sqlDrill.append("select /*+ FIRST_ROWS */ distinct a.*, COUNT(*) OVER () RESULT_COUNT from (");

                if(nvl!=101) {
                    sqlDrill.append("select l.rowid as rwd, apn.application_name as source, xte.entity_code, h.event_type_code,accounting_class_code,\n")
                            .append(cond.getSqlHeaders())
                            .append("l.ae_line_num,\n")
                            .append("xte.transaction_number,\n")
                            .append("(\n")
                            .append("CASE l.party_type_code\n")
                            .append("WHEN \'S\'\n")
                            .append("THEN\n")
                            .append("(SELECT vendor_name FROM ap_suppliers WHERE vendor_id=l.party_id)\n")
                            .append("WHEN 'C'\n")
                            .append("THEN\n")
                            .append("(SELECT account_name\n")
                            .append("FROM hz_cust_accounts\n")
                            .append("WHERE cust_account_id(+)=l.party_id)\n")
                            .append("ELSE \'\'\n")
                            .append("END) party,\n")
                            .append("(\n")
                            .append("CASE l.party_type_code\n")
                            .append("WHEN \'S\'\n")
                            .append("THEN\n")
                            .append("(SELECT vendor_site_code\n")
                            .append("FROM ap_supplier_sites_all\n")
                            .append("WHERE vendor_site_id=l.party_site_id)\n")
                            //.append("WHEN \'C\'\n")
                            //.append("THEN\n")
                            //.append("(SELECT orig_system_reference\n")
                            //.append("FROM hz_cust_site_uses_all\n")
                            //.append("WHERE cust_acct_site_id(+)=l.party_site_id)\n")
                            .append("ELSE \'\'\n")
                            .append("END) party_site,\n")
                            .append("l.currency_code,\n")
                            .append("l.entered_dr,\n")
                            .append("l.entered_cr,\n")
                            .append("l.accounted_dr,\n")
                            .append("l.accounted_cr,\n")
                            .append("l.description,\n")
                            .append("gb.name AS Batch_name,\n")
                            .append("gb.status AS Batch_status ,\n")
                            .append("gh.name AS Journal_name,\n")
                            .append("gr.je_line_num AS Journal_line,\n")
                            .append("h.ae_header_id,\n")
                            .append("gh.je_header_id\n")
                            .append("FROM xla_ae_lines l,\n" +
                                    "xla_ae_headers h,\n" +
                                    "gl_code_combinations cc,\n" +
                                    "gl_import_references gr,\n" +
                                    "gl_je_batches gb,\n" +
                                    "gl_je_headers gh,\n" +
                                    "xla.xla_transaction_entities xte,\n" +
                                    "fnd_application_tl apn\n")
                            .append("WHERE 1=1\n" +
                                    "AND h.application_id      =apn.application_id\n" +
                                    "AND l.ae_header_id        =h.ae_header_id\n" +
                                    "AND apn.language          =userenv('LANG')\n" +
                                    "AND cc.code_combination_id=l.code_combination_id\n" +
                                    "AND h.entity_id           =xte.entity_id\n" +
                                    "AND l.gl_sl_link_table    =gr.gl_sl_link_table(+)\n" +
                                    "AND l.gl_sl_link_id       =gr.gl_sl_link_id(+)\n" +
                                    "AND gr.je_batch_id        =gb.je_batch_id(+)\n" +
                                    "AND gr.je_header_id       =gh.je_header_id(+)\n" +
                                    "and l.application_id=xte.application_id\n" +
                                    "and h.application_id=xte.application_id\n")
                            .append("AND h.ledger_id=").append(cond.getLedgerId()).append("\n")
                            .append("AND gh.ledger_id=").append(cond.getLedgerId()).append("\n")
                            .append("AND h.accounting_date BETWEEN to_date(").append(cond.getAccountingDateFrom()).append(",\'dd.mm.yyyy\') and to_date(").append(cond.getAccountingDateTo()).append(",\'dd.mm.yyyy\')\n")
                            .append("AND h.balance_type_code IN (").append(cond.getBalanceTypeCode()).append(")\n")


                            .append((cond.getCurrencyCode() == null) ? "and 1=1\n" : "and l." + cond.getCurrencyCode()) // only for foreign currencies


                            .append("and cc.code_combination_id in ( select code_combination_id from gl_code_combinations cc where 1=1 ")
                            .append(cond.getSegments())
                            .append(")\n");
                            if (nvl!=0) {
                                sqlDrill.append("and h.application_id=").append(nvl.toString()).append("\n");
                            }

                }
                if (nvl==0) {
                    sqlDrill.append("union all\n");
                }
        if(nvl==0 || nvl==101) {
            sqlDrill.append("select l.rowid as rwd, 'GL' as source, ' ' as entity_code, ' ' as event_type_code, ' ' as accounting_class_code,\n")
                    .append(cond.getSqlHeaders())

                    .append("0 as ae_line_num,\n" +
                            "' ' as transaction_number,\n" +
                            "' ' as party,\n" +
                            "' ' as party_site,\n" +
                            "h.currency_code,\n" +
                            "l.entered_dr,\n" +
                            "l.entered_cr,\n" +
                            "l.accounted_dr,\n" +
                            "l.accounted_cr,\n" +
                            "l.description,\n" +
                            "gb.name AS Batch_name,\n" +
                            "gb.status AS Batch_status ,\n" +
                            "h.name as journal_name,\n" +
                            "l.je_line_num AS Journal_line,\n" +
                            "0 as ae_header_id,\n" +
                            "h.je_header_id\n" +
                            "from gl_je_lines l, gl_je_headers h , gl_code_combinations cc, gl_je_batches gb\n" +
                            "where 1=1\n" +
                            "and l.je_header_id=h.je_header_id\n" +
                            "and h.je_batch_id=gb.je_batch_id\n" +
                            "and nvl(h.je_from_sla_flag,'N')='N'\n" +
                            "and cc.code_combination_id=l.code_combination_id\n")
                    .append("AND l.ledger_id=").append(cond.getLedgerId()).append("\n")
                    .append("AND l.effective_date BETWEEN to_date(").append(cond.getAccountingDateFrom()).append(",\'dd.mm.yyyy\') and to_date(").append(cond.getAccountingDateTo()).append(",\'dd.mm.yyyy\')\n")
                    .append("AND h.actual_flag IN (").append(cond.getBalanceTypeCode()).append(")\n")
                    .append((cond.getCurrencyCode() == null) ? "and 1=1\n" : "and h." + cond.getCurrencyCode()) // only for foreign currencies
                    .append("and cc.code_combination_id in ( select code_combination_id from gl_code_combinations cc where 1=1 ")
                    .append(cond.getSegments())
                    .append(")\n");
        }
                    sqlDrill.append(") a\n")
                    .append("where 1=1\n")
                    .append(filter);

        
        //System.out.println(sqlDrill.toString());
        
        
        
        result= em.createNativeQuery(sqlDrill.toString(), tableDrill.class).setFirstResult(first).setMaxResults(pageSize).getResultList();

//System.out.println(result.size());

   return  new AsyncResult <List<tableDrill>> (result);
  
    }
        
}
