package com.testcustom.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class sendEmail {

	public static boolean sendMassage(String e_address,String i_code){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		//获得邮件发送者对象
		JavaMailSender sender = (JavaMailSender)ac.getBean("mailSender");
		//创建邮件消息对象
		MimeMessage mime = sender.createMimeMessage();
		String url = "http://localhost:8080/TestCustom2/changePass.jsp";
		try {
			//设置邮件信息对象
			MimeMessageHelper helper = new MimeMessageHelper(mime,true,"utf-8");
			//设置邮件发送者
			helper.setFrom("13502563409@163.com");
			//设置邮件接收者
			helper.setTo(e_address);
			//设置邮件主题
			helper.setSubject("【招聘试题】验证码");
			//设置邮件内容
			helper.setText("您的验证码为:"+i_code+",请点击以下链接修改密码:"+url);
			//发送邮件
			sender.send(mime);
			System.out.println("邮件发送成功...");
			return true;
		} catch (MessagingException e) {
			
			return false;
		}	
	}
	
	public static boolean sendExamCode(String email,String ExamCode){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		//获得邮件发送者对象
		JavaMailSender sender = (JavaMailSender)ac.getBean("mailSender");
		//创建邮件消息对象
		MimeMessage mime = sender.createMimeMessage();
		String url = "http://localhost:8080/TestCustom2/test_login.jsp";
		try {
			//设置邮件信息对象
			MimeMessageHelper helper = new MimeMessageHelper(mime,true,"utf-8");
			//设置邮件发送者
			helper.setFrom("13502563409@163.com");
			//设置邮件接收者
			helper.setTo(email);
			//设置邮件主题
			helper.setSubject("【招聘试题】验证码");
			//设置邮件内容
			helper.setText("您的个人考试登录码为:"+ExamCode+",请点击以下链接进入考试:"+url);
			//发送邮件
			sender.send(mime);
			System.out.println("邮件发送成功...");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}	
	}
}
