package spring;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {
	private MemberDao memDao;
	private MemberPrinter printer;
	
	public void setMemberDao(MemberDao memberDao){
		this.memDao = memberDao;
	}
	public void setPrinter(MemberPrinter printer){
		this.printer = printer;
	}
	
	public void printMember(String email){
		Member member = memDao.selectByEmail(email);
		if(member == null){
			System.out.println("데이터 없음\n");
			return;
		}
		
		printer.print(member);
		System.out.println();
	}

}
