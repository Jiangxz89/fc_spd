package com.thinkgem.jeesite.common.utils;

import java.util.HashSet;
import java.util.Set;

public class RandomNum {
	public static String generateRandomStr(int len) {
	    //字符源，可以根据需要删减
	    String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz";
	    String rtnStr = "";
	    for (int i = 0; i < len; i++) {
	        //循环随机获得当次字符，并移走选出的字符
	        String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
	        rtnStr += nowStr;
	        generateSource = generateSource.replaceAll(nowStr, "");
	    }
	    return rtnStr;
	}
	
	public static Set getRandom(int n) {
		 //long startTime = System.currentTimeMillis(); 
		 Set<String> set = new HashSet<String>();
		 while(true){
		 Double random = Math.random();
		 String str = random.toString().substring(2, 11);
		 set.add(str);
		 if(set.size()==n){
			 break;}
		 }
		 //long endTime = System.currentTimeMillis();  
		 //float seconds = (endTime - startTime) / 1000F;
		 return set;
	}
	
	public static void main(String[] args) {
		for(int i =0;i<10;i++){
			//System.out.println(generateRandomStr(10));
			System.out.println(Math.random());
		  }
		
	}

}
