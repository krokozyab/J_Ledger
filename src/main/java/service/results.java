/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author facet
 */
public class results {
    private List<String> res;
    public results(){
        res=new ArrayList<>();
        res.add("PTD");
        res.add("QTD");
        res.add("YTD");
        res.add("PTD-YTD");
        res.add("PTD-QTD");
        res.add("PTD-QTD-YTD");
    }
    
    public String getOut(String selRes){
        String converted="result converted error";
        switch (selRes) {
            case "PTD": converted= "sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0)) as ptd \n";
            break;
            case "QTD": converted= "sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0)+nvl(gb.QUARTER_TO_DATE_DR,0) - nvl(gb.QUARTER_TO_DATE_CR,0)) as qtd \n";
            break;
            case "YTD": converted= "sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0) + nvl(gb.begin_balance_dr,0) - nvl(gb.begin_balance_cr,0)) as ytd \n";
            break;
            case "PTD-YTD": converted="sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0)) as ptd, sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0) + nvl(gb.begin_balance_dr,0) - nvl(gb.begin_balance_cr,0)) as YTD \n";
            break;
            case "PTD-QTD": converted="sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0)) as ptd, sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0) + nvl(gb.QUARTER_TO_DATE_DR,0) - nvl(gb.QUARTER_TO_DATE_CR,0)) as QTD \n";
            break;
            case "PTD-QTD-YTD": converted="sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0)) as ptd, sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0) + nvl(gb.QUARTER_TO_DATE_DR,0) - nvl(gb.QUARTER_TO_DATE_CR,0)) as QTD, sum(nvl(gb.period_net_dr,0) - nvl(gb.period_net_cr,0) + nvl(gb.begin_balance_dr,0) - nvl(gb.begin_balance_cr,0)) as YTD \n";
        }
        return converted;
    }
    
    public String zeroSupress(String selRes){
        String converted="result converted error";
        switch (selRes) {
            case "PTD": converted= "and ptd!=0 \n";
            break;
            case "QTD": converted= "and qtd!=0 \n";
            break;
            case "YTD": converted= "and ytd!=0 \n";
            break;
            case "PTD-YTD": converted="and (abs(ptd)+abs(ytd))!=0 \n";
            break;
            case "PTD-QTD": converted="and (abs(ptd)+abs(qtd))!=0 \n";
            break;
            case "PTD-QTD-YTD": converted="and (abs(ptd)+abs(qtd)+abs(ytd))!=0 \n";
        }
        //return converted;
        return "";
    }
    
    public String getPivOut(String selRes){
        String converted="result converted error";
        switch (selRes) {
            case "PTD": converted= "sum(nvl(ptd,0)) \n";
            break;
            case "QTD": converted= "sum(nvl(qtd,0)) \n";
            break;
            case "YTD": converted= "sum(nvl(ytd,0)) \n";
            break;
            case "PTD-YTD": converted="sum(nvl(ptd,0)) PTD, sum(nvl(ytd,0)) YTD \n";
            break;
            case "PTD-QTD": converted="sum(nvl(ptd,0)) PTD, sum(nvl(qtd,0)) QTD \n";
            break;
            case "PTD-QTD-YTD": converted="sum(nvl(ptd,0)) PTD, sum(nvl(qtd,0)) QTD , sum(nvl(ytd,0)) YTD \n";
            
        }
        return converted;
    }
    
    public Integer getSpn(String selRes){
        Integer spn=1;
        if(selRes.matches("PTD|QTD|YTD")){
            spn=1;
        } 
        else if(selRes.matches("PTD-YTD|PTD-QTD")){
            spn=2;
        }
        else {
            spn=3;
        }
        return spn;
    }
    /**
     * @return the res
     */
    public List<String> getRes() {
        return res;
    }

    /**
     * @param res the res to set
     */
    public void setRes(List<String> res) {
        this.res = res;
    }


}
