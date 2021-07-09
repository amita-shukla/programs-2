/*
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 */
public class StringLongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();

        int minN = Integer.MAX_VALUE;
        for(String str: strs){
            minN = Math.min(minN, str.length());
        }
        for(int i=0; i<minN; i++){
            char c = strs[0].charAt(i);
            for(int j=1; j<strs.length; j++){
                char h = strs[j].charAt(i);
                if(c!=h){
                    return sb.toString();
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringLongestCommonPrefix obj = new StringLongestCommonPrefix();
        System.out.println(obj.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }
}
