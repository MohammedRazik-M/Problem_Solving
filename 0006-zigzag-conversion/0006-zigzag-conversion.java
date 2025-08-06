class Solution {
    public String convert(String s, int numRows) {
        int n = s.length();

        if(numRows == 1 || n <= numRows) return s;

        char[][] matrix = new char[numRows][n];

        int row, col, index;
        row = col = index = 0;

        while(index < n) {
            while(row < numRows && index < n) {
                matrix[row++][col] = s.charAt(index);
                index++;
            }
            row = Math.max(0, row - 2);

            while(row > 0 && index < n) {
                matrix[row--][++col] = s.charAt(index);
                index++;
            }
            col++;
        }
        
        StringBuilder res = new StringBuilder();

        for(row=0; row<numRows; row++) {
            for(col=0; col<n; col++) {
                char ch = matrix[row][col];
                if(Character.isLetter(ch) || ch == '.' || ch == ',') res.append(ch);
            }
        }
        return res.toString();
    }
}