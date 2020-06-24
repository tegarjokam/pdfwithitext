package latihanpdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Main {
	
    private static Path destinationPath = Paths.get("src","main","resources","SPB-02.pdf");
	private static Path path = Paths.get("src","main","resources","font","arial.ttf");
    
	public static void addHeader(Document document) throws DocumentException, URISyntaxException, MalformedURLException, IOException {
		document.addTitle("Surat Permintaan Barang");
        document.addSubject("SPB");
        document.addKeywords("Spb, Barang");
        document.addAuthor("Kanwil DJBC Maluku");
        document.addCreator("username");
        
		BaseFont base = BaseFont.createFont(path.toString(), BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		Font kemenkeuTitle = new Font(base, 13, Font.BOLD);
		Font djbcTitle = new Font(base, 11, Font.BOLD);
		Font kanwilTitle = new Font(base, 11, Font.BOLD);
		Font alamatTitle = new Font(base, 7, Font.BOLD);
		alamatTitle.setColor(BaseColor.GRAY);
		
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(destinationPath.toString()));
		         
		document.open();
		
		PdfPTable table = new PdfPTable(4);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table.setWidthPercentage(100);
		
        Path pathImg = Paths.get(ClassLoader.getSystemResource("LogoKemenkeu.jpg").toURI());
		Image img = Image.getInstance(pathImg.toAbsolutePath().toString());
        
        PdfPCell c1 = new PdfPCell(img);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setRowspan(7);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("KEMENTERIAN KEUANGAN REPUBLIK INDONESIA", kemenkeuTitle));
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("DIREKTORAT JENDERAL BEA DAN CUKAI", djbcTitle));
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(3);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("KANTOR WILAYAH DIREKTORAT JENDERAL BEA DAN CUKAI MALUKU", kanwilTitle));
        c1.setBorder(Rectangle.NO_BORDER);
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    c1.setColspan(3);
	    table.addCell(c1);
        
	    Paragraph paragraph = new Paragraph("JALAN BENTENG KAPAHAHA, AMBON, MALUKU 97124", alamatTitle);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    c1 = new PdfPCell(new Phrase(" aadsasdas ", alamatTitle));
	    c1.setBorder(Rectangle.NO_BORDER);
	    c1.addElement(paragraph);
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    c1.setVerticalAlignment(Element.ALIGN_BOTTOM);
	    c1.setColspan(3);
	    table.addCell(c1);
        
	    c1 = new PdfPCell(new Phrase("TELEPON (0911) 344529; FAKSIMILE (0911) 344348; LAMAN WWW.BEACUKAI.GO.ID", alamatTitle));
	    c1.setBorder(Rectangle.NO_BORDER);
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    c1.setColspan(3);
	    table.addCell(c1);
	    
	    c1 = new PdfPCell(new Phrase("PUSAT KONTAK LAYANAN 1500225; SUREL INFO@CUSTOMS.GO.ID", alamatTitle));
	    c1.setBorder(Rectangle.NO_BORDER);
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    c1.setColspan(3);
	    table.addCell(c1);
        
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        
        PdfContentByte canvas = writer.getDirectContent();
        canvas.setColorStroke(BaseColor.BLACK);
        canvas.moveTo(40, 710);
        canvas.lineTo(550, 710);
        canvas.closePathStroke();
      
        document.add(table);
		
	}
	
	public static void main(String[] args) throws DocumentException, URISyntaxException, MalformedURLException, IOException {
		
		Document document = new Document(PageSize.A4);
		document.open();
		
		addHeader(document);
		
		document.close();
	}
}