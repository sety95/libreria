package com.sampa.library.utility;

public class Controll {
	
	public static String eliminaVirgoletteSingole(String stringa) {
		
		if(stringa.contains("'"))
			return stringa.replace("'", " ");
		else
			return stringa;
	}
}
