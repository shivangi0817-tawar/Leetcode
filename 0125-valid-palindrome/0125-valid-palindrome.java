class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        String rev = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                rev = ch + rev;
            }
        }
        String original = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                original = original + ch;
            }
        }

        return rev.equals(original);
    }
}
