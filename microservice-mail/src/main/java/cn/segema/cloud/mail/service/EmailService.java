package cn.segema.cloud.mail.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.util.Pair;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.segema.cloud.mail.config.EmailConfig;

@Service
public class EmailService {

	@Autowired
	private EmailConfig emailConfig;
	@Autowired
	private JavaMailSender mailSender;
	// @Autowired
	// private VelocityEngine velocityEngine;
	/** 
     * 发送简单邮件 
     * @param sendTo 收件人地址 
     * @param titel  邮件标题 
     * @param content 邮件内容 
     */  
	public void sendSimpleMail(String sendTo, String titel, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailConfig.getEmailFrom());
		message.setTo(sendTo);
		message.setSubject(titel);
		message.setText(content);
		mailSender.send(message);
	}

	public void sendAttachmentsMail(String sendTo, String titel, String content, List<Pair<String, File>> attachments) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(emailConfig.getEmailFrom());
			helper.setTo(sendTo);
			helper.setSubject(titel);
			helper.setText(content);

			for (Pair<String, File> pair : attachments) {
				helper.addAttachment(pair.getFirst(), new FileSystemResource(pair.getSecond()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		mailSender.send(mimeMessage);
	}

	
	 /** 
     * 发送简单邮件 
     * @param sendTo 收件人地址 
     * @param titel  邮件标题 
     * @param content 邮件内容 
     * @param attachments<文件名，附件> 附件列表 
     */  
	public void sendInlineMail() {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(emailConfig.getEmailFrom());
			helper.setTo("286352250@163.com");
			helper.setSubject("主题：嵌入静态资源");
			helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);

			FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
			helper.addInline("weixin", file);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		mailSender.send(mimeMessage);
	}

	
	/** 
     * 发送模板邮件 
     * @param sendTo 收件人地址 
     * @param titel  邮件标题 
     * @param content<key, 内容> 邮件内容 
     * @param attachments<文件名，附件> 附件列表 
     */  
	public void sendTemplateMail(String sendTo, String titel, Map<String, Object> content,
			List<Pair<String, File>> attachments) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(emailConfig.getEmailFrom());
			helper.setTo(sendTo);
			helper.setSubject(titel);

			//String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template.vm", "UTF-8", content);
			String text =null;
			helper.setText(text, true);
			for (Pair<String, File> pair : attachments) {
				helper.addAttachment(pair.getFirst(), new FileSystemResource(pair.getSecond()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		mailSender.send(mimeMessage);
	}

}
