package util;

import java.io.File;
import java.io.IOException;

import javax.swing.JTable;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;


public class PDFExport {
    public static void exportTableToPDF(JTable table, String filePath, String title) {
        try (PDDocument doc = new PDDocument()) {
            PDRectangle landscape = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());
            PDPage page = new PDPage(landscape);
            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);

            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 8;

            float margin = 40;
            float y = page.getMediaBox().getHeight() - margin - 20;

            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float colWidth = tableWidth / table.getColumnCount();
            float rowHeight = 18;

            content.setLineWidth(1f);
            content.addRect(margin - 5, margin - 5, 
                           page.getMediaBox().getWidth() - 2 * margin + 10, 
                           page.getMediaBox().getHeight() - 2 * margin + 10);
            content.stroke();

            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(title) / 1000 * 16;
            float titleX = (page.getMediaBox().getWidth() - titleWidth) / 2;

            content.beginText();
            content.newLineAtOffset(titleX, y);
            content.showText(title);
            content.endText();

            y -= 50; 
            content.setLineWidth(0.5f);
            content.addRect(margin, y, tableWidth, rowHeight);
            content.stroke();

            content.setFont(font, fontSize + 2);

            for (int col = 0; col < table.getColumnCount(); col++) {
                String headerText = table.getColumnName(col);
                float textWidth = font.getStringWidth(headerText) / 1000 * (fontSize + 2);
                float centerX = margin + col * colWidth + (colWidth - textWidth) / 2;
                
                content.beginText();
                content.newLineAtOffset(centerX, y + (rowHeight - fontSize) / 2);
                content.showText(headerText);
                content.endText();
                
                if (col > 0) {
                    content.setLineWidth(0.5f);
                    content.moveTo(margin + col * colWidth, y);
                    content.lineTo(margin + col * colWidth, y + rowHeight);
                    content.stroke();
                }
            }

            y -= rowHeight;

            content.setFont(font, fontSize);

            for (int row = 0; row < table.getRowCount(); row++) {
                if (y < margin) {
                    content.close();
                    PDRectangle newLandscape = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());
                    page = new PDPage(newLandscape);
                    doc.addPage(page);
                    content = new PDPageContentStream(doc, page);

                    y = page.getMediaBox().getHeight() - margin - 20;
                    
                    content.setLineWidth(1f);
                    content.addRect(margin - 5, margin - 5, 
                                   page.getMediaBox().getWidth() - 2 * margin + 10, 
                                   page.getMediaBox().getHeight() - 2 * margin + 10);
                    content.stroke();
                    
                    content.setFont(PDType1Font.HELVETICA_BOLD, 16);
                    content.beginText();
                    content.newLineAtOffset(titleX, y);
                    content.showText(title + " (continued)");
                    content.endText();
                    y -= 50;
                    
                    content.setLineWidth(0.5f);
                    content.addRect(margin, y, tableWidth, rowHeight);
                    content.stroke();
                    
                    content.setFont(font, fontSize + 2);
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        String headerText = table.getColumnName(col);
                        float textWidth = font.getStringWidth(headerText) / 1000 * (fontSize + 2);
                        float centerX = margin + col * colWidth + (colWidth - textWidth) / 2;
                        
                        content.beginText();
                        content.newLineAtOffset(centerX, y + (rowHeight - fontSize) / 2);
                        content.showText(headerText);
                        content.endText();
                        
                        if (col > 0) {
                            content.setLineWidth(0.5f);
                            content.moveTo(margin + col * colWidth, y);
                            content.lineTo(margin + col * colWidth, y + rowHeight);
                            content.stroke();
                        }
                    }
                    y -= rowHeight;
                    content.setFont(font, fontSize);
                }

                content.setLineWidth(0.5f);
                content.addRect(margin, y, tableWidth, rowHeight);
                content.stroke();

                for (int col = 0; col < table.getColumnCount(); col++) {
                    Object cell = table.getValueAt(row, col);
                    String cellText = cell == null ? "" : cell.toString();
                    float textWidth = font.getStringWidth(cellText) / 1000 * fontSize;
                    float centerX = margin + col * colWidth + (colWidth - textWidth) / 2;

                    content.beginText();
                    content.newLineAtOffset(centerX, y + (rowHeight - fontSize) / 2);
                    content.showText(cellText);
                    content.endText();
                    
                    if (col > 0) {
                        content.setLineWidth(0.5f);
                        content.moveTo(margin + col * colWidth, y);
                        content.lineTo(margin + col * colWidth, y + rowHeight);
                        content.stroke();
                    }
                }

                y -= rowHeight;
            }

            File file = new File(filePath);

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            content.close();
            doc.save(file);
            System.out.println("Saved PDF to: " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}