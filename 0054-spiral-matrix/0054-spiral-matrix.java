class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        List<Integer> ans = new ArrayList<>();

        int top, bottom, left, right;
        top = left = 0;
        bottom = m-1;
        right = n-1;

        while(top <= bottom && left <= right) {
            // top
            for(int col=left; col<=right; col++) {
                ans.add(matrix[top][col]);
            }
            top++;

            // right
            for(int row=top; row<=bottom; row++) {
                ans.add(matrix[row][right]);
            }
            right--;

            //bottom
            if(top <= bottom) {
                for(int col=right; col>=left; col--) {
                    ans.add(matrix[bottom][col]);
                }
                bottom--;
            }
            

            //left
            if(left <= right) {
                for(int row=bottom; row>=top; row--) {
                    ans.add(matrix[row][left]);
                }
                left++;
            }
        }
        return ans;
    }
}