package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import assembler.Assembler;
import spring.AlreadyExsitingMemberException;
import spring.ChangePasswordService;
import spring.IdPasswordNotMatchingException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.VersionPrinter;

public class MainForAssembler {
	
	private static ApplicationContext ctx = null;
	
	//phase1
	public static void main(String[] args) throws IOException{
		//설정 파일을 두 개 이상으로 분리할 때
		//String[] conf = {"classpath:conf1.xml", "classpath:conf2.xml"};
		
		//import를 사용할 떄
		String[] conf = {"classpath:conf1.xml"};

		//한 개의 설정 파일을 사용할 떄
		//ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
		
		//설정 파일을 두 개 이상으로 분리할 때
		ctx = new GenericXmlApplicationContext(conf);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true){
			myPrint("이것은 Chap04입니다..");
			System.out.println("명령어를 입력하세요 : ");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")){
				System.out.println("종료합니다.");
				break;
			}
			
			if(command.startsWith("new")){
				processNewCommand(command.split(" "));
				continue;
			} else if( command.startsWith("change")){
				processChangeCommand(command.split(" "));
				continue;
			} else if( command.startsWith("list")){
				processListCommand();
				continue;
			} else if( command.startsWith("info")){
				processInfoCommand(command.split(" "));
				continue;
			} else if( command.startsWith("version")){
				processVersionCommand();
				continue;
			}
			printHelp();
		}//~while	
	}//~main
	
	
	//phase2
	private static Assembler assembler = new Assembler();
	
	private static void processNewCommand(String[] arg){
		if(arg.length != 5){
			printHelp();
			return;
		}
		
		//Part1.Assembler 사용하기
		//MemberRegisterService regSvc = assembler.getMemberRegisterSerivce();
		//Part2.생성자를 통한 의존 객체 주입.
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc",MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualsToConfrimPassword()){
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try{
			regSvc.regist(req);
		}catch(AlreadyExsitingMemberException e){
			System.out.println("이미 존재하는 이메일입니다. \n");
		}
	}//~processNewCommand
	
	private static void processChangeCommand(String[] arg){
		if(arg.length != 4){
			printHelp();
			return;
		}
		
		//Part1.Assembler 사용하기
		//ChangePasswordService changePwdSvc = 
		//				assembler.getChangePasswordService();
		//Part2.생성자를 통한 의존 객체 주입.
		ChangePasswordService changePwdSvc = 
				ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
		try{
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.\n");
		}catch(MemberNotFoundException e){
			System.out.println("존재하지 않는 이메일입니다.\n");
		}catch (IdPasswordNotMatchingException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}//~processChangeCommand
	
	private static void processListCommand(){
		MemberListPrinter listPrinter 
								= ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}//~processListCommand
	
	private static void processInfoCommand(String[] arg){
		if(arg.length != 2){
			printHelp();
			return;
		}
		
		MemberInfoPrinter infoPrinter =
							ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMember(arg[1]);
	}
	
	private static void processVersionCommand(){
		VersionPrinter versionPrinter = 
							ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.versionPrint();
	}
	
	//phase3
	private static void printHelp(){
		myPrint("");
		myPrint("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		myPrint("명령어 사용법 : ");
		myPrint("new 이메일 이름 암호 암호확인");
		myPrint("change 이메일 현재비번 변경비번");
		myPrint("list");
		myPrint("info 이메일");
		myPrint("version");
		myPrint("");
	}
	
	//my
	private static void myPrint(String s){
		System.out.println(s);
	}
}
