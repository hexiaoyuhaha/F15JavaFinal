/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chart;

import util.dual;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import data.*;
/**
 *
 * @author linqiaochu
 */
public class PredictedDroppingChart {
    String name;
    public PredictedDroppingChart() {
	}
        
    public PredictedDroppingChart(String name) {
            this.name = name;
	}
    public DefaultPieDataset createDataset() {
	String[] categories = { "Pass" ,"Drop"};
    int[] datas = J48test.J48("data/TrainDatav2.arff"
			, FileTransfer.transfer(name));

	DefaultPieDataset dataset = dual.createDefaultPieDataset(categories, datas);
	return dataset;
	}

	public ChartPanel createChart(){
		JFreeChart chart = ChartFactory.createRingChart("Prediction of the dropping rate of the students enrolled in 2014 and 2015", createDataset(),true,false,false);
		
		dual.setPieRender(chart.getPlot());
                
		// plot.setSimpleLabels(true);
		// plot.setLabelGenerator(null);
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		chart.getLegend().setPosition(RectangleEdge.BOTTOM);
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
				ChartPanel chartPanel = new PredictedDroppingChart("data/MOCK_DATA-1.csv").createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}
}
