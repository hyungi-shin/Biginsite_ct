package com.bs.ct.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.bs.ct.model.Common;

public class ElfSkill {
	
	static Timer timer = null;
	static Timer timer2 = null;
	static TimerTask tt = null;
	static TimerTask tt2 = null;

	public ElfSkill() {}
	
	/** 스킬 목록 불러오기
	 * @param user
	 */
	public static List<String> getSkills(Common user) {
		List<String> list = new ArrayList<>();
		list.add("elusion");
		System.out.println("Elusion - 캐릭터의 회피력 30% 상승");
		if(user.getLevel() >= 99) {
			System.out.println("(궁극)Rapid - 1분 동안 캐릭터의 공격속도 500% 상승");
			list.add("rapid");
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
			case "elusion" : Elusion(user); break;
			case "rapid" : if(user.getLevel() < 99) { System.out.println("잠금 해제되지 않은 스킬입니다."); } else { Rapid(user); }; break;
		}
	}

	/** 20초 간 캐릭터의 회피력 30% 상승
	 *  소모 mp : 20
	 * @param user
	 */
	private static void Elusion(Common user) {

		if(user.getMp() < 20) {
			System.out.println("마나가 부족합니다.");
		} else {
			user.setAvoidability(user.getAvoidability() * 1.3);
			user.setMp(user.getMp() - 20);
			System.out.println("20초간 회피력이 30% 상승합니다.");
			timer = new Timer();
			tt = new TimerTask() {
				
				@Override
				public void run() {
					// 20초 후 스킬 종료
					double avo = (user.getAvoidability() * 1.0) * (10.0 / 13.0);
					user.setAvoidability(avo);
					System.out.println("Elusion 해제");
					timer.cancel();
				}
			};
			
			timer.schedule(tt, 20000);
		}
	}

	/** 1분 간 캐릭터의 공격속도 500% 상승
	 *  소모 mp : 30
	 * @param user
	 */
	private static void Rapid(Common user) {
		
		if(user.getMp() < 30) {
			System.out.println("마나가 부족합니다.");
		} else {
			user.setAvoidability(user.getSpeed() * 6);
			user.setMp(user.getMp() - 30);
			System.out.println("1분 간 공격속도가 500% 상승합니다.");
			timer2 = new Timer();
			tt2 = new TimerTask() {
				
				@Override
				public void run() {
					// 1분 후 스킬 종료
					double avo = (user.getAvoidability() * 1.0) * (10.0 / 60.0);
					user.setAvoidability(avo);
					System.out.println("Rapid 해제");
					timer2.cancel();
				}
			};
			
			timer2.schedule(tt2, 20000);
		}
	}
	
	
	/** 전투 종료 시 스킬 해제
	 * @param user
	 */
	public static void battleFin(Common user) {
		if(timer != null) {
			double avo = (user.getAvoidability() * 1.0) * (10.0 / 13.0);
			user.setAvoidability(avo);
			timer.cancel();
			timer = null;
		}
		if(timer2 != null) {
			double avo = (user.getAvoidability() * 1.0) * (10.0 / 60.0);
			user.setAvoidability(avo);
			timer2.cancel();
			timer2 = null;
		}
	}
}
