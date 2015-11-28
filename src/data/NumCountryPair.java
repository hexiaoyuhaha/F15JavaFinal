/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author April
 */
public class NumCountryPair {
    int num;
    String countryName;
    NumCountryPair(int num,String countryName) {
        this.num = num;
        this.countryName = countryName;
    }
    
    public int getNum(){
        return num;
    }
    
    public String getCountryName(){
        return countryName;
    }
}
