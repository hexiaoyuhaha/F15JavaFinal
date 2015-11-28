/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.IOException;
import weka.classifiers.Evaluation;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 *
 * @author WZJ
 */
public class ClusteringTest{
        private static int[] size;
        
        //public static int[] Clustering(){
	public static void main(String[] args) throws IOException, Exception {
            int i;

            ArffLoader arl = new ArffLoader();
            //File fileTest = new File("Temp.arff");
            File fileTest = new File("newData.arff");
            arl.setFile(fileTest);
            Instances testSet = arl.getDataSet();

            SimpleKMeans cluster = new SimpleKMeans();
            cluster.setNumClusters(7);
            cluster.buildClusterer(testSet);
            size = cluster.getClusterSizes();
            
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(cluster);                                   // the cluster to evaluate
            eval.evaluateClusterer(testSet);                                // data to evaluate the clusterer on
            System.out.println("# of clusters: " + eval.getNumClusters()); 
            System.out.println(eval.clusterResultsToString()); 
            
            for(i=0;i<size.length;i++){
                System.out.println(size[i]);
            }
            int[] labels = new int[testSet.numInstances()];
            for (i=0; i<testSet.numInstances(); i++) {
                labels[i] = cluster.clusterInstance(testSet.instance(i));   
            }
            
            //return labels;
	}
        
        public static int[] getSizes(){
            return size;
        }
}
