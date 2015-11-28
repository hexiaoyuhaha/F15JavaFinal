/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.functions.LinearRegression;

/**
 *
 * @author April
 */
public class Regression {

	public int regression(String fileName) {

		String arffName = FileTransfer.transfer(fileName);

		try {
			//load data
			Instances data = new Instances(new BufferedReader(new FileReader(arffName)));
			data.setClassIndex(data.numAttributes() - 1);
			//build model
			LinearRegression model = new LinearRegression();
			model.buildClassifier(data);
			//the last instance with missing class is not used
			System.out.println(model);
			//classify the last instance
			Instance num = data.lastInstance();
			int people = (int) model.classifyInstance(num);
			System.out.println("NumOfEnrolled (" + num + "): " + people);
			return people;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Regression fail");
		}
		return 0;
	}
}
