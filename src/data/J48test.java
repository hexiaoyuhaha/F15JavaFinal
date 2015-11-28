/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.IOException;
import weka.classifiers.trees.J48;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 *
 * @author WZJ
 */
public class J48test {
    //public static int J48(String train, String test) throws IOException, Exception{
    public static void main(String[] args) throws IOException, Exception{
    //public int predictionForGraduation(String filename) throws IOException, Exception{
        int i;
        int count=0;
        Classifier classf = new J48();
        
        File fileTrain = new File("TrainDatav2.arff");
        ArffLoader arl = new ArffLoader();
        arl.setFile(fileTrain);
        Instances trainSet = arl.getDataSet();
        
        File fileTest = new File("newData.arff");
        arl.setFile(fileTest);
        Instances testSet = arl.getDataSet();
        
        testSet.setClassIndex(21);
        trainSet.setClassIndex(22);
        classf.buildClassifier(trainSet);
         
//        Evaluation eval = new Evaluation(trainSet);
//        eval.evaluateModel(classf,testSet);
//        System.out.println(eval.toSummaryString());
        for(i=0;i<testSet.numInstances();i++){
            if(testSet.instance(i).value(9)>2013){
                double label = classf.classifyInstance(testSet.instance(i));
                System.out.println(testSet.instance(i));
                if(label == 1){
                    count++;
                    System.out.println(i);
                }
            }
        }
        System.out.println("\n"+count);
        
        //return count;
    }
}
