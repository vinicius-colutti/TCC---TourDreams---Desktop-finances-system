package application;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Pdf {

	public static String nome_hotel;
	public static String valor_pagar;
    public static final String IMAGE = "C:/tourdreamsdesktop/img/boleto.png";
    public static final String DEST = "C:/tourdreamsdesktop/results/images/boleto.pdf";


    public static void pdf() throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Pdf().createPdf(DEST);

    }
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();





        PdfContentByte canvas3 = writer.getDirectContent();
        ColumnText cvp = new ColumnText(canvas3);
        cvp.setSimpleColumn(1200, 350, 680, 298);

        cvp.addElement(new Paragraph("R$" + valor_pagar));
        cvp.go();



        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = Image.getInstance(IMAGE);
        image.scaleAbsolute(PageSize.A4.rotate());
        image.setAbsolutePosition(0, 0);
        canvas.addImage(image);
        PdfContentByte canvas2 = writer.getDirectContent();

        ColumnText ct = new ColumnText(canvas2);

        ct.setSimpleColumn(1200, -200, 128, 298);

        ct.addElement(new Paragraph(nome_hotel));
        ct.go();
        document.close();

        Desktop.getDesktop().open(new File(DEST));
    }

}