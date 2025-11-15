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

            PDPage page = new PDPage(PDRectangle.LETTER);
            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);

            PDFont font = PDType1Font.HELVETICA;
            float fontSize = 8;

            float margin = 40;
            float y = page.getMediaBox().getHeight() - margin;

            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float colWidth = tableWidth / table.getColumnCount();
            float rowHeight = 18;

            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(title) / 1000 * 16;
            float titleX = (page.getMediaBox().getWidth() - titleWidth) / 2;

            content.beginText();
            content.newLineAtOffset(titleX, y);
            content.showText(title);
            content.endText();

            y -= 30; 


            content.setFont(font, fontSize + 2);

            for (int col = 0; col < table.getColumnCount(); col++) {
                content.beginText();
                content.newLineAtOffset(margin + col * colWidth, y);
                content.showText(table.getColumnName(col));
                content.endText();
            }

            y -= rowHeight;

            content.setFont(font, fontSize);

            for (int row = 0; row < table.getRowCount(); row++) {

                if (y < margin) {
                    content.close();
                    page = new PDPage(PDRectangle.LETTER);
                    doc.addPage(page);
                    content = new PDPageContentStream(doc, page);

                    y = page.getMediaBox().getHeight() - margin;
                }

                for (int col = 0; col < table.getColumnCount(); col++) {
                    Object cell = table.getValueAt(row, col);

                    content.beginText();
                    content.newLineAtOffset(margin + col * colWidth, y);
                    content.showText(cell == null ? "" : cell.toString());
                    content.endText();
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
