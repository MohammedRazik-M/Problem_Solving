class Solution {
    
    public void reverse(int[][] matrix, int row, int n) {
        for(int col=0; col<n/2; col++) {
            int temp = matrix[row][col];
            matrix[row][col] = matrix[row][n-1-col];
            matrix[row][n-1-col] = temp;
        }
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for(int row=0; row<n-1; row++) {
            for(int col=row+1; col<n; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }

        for(int row=0; row<n; row++) {
            reverse(matrix, row, n);
        }
    }
}