class Solution {
    public long minOperations(int[] nums, int x, int k) {
         int n = nums.length;
        long[] cost = new long[n - x + 1];

        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        long leftSum = 0, rightSum = 0;
        int leftSize = 0, rightSize = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];

            if (left.isEmpty() || val <= left.lastKey()) {
                left.put(val, left.getOrDefault(val, 0) + 1);
                leftSum += val;
                leftSize++;
            } else {
                right.put(val, right.getOrDefault(val, 0) + 1);
                rightSum += val;
                rightSize++;
            }

            int target = (leftSize + rightSize + 1) / 2;

            while (leftSize > target) {
                int v = left.lastKey();
                remove(left, v);
                leftSum -= v;
                leftSize--;
                right.put(v, right.getOrDefault(v, 0) + 1);
                rightSum += v;
                rightSize++;
            }

            while (leftSize < target) {
                int v = right.firstKey();
                remove(right, v);
                rightSum -= v;
                rightSize--;
                left.put(v, left.getOrDefault(v, 0) + 1);
                leftSum += v;
                leftSize++;
            }

            if (i >= x) {
                int old = nums[i - x];

                if (left.containsKey(old)) {
                    remove(left, old);
                    leftSum -= old;
                    leftSize--;
                } else {
                    remove(right, old);
                    rightSum -= old;
                    rightSize--;
                }

                target = (leftSize + rightSize + 1) / 2;

                while (leftSize > target) {
                    int v = left.lastKey();
                    remove(left, v);
                    leftSum -= v;
                    leftSize--;
                    right.put(v, right.getOrDefault(v, 0) + 1);
                    rightSum += v;
                    rightSize++;
                }

                while (leftSize < target) {
                    int v = right.firstKey();
                    remove(right, v);
                    rightSum -= v;
                    rightSize--;
                    left.put(v, left.getOrDefault(v, 0) + 1);
                    leftSum += v;
                    leftSize++;
                }
            }

            if (i >= x - 1) {
                int start = i - x + 1;
                int median = left.lastKey();

                cost[start] = (long) median * leftSize - leftSum
                            + rightSum - (long) median * rightSize;
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(curr[i],
                        prev[i - x] + cost[i - x]);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }

    private void remove(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }
    }