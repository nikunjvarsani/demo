package com.urekaserver.serviceimpl;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.urekaserver.services.services;

@Component
public class serviceImpl implements services{
	@Value("${pdfpage}")
	private Integer pageNo;

	
	@Autowired
	private JavaMailSender mailSender;
	@Override
	public ByteArrayInputStream createPdf() {
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		
		Document document=new Document();
		
		PdfWriter.getInstance(document, outputStream);
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 15);
		font.setColor(Color.BLACK);
		
		Paragraph p=new Paragraph("Hello Nikunj",font);
		document.add(p);
		
		document.close();
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	@Override
	public void createPdf1(HttpServletResponse response) throws DocumentException, IOException {
		String absolutePath = new ClassPathResource("pdf").getFile().getAbsolutePath();
        Document document=new Document();

          File file=new File(absolutePath,"Data.pdf");
          System.err.println("----++++++"+file);
          
           FileOutputStream fileOutputStream = new FileOutputStream(file);
           System.err.println("----"+fileOutputStream);
		PdfWriter.getInstance(document,fileOutputStream);
		
		
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 15);
		font.setColor(Color.BLACK);
	
		
		Paragraph p=new Paragraph("Hello Nikunj",font);
		document.add(p);
		
		PdfPTable table=new PdfPTable(3);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f,3.5f,2.5f});
		
		PdfPCell cell =new PdfPCell();
		cell.setPhrase(new Phrase("id"));
		
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("name"));
		
		table.addCell(cell);
		cell.setPhrase(new Phrase("rollNo"));
	
		table.addCell(cell);
		
//		
		table.addCell("1");
		table.addCell("nikunj");
		table.addCell("101");
		
		document.add(table);
	document.close();
		fileOutputStream.close();
	}

	@Override
	public String sendMail() {
//		MimeMessage mimeMessage=mailSender.createMimeMessage();
		String to="nikunj.varsani1611@gmail.com";
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.addAttachment("data.pdf", new ClassPathResource("pdf/Data.pdf"));
			
			helper.setTo(to);
			helper.setSubject("pdf data");
			helper.setText("Hello");
			
			mailSender.send(mimeMessage);
			
			return "Done";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
