package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import config.JavaConfig;
import spring.Client;
import spring.Client2;

public class main {
	public static void main(String[] args){
		//GenericXmlApplicationContext ctx =
			//	new GenericXmlApplicationContext("classpath:springconf.xml");
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(JavaConfig.class);
		
		Client2 clinet = ctx.getBean("client2", Client2.class);
		clinet.send();
		ctx.close();
	}
}