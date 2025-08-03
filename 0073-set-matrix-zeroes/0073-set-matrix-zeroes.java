class Solution {

    // O(n)
    private void setRow(int row, int n, int[][] matrix) {
        for(int col=0; col<n; col++) {
            matrix[row][col] = 0;
        }
    }

    // O(m)
    private void setCol(int col, int m, int[][] matrix) {
        for(int row=0; row<m; row++) {
            matrix[row][col] = 0;
        }
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] rflag = new boolean[m];
        boolean[] cflag = new boolean[n];

        // O(m * n)
        for(int row=0; row<m; row++) {
            for(int col=0; col<n; col++) {
                if(matrix[row][col] == 0) {
                    rflag[row] = true;
                    cflag[col] = true;
                }
            }
        }

        // O(m * n)
        for(int row=0; row<m; row++) {
            if(rflag[row]) {
                setRow(row, n, matrix);
            }
        }

        // O(m * n)
        for(int col=0; col<n; col++) {
            if(cflag[col]) {
                setCol(col, m, matrix);
            }
        }
    }
}