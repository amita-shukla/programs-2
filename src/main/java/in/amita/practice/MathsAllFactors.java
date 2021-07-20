package in.amita.practice;

/*
Given a number N, find all factors of N.
Example:
N = 6 
factors = {1, 2, 3, 6}
Make sure the returned array is sorted.
*/
public class MathsAllFactors {
    public int[] allFactors(int a) {
        ArrayList<Integer> fac1 = new ArrayList<>();
        // either I can sort fact1 later on, or keep another array for saving the later half of factors,
       // and merge them later...
        ArrayList<Integer> fac2 = new ArrayList<>();
        int sqrt = (int)Math.sqrt(a);
        // only need to traverse till sq root of N. This is improvement over O(N)
        for (int i =1; i<=sqrt; i++){
            int mod = a % i;
            int quot = a / i;
            if(mod ==0)    {
                fac1.add(i);
                if(quot!=i){
                  // avoid duplicates here
                    fac1.add(0,quot);
                }
            }
        }
        fac1.addAll(fac2);
        
        // convert list to array
        int[] res = new int[fac1.size()];
        int i =0;
        for (int elem: fac1){
            res[i++] = elem;
        }
        return res;
    }
}
