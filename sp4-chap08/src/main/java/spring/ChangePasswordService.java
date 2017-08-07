package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {
	
	
	private MemberDao memberDao;
	
	@Autowired
	public ChangePasswordService(MemberDao memberDao){
		this.memberDao = memberDao;
	}
	
	public void changePassword(String email, String oldPwd, String newPwd){
		Member member = memberDao.selectByEmail(email);
		
		if(member == null)
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
	}
}
