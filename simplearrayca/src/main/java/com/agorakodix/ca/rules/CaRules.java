package com.agorakodix.ca.rules;

public class CaRules {
	
	//private static int[]  STATE_TABLE = {0,0,0,1,1,1,1,0}; // Rule 30
	private static int[]  STATE_TABLE = {0,1,0,1,1,0,1,0}; // Rule 90 

	public CaRules() {
		super();
	}

	public static int getState(int left, int middle, int right) {
	    String s = "" + left + middle + right;
	    //int index =  Integer.parseInt(s,2);
	    int index =  (7 - Integer.parseInt(s,2));
	    return STATE_TABLE[index];
	}
}
