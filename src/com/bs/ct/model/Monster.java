package com.bs.ct.model;

public class Monster {

	private int hp;
	private int power;
	private int defense;
	private double speed;
	
	public Monster() {
		this.hp = 100;
		this.power = 40;
		this.defense = 15;
		this.speed = 1.5;
	}

	public Monster(int hp, int power, int defense) {
		super();
		this.hp = hp;
		this.power = power;
		this.defense = defense;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	
	@Override
	public String toString() {
		return "Monster [hp=" + hp + ", power=" + power + ", defense=" + defense + ", speed=" + speed + "]";
	}

	public void attack(Common user) {
		user.setHp(user.getHp() - this.power);
		System.out.println("-" + this.power + "!");
		System.out.println(user.getHp() + "/" + user.getFullHp());
	}
}
