/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import util.NameWithValueList;

import util.dual;
import data.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author zhang
 */
public class RegreesionChart {
    String name;

    public RegreesionChart() {
    }
    
    public RegreesionChart(String name) {
        this.name = name;
    }
    
    public ChartPanel createChart(){
        DataInterface data = new DataInterface(name);
        String[] categories = { "2007", "2008", "2009", "2010", "2011", "2012", "2013","prediction"};
        Regression reg = new Regression();
//        Vector<Serie> seriesEnrolledNum = new Vector<Serie>();
//        Vector<Serie> seriesDroppingNum = new Vector<Serie>();
        System.out.println("Regression name: " + name);
        int [] EnrolledNum = data.getNumOfEnrolledByYear(2007,2013);
        int prediction = reg.regression("NumOfEnrolledByYear.csv");
        int [] predictionChart = new int[8];
        for (int i=0;i<EnrolledNum.length;i++){
            predictionChart[i]= EnrolledNum[i];
        }
        predictionChart[7]= prediction;
        
        
        int [] DroppingNum = data.getNUmOfDroppedByYear(2007,2013);
        

        ArrayList<NameWithValueList> seriesDroppingNum = new ArrayList<NameWithValueList>();
		seriesDroppingNum.add(new NameWithValueList("DroppingNum", DroppingNum));
        ArrayList<NameWithValueList> seriesEnrolledNum = new ArrayList<NameWithValueList>();
		seriesEnrolledNum.add(new NameWithValueList("EnrolledNum", predictionChart));

//        DefaultCategoryDataset datasetEnrolledNum = dual.createDefaultCategoryDataset(seriesEnrolledNum, categories);
        DefaultCategoryDataset datasetEnrolledNum = dual.createSexRatioByMajorBarChartDataset(seriesEnrolledNum, categories);

        JFreeChart chart = ChartFactory.createBarChart("dropping and enrolled", "year", "number of enrolled student",datasetEnrolledNum );
        
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        DefaultCategoryDataset datasetDroppingNum = dual.createSexRatioByMajorBarChartDataset(seriesDroppingNum, categories);
        //categoryplot.setDataset(1, datasetDroppingNum);
        categoryplot.mapDatasetToRangeAxis(1, 1);
        
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		NumberAxis numberaxis = new NumberAxis("number of dropping ");
		categoryplot.setRangeAxis(1, numberaxis);
		numberaxis.setAxisLineVisible(false);
		numberaxis.setTickMarksVisible(false);
                
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
		lineRenderer.setSeriesPaint(0, new Color(255, 185, 1));
		lineRenderer.setBaseShapesVisible(true);
		categoryplot.setRenderer(1,lineRenderer);
                
                categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		chart.getLegend().setPosition(RectangleEdge.TOP); 
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
                
        
                
    }
    
        public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 420);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
                            try {
                                ChartPanel chartPanel = new RegreesionChart("data/MOCK_DATA-1.csv").createChart();
                                frame.getContentPane().add(chartPanel);
                                frame.setVisible(true);
                            } catch (Exception ex) {
                                Logger.getLogger(RegreesionChart.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});

	}
        
        
        
        
        
        
        
    }
    

