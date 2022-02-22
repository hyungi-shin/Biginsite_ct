package com.bs.ct.test;

import java.util.Timer;
import java.util.TimerTask;

import com.bs.ct.model.Common;

public class StaticTestClass {
	static int aa = 35;
	public static void main(String[] args) {
//		Common user = new Human();
//		System.out.println(user.getPower());
//		A a = new A(user);
//		System.out.println(A.user.getPower());
//		System.out.println(user.getPower());
//		B b = new B();
//		System.out.println(user.getPower());
//		user.setPower(50);
		int c = 30;
		int b = (int)(c * 1.2);
		int a = (int)((b* 1.0)*(10.0 / 12.0));
		System.out.println(b);
		System.out.println(a);
		Timer t = new Timer();
		System.out.println("타이머 대기");
		aa = (int)(aa * 1.2);
		System.out.println(aa);
		TimerTask tt = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("타이머 실행");
				aa = (int)((aa*1.0) * (10.0/12.0));
				System.out.println(aa);
				t.cancel();
			}
		};
		aa += 3;
		System.out.println(aa);
		t.schedule(tt, 2000);
		
	}
}

class A {
	static Common user = null;
	public A(Common user) {
		this.user = user;
		this.user.setPower(1000);
	}
}

class B {
	public B() {
		A.user.setPower(2000);
	}
}
