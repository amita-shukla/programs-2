package in.amita.practice;

public class BMNumberOf1Bits {
	public int hammingWeight(int n) {
		int mask = 1;
		int bitCount = 0;
		for (int i = 0; i < 32; i++) {
			if((mask&n) !=0){ // the there is a set bit at the same position as at mask, only then mask & n != 0
				bitCount++;
			}
			mask = mask << 1;
		}
		return bitCount;
	}

	public int hammingWeight2(int n){
		int sum = 0; // this is basically the count of the number of times the below loop runs
		while(n!=0){
			sum++;
			n = (n&(n-1)); // trick: n & n-1 sets the least significant set bit to 0
		}
		return sum; // during the above loop, all the bits above are set
	}

	public static void main(String[] args) {
		BMNumberOf1Bits obj = new BMNumberOf1Bits();
		System.out.println(obj.hammingWeight(4));
		System.out.println(obj.hammingWeight2(4));
	}
}
