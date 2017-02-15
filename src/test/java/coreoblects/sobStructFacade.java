/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coreoblects;

import coreobjects.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Convert;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import beans.rangeBean; 

/**
 *
 * @author facet
 */
@Stateless
public class sobStructFacade extends AbstractFacade<sobStruct> {

    @PersistenceContext(unitName = "com.local_J_Ledger_war_1.0-SNAPSHOTPU")
    private  EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public sobStructFacade() {
        super(sobStruct.class);
    }
    
    @SuppressWarnings("null")
        public List<sobStruct> allStruct(ledgers ledger){
        List<sobStruct> results=null;
        List<sobStruct> result= new ArrayList<>();
        sobStruct ax;
        //ledgers ledger;
        StringBuilder qry;
        qry=new StringBuilder();
        //ledger=rangeBean.getSelectedLedger();
        qry.append("select sob.name \n" +
",ffvs.flex_value_set_id+ifs.segment_num seg_id \n" +
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

/*
        String qry="select sob.name \n" +
",ffvs.flex_value_set_id+ifs.segment_num seg_id \n" +
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
"and sob.ledger_id =\n" +
"nvl(fnd_profile.value('GL_SET_OF_BKS_ID'),sob.ledger_id)\n" +
"and fifst.language=USERENV('LANG')\n" +
"order by sob.name, sob.chart_of_accounts_id, ifs.segment_num";
*/
        


        
        //results =  (ArrayList<sobStruct>) em.createNativeQuery(qry, "AccStructMapping").getResultList();
        //results =   em.createNativeQuery(qry, "AccStructMapping").getResultList();
        results =  (List<sobStruct>) em.createNativeQuery(qry.toString(), sobStruct.class).getResultList();



//result = results.stream().map(i::sobStruct);
           //System.out.println( results.get(0));
           //System.out.println( results);
           
      /* 
           Iterator itr=results.iterator();
           while (itr.hasNext()){
               Object[] obj = (Object[]) itr.next();
               ax=new sobStruct();

         
          ax.setLedgerName(String.valueOf(obj[0]));
          ax.setLedgerId(Integer.parseInt(String.valueOf(obj[1])));
          ax.setCoaId(Integer.parseInt(String.valueOf(obj[2])));
          ax.setStructName(String.valueOf(obj[3])); //structName
          ax.setSegmentName(String.valueOf(obj[4])); //segmentName
          ax.setSegmentNum(Integer.parseInt(String.valueOf(obj[5])));//segmentNum
          
          ax.setDisplaySize(Integer.parseInt(String.valueOf(obj[6])));//displaySize
          ax.setDefaultType(String.valueOf(obj[7]));//defaultType
          ax.setDefaultValue(String.valueOf(obj[8]));//defaultValue
          
          
          ax.setFormLeftPrompt(String.valueOf(obj[9]));  //formLeftPrompt
          ax.setColumnName(String.valueOf(obj[10])); //columnName
          ax.setBalancing(String.valueOf(obj[11]));//balancing
          ax.setCostCenter(String.valueOf(obj[12])); //costCenter
          ax.setNaturalAccount(String.valueOf(obj[13]));//naturalAccount
          ax.setIntercompany(String.valueOf(obj[14]));//intercompany
          ax.setSecondaryTracking(String.valueOf(obj[15]));//secondaryTracking
          ax.setGlobal(String.valueOf(obj[16]));//global
          ax.setFlexValueSetName(String.valueOf(obj[17]));//flexValueSetName
          ax.setFlexValueSetId(Long.parseLong(String.valueOf(obj[18]))); //flexValueSetId
             result.add((sobStruct) ax);
             
             
              //System.out.println(ax.getFormLeftPrompt());
           }
           
 */

        return results;
    }
    
    
}
