/*
Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.
Example:
Input : [1, 0]
Return : [0, 1]

Input: [4 0 2 1 3]
Return: [3 4 2 0 1]

Lets say N = size of the array. Then, following holds true :
  - All elements in the array are in the range [0, N-1]
  - N * N does not overflow for a signed integer
*/
public class MathsRearrangeArray {
    /*
    the idea is: each number can be expressed as 2 numbers:
    dividend = divisor * n + remainder
    
    so we encode two numbers as:
    encodedNo = arr[i]* N + arr[arr[i]]
    
    therefore, for an index i:
    original value = encodedKey/N
    new value = encodedKey%N
    */
    public void arrange(ArrayList<Integer> a) {
        int n = a.size();
        for(int i=0; i<n; i++){
            int key = a.get(i);
            if(key<i){
                // a.get(key) is accessing an index<i
                // which has already been encoded
                // hence, extract the original value before encoding
                a.set(i, n*key+(a.get(key)/n));
            } else {
                // not encoded till now, encode it as it is
                a.set(i, n*key+a.get(key));
            }
            
        }
      
        // all values have been encoded, extract the new values
        for(int i=0; i<n; i++){
            a.set(i, a.get(i)%n);
        }
    }
}
