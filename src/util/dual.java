/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author zhang
 */
public class dual {
    
    	private static String NO_DATA_MSG = "No data";
	private static Font FONT = new Font("Arial", Font.PLAIN, 12);
	public static Color[] CHART_COLORS = {
			  new Color(7, 114, 161),new Color(255, 135, 0),
              new Color(74, 17, 174), new Color(255, 126, 64),new Color(77, 222, 0)
             };
//	public static Color[] CHART_COLORS = {
			  
//                      new Color(255,117,153), new Color(92,92,97), new Color(144,237,125), 
//                        new Color(255,188,117),new Color(153,158,255), new Color(253,236,109), 
//                        new Color(128,133,232),new Color(158,90,102),new Color(255, 204, 102) };

	static {
		setChartTheme();
	}

	public dual() {
	}
    
        
        public static void setChartTheme() {
		StandardChartTheme chartTheme = new StandardChartTheme("CN");
		chartTheme.setExtraLargeFont(FONT);
		chartTheme.setRegularFont(FONT);
		chartTheme.setLargeFont(FONT);
		chartTheme.setSmallFont(FONT);
		chartTheme.setTitlePaint(new Color(51, 51, 51));
		chartTheme.setSubtitlePaint(new Color(85, 85, 85));

		chartTheme.setLegendBackgroundPaint(Color.WHITE);// ���ñ�ע
		chartTheme.setLegendItemPaint(Color.BLACK);//
		chartTheme.setChartBackgroundPaint(Color.WHITE);
		// paintSequence,outlinePaintSequence,strokeSequence,outlineStrokeSequence,shapeSequence

		Paint[] OUTLINE_PAINT_SEQUENCE = new Paint[] { Color.WHITE };
		// ��������ɫԴ
		DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(CHART_COLORS, CHART_COLORS, OUTLINE_PAINT_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
		chartTheme.setDrawingSupplier(drawingSupplier);

		chartTheme.setPlotBackgroundPaint(Color.WHITE);// ��������
		chartTheme.setPlotOutlinePaint(Color.WHITE);// ����������߿�
		chartTheme.setLabelLinkPaint(new Color(8, 55, 114));// ���ӱ�ǩ��ɫ
		chartTheme.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

		chartTheme.setAxisOffset(new RectangleInsets(5, 12, 5, 12));
		chartTheme.setDomainGridlinePaint(new Color(192, 208, 224));// X�����ᴹֱ������ɫ
		chartTheme.setRangeGridlinePaint(new Color(192, 192, 192));// Y������ˮƽ������ɫ

		chartTheme.setBaselinePaint(Color.WHITE);
		chartTheme.setCrosshairPaint(Color.BLUE);// ��ȷ������
		chartTheme.setAxisLabelPaint(new Color(51, 51, 51));// ���������������ɫ
		chartTheme.setTickLabelPaint(new Color(67, 67, 72));// �̶�����
		chartTheme.setBarPainter(new StandardBarPainter());// ������״ͼ��Ⱦ
		chartTheme.setXYBarPainter(new StandardXYBarPainter());// XYBar ��Ⱦ

		chartTheme.setItemLabelPaint(Color.black);
		chartTheme.setThermometerPaint(Color.white);// �¶ȼ�

		ChartFactory.setChartTheme(chartTheme);
	}

   
        
        public static DefaultCategoryDataset createSexRatioByMajorBarChartDataset(ArrayList<NameWithValueList> lists, String[] categories) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (NameWithValueList list : lists) {
			String name = list.name; //Male or female
			int[] data = list.data;
			for (int i = 0; i < data.length; i++) {
				dataset.setValue(data[i], name, categories[i]);
			}
		}
		return dataset;
	}
        
        public static DefaultPieDataset createDefaultPieDataset(String[] categories, int[] datas) {
            DefaultPieDataset dataset = new DefaultPieDataset();
            for (int i = 0; i < categories.length && categories != null; i++) {
                int value = datas[i];
                dataset.setValue(categories[i], value);
            }
            return dataset;
        }

	public static void setPieRender(Plot plot) {
            plot.setNoDataMessage(NO_DATA_MSG);
            plot.setInsets(new RectangleInsets(10, 10, 5, 10));
            PiePlot piePlot = (PiePlot) plot;
            piePlot.setInsets(new RectangleInsets(0, 0, 0, 0));
	    piePlot.setCircular(true);

            //piePlot.setLabelGap(0.01);
            //piePlot.setInteriorGap(0.05D);
            piePlot.setLegendItemShape(new Rectangle(10, 10));
            piePlot.setIgnoreNullValues(true);
            piePlot.setLabelBackgroundPaint(null);
            piePlot.setLabelShadowPaint(null);
            piePlot.setLabelOutlinePaint(null);
            piePlot.setShadowPaint(null);
            // 0:category 1:value:2 :percentage
            piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{2}"));
	}
    
}
