package view;

import java.awt.Color;
import java.awt.Dimension;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class BarChart extends ApplicationFrame {

	private static final long serialVersionUID = -5277051379488530572L;
	
	public Map<String, Double> data;
	
	public BarChart(Map<String, Double> data){
		
		super("");
		
		this.data = data;
		
        final CategoryDataset dataset = createDataset(this.data);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
	}
		
    /**
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset(Map<String, Double> data) {
    	
    	//Sort areas properly
    	SortedSet<String> keys = new TreeSet<String>(new AlphanumComparator());
    	
    	keys.addAll(data.keySet());

    	final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	
    	for(String key: keys){
    		
            dataset.addValue(data.get(key), "First", key);
    	}
        
        return dataset;   
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            null,         // chart title
            "Areas",               // domain axis label
            "Occupation",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            false,                     // include legend
            false,                     // tooltips?
            false                     // URLs?
        );
        
        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        
        return chart;   
    }	
}