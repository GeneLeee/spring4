package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberPrinter;
import spring.MemberRegisterService;

@Configuration
public class JavaSubConf {
	
	@Autowired
	MemberDao memberDao;
	
	@Bean
	public MemberPrinter memberPrinter(){
		return new MemberPrinter();
	}
	
	@Bean
	public MemberRegisterService memberRegSvbvc(){
		return new MemberRegisterService(memberDao);
	}
}
