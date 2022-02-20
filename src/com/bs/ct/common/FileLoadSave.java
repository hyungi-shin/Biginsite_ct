package com.bs.ct.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.bs.ct.model.Common;
import com.bs.ct.model.Elf;
import com.bs.ct.model.Human;
import com.bs.ct.model.Oak;
import com.bs.ct.weapon.Weapon;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FileLoadSave {
	
	private JSONObject jo = null;
	private static ObjectMapper om = null;
	
	public FileLoadSave() {
		jo = new JSONObject();
		om = new ObjectMapper();
	}

	/** 게임 데이터 저장
	 * @param user
	 */
	public static void saveFile(Common user) {
		
	      BufferedWriter bw = null;
	      String data = null;
	      try {
	    	  data = om.writeValueAsString(user);
	    	  bw = new BufferedWriter(new FileWriter("charactor.json"));
	    	  bw.write(data);
	    	  bw.flush();
	    	  
	      } catch (IOException e1) {
	    	  e1.printStackTrace();
	      } finally {
    		  try {
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	      }
	}
	
	/** 새로하기 시 파일 생성
	 */
	public Common newStart() {
		
		Common user = createCharactor();
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("charactor.json"));

			if(user != null ) {

				jo.put("tribe", user.getTribe());
				jo.put("level", user.getLevel());
				jo.put("fullHp", user.getFullHp());
				jo.put("fullMp", user.getFullMp());
				jo.put("hp", user.getHp());
				jo.put("mp", user.getMp());
				jo.put("power", user.getPower());
				jo.put("defense", user.getDefense());
				jo.put("avoidability", user.getAvoidability());
				user.setWeaponList(new ArrayList<Weapon>());
				jo.put("weaponList", user.getWeaponList());
				bw.write(jo.toJSONString());
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
	public Common loadFile() {
		List<Weapon> weaponList = null;
		BufferedReader br = null ;
		Common user = null;
		try {
			br = new BufferedReader(new FileReader("charactor.json"));
			
			if(br != null) {
				// 캐릭터 정보 불러오기
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(br);
				JSONObject data = (JSONObject) obj;
				
				switch(data.get("tribe").toString()) {
					case "Oak" : user = new Oak(); break;
					case "Human" : user = new Human(); break;
					case "Elf" : user = new Elf(); break;
				}
				
				user.setLevel(Integer.parseInt(data.get("level").toString()));
				user.setFullHp(Integer.parseInt(data.get("fullHp").toString()));
				user.setFullMp(Integer.parseInt(data.get("fullMp").toString()));
				user.setHp(Integer.parseInt(data.get("hp").toString()));
				user.setMp(Integer.parseInt(data.get("mp").toString()));
				user.setPower(Integer.parseInt(data.get("power").toString()));
				user.setDefense(Integer.parseInt(data.get("defense").toString()));
				user.setAvoidability(Double.parseDouble(data.get("avoidability").toString()));
				user.setTribe(data.get("tribe").toString());
				
				// 창고 불러오기
				JSONArray wL = (JSONArray) data.get("weaponList");
				weaponList = new ArrayList<Weapon>();
				if(wL.size() > 0) {
					for(int i = 0; i < wL.size(); i++) {
						JSONObject jo = (JSONObject) wL.get(i);
						Weapon weapon = om.readValue(jo.toJSONString(), Weapon.class);
						weaponList.add(weapon);
					}
				}
				
				user.setWeaponList(weaponList);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
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
					+ "3.엘프(강한 민첩성)");
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
