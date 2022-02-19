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
	private String IsActivate;
	private List<Map<String, Weapon>> weaponList;
	
	public Common() {}

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
	
	public String getIsActivate() {
		return IsActivate;
	}

	public void setIsActivate(String isActivate) {
		IsActivate = isActivate;
	}

	public List<Map<String, Weapon>> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(List<Map<String, Weapon>> weaponList) {
		this.weaponList = weaponList;
	}

	@Override
	public String toString() {
		return "종족 : " + this.getClass().getSimpleName() + "\n" + "레벨 : " + level + "\n" + "체력 : " + hp + "/" + fullHp + "\n" + "마나 : " + mp + "/" + fullMp + "\n" 
				+ "공격력 : " + power + "\n" + "공격속도 : " + speed
				+ "\n" + "방어력 : " + defense + "\n" + "회피율 : " + Avoidability;
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
	
}
