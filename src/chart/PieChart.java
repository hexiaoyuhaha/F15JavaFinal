package chart;

import util.PieChartUtils;
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

public class PieChart {
    String name;
    
	public PieChart() {
	}
        
    public PieChart(String name) {
            this.name = name;
	}

	public DefaultPieDataset createDataset() {
                DataInterface data = new DataInterface(name);
				String[] categories = { "Male", "Female" };
                Object[] datas = new Object[2]; 
                datas[0] = data.getNumOfMale();
                datas[1] = data.getNumOfFemale();
		//Object[] datas = { 700, 300 };
		DefaultPieDataset dataset = PieChartUtils.createDefaultPieDataset(categories, datas);
		return dataset;
	}

	public ChartPanel createChart() {
		JFreeChart chart = ChartFactory.createPieChart("Contents of Male and Female Ratio", createDataset());
		
		PieChartUtils.setPieRender(chart.getPlot());
                
		// plot.setSimpleLabels(true);
		// plot.setLabelGenerator(null);
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
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
				ChartPanel chartPanel = new PieChart("MOCK_DATA-1.csv").createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
