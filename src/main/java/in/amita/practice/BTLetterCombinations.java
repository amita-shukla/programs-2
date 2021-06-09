package in.amita.practice;

import java.util.*;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = ""
Output: []

Example 3:
Input: digits = "2"
Output: ["a","b","c"]
 */
public class BTLetterCombinations {
	List<String> res;
	Map<String, String> lettersDigitMap;

	public List<String> letterCombinations(String digits){
		lettersDigitMap = new HashMap<>();
		lettersDigitMap.put("2", "abc");
		lettersDigitMap.put("3", "def");
		lettersDigitMap.put("4", "ghi");
		lettersDigitMap.put("5", "jkl");
		lettersDigitMap.put("6", "mno");
		lettersDigitMap.put("7", "pqrs");
		lettersDigitMap.put("8", "tuv");
		lettersDigitMap.put("9", "wxyz");

		res = new ArrayList<>();
		if (digits.equals("")) return res;
		recurse(digits, 0, "");
		return res;
	}

	public void recurse(String digits, int ind, String partial){
		if(ind == digits.length()) {
			res.add(partial);
			return;
		}

		String d = String.valueOf(digits.charAt(ind));
		String chars = lettersDigitMap.get(d);
		for (int i = 0; i < chars.length(); i++) {
			recurse(digits, ind+1, partial + chars.charAt(i));
		}
	}

	public static void main(String[] args) {
		BTLetterCombinations obj = new BTLetterCombinations();
		System.out.println(obj.letterCombinations("23"));
		System.out.println(obj.letterCombinations(""));
		System.out.println(obj.letterCombinations("2"));
	}
}
