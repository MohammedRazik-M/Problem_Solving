class Solution {
    public boolean isValidSudoku(char[][] board) {
        int rflag[] = new int[9];
        int cflag[] = new int[9];
        int smflag[] = new int[9];
        

        for(int row=0; row<9; row++) {
            for(int col=0; col<9; col++) {
                char ch = board[row][col];
                int smIndex = (row/3)*3 + col/3;
                if(ch!='.') {
                    int digit = ch - '0';
                    if( (rflag[row] & (1 << digit)) != 0 ||
                        (cflag[col] & (1 << digit)) != 0 ||
                        (smflag[smIndex] & (1 << digit)) != 0 ) return false;
                    rflag[row] |= (1<<digit);
                    cflag[col] |= (1<<digit);
                    smflag[smIndex] |= (1<<digit);
                }
            }
        }
        return true;
    }
}