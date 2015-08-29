
package com.raulsuarezdabo.flight.view;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Header;
import com.itextpdf.text.Rectangle;
import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PDFBuilder extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        BookEntity book = (BookEntity) model.get("book");
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
        
        doc.add(new Paragraph(
                book.getUser().getSurname() + ", " + book.getUser().getName() + 
                " at " + sdf.format(book.getCreatedAt())
            , FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC)));
        
        doc.add(new Paragraph("Flight Book resume", FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLDITALIC)));
        
        doc.add(new Paragraph(
            "Aircraft: " + book.getFlight().getAirplane().getModel() + " / " + book.getFlight().getAirplane().getMaker(), 
            FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL))
        );
        doc.add(new Paragraph(
            "From: " + book.getFlight().getAirportFrom().getName() + "[" + book.getFlight().getAirportFrom().getCode() + "]" + " " + book.getFlight().getAirportFrom().getCity().getName() + ", " + book.getFlight().getAirportFrom().getCountry().getName(), 
            FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL))
        );
        doc.add(new Paragraph("To: " + book.getFlight().getAirportTo().getName() + "[" + book.getFlight().getAirportTo().getCode() + "]" + " " + book.getFlight().getAirportTo().getCity().getName() + ", " + book.getFlight().getAirportTo().getCountry().getName(), 
            FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL))
        );
        doc.add(new Paragraph("Depart: " + sdf.format(book.getFlight().getStart()) , 
            FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL))
        );
        doc.add(new Paragraph("Passengers: " , 
            FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL))
        );
         
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(50.0f);
        table.setWidths(new float[] {3.0f, 2.0f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setPadding(5);
         
        // write table header
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Class", font));
        table.addCell(cell);
         
        // write table row data
        for(SeatEntity seat: book.getSeatsList()) {
            table.addCell(seat.getFullName());
            table.addCell(seat.getTypeName());
        }
        doc.add(table);
    }
         
}
