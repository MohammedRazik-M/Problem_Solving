class Solution {
    public String convert(String s, int numRows) {

        int n = s.length();
        
        if(numRows == 1 || n <= numRows) return s;
    
        StringBuilder[] rows = new StringBuilder[numRows];

        for(int row=0; row<numRows; row++) rows[row] = new StringBuilder();

        boolean goingDown = false;
        int currRow = 0;

        for(char ch : s.toCharArray()) {
            rows[currRow].append(ch);
            
            if(currRow == 0 || currRow == numRows-1) goingDown = !goingDown;

            currRow += goingDown ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();

        for(StringBuilder row : rows) res.append(row);

        return res.toString();
    }
}