package com.bs.ct.run;

import java.util.List;
import java.util.Scanner;

import com.bs.ct.controller.BattleController;
import com.bs.ct.controller.FileLoadSave;
import com.bs.ct.model.Common;
import com.bs.ct.skill.CommonSkill;
import com.bs.ct.skill.ElfSkill;
import com.bs.ct.skill.HumanSkill;
import com.bs.ct.skill.OakSkill;
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
			System.out.println("5. 저장하기");
			System.out.println("6. 종료");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1 : System.out.println(user); break;
				case 2 : 
					List<Weapon> wL = user.getWeaponList();
					System.out.println("======= 창고 =======");
					if(wL.size() != 0) {
						for(int i = 0; i < wL.size(); i++) {
							System.out.println((i + 1) + ". " + wL.get(i)); 
						}
						System.out.println((wL.size() + 1) + ". 창고 나가기");
						sc.nextLine();
						int weaponNum = sc.nextInt();
						if(weaponNum == (wL.size() + 1)) {
							break;
						}
						
						// 입력 숫자에 맞는 무기 장착
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
					CommonSkill.getSkills();
					switch(user.getTribe()) {
						case "Oak" : OakSkill.getSkills(user); break;
						case "Human" : HumanSkill.getSkills(user); break;
						case "Elf" : ElfSkill.getSkills(user); break;
					}
					break;
				case 4 : 
					BattleController b = new BattleController(user);
					b.battleMode();
					break;
				case 5 : 
					System.out.println("저장중..");
					FileLoadSave.saveFile(user); 
					System.out.println("저장완료!");
					break;
				case 6 : return;
				default : System.out.println("정확한 숫자를 입력해주세요.");
			}
		}
	}
	
	
}
