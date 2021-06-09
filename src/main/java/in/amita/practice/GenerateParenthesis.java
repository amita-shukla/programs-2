package in.amita.practice;

import java.util.*;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]
 */
public class GenerateParenthesis {
	ArrayList<String> res;
	public List<String> generateParenthesis(int n){
		res = new ArrayList<>();
		generate(n, 0, 0, "");
		return res;
	}

	public void generate(int n, int open, int closed, String partial){
		if(open==n && closed==n)
			res.add(partial);

		if(open > n || open<closed) return;

		generate(n, open+1, closed, partial+"(");
		generate(n, open, closed+1, partial+")");
	}

	public static void main(String[] args) {
		GenerateParenthesis obj = new GenerateParenthesis();
		System.out.println(obj.generateParenthesis(1));
		System.out.println(obj.generateParenthesis(2));
		System.out.println(obj.generateParenthesis(3));
	}
}
