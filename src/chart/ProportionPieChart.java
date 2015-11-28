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

public class ProportionPieChart {
	public ProportionPieChart() {
	}

	public DefaultPieDataset createDataset() {
		String[] categories = { "Europe", "Asia", "Africa", "America" };
		Object[] datas = { 100, 470, 120, 50 };
		DefaultPieDataset dataset = PieChartUtils.createDefaultPieDataset(categories, datas);
		return dataset;
	}

	public ChartPanel createChart() {
		JFreeChart chart = ChartFactory.createPieChart("Contents of Proportion in Diferent Countries", createDataset());
		
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
				ChartPanel chartPanel = new ProportionPieChart().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}
