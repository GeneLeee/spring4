package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;

@Configuration
public class JavaConfig3 {
	
	@Bean
	public MemberDao memberDao(){
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvbvc(MemberDao memDao){
		return new MemberRegisterService(memDao); //위의 memberDao() 메서드를 통해 MemberDao 빈 객체를 전달한다.  
	}
	
	@Bean
	public MemberPrinter printer(){
		return new MemberPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter(MemberDao memDao, MemberPrinter memPrinter){
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memDao);
		infoPrinter.setPrinter(memPrinter);
		return infoPrinter;
	}

}