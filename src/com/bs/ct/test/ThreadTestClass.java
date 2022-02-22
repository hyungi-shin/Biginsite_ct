package com.bs.ct.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.bs.ct.model.Common;

public class ThreadTestClass {

	public static ThreadLocal<Integer> local = new ThreadLocal<Integer>();
	
	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		local.set(10);
		
		Run run = new Run();
		while(true) {
			try {
				Future<Integer> future = executorService.submit(run);
		        System.out.println(future.get());
		        Thread.sleep(2000);
		        
		        if(future.get() == 0) {
		        	System.out.println("스레드 종료");
		        	executorService.shutdown();
		        }
			} catch(Exception e) {
				break;
			}
		}
	}
	
}

class Run implements Callable<Integer> {

	private Common user;
	
	public Run() {
		this.user = new Common();
		user.setHp(10);
	}
	
	public Common getCheck() {
		return user;
	}
	
	public void setCheck(Common user) {
		this.user = user;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("공격");
		user.setHp(user.getHp()-1);
		return this.user.getHp();
	}
	
}
