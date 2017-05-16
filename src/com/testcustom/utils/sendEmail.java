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
		//����ʼ������߶���
		JavaMailSender sender = (JavaMailSender)ac.getBean("mailSender");
		//�����ʼ���Ϣ����
		MimeMessage mime = sender.createMimeMessage();
		String url = "http://localhost:8080/TestCustom2/changePass.jsp";
		try {
			//�����ʼ���Ϣ����
			MimeMessageHelper helper = new MimeMessageHelper(mime,true,"utf-8");
			//�����ʼ�������
			helper.setFrom("13502563409@163.com");
			//�����ʼ�������
			helper.setTo(e_address);
			//�����ʼ�����
			helper.setSubject("����Ƹ���⡿��֤��");
			//�����ʼ�����
			helper.setText("������֤��Ϊ:"+i_code+",�������������޸�����:"+url);
			//�����ʼ�
			sender.send(mime);
			System.out.println("�ʼ����ͳɹ�...");
			return true;
		} catch (MessagingException e) {
			
			return false;
		}	
	}
	
	public static boolean sendExamCode(String email,String ExamCode){
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		//����ʼ������߶���
		JavaMailSender sender = (JavaMailSender)ac.getBean("mailSender");
		//�����ʼ���Ϣ����
		MimeMessage mime = sender.createMimeMessage();
		String url = "http://localhost:8080/TestCustom2/test_login.jsp";
		try {
			//�����ʼ���Ϣ����
			MimeMessageHelper helper = new MimeMessageHelper(mime,true,"utf-8");
			//�����ʼ�������
			helper.setFrom("13502563409@163.com");
			//�����ʼ�������
			helper.setTo(email);
			//�����ʼ�����
			helper.setSubject("����Ƹ���⡿��֤��");
			//�����ʼ�����
			helper.setText("���ĸ��˿��Ե�¼��Ϊ:"+ExamCode+",�����������ӽ��뿼��:"+url);
			//�����ʼ�
			sender.send(mime);
			System.out.println("�ʼ����ͳɹ�...");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}	
	}
}
