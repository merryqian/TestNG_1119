package com.merry.android.blemall;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Num {
	public static void main(String[] args) {
		String s="";
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(s);   
		System.out.println( m.replaceAll("").trim());
	}

}
