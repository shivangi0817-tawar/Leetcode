class Solution {
public:
    bool isPalindrome(int x) {
    int temp=x;
    long rev=0;
    while(x>0){
    rev=rev * 10 + x % 10;
    x= x / 10;
    }
    if(x<0) return false;
    if(temp==rev){
        return true;
    }
    else{
        return false;
    }
}
};