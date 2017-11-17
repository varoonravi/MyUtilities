package com.utils;

import java.io.UnsupportedEncodingException;

public class LanguageBasics {
	
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		LanguageBasics lb = new LanguageBasics();
		String input = new String("ಸಕ್ಕೊಟೈಬ್ಲಾಕ್".getBytes());
		char languageContext = lb.getLangContext(input);
		System.out.println(Integer.toHexString(languageContext & 0xff00));
	}
	
	public char getLangContext(String word)
	{
		char lc = (char)(word.charAt(0) & 0xff80);
		return lc;
	}
}
