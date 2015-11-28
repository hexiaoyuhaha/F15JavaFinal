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
public class TestMain {
    public static void main(String[] args) throws Exception {
        DataInterface data = new DataInterface("newData1.csv");
        Regression reg = new Regression();
        RegressionDrop reg2 = new RegressionDrop();
        
        data.getNumOfFemale();
        data.getGPADistributionFemale();
        data.getGPADistributionMale();
        data.getNumOfPartTimeAndFullTime();
        data.getNumOfEnrolledByYear(2008, 2014);
        reg.regression();
        data.getNumOfMaleByMajor();
        data.getNumOfFemaleByMajor();
        data.getNUmOfDroppedByYear(2008, 2014);
        reg2.regression();

        
    }
}
