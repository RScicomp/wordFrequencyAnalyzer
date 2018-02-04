package mchacks2018;

import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;

public class Dialog extends JDialog {
    private JLabel labelPath;
    private JTextField filePathField;
    private JButton generateChart;

    public Dialog() {
        labelPath = new JLabel("PDF Path: ");
        filePathField = new JTextField();
        generateChart = new JButton("Generate PDF");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel panelPath = new JPanel();
        panelPath.setLayout(new BorderLayout());
        panelPath.add(labelPath, BorderLayout.WEST);
        panelPath.add(filePathField, BorderLayout.CENTER);
        mainPanel.add(panelPath);
        mainPanel.add(generateChart);

        generateChart.addActionListener((event) -> {
            PDFReader.convertToText(filePathField.getText(), "temp.txt", 0, -1);
            Parser parser = new Parser();
            parser.sortByFrequency("temp.txt", "commonWords.txt", "data.txt");

            ChartGenerator demo = new ChartGenerator( "Word Frequencies" );
            demo.setSize( 560 , 367 );
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
        });

        Container contentPane = getContentPane();
        contentPane.add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("wordXtract");
        setResizable(false);
    }
}
