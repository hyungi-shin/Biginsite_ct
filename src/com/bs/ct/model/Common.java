package com.bs.ct.model;

import java.util.List;
import java.util.Map;

import com.bs.ct.weapon.Weapon;

public class Common {

	private int level;
	private int fullHp;
	private int fullMp;
	private int hp;
	private int mp;
	private int power;
	private double speed;
	private int defense;
	private double Avoidability;
	private Weapon activated;
	private String tribe;
	private List<Weapon> weaponList;
	
	public Common() {}
	
	public void setTribe(String tribe) {
		this.tribe = tribe;
	}
	
	public String getTribe() {
		return tribe;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getFullHp() {
		return fullHp;
	}

	public void setFullHp(int fullHp) {
		this.fullHp = fullHp;
	}

	public int getFullMp() {
		return fullMp;
	}

	public void setFullMp(int fullMp) {
		this.fullMp = fullMp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public double getAvoidability() {
		return Avoidability;
	}

	public void setAvoidability(double avoidability) {
		Avoidability = avoidability;
	}
	
	public Weapon getActivated() {
		return activated;
	}

	public void setActivated(Weapon activated) {
		this.activated = activated;
	}

	public List<Weapon> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(List<Weapon> weaponList) {
		this.weaponList = weaponList;
	}

	@Override
	public String toString() {
		String info = "종족 : " + this.getClass().getSimpleName() + "\n" + "레벨 : " + level + "\n" + "체력 : " + hp + "/" + fullHp + "\n" + "마나 : " + mp + "/" + fullMp + "\n" 
				+ "공격력 : " + power + "\n" + "공격속도 : " + speed
				+ "\n" + "방어력 : " + defense + "\n" + "회피율 : " + Avoidability + "\n" + "장착무기 : ";
		if(this.activated != null) { 
			info += this.activated.getName();
		} else {
			info += "없음";
		}
		return info;
	}
	
	/**
	 * 레벨업
	 */
	public void levelUp() {
		this.level++;
		this.fullHp += 20;
		this.fullMp += 10;
		this.power += 3;
		this.defense += 3;
		this.hp = this.fullHp;
		this.mp = this.fullMp;
		
		System.out.println("레벨 업!");
	}
	
	/**
	 * 사망 시 레벨 다운
	 */
	public void levelDown() {
		System.err.println("===== 캐릭터가 사망하였습니다. =====");
		if(this.level <= 1) {
			System.out.println("최소 레벨은 1입니다.");
		} else {
			System.out.println("레벨이 1 낮아집니다.");
			this.level--;
			this.fullHp -= 20;
			this.fullMp -= 10;
			this.power -= 3;
			this.defense -= 3;
			this.hp = this.fullHp;
			this.mp = this.fullMp;
		}
	}
	
	/** 회피율 계산 메소드
	 * @return true - 회피 / false - 회피 실패
	 */
	public boolean avoid() {
		boolean result = true;
		int random = (int)(Math.random() * 100 + 1);
		int avoidAbi = (int)(this.getAvoidability() * 100);
		
		if(random > 0 && random < avoidAbi) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
	
}
