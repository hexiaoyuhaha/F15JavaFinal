package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Rectangle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;


public class PieChartUtils {
	private static String NO_DATA_MSG = "Loading Failed!";
	private static Font FONT = new Font("Arial", Font.PLAIN, 12);
	public static Color[] CHART_COLORS = {
			new Color(240, 255, 240),new Color(255, 245, 238),
                        new Color(255,117,153), new Color(92,92,97), new Color(144,237,125), 
                        new Color(255,188,117),new Color(153,158,255), new Color(253,236,109), 
                        new Color(128,133,232),new Color(158,90,102),new Color(255, 204, 102) };

	static {
		setChartTheme();
	}

	public PieChartUtils() {
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
                
		DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(CHART_COLORS, CHART_COLORS, OUTLINE_PAINT_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
		chartTheme.setDrawingSupplier(drawingSupplier);

		chartTheme.setPlotBackgroundPaint(Color.WHITE);// ��������
		chartTheme.setPlotOutlinePaint(Color.WHITE);// ����������߿
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

	public static DefaultPieDataset createDefaultPieDataset(String[] categories, Object[] datas) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (int i = 0; i < categories.length && categories != null; i++) {
			String value = datas[i].toString();
			if (isPercent(value)) {
				value = value.substring(0, value.length() - 1);
			}
			if (isNumber(value)) {
				dataset.setValue(categories[i], Double.valueOf(value));
			}
		}
		return dataset;

	}

	public static void setPieRender(Plot plot) {

		plot.setNoDataMessage(NO_DATA_MSG);
		plot.setInsets(new RectangleInsets(10, 10, 5, 10));
		PiePlot piePlot = (PiePlot) plot;
		piePlot.setInsets(new RectangleInsets(0, 0, 0, 0));
		piePlot.setCircular(true);// Բ��

		// piePlot.setSimpleLabels(true);// �򵥱�ǩ
		piePlot.setLabelGap(0.01);
		piePlot.setInteriorGap(0.05D);
		piePlot.setLegendItemShape(new Rectangle(10, 10));// ͼ����״
		piePlot.setIgnoreNullValues(true);
		piePlot.setLabelBackgroundPaint(null);// ȥ������ɫ
		piePlot.setLabelShadowPaint(null);// ȥ����Ӱ
		piePlot.setLabelOutlinePaint(null);// ȥ���߿�
		piePlot.setShadowPaint(null);
		// 0:category 1:value:2 :percentage
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{2}"));// ��ʾ��ǩ����
	}

	public static boolean isPercent(String str) {
		return str != null ? str.endsWith("%") && isNumber(str.substring(0, str.length() - 1)) : false;
	}

	public static boolean isNumber(String str) {
		return str != null ? str.matches("^[-+]?(([0-9]+)((([.]{0})([0-9]*))|(([.]{1})([0-9]+))))$") : false;
	}

}
