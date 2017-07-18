package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;

@Configuration
public class JavaConfig2 {
	
	@Bean
	public MemberDao memberDao(){
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvbvc(){
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public MemberPrinter printer(){
		return new MemberPrinter();
	}
	
	
	// 자바 설정에서 @Autowired 애노테이션을 적용해서 자동 주입을 처리할 경우, 자동 주입은 필드나 메서드에 대해서만 동작한다.
	@Bean
	public MemberInfoPrinter infoPrinter(){
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();		
		return infoPrinter;
	}

}
