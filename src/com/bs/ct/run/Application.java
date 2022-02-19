package com.bs.ct.run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.bs.ct.model.Common;
import com.bs.ct.model.Elf;
import com.bs.ct.model.Human;
import com.bs.ct.model.Oak;

public class Application {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Common user = null;
		label:
		while(true) {
			System.out.println("1.새로하기");
			System.out.println("2.불러오기");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1 : user = newStart(); 
					if(user == null) {
						System.out.println("다시 시도해주세요.");
						continue label;
					}
					break;
				case 2 : user = loadFile();
					if(user == null) {
						System.out.println("저장된 파일이 없습니다.");
						continue label;
					}
					break;
				default : System.out.println("정확한 숫자를 입력해주세요."); continue label;
			}
		}
		
		
	}
	
	/** 새로하기 시 파일 생성
	 */
	public static Common newStart() {
		
		Common user = createCharactor();
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("charactor.txt"));

			if(user != null ) {

				bw.write(user.getClass().getSimpleName() + "\n");
				bw.write(user.getLevel() + "\n");
				bw.write(user.getFullHp() +"\n");;
				bw.write(user.getFullMp()+"\n");
				bw.write(user.getHp()+ "\n");
				bw.write(user.getMp()+ "\n");
				bw.write(user.getPower() +"\n");
				bw.write(user.getDefense() +"\n");
				bw.write(user.getAvoidability() + "");
				bw.flush();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
		
		return user;
	}
	
	/** 불러오기
	 */
	public static Common loadFile() {
		ArrayList<Object> arr = null;
		BufferedReader br = null ;
		Common user = null;
		try {
			arr = new ArrayList<>();
			br = new BufferedReader(new FileReader("charactor.txt"));
			String line ;
			try {
				for (int i = 0; (line = br.readLine()) != null; i++) {
					arr.add(line);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		if( br != null) {
			
			switch((String) arr.get(0)) {
				case "Oak" : user = new Oak(); break;
				case "Human" : user = new Human(); break;
				case "Elf" : user = new Elf(); break;
			}
			
			user.setLevel(Integer.parseInt((String)arr.get(1)));
			user.setFullHp(Integer.parseInt((String)arr.get(2)));
			user.setFullMp(Integer.parseInt((String)arr.get(3)));
			user.setHp(Integer.parseInt((String)arr.get(4)));
			user.setMp(Integer.parseInt((String)arr.get(5)));
			user.setPower(Integer.parseInt((String)arr.get(6)));
			user.setDefense(Integer.parseInt((String)arr.get(7)));
			user.setAvoidability(Double.parseDouble((String)arr.get(8)));
		}
		
		return user;
	}
	
	/** 캐릭터 생성
	 * @return 선택한 종족의 Common 객체
	 */
	public static Common createCharactor() {
		
		Scanner sc = new Scanner(System.in);
		Common user = null;
		label:
		while(true) {
			System.out.println("캐릭터를 생성합니다.");
			System.out.println("종족을 선택해주세요.");
			System.out.println("1.오크(강한 공격력) \n"
					+ "2.인간(강한 방어능력) \n"
					+ "3.엘프(강한 민첩성) \n");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1 : 
					user = new Oak();
					System.out.println("오크를 선택하시겠습니까?(Y/N)");
					break;
				case 2 : 
					user = new Human();
					System.out.println("인간을 선택하시겠습니까?(Y/N)");
					break;
				case 3 : 
					user = new Elf();
					System.out.println("엘프를 선택하시겠습니까?(Y/N)");
					break;
				default : System.out.println("숫자를 정확하게 입력해주세요."); continue label;
			}
			sc.nextLine();
			String check = sc.nextLine().toUpperCase();
			
			if(check.equals("Y")) {
				break label;
			} else {
				continue label;
			}
		}
		
		return user;
	}
	
	
}
