class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int mid= nums[nums.length/2];
        long move=0;
        for(int num:nums){
            move+=Math.abs(num-mid);
    }
return (int) move;
    }
}