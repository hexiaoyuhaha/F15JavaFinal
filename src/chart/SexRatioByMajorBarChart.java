package chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import util.dual;
import util.NameWithValueList;
import data.*;

public class SexRatioByMajorBarChart {
	int[] femaleNumArray;
	int[] maleNumArray;
	String fileName;
	String[] categories;
	
	public SexRatioByMajorBarChart(String fileName) {
		this.fileName = fileName;
	}

	public DefaultCategoryDataset getData() {
		//int[] femaleNumArray = new int[] { 49, 71, 104, 122};
		//int[] maleNumArray = new int[] { 119, 61, 54, 62};
		//String[] categories = { "MISM", "MSPPM", "MSIT", "CS" };
		DataInterface data = new DataInterface(fileName);
		femaleNumArray = data.getNumOfFemaleByMajor();
		maleNumArray = data.getNumOfMaleByMajor();
		categories = data.getMajorName();
				
		ArrayList<NameWithValueList> lists = new ArrayList<NameWithValueList>();
		lists.add(new NameWithValueList("Female", femaleNumArray));
		lists.add(new NameWithValueList("Male",maleNumArray));

		DefaultCategoryDataset dataset = dual.createSexRatioByMajorBarChartDataset(lists, categories);
		return dataset;
	}
	
	public static void setYInvisibleStyle(CategoryPlot plot) {
		plot.setRangeGridlineStroke(new BasicStroke(1));
		ValueAxis axis = plot.getRangeAxis();
		axis.setAxisLineVisible(false);
		axis.setTickMarksVisible(false);
	}
	
	public ChartPanel createChart() {
		JFreeChart chart = ChartFactory.createStackedBarChart("Sex Ratio", "", "Number of Students", getData());
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		setYInvisibleStyle(chart.getCategoryPlot());
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set the default size
		frame.setSize(624, 420);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ChartPanel chartPanel = new SexRatioByMajorBarChart("MOCK_DATA-1.csv").createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
