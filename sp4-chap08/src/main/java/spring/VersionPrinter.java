package spring;

public class VersionPrinter {
	int mainVersion, serveVersion;
	
	/*
	//Part1. set메서드 방식
	
	public void setMainVersion(int mainVer){
		this.mainVersion = mainVer;
	}
	public void setServeVersion(int serveVer){
		this.serveVersion = serveVer;
	}
	*/
	
	//part2. 생성자 방식
	public VersionPrinter(int mainVer, int serveVer){
		this.mainVersion = mainVer;
		this.serveVersion = serveVer;
	}
	
	public void versionPrint(){
		System.out.printf("현재 버젼은 %d.%d입니다.\n",
				mainVersion, serveVersion);
		
		System.out.println();
	}
}
