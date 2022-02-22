package com.bs.ct.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.bs.ct.model.Common;

public class HumanSkill {
	
	static Timer timer = null;
	static Timer timer2 = null;
	static TimerTask tt = null;
	static TimerTask tt2 = null;

	public HumanSkill() {}
	
	/** 스킬 목록 조회
	 * @param user
	 */
	public static List<String> getSkills(Common user) {
		List<String> list = new ArrayList<>();
		list.add("guard");
		System.out.println("Guard - 캐릭터의 방어력 30% 상승");
		if(user.getLevel() >= 99) {
			System.out.println("(궁극)Invincible - 10초 동안 무적");
			list.add("invincible");
		} else {
			System.out.println("잠금(Lv.99)");
		}
		return list;
	}
	
	/** 스킬 사용
	 * @param user
	 */
	public static void useSkill(String skill, Common user) {
		switch(skill) {
			case "guard" : Guard(user); break;
			case "invincible" : if(user.getLevel() < 99) { System.out.println("잠금 해제되지 않은 스킬입니다."); } else { Invincible(user); }; break;
		}
	}
	
	/** 20초간 방어력 30% 상승
	 *  소모 mp : 20
	 * @param user
	 * @return 추가 방어력
	 */
	public static void Guard(Common user) {
		
		if(user.getMp() < 20) {
			System.out.println("마나가 부족합니다.");
		} else {
			user.setDefense((int)(user.getDefense() * 1.3));
			user.setMp(user.getMp() - 20);
			System.out.println("20초간 방어력이 30% 상승합니다.");
			timer = new Timer();
			tt = new TimerTask() {
				
				@Override
				public void run() {
					// 20초 후 스킬 종료
					int defense = (int)((user.getDefense() * 1.0) * (10.0 / 13.0));
					user.setDefense(defense);
					System.out.println("Guard 해제");
					timer.cancel();
				}
			};
			
			timer.schedule(tt, 20000);
		}
	}
	
	/** 캐릭터 10초 무적
	 * 소모 mp : 30
	 * @return
	 */
	public static void Invincible(Common user) {
		
		if(user.getMp() < 30) {
			System.out.println("마나가 부족합니다.");
		} else {
			user.setDefense(user.getDefense() + 99999);
			user.setMp(user.getMp() - 30);
			System.out.println("10초 간 무적 상태를 유지합니다.");
			timer2 = new Timer();
			tt2 = new TimerTask() {
				
				@Override
				public void run() {
					// 10초 후 스킬 종료
					int defense = user.getDefense() - 99999;
					user.setDefense(defense);
					System.out.println("Invincible 해제");
					timer2.cancel();
				}
			};
			
			timer2.schedule(tt2, 10000);
		}
	}
	
	/** 전투 종료 시 스킬 해제
	 * @param user
	 */
	public static void battleFin(Common user) {
		if(timer != null) {
			int defense = (int)((user.getDefense() * 1.0) * (10.0 / 13.0));
			user.setDefense(defense);
			timer.cancel();
			timer = null;
		}
		if(timer2 != null) {
			int defense = user.getDefense() - 99999;
			user.setDefense(defense);
			timer2.cancel();
			timer2 = null;
		}
	}
	
	
}
