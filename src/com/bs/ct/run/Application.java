package com.bs.ct.run;

import java.util.List;
import java.util.Scanner;

import com.bs.ct.common.FileLoadSave;
import com.bs.ct.model.Common;
import com.bs.ct.weapon.Weapon;

public class Application {
	
	private static FileLoadSave fs = null;

	public static void main(String[] args) {
		
		fs = new FileLoadSave();
		
		Scanner sc = new Scanner(System.in);
		
		Common user = null;
		
		while(true) {
			System.out.println("1.새로하기");
			System.out.println("2.불러오기");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1 : user = fs.newStart(); 
					if(user == null) {
						System.out.println("다시 시도해주세요.");
					} else {
						System.out.println("접속 완료..");
						waiting(user);
					}
					break;
				case 2 : System.out.println("파일 로드중..");
					user = fs.loadFile();
					if(user == null) {
						System.out.println("저장된 파일이 없습니다.");
					} else {
						System.out.println("접속 완료..");
						waiting(user);
					}
					break;
				default : System.out.println("정확한 숫자를 입력해주세요.");
			}
		}
		
		
	}
	
	public static void waiting(Common user) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("1. 캐릭터 정보 확인");
			System.out.println("2. 무기 장착");
			System.out.println("3. 스킬 확인");
			System.out.println("4. 전투 입장");
			System.out.println("5. 종료");
			System.out.println("무기얻기");
			System.out.println("저장하기");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1 : System.out.println(user); break;
				case 2 : 
					List<Weapon> wL = user.getWeaponList();
					System.out.println("====== 창고 ======");
					if(wL.size() != 0) {
						for(int i = 0; i < wL.size(); i++) {
							System.out.println((i + 1) + ". " + wL.get(i)); 
						}
						sc.nextLine();
						int weaponNum = sc.nextInt();
						
						if(wL.get(weaponNum - 1).getTribe().equals(user.getClass().getSimpleName())) {
							user.setActivated(wL.get(weaponNum - 1));
							System.out.println("장착 완료!");
						} else {
							System.out.println("종족에 맞는 무기만 장착할 수 있습니다.");
						}
						
					} else {
						System.out.println("아직 비어있습니다..");
					}
					break;
				case 3 :
					break;
				case 4 : break;
				case 5 : return;
				case 6 : Weapon ws = new Weapon(); ws.getShortAxe(user); break;
				case 7 : fs = new FileLoadSave(); fs.saveFile(user); break;
				default : System.out.println("정확한 숫자를 입력해주세요.");
			}
		}
	}
	
	
	
	
	
}
