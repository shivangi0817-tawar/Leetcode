class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[] can = new boolean[target + 1];
        can[0] = true;                           
        for (int x : nums) {
            for (int s = target; s >= x; s--) {  
                if (can[s - x]) can[s] = true;   
            }
        }
        return can[target];
    }
}