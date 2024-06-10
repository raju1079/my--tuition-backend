package com.snipe.myTuitionCenter.auth.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.snipe.myTuitionCenter.auth.common.CommonConstants;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private Configuration config;
	

	public void resetPasswordTriggerMail(String emailId, String resetUrl) {
		Map<String, Object> model = new HashMap<>();
		model.put("password_reset_link", resetUrl);

		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			
			Template t = config.getTemplate("resetPasswordMail.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			//helper.setTo(emailId)
			helper.setTo("mytuitioncenterdev@gmail.com");
			helper.setText(html, true);
			helper.setSubject(CommonConstants.EMAIL_SUBJECT);
			helper.setFrom(sender);
			javaMailSender.send(message);


		} catch (MessagingException | IOException | TemplateException e) {
			System.out.println(e.getMessage());
		}

	}
}