class Solution {

    static int[] drow = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dcol = {-1, 0, 1, -1, 1, -1, 0, 1};

    private boolean isValid(int row, int col, int m, int n) {
        return (row >= 0 && row < m && col >= 0 && col < n);
    }

    private void dfs(int row, int col, int m, int n, int[][] board, int[][] futureLife) {

        boolean isAlive = board[row][col] == 1 ? true : false;

        int neighborCount = 0;

        for(int i=0; i<8; i++) {

            int nrow = row + drow[i];
            int ncol = col + dcol[i];

            if(isValid(nrow, ncol, m, n) && board[nrow][ncol] == 1) {
                neighborCount++;
            }
        }    

        if(isAlive && neighborCount < 2) futureLife[row][col] = 0;
        else if(isAlive && (neighborCount == 2 || neighborCount == 3)) futureLife[row][col] = 1;
        else if(!isAlive && neighborCount == 3) futureLife[row][col] = 1;
        else if(isAlive && neighborCount > 3) futureLife[row][col] = 0;

        return;
    }

    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int[][] futureLife = new int[m][n];

        for(int row = 0; row<m; row++) {
            for(int col=0; col<n; col++) {
                dfs(row, col, m, n, board, futureLife);
            }
        }    


        for(int row=0; row<m; row++) {
            System.arraycopy(futureLife[row], 0, board[row], 0, n);
        }
    }
}