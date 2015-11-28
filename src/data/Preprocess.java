/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author WZJ
 */
public class Preprocess {
    public static void main(String[] args){
        int i,j;
        double csGenderPercent = 0.85;
        double ppmGenderPercent = 0.17;
        
        File origin = new File("MOCK_DATA.csv");
        ArrayList<String> originData = ImportCSV.importCSV(origin);
        ArrayList<String> processData = new ArrayList();
        int size = originData.size();
        String[][] divide = new String[size][23];
        processData.add(originData.get(0));
        
        //System.out.println(size);
        for(i=1;i<size;i++){
            divide[i] = originData.get(i).split(",");
            for(j=0;j<divide[i].length;j++){
                if(divide[i][10].equals("MS in CS")){
                    if(i<(int)(csGenderPercent*size)){
                        divide[i][3] = "Male";
                    }
                    else divide[i][3] = "Female";
                }
                
                if(divide[i][10].equals("MISPPM")){
                    if(i<(int)(ppmGenderPercent*size)){
                        divide[i][3] = "Male";
                    }
                    else divide[i][3] = "Female";
                }
                
                if(divide[i][17].equals("GRE")){
                    divide[i][19] = String.valueOf((int)(300+Math.random()*40));
                    divide[i][14] = String.valueOf(Integer.parseInt(divide[i][19]) * 4.0 / 340 + Math.random()*0.2 - Math.random()*0.2);
                }
                if(divide[i][17].equals("GMAT")){
                    divide[i][14] = String.valueOf(Integer.parseInt(divide[i][19]) * 4.0 / 800 + Math.random()*0.2 - Math.random()*0.2);
                }
                
                if(Integer.parseInt(divide[i][9])<2014){
                    if((Double.parseDouble(divide[i][14])<3.2&&divide[i][12].equals("full-time"))||(Double.parseDouble(divide[i][14])<3.1&&divide[i][12].equals("part-time"))){
                        divide[i][22] = "No";
                    }
                    else divide[i][22] = "Yes";
                }
                else divide[i][22] = "";
            }
            processData.add(newString(divide[i]));
        }
        
        System.out.println(processData.get(10));
        ImportCSV.newCSV(processData);
    }
    
    public static String newString(String[] divi){
        int i;
        String str = "";
        for(i=0;i<(divi.length-1);i++){
            str = str + divi[i] + ",";
        }
        str = str + divi[i];
        return str;
    }
    
}
