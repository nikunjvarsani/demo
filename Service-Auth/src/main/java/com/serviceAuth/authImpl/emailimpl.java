package com.serviceAuth.authImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.serviceAuth.entity.Student_AdminEntity;
import com.serviceAuth.repository.StudentRepo;
import com.serviceAuth.services.emailService;

@Component
public class emailimpl implements emailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	private StudentRepo repo;

	@Override
	public void sendEmail(String to, String subject, String body) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			
			MimeMessageHelper Helper = new MimeMessageHelper(mimeMessage, true);
			Helper.setTo(to);
			Helper.setSubject(subject);
			Helper.setText(body);
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String forgotPassword(String mail, String password, Integer otp, java.util.Date date) {

		Student_AdminEntity byEmail = repo.findByEmailAndDob(mail, date);

		// Current time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime curreDateTime = LocalDateTime.now();

		// get time into DB
		 Date currentTimeAndDate = byEmail.getCurrentTimeAndDate();
		 
		
		
		
		Timestamp timestamp1 = new Timestamp(currentTimeAndDate.getTime()); // DB Time Stamp
		Timestamp timestamp2 = new Timestamp(System.currentTimeMillis()); // Current Time Stamp
		long differenceInMillis = timestamp2.getTime() - timestamp1.getTime();
		long differenceInMinutes = differenceInMillis / (60 * 1000);


		System.err.println(differenceInMinutes);

		
		if (differenceInMinutes<= 2) {
			
           
			if (byEmail != null && otp.equals(byEmail.getOtp()) && date.equals(byEmail.getDob())) {
				byEmail.setPassword(password);
				repo.save(byEmail);
				return "Password update";
			} else {
				return "Password Not update";
			}
		} else {
			return "your otp expire";
		}
	}

	@Override
	public String userInfo(String email, java.util.Date dob) {
		Student_AdminEntity byEmail = repo.findByEmailAndDob(email, dob);

		Random random = new Random();
		int nextInt = random.nextInt(999999);
		String subject = "Email verification";
		String otp = "your otp is " + nextInt;

		// Current time
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime curreDateTime = LocalDateTime.now();
//		curreDateTime.getMinute();
		
		

		if (byEmail != null) {
			Student_AdminEntity data = Student_AdminEntity.StudentCon(byEmail.getId(), byEmail.getPassword(),
					byEmail.getRole(), byEmail.getName(), byEmail.getAddress(), byEmail.getEmail(), byEmail.getDob(),
					nextInt,new Date(),null);
			repo.save(data);

			sendEmail(email, subject, otp);
			return "otp" + nextInt;
		}
		return "not valid email";

	}

	@Override
	public String SendUrl(String mail,Date dob ) {
		Student_AdminEntity byEmailAndDob = repo.findByEmailAndDob(mail, dob);
		if(byEmailAndDob !=null) {
		String rondomId = UUID.randomUUID().toString();
		String body = "URL Through Reset Password";
		String URL = "http://localhost:4200/URLPassUpdate/"+ mail +"/"+dob+ "?"+rondomId;
		
		System.out.println(URL);
		byEmailAndDob.setURL(URL);
		repo.save(byEmailAndDob);
		sendEmail(mail, body, URL);

		return "done";
	}
		return "invalid";
	}

	
	
	@Override
	public String UrlPassReset(String mail, Date date,String pass) {
		 Student_AdminEntity byEmailAndDob = repo.findByEmailAndDob(mail, date);
		 
		 if (byEmailAndDob != null) {
			 byEmailAndDob.setPassword(pass);
			 repo.save(byEmailAndDob);
			 return "save";
		}
		return "not save";
	}

}
