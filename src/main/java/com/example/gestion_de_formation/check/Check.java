package com.example.gestion_de_formation.check;

import com.example.gestion_de_formation.DB.DbConnection;

import java.sql.SQLException;
import java.util.Objects;

public class Check {
    public Check() {
    }

    public  int checkchamp(String ch){
        if(ch.equals("")){
            return -1;
        }
        return 0;
    }

    public  int checkzone(String strNum){
        if (strNum == null) {
            return -1;
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
    public  int cheknumber(String strNum){
        if (strNum == null) {
            return -1;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        return 0;
    }
    public int check_champ(String[] champ){
        int result =0;
        for (String s : champ) {

            if (checkchamp(s)!=0) {
                return 0;
            }
            result = result + 1;

        }
        if(result != 0) {
            if(checkzone(champ[0])!=0){
                return 0;
            }

            if(checkzone(champ[1])!=0){
                return 0;
            }

            if(cheknumber(champ[3])!=0){
                return 0;
            }
        }

        return result;
    }


}
