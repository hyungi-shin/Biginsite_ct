package com.bs.ct.model;

public class Elf extends Common {

	// 종족 기본 능력치
	public Elf() {

		super.setFullHp(80);
		super.setHp(80);
		super.setDefense(10);
		super.setFullMp(70);
		super.setMp(70);
		super.setPower(15);
		super.setAvoidability(0.4);
		super.setSpeed(1.4);
	}
}
