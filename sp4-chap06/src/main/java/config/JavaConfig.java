package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.Client2;

@Configuration
public class JavaConfig {
	@Bean(initMethod="connect", destroyMethod="close")
	public Client2 client2(){
		Client2 client2 = new Client2();
		client2.setHost(" 서버2, 자바 ");
		//자바 설정의 경우, initMethod를 사용하지 않고 직접 초기화 메서드를 실행해도 된다.
		//cl2.connect()
		return client2;
	}
}
