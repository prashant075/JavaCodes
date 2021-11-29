package com.codingBlock.DP;

import java.util.Arrays;

public class LCS {
	
	public static void main(String[] args) {
		String s1 = "affbcererreewwewes";
		String s2 = "edffwewewewwewa";
		
		int [][] strg = new int[s1.length()][s2.length()];
		for(int i=0; i<strg.length;i++) {
			for(int j=0; j<strg[0].length;j++) {
				strg[i][j] = -1;
			}
		}
		System.out.println(LCSRecursion(s1,s2));
		System.out.println(LCSRecursionvidx(s1,s2,0,0));
		System.out.println(LCSTD(s1,s2,0,0,strg));
		System.out.println(LCSBU(s1,s2));
	}
	
	public static int LCSRecursion(String s1, String s2) {
		
		if(s1.length() ==0 || s2.length() ==0) {
			return 0;
		}
		char ch1 = s1.charAt(0);
		char ch2 = s2.charAt(0);
		
		String ros1 = s1.substring(1);
		String ros2 = s2.substring(1);
		
		int ans =0;
		if(ch1 == ch2) {
			ans = LCSRecursion(ros1, ros2) +1;
		}else {
			int o1 = LCSRecursion(s1,ros2);
			int o2 = LCSRecursion(ros1, s2);
			ans = Math.max(o1,o2);
		}
		
		return ans;
	}
	
public static int LCSRecursionvidx(String s1, String s2, int vidx1, int vidx2) {
		
		if(s1.length() ==vidx1 || s2.length() ==vidx2) {
			return 0;
		}
		char ch1 = s1.charAt(vidx1);
		char ch2 = s2.charAt(vidx2);
		
		int ans =0;
		if(ch1 == ch2) {
			ans = LCSRecursionvidx(s1, s2, vidx1+1 , vidx2 +1 ) +1;
		}else {
			int o1 = LCSRecursionvidx(s1,s2, vidx1 , vidx2+1);
			int o2 = LCSRecursionvidx(s1, s2, vidx1 +1, vidx2);
			ans = Math.max(o1,o2);
		}
		
		return ans;
	}

	public static int LCSTD(String s1, String s2, int vidx1, int vidx2, int [][] strg) {
		
		if(s1.length() ==vidx1 || s2.length() ==vidx2) {
			return 0;
		}
		
		if(strg[vidx1][vidx2] != -1) {
			return strg[vidx1][vidx2];
		}
		char ch1 = s1.charAt(vidx1);
		char ch2 = s2.charAt(vidx2);
		
		int ans =0;
		if(ch1 == ch2) {
			ans = LCSTD(s1, s2, vidx1+1 , vidx2 +1,strg ) +1;
		}else {
			int o1 = LCSTD(s1,s2, vidx1 , vidx2+1,strg);
			int o2 = LCSTD(s1, s2, vidx1 +1, vidx2,strg);
			ans = Math.max(o1,o2);
		}
		strg[vidx1][vidx2] = ans;
		return ans;
	}
	
	public static int LCSBU(String s1, String s2 ) {
		
		int [][] strg = new int[s1.length() +1][s2.length() +1];
		
		for(int row = s1.length()-1; row >=0; row --) {
			for(int col =s2.length()-1; col >=0; col--) {
				if(s1.charAt(row) == s2.charAt(col)) {
					strg[row][col] = strg[row+1][col+1] +1;
				} else {
					int o1 =strg[row][col+1];
					int o2 = strg[row+1][col];
					strg[row][col] = Math.max(o1, o2);
				}
			}
		}
		return strg[0][0];
	}

}