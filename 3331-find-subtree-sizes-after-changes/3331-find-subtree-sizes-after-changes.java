class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        int[] firstChild = new int[n], nextSibling = new int[n];  // children as a linked list in two int arrays
        Arrays.fill(firstChild, -1);
        for (int x = n - 1; x > 0; x--) {
            nextSibling[x] = firstChild[parent[x]];
            firstChild[parent[x]] = x;
        }
        int[] size = new int[n], above = new int[n], last = new int[26], stack = new int[2 * n];
        Arrays.fill(size, 1);
        Arrays.fill(last, -1);
        int top = 0;
        stack[top++] = 0;  
        while (top > 0) {
            int x = stack[--top];
            if (x >= 0) {
                int c = s.charAt(x) - 'a';
                above[x] = last[c]; 
                last[c] = x;
                stack[top++] = ~x;  
                for (int child = firstChild[x]; child != -1; child = nextSibling[child])
                    stack[top++] = child;
            } else {
                x = ~x;
                last[s.charAt(x) - 'a'] = above[x];
                if (x > 0) 
                    size[above[x] >= 0 ? above[x] : parent[x]] += size[x];
            }
        }
        return size;
    }
}