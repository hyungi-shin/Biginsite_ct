package com.bs.ct.skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.bs.ct.model.Common;

public class CommonSkill {

	static Timer timer = null;
	static TimerTask tt = null;
	
	public CommonSkill() {}
	
	/** 
	 * @return 스킬 수
	 */
	public static List<String> getSkills() {
		List<String> list = new ArrayList<>();
		list.add("heal");
		list.add("steam");
		System.out.println("Heal - 캐릭터의 Hp를 전체의 30% 만큼 회복");
		System.out.println("Steam - 캐릭터의 공격력 20% 상승");
		return list;
	}
	
	public static void useSkill(String skill, Common user) {
		switch(skill) {
			case "heal" : heal(user); break;
			case "steam" : steam(user); break;
		}
	}
	
	/** 캐릭터의 Hp를 전체의 30% 만큼 회복
	 *  소모 mp : 10
	 * @param user
	 */
	public static void heal(Common user) {
		
		if(user.getMp() < 10) {
			System.out.println("마나가 부족합니다.");
		} else {
			user.setHp(user.getHp() + (int)(user.getFullHp() * 0.3)); 
			user.setMp(user.getMp() - 10);
			// 회복량이 최대 체력을 초과할 경우
			if(user.getHp() > user.getFullHp()) {
				user.setHp(user.getFullHp());
			}
			System.out.println("체력을 회복합니다.");
			System.out.println("현재 체력 : (" + user.getHp() + "/" + user.getFullHp() + ")");
		}
		
	}
	
	/** 20초간 공격력 20% 상승
	 *  소모 mp : 15
	 * @param user
	 */
	public static void steam(Common user) {
		
		if(user.getMp() < 15) {
			System.out.println("마나가 부족합니다.");
		} else {
			user.setPower((int)(user.getPower() * 1.2));
			user.setMp(user.getMp() - 10);
			System.out.println("공격력이 20초간 20% 상승합니다.");
			timer = new Timer();
			tt = new TimerTask() {
				
				@Override
				public void run() {
					// 20초 후 스킬 종료
					int power = (int)((user.getPower() * 1.0) * (10.0 / 12.0));
					user.setPower(power);
					System.out.println("Steam 해제");
					timer.cancel();
				}
			};
			
			timer.schedule(tt, 20000);
		}
	}
	

	/** 전투 종료 시 스킬 해제
	 * @param user
	 */
	public static void battleFin(Common user) {
		if(timer != null) {
			int power = (int)((user.getPower() * 1.0) * (10.0 / 12.0));
			user.setPower(power);
			timer.cancel();
			timer = null;
		}
	}
}
