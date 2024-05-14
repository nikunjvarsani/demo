
package com.urekaserver.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;

@Service
public interface services {

	public ByteArrayInputStream createPdf();

	public String sendMail();
	
	
	public void createPdf1(HttpServletResponse response) throws DocumentException, IOException;
}
