class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int[] pre=new int[nums.size()+1];
        for(int i=0;i<nums.size();i++){
            pre[i+1]=pre[i]+nums.get(i);
        }
        int res=Integer.MAX_VALUE;
        for(int i=l;i<=r;i++){
            int m=0;
            for(int j=i,k=0;j<=nums.size();j++){
                int tes=pre[j]-m;
                m+=nums.get(k++);
                if(tes>0)
                    res=Math.min(res,tes);
            }
        }
        return res==Integer.MAX_VALUE?-1:res;
    }
}