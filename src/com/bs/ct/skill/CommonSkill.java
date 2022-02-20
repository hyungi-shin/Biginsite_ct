package com.bs.ct.skill;

import com.bs.ct.model.Common;

public class CommonSkill {

	private int buffPower;
	private int buffSpeed;
	private double buffAvoidability;
	private int buffDefense;
	
	public CommonSkill() {}
	
	public int getBuffPower() {
		return buffPower;
	}

	public void setBuffPower(int buffPower) {
		this.buffPower = buffPower;
	}

	public int getBuffSpeed() {
		return buffSpeed;
	}

	public void setBuffSpeed(int buffSpeed) {
		this.buffSpeed = buffSpeed;
	}

	public double getBuffAvoidability() {
		return buffAvoidability;
	}

	public void setBuffAvoidability(double buffAvoidability) {
		this.buffAvoidability = buffAvoidability;
	}

	public int getBuffDefense() {
		return buffDefense;
	}

	public void setBuffDefense(int buffDefense) {
		this.buffDefense = buffDefense;
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
		}
		
	}
	
	/** 두 턴동안 공격력 20% 상승
	 *  소모 mp : 15
	 * @param user
	 */
	public void steam(Common user) {
		
		if(user.getMp() < 15) {
			System.out.println("마나가 부족합니다.");
		} else {
			this.buffPower = (int)(user.getPower() * 0.2);
			user.setMp(user.getMp() - 10);
		}
	}
}
