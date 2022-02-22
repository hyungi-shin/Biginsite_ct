package com.bs.ct.controller;

import java.util.List;
import java.util.Scanner;

import com.bs.ct.model.Common;
import com.bs.ct.model.Monster;
import com.bs.ct.skill.CommonSkill;
import com.bs.ct.skill.ElfSkill;
import com.bs.ct.skill.HumanSkill;
import com.bs.ct.skill.OakSkill;
import com.bs.ct.weapon.Weapon;

public class BattleController {

	static Common user = null;
	private Monster monster = null;
	
	public BattleController(Common user) {
		this.user = user;
		this.monster = new Monster();
		
		
	}
	
	public void battleMode() {
		Scanner sc = new Scanner(System.in);
		
		// 몬스터의 캐릭터 공격
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						if(user.getHp() < 1 || monster.getHp() < 1) {
							break;
						} else {
							int damage = 0;
							if(!user.avoid()) {
								System.err.println("공격 당함! ===== " + damage + "!");
								damage = user.getDefense() - (int)(monster.getPower() * 0.7);
								if(damage > 0) damage = 0;
							} else {
								System.out.println("회피!");
							}
							user.setHp(user.getHp() + damage);
							
							Thread.sleep((int)(monster.getSpeed() * 1000));
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		Thread monAttack = new Thread(r1);
		monAttack.start();
		
		// 사용자 전투
		try {
			while(true) {
				// 전투 종료
				if(user.getHp() < 1) {
					// 캐릭터 사망
					CommonSkill.battleFin(user);
					OakSkill.battleFin(user);
					user.levelDown();
					break;
				} else if(monster.getHp() < 1) {
					// 스킬 해제
					OakSkill.battleFin(user);
					CommonSkill.battleFin(user);
					System.out.println("===== 몬스터를 물리쳤습니다! =====");
					user.levelUp();
					// 전투 보상
					Weapon w = new Weapon();
					String reward = w.battleReward(user);
					System.out.println("===== 전투 보상 =====");
					System.out.println(reward);
					
					break;
				}
				
				System.out.println("1. 기본공격");
				System.out.println("2. 스킬사용");
				int choice = sc.nextInt();
				switch(choice) {
					case 1 : attackMonster(); Thread.sleep((int)(user.getSpeed() * 1000)); break;
					case 2 : usingSkill(); break;
					default : System.out.println("정확한 번호를 입력해주세요."); break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void usingSkill() {
		
		Scanner sc = new Scanner(System.in);
		// 스킬 목록 확인
		while(true) {
			List<String> commons = CommonSkill.getSkills();
			List<String> tribes = null;
			String tribe = user.getTribe();
			switch(tribe) {
				case "Oak" : tribes = OakSkill.getSkills(user); break;
				case "Human" : tribes = HumanSkill.getSkills(user); break;
				case "Elf" : tribes = ElfSkill.getSkills(user); break;
			}
			System.out.println("사용할 스킬의 이름을 입력해주세요.");
			String used = sc.nextLine().toLowerCase();
			
			if(commons.contains(used)) {
				CommonSkill.useSkill(used, user);
				break;
			} else if(tribes.contains(used)) {
				switch(tribe) {
					case "Oak" : OakSkill.useSkill(used, user); break;
					case "Human" : HumanSkill.getSkills(user); break;
					case "Elf" : ElfSkill.getSkills(user); break;
				}
				break;
			} else {
				System.out.println("정확한 이름을 입력해주세요.");
			}
		}
	}

	/**사용자가 몬스터 공격
	 * @param user
	 * @param monster
	 * @return
	 */
	public void attackMonster() {
		int userPower = user.getPower();
		int weaponPower = 0;
		if(user.getActivated() != null) {
			weaponPower = (int)(user.getPower() * user.getActivated().getPower());
		}
		int totalPower = userPower + weaponPower;
		int defense = monster.getDefense();
		
		// 반격 구현
		int random = (int)(Math.random() * 100 + 1);
		if(random > 0 && random <= 30) {
			System.err.println("몬스터의 반격!");
			int damage = 0;
			if(!user.avoid()) {
				damage = user.getDefense() - (int)(monster.getPower() * 0.7);
				if(damage > 0) damage = 0;
				System.err.println(damage + "!");
			} else {
				System.out.println("회피!");
			}
			user.setHp(user.getHp() + damage);
		} else {
			int damage = defense - totalPower;
			if(damage > 0) damage = 0;
			monster.setHp(monster.getHp() + damage);
			System.out.println("몬스터에게 " + (-damage) + "만큼의 데미지를 입혔습니다.");
		}
		
		System.out.println("몬스터의 남은 체력 : " + monster.getHp());
		System.out.println("나의 남은 체력 : " + user.getHp() + "/" + user.getFullHp());
	}
	
}

