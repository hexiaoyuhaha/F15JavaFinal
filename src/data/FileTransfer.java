/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSink;
/**
 *
 * @author WZJ
 */
public class FileTransfer {

    /**
     * @param args the command line arguments
     */
    //public static void transfer(String name){
    public static String transfer(String name) {
        // TODO code application logic here
		try {
        int i;
        int j = name.lastIndexOf(".");
        String arffName = name.substring(0,j);
        //File file = new File(name);
        File file = new File(name);
		System.out.println("Opened" + name + "good");
		
        CSVLoader loader = new CSVLoader();
        loader.setSource(file);
        Instances datasrc = loader.getDataSet();
        System.out.println("Instances datasrc = loader.getDataSet();");
		
        ArffSaver saver = new ArffSaver();
		System.out.println(" ArffSaver saver = new ArffSaver();");
        saver.setInstances(datasrc);
		System.out.println("saver.setInstances(datasrc);");
        saver.setFile(new File(arffName+".arff"));
		System.out.println("saver.setFile(new File(arffName+\".arff\"));");
        saver.writeBatch();
		System.out.println("saver.writeBatch();");
		return arffName+".arff";
		} catch (Exception e) {
			System.out.println("FileTransfer Failed");
		}
        return null;
    } 
}
