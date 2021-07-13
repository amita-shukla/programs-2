package in.amita.practice;

class ArrayTrappingRainWater {
    // use two arrays to maintain the max on left and max on right
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        
        for(int i=0; i<n; i++){
            if(i==0) leftMax[i] = height[i];
            else leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }
        
        for(int i=n-1; i>=0; i--){
            if(i==n-1) rightMax[i] = height[i];
            else rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }
        
        int total=0;
        for(int i=1; i<n-1; i++){
            // rain on top of building
            int top = Math.min(leftMax[i-1], rightMax[i+1]) - height[i];
            
            // if the ith building was tallest
            if(top<0) top = 0;
            total += top;
        }
        return total;
    }
}
