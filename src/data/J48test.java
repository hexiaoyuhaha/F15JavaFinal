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
    //private static int[] count;
    public static int[] J48(String train, String test) {
   // public static void main(String[] args) throws IOException, Exception{
    //public int predictionForGraduation(String filename) throws IOException, Exception{
        int[] count = {0,0};
        try{
        int i;
        //count={0,0};
        Classifier classf = new J48();
        
        File fileTrain = new File(train);
        ArffLoader arl = new ArffLoader();
        arl.setFile(fileTrain);
        Instances trainSet = arl.getDataSet();
        
        File fileTest = new File(test);
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
                    count[1]++;
                    System.out.println(i);
                }
                count[0]++;
            }
        }
        System.out.println("\n"+count);
        count[0] = count[0] - count[1];
        return count;
        }catch(Exception e){}
        return count;
    }
}
