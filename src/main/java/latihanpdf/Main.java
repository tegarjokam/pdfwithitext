package latihanpdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
    
	public static void addKop(Document document, PdfWriter writer) throws DocumentException, URISyntaxException, MalformedURLException, IOException {
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
        canvas.moveTo(40, 700);
        canvas.lineTo(550, 700);
        canvas.closePathStroke();
      
        document.add(table);
		
	}
	
	public static void addHeader(Document document) throws DocumentException, IOException {
		
		BaseFont base = BaseFont.createFont(path.toString(), BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		Font spbTitleFont = new Font(base, 12, Font.BOLD);
		Font spbNumberFont = new Font(base, 12, Font.NORMAL);
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
        PdfPCell c1 = new PdfPCell(new Phrase("SURAT PERMINTAAN BARANG", spbTitleFont));
        c1.setPaddingTop(15);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("NOMOR : SPB-01/WBC.19/BG.01/2020", spbNumberFont));
        c1.setPaddingTop(5);
        c1.setBorder(Rectangle.NO_BORDER);
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    c1.setVerticalAlignment(Element.ALIGN_CENTER);
	    c1.setPaddingBottom(20);
	    table.addCell(c1);
	    
	    document.add(table);
	    
      
	}
	
	public static void addBidangCreator(Document document, PdfWriter writer) throws DocumentException, IOException {
		
		BaseFont base = BaseFont.createFont(path.toString(), BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		Font bidangCreatorFont = new Font(base, 12, Font.NORMAL);
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		
        PdfPCell c1 = new PdfPCell(new Phrase("Dari", bidangCreatorFont));
        c1.setPaddingLeft(20);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase(":  Bagian Umum", bidangCreatorFont));
        c1.setColspan(3);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Tanggal", bidangCreatorFont));
        c1.setPaddingTop(5);
        c1.setPaddingLeft(20);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase(":  30 Januari 2020", bidangCreatorFont));
        c1.setPaddingTop(5);
        c1.setColspan(3);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setPaddingBottom(15);
        table.addCell(c1);
        
        PdfContentByte canvas = writer.getDirectContent();
        canvas.setColorStroke(BaseColor.BLACK);
        canvas.moveTo(40, 590);
        canvas.lineTo(550, 590);
        canvas.closePathStroke();
	    
	    document.add(table);
	    
	    
	}
	
	public static void addContent(Document document) throws DocumentException, IOException, URISyntaxException {
		
		BaseFont base = BaseFont.createFont(path.toString(), BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		Font introFont = new Font(base, 12, Font.NORMAL);
		
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		Chunk chunk = new Chunk();
		chunk.append("Bersama dengan Surat Permintaan Barang ini pegawai yang bertanda tangan pada surat ini meminta barang persediaan untuk digunakan operasional Bagian/Bidang, adapun rincian permintaan barang tersebut sebagai berikut ");
		
		Paragraph intro = new Paragraph();
		intro.add(chunk);
		intro.setFont(introFont);
		intro.setAlignment(Element.ALIGN_JUSTIFIED);
		intro.setFirstLineIndent(40);
		intro.setIndentationLeft(20);
		intro.setIndentationRight(10);
		intro.setSpacingAfter(20);
		
        PdfPCell c1 = new PdfPCell();
        c1.addElement(intro);
        c1.setPaddingTop(10);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
	    
	    document.add(table);
	    
	    addListGoods(document);
	}
	
	public static void addListGoods(Document document) throws DocumentException, IOException {
		
		BaseFont base = BaseFont.createFont(path.toString(), BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		Font headerItem = new Font(base, 12, Font.BOLD);
		
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(80);
		
		PdfPCell c1 = new PdfPCell(new Phrase("Kode Stok", headerItem));
		c1.setPaddingLeft(10);
		c1.setPaddingBottom(8);
		c1.setPaddingTop(5);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Nama Barang", headerItem));
		c1.setPaddingLeft(10);
		c1.setPaddingBottom(8);
		c1.setPaddingTop(5);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Jumlah", headerItem));
		c1.setPaddingLeft(10);
		c1.setPaddingBottom(8);
		c1.setPaddingTop(5);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Satuan", headerItem));
		c1.setPaddingLeft(10);
		c1.setPaddingBottom(8);
		c1.setPaddingTop(5);
		table.addCell(c1);
		
		table.addCell("2.1");
		table.addCell("2,2");
		table.addCell("2,3");
		table.addCell("2,4");
		table.addCell("3,1");
		table.addCell("3,2");
		table.addCell("3,3");
		table.addCell("3,4");
		table.addCell("4,1");
		table.addCell("4,2");
		table.addCell("4,3");
		table.addCell("4,4");
		table.addCell("5,1");
		table.addCell("5,2");
		table.addCell("5,3");
		table.addCell("5,4");

	    document.add(table);
		
	}
	
	public static void addFooter(Document document) throws DocumentException, IOException, URISyntaxException {
		
		BaseFont base = BaseFont.createFont(path.toString(), BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		Font outerFont = new Font(base, 12, Font.NORMAL);
		
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
        Chunk outerWord = new Chunk();
		outerWord.append("Demikian disampaikan, atas perhatiannya kami ucapkan terima kasih.");
		
		Paragraph outerSentence = new Paragraph();
		outerSentence.add(outerWord);
		outerSentence.setFont(outerFont);
		outerSentence.setAlignment(Element.ALIGN_JUSTIFIED);
		outerSentence.setFirstLineIndent(40);
		outerSentence.setIndentationLeft(20);
		outerSentence.setIndentationRight(10);
		outerSentence.setSpacingAfter(20);
		
		PdfPCell c1 = new PdfPCell();
        c1.addElement(outerSentence);
        c1.setPaddingTop(13);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        System.out.println("rowspan = " + c1.getRowspan());
        c1.setColspan(3);
        table.addCell(c1);
        
        c1 = new PdfPCell();
        c1.addElement(new Phrase(" "));
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);
		
        Chunk signDateWord = new Chunk();
        signDateWord.append("Ambon, 30 Januari 2020");
        Paragraph signDate = new Paragraph();
        signDate.add(signDateWord);
        signDate.setFont(outerFont);
        signDate.setAlignment(Element.ALIGN_LEFT);
        signDate.setIndentationRight(40);
        
        c1 = new PdfPCell();
        c1.addElement(signDate);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setPaddingLeft(160);
        c1.setColspan(2);
        table.addCell(c1);
        
        c1 = new PdfPCell();
        c1.addElement(new Phrase(" "));
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);
        
        Path pathImg = Paths.get(ClassLoader.getSystemResource("LogoKemenkeu.jpg").toURI());
		Image img = Image.getInstance(pathImg.toAbsolutePath().toString());
        
        c1 = new PdfPCell(img);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setPaddingLeft(180);
        c1.setColspan(2);
        table.addCell(c1);
        
        c1 = new PdfPCell();
        c1.addElement(new Phrase(" "));
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);

        Chunk employeeName = new Chunk();
        employeeName.append("Gama Wira Nusa");
        Paragraph eName = new Paragraph();
        eName.add(employeeName);
        eName.setFont(outerFont);
        eName.setAlignment(Element.ALIGN_LEFT);
        
        c1 = new PdfPCell();
        c1.addElement(eName);
        c1.setBorder(Rectangle.NO_BORDER);
        c1.setPaddingLeft(160);
        c1.setVerticalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);
        
	    document.add(table);
	    
      
	}
	
	
	public static void main(String[] args) throws DocumentException, URISyntaxException, MalformedURLException, IOException {
		
		Document document = new Document(PageSize.A4);
		
		document.open();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(destinationPath.toString()));
		
		addKop(document, writer);
		addHeader(document);
		addBidangCreator(document, writer);
		addContent(document);
		addFooter(document);
		
		document.close();
	}
}