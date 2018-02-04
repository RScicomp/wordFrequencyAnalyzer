package mchacks2018;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.prefs.Preferences;

public class ChartGenerator extends ApplicationFrame {
    public ChartGenerator(String title) {
        super(title);
        try {
            setContentPane(createDemoPanel());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static PieDataset createDataSet() throws IOException {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        BufferedReader reader = new BufferedReader(new FileReader("top.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(":");
            dataSet.setValue(data[0], Integer.parseInt(data[1]));
        }
        return dataSet;
    }

    private static JFreeChart createChart(PieDataset dataSet) {
        return ChartFactory.createPieChart("Frequencies", dataSet,true, true, false);
    }

    public static JPanel createDemoPanel() throws IOException {
        JFreeChart chart = createChart(createDataSet());
        return new ChartPanel(chart);
    }
}