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
import java.util.ArrayList;

public class ProportionPieChart {
    String name;
    
	public ProportionPieChart() {
	}
        
        public ProportionPieChart(String name) {
            this.name = name;
	}

	public DefaultPieDataset createDataset() {
            DataInterface data = new DataInterface(name);
            String[] categories = new String[5];
            int[] datas = new int[5];
            ArrayList<NumCountryPair> infor = data.getTop5NumCountryPair();
            for (int i=0;i<infor.size();i++){
                datas[i] = infor.get(i).getNum();
                categories[i] = infor.get(i).getCountryName();
            }
            //String[] categories = { "Europe", "Asia", "Africa", "America" };
            //int[] datas = { 100, 470, 120, 50 };
            DefaultPieDataset dataset = dual.createDefaultPieDataset(categories, datas);
            return dataset;
	}

	public ChartPanel createChart() {
		JFreeChart chart = ChartFactory.createPieChart("Contents of Proportion in Diferent Countries", createDataset());
		
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
				ChartPanel chartPanel = new ProportionPieChart("MOCK_DATA.csv").createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
