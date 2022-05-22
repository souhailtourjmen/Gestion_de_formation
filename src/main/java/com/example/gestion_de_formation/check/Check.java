package com.example.gestion_de_formation.check;

import java.util.Objects;

public class Check {

    public boolean checkchamp(String ch){
        return ch==null;
    }
    public  int checkzone(String strNum){
        if (strNum == null) {
            return 0;
        }
        int s = 0;
        char[] chars = strNum.toCharArray();
        for(char c : chars){
            if(Character.isDigit(c)){
                s=s+1;
            }
        }
        return s;
    }
    public  boolean cheknumber(String strNum){
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
