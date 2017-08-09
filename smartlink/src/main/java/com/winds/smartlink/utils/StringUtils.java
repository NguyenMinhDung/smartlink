package com.winds.smartlink.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Class contain utility for handle String.
 * @author Windreams
 *
 */
public class StringUtils {

	/**
	 * Gets the md5 hash.
	 * @param value 
	 * @param salt
	 * @return the md5 hash
	 */
	public static String getMD5Hash(String value, String salt) {
		MessageDigest digest;
		try {
			digest = java.security.MessageDigest.getInstance("MD5");
			digest.update((salt + value).getBytes());
			final byte[] hash = digest.digest();
			final StringBuilder result = new StringBuilder(hash.length);
			for (int i = 0; i < hash.length; i++) {
				result.append(Integer.toString((hash[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return result.toString();
		} catch (final NoSuchAlgorithmException e) {
			return "error";
		}
	}
	
	/**
	 * Generate hash.
	 * @param input
	 * @param salt
	 * @return hash
	 */
	public static String generateHash(String input, String salt) {
		// SHA or MD5
		try {
			if (salt == null) {
				salt = "";
			}
			input = input.toLowerCase();
			salt = salt.toLowerCase();
			MessageDigest md = MessageDigest.getInstance("MD5");
			String hash = "";	
			input += salt;
			byte[] data = input.getBytes("US-ASCII");
			
			md.update(data);
			byte[] digest = md.digest();
			for (int i = 0; i < digest.length; i++) {
			String hex = Integer.toHexString(digest[i]);
			if (hex.length() == 1)
				hex = "0" + hex;
				hex = hex.substring(hex.length() - 2);
				hash += hex;
			}		
			return hash;
		} catch (Exception e) {			
		}
		return "";		
	}
	
	/**
	 * Check string empty or null.
	 * @param s
	 * @return true is empty, false not empty
	 */
	public static boolean isNullOrEmpty(final String s) {
		return (s == null || s.trim().length() == 0);
	}
	
	/**
	 * Check string not empty.
	 * @param s
	 * @return true if string empty, else false 
	 */
	public static boolean isNotEmpty(final String s) {
		return !isNullOrEmpty(s);
	}

	/**
	 * Remove special character in string.
	 * @param text
	 * @param byString
	 * @return plaintext no special character
	 */
	public static String removeSpecialChars(String text, String byString) {
		final String[] chars = new String[] {",", ".", "/", "!", "@", "#",
				"$", "%", "^", "&", "*", "'", "\"", ";", "-", "_", "(", ")",
				":", "|", "[", "]", "~", "+", "{", "}", "?", "\\", "<", ">" };
		if (StringUtils.isNullOrEmpty(text))
			return text;

		for (int i = 0; i < chars.length; i++) {
			if (text.indexOf(chars[i]) >= 0) {
				text = text.replace(chars[i], byString);
			}
		}
		return text;
	}
	
	/**
	 * Remove all underlined from input.
	 * @param input
	 * @return string without underlined.
	 */
	public static String dropUnderlined(String input) {
		input = input.replace("_", "");
		return input;
	}
	
	/**
	 * @param plaintext
	 * @return encrypt string.
	 * @throws Exception
	 * @description : encrypt password VSA
	 */
	public static String encryptPassWordVSA(String plaintext) throws Exception {
        MessageDigest md = null;
        String hash = null;
    
        md = MessageDigest.getInstance("SHA-1"); //step 2
        md.update(plaintext.getBytes("UTF-8")); //step 3
        // byte raw[] = md.digest(); //step 4
        
        // TODO: Kiem tra lai cho nay
        //hash = (new BASE64Encoder()).encode(raw); //step 5
      
        return hash; //step 6
    }
	
	public static List<String> breakIntoSentences(String content, int limit) {
		List<String> results = new ArrayList<String>();
        
		BreakIterator bi = BreakIterator.getSentenceInstance();
        bi.setText(content);
        
        StringBuilder builder = new StringBuilder();
        
        int index = 0;
        while (bi.next() != BreakIterator.DONE) {
	        String sentence = content.substring(index, bi.current());
	        
	        if(builder.length() + sentence.length() <= limit) {
	        	builder.append(sentence);
	        } else {
	        	results.add(builder.toString());
	        	
	        	builder.setLength(0);
	        	builder.append(sentence);
	        }
	        
	        index = bi.current();
        }
        
        results.add(builder.toString());
        
        return results;
	}
	
	public static String trimDomainEmail(String email){
		int index = email.indexOf('@');
		return email.substring(0,index);
	}
}
