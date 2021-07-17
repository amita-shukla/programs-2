package in.amita.practice;

import java.util.*;

public class MathsPrimes {
  
    /*
    Given a number N, verify if N is prime or not. Return 1 if N is prime, else return 0.
    Example :
    Input : 7
    Output : 1
    */
    public int isPrime(int num) {
        if(num==0 || num==1) return 0;
        
        // whenever we talk about going in multiples/factors, 
        // a square root bound is enough,
        // because factors exist in pairs (except perfect squares)
        int sqrt = (int) Math.sqrt(num);
        for(int i=2; i<=sqrt; i++){
            if(num%i==0) return 0;
        }
        return 1;
    }
  
    /*
    Given a number N, find all prime numbers upto N ( N included ).
    Example:
    if N = 7,
    all primes till 7 = {2, 3, 5, 7}
    Make sure the returned array is sorted.
    
    This is solved by Sieve of Eratosthenes.
    The time complexity is: O(nlog(logn)) https://youtu.be/eKp56OLhoQs
    */
    public ArrayList<Integer> sieve(int n) {
        if(n<2) return new ArrayList<>();

        boolean[] sieve = new boolean[n+1];
        Arrays.fill(sieve, true);

        int sqrt = (int) Math.sqrt(n);
        for(int i=2; i<=sqrt; i++){
            if(!sieve[i]) continue;
            for(int j=i*i; j<=n; j+=i){
                sieve[j]=false;
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for(int i=2; i<sieve.length; i++){
            if(sieve[i]) primes.add(i);
        }

        return primes;
    }
}
