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
public class TestClass {
    public static void main(String[] args) {
        DataInterface data = new DataInterface("MOCK_DATA.csv");
		System.out.println("getNumOfFemale");
        System.out.println(data.getNumOfFemale());
		System.out.println("getGPADistributionFemale");
        data.getGPADistributionFemale();
		System.out.println("getGPADistributionMale");
        data.getGPADistributionMale();
		System.out.println("getNumOfPartTimeAndFullTime");
        data.getNumOfPartTimeAndFullTime();
		System.out.println("getNumOfEnrolledByYear(2014, 2014)");
        data.getNumOfEnrolledByYear(2014, 2014);
		System.out.println("getNumOfMaleByMajor");
        data.getNumOfMaleByMajor();
		System.out.println("getNumOfFemaleByMajor");
        data.getNumOfFemaleByMajor();
		
		System.out.println("top5Country");
        data.getTop5NumCountryPair();
    }
}
