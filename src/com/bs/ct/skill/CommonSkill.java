package com.bs.ct.skill;

import com.bs.ct.model.Common;

public class CommonSkill {

	/** 캐릭터의 Hp를 전체의 30% 만큼 회복
	 * @param user
	 */
	public static Boolean heal(Common user) {
		
		if(user.getMp() < 10) {
			System.out.println("마나가 부족합니다.");
			return false;
		} else {
			user.setHp(user.getHp() + (int)(user.getFullHp() * 0.3)); 
			
			// 회복량이 최대 체력을 초과할 경우
			if(user.getHp() > user.getFullHp()) {
				user.setHp(user.getFullHp());
			} 
			System.out.println("체력을 회복합니다.");
			return true;
		}
		
	}
	
	/** 두 턴동안 공격력 20% 상승
	 * @param user
	 */
	public static void steam(Common user) {
		
		user.setPower(user.getPower() + (int)(user.getPower() * 0.2));
	}
}
