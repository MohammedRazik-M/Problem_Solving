class Solution {
    public boolean judgeCircle(String moves) {
        int left, up;

        left = up = 0;

        for(char ch : moves.toCharArray()) {
            if(ch == 'U') up++;
            else if(ch == 'D') up--;
            else if(ch == 'L') left++;
            else left--;
        }

        boolean originFlag = (up == 0 && left == 0);

        return originFlag;


        // TC => O(moves.length())
        // SC => O(1)
    }
}