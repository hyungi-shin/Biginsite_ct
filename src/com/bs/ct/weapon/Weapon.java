package com.bs.ct.weapon;

import com.bs.ct.model.Common;

public class Weapon {

	private double power;
	private double speed;
	private String name;
	private String tribe;
	
	public Weapon() {}
	
	public double getPower() {
		return power;
	}

	public String getTribe() {
		return tribe;
	}
	
	public String getName() {
		return name;
	}


	/** 
	 * 착용자의 공격력 + 5%
	 * 휴먼
	 * @param user
	 * @return
	 */
	public Common getShortSword(Common user) {
		this.power = 0.05;
		this.name = "Short sword";
		this.tribe = "Human";
		user.getWeaponList().add(this);
		
		return user;
	}
	
	/**
	 * 착용자의 공격력 + 10%
	 * 휴먼
	 * @param user
	 * @return
	 */
	public Common getLongSword(Common user) {
		this.power = 0.1;
		this.name = "Long sword";
		this.tribe = "Human";
		user.getWeaponList().add(this);
		return user;
	}
	
	/**
	 * 착용자의 공격속도 + 5%
	 * 엘프
	 * @param user
	 * @return
	 */
	public Common getShortBow(Common user) {
		this.speed = 0.05;
		this.name = "Short bow";
		this.tribe = "Elf";
		user.getWeaponList().add(this);
		return user;
	}
	
	/**
	 * 착용자의 공격속도 + 10%
	 * 엘프
	 * @param user
	 * @return
	 */
	public Common getIronBow(Common user) {
		this.speed = 0.1;
		this.name = "Iron bow";
		this.tribe = "Elf";
		user.getWeaponList().add(this);
		return user;
	}
	
	/**
	 * 착용자의 공격력 + 10%, 공격속도 - 5%
	 * 오크
	 * @param user
	 * @return
	 */
	public Common getShortAxe(Common user) {
		this.power = 0.1;
		this.speed = -0.05;
		this.name = "Short Axe";
		this.tribe = "Oak";
		user.getWeaponList().add(this);
		return user;
	}
	
	/**
	 * 착용자의 공격력 + 20%, 공격속도 - 10%
	 * 오크
	 * @param user
	 * @return
	 */
	public Common getIronHammer(Common user) {
		this.power = 0.2;
		this.speed = 0.1;
		this.name = "Iron Hammer";
		this.tribe = "Oak";
		user.getWeaponList().add(this);
		return user;
	}

	@Override
	public String toString() {
		String info = this.name;
		if(this.power != 0) {
			info += " [" + "공격력 : + " + power + "]";
		}
		if(this.speed != 0) {
			info += " [" + "공격속도 : + " + speed + "]";
		}
		
		info += " (" + this.tribe + " 전용무기)";
		
		return info;
	}
	
	
	
}
