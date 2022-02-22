package com.bs.ct.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.bs.ct.model.Common;

public class OakSkill {

	static Timer timer = null;
	static Timer timer2 = null;
	static TimerTask tt = null;
	static TimerTask tt2 = null;
	
	public OakSkill() {}
	
	/** 스킬 목록 불러오기
	 * @param user
	 */
	public static List<String> getSkills(Common user) {
		List<String> list = new ArrayList<>();
		list.add("anger");
		System.out.println("Anger - 캐릭터의 공격력 50% 상승, 방어력 10% 하락");
		if(user.getLevel() >= 99) {
			System.out.println("(궁극)Frenzy - 1분 동안 캐릭터의 공격력 500% 상승");
			list.add("frenzy");
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
			case "anger" : Anger(user); break;
			case "frenzy" : if(user.getLevel() < 99) { System.out.println("잠금 해제되지 않은 스킬입니다."); } else { Frenzy(user); }; break;
		}
	}
	
	
	/** 15초간 캐릭터의 공격력 50% 상승, 방어력 10% 하락
	 * 사용 mp : 15
	 * @param user
	 */
	private static void Anger(Common user) {

		if(user.getMp() < 15) {
			System.out.println("마나가 부족합니다.");
		} else {
			user.setPower((int)(user.getPower() * 1.5));
			user.setDefense((int)(user.getDefense() * 0.9));
			user.setMp(user.getMp() - 15);
			System.out.println("15초간 공격력이 50% 상승, 방어력이 10% 하락합니다.");
			timer = new Timer();
			tt = new TimerTask() {
				
				@Override
				public void run() {
					// 15초 후 스킬 종료
					int power = (int)((user.getPower() * 1.0) * (10.0 / 15.0));
					user.setPower((int)power);
					int defense = (int)((user.getDefense() * 1.0) * (10.0 / 9.0));
					user.setDefense(defense);
					System.out.println("Anger 해제");
					timer.cancel();
				}
			};
			
			timer.schedule(tt, 15000);
		}
	}

	/** 1분 동안 공격력 500% 상승
	 * 소모 mp : 30
	 * @param user
	 */
	public static void Frenzy(Common user) {

		if(user.getMp() < 30) {
			System.out.println("마나가 부족합니다.");
		} else {
			user.setPower((int)(user.getPower() * 5.0));
			user.setMp(user.getMp() - 30);
			System.out.println("1분간 공격력이 500% 상승합니다.");
			timer2 = new Timer();
			tt2 = new TimerTask() {
				
				@Override
				public void run() {
					// 1분 후 스킬 종료
					int power = (int) Math.round((user.getPower() * 1.0) * (10.0 / 50.0));
					user.setPower(power);
					System.out.println("Frenzy 해제");
					timer.cancel();
				}
			};
			
			timer2.schedule(tt2, 60000);
		}
	}
	
	/** 전투 종료 시 스킬 해제
	 * @param user
	 */
	public static void battleFin(Common user) {
		if(timer != null) {
			int power = (int)((user.getPower() * 1.0) * (10.0 / 15.0));
			user.setPower((int)power);
			int defense = (int)((user.getDefense() * 1.0) * (10.0 / 9.0));
			user.setDefense(defense);
			timer.cancel();
			timer = null;
		}
		if(timer2 != null) {
			int power = (int) Math.round((user.getPower() * 1.0) * (10.0 / 50.0));
			user.setPower(power);
			timer2.cancel();
			timer2 = null;
		}
	}
	

}
