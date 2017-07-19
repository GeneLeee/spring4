package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import assembler.Assembler;
import config.ConfigPart1;
import config.ConfigPart2;
import config.JavaConfig;
import config.JavaConfig2;
import config.JavaMainConf;
import spring.AlreadyExsitingMemberException;
import spring.ChangePasswordService;
import spring.IdPasswordNotMatchingException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.VersionPrinter;

public class MainTwoConfs {
	
	//XML이 아닌 자바 설정 파일을 이용할 경우.
	public static void main(String[] args) throws IOException{
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig2.class);
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigPart1.class, ConfigPart2.class);
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigPart1.class);
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaMainConf.class);
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:main-conf.xml");
		MemberRegisterService regSvc = 
				ctx.getBean("memberRegSvbvc", spring.MemberRegisterService.class);
		MemberInfoPrinter infoPrinter = 
				ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		
		RegisterRequest regReq = new RegisterRequest();
		regReq.setEmail("dlgustlr45@naver.com");
		regReq.setName("LeeHS");
		regReq.setPassword("123");
		regReq.setConfirmPassword("123");
		
		regSvc.regist(regReq);
		
		infoPrinter.printMember("dlgustlr45@naver.com");
		
	}//~main
}
