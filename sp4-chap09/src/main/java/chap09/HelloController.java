package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //@Controller 애노테이션을 적용한 클래스는 스프링 MVC에서 컨트롤러로 사용된다.
public class HelloController {
	
	@RequestMapping("/hello") //@RequsetMapping 애노테이션은 메서드가 처리할 요청 경로를 지정한다. "/hello" 경로로 들어온 요청을 hello() 메서드를 이용해서 처리한다고 설정했다.
	public String hello(Model model, //Model 파라미터 : 컨트롤러의 처리 결과를 뷰에 전달할 떄 사용된다.
			@RequestParam(value="name", required=false) String name){ //@RequestParam 애노테이션 : HTTP 요청 파라미터 값을 메서드의 파라미터로 전달할 때 사용된다. 이 코드의 경우 name 요청 파라미터의 값을 name 파라미터에 전달한다. 
		model.addAttribute("greeting", "안녕하세요, "+name); //"greeting"이라는 모델 속성에 값을 설정한다. 값으로는 "안녕하세요, "와 name 파라미터 값을 연결한 문자열을 사용한다. 뒤에서 작성할 JSP 코드는 이 속성을 이용해서 값을 사용한다.
		return "hello"; //컨트롤러의 처리 결과를 보여줄 뷰 이름으로 "hello"를 사용한다.
	}
}