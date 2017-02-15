package coreobjects;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by facet on 15/07/16.
 *
 */
public class getSegRange {
    @EJB
    private static flexSegmentFacade segFac;
    public static String range(String segNum, String segSetId, String segValue, String side){
        StringBuilder qry = new StringBuilder();
        sgv selectedSeg;
        List<String> sumList;

        selectedSeg = segFac.getSegValue(segSetId, segValue);
        if (selectedSeg.getSummary_flag()=="Y") {
            sumList=segFac.getSumSegList(segSetId, segValue);
           return  "and cc.segment"+segNum+" in ("+sumList.stream().reduce("",String::concat)+")\n";
        }
        else {
            return "and cc.segment"+segNum+side+segValue+"\n";
        }
    }
}
