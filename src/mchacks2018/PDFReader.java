package mchacks2018;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public class PDFReader {
    private PDFReader() { }

    public static void convertToText(String documentFile, String outputFile, int startPage, int endPage) {
        try {
            PDDocument document = PDDocument.load(new File(documentFile));
            PDFTextStripper textStripper = new PDFTextStripper();
            textStripper.setSortByPosition(true);
            if (endPage < 0 || endPage < startPage || endPage > document.getNumberOfPages())
                endPage = document.getNumberOfPages();
            if (startPage < 0 || startPage > endPage)
                startPage = 0;
            textStripper.setStartPage(startPage);
            textStripper.setEndPage(endPage);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String allText = textStripper.getText(document);
            writer.write(allText);
            document.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
