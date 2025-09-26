class Solution {
    public boolean judgeCircle(String moves) {
        int left, right, up, down;  

        left = right = up = down = 0;

        for(char ch : moves.toCharArray()) {
            if(ch == 'U') up++;
            else if(ch == 'D') down++;
            else if(ch == 'L') left++;
            else right++;
        }

        int flag1 = Math.abs(up - down);
        int flag2 = Math.abs(left - right);

        boolean originFlag = (flag1 == 0 && flag2 == 0);

        return originFlag;
    }
}