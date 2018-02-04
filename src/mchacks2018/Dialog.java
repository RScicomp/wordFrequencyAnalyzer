package mchacks2018;

import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class Dialog extends JDialog {
    private JLabel labelPath;
    private JTextField fieldFilePath;
    private JButton buttonGenerateChart;
    private JButton buttonChooseFile;

    public Dialog() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        labelPath = new JLabel("PDF Path: ");
        fieldFilePath = new JTextField();
        buttonGenerateChart = new JButton("Generate Chart");
        buttonChooseFile = new JButton("...");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel panelPath = new JPanel();
        panelPath.setLayout(new BorderLayout());
        panelPath.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPath.add(labelPath, BorderLayout.WEST);
        panelPath.add(fieldFilePath, BorderLayout.CENTER);
        panelPath.add(buttonChooseFile, BorderLayout.EAST);
        mainPanel.add(panelPath);
        mainPanel.add(buttonGenerateChart);

        buttonChooseFile.addActionListener((event) -> {
            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files (*.pdf)", "pdf");
            chooser.setFileFilter(filter);
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                fieldFilePath.setText(selectedFile.getAbsolutePath());
            }
        });

        buttonGenerateChart.addActionListener((event) -> {
            PDFReader.convertToText(fieldFilePath.getText(), "temp.txt", 0, -1);
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
        setTitle("Word Frequency Analyzer");
        setResizable(false);
    }
}
