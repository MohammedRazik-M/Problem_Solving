class Solution {

    private void generate(StringBuilder curr, int n, int opCount, int clCount, List<String> res) {
        
        if(opCount == n && clCount == n) {
            res.add(curr.toString());
            return;
        }

        if(opCount < n) {
            generate(curr.append("("), n, opCount+1, clCount, res);
            curr.deleteCharAt(curr.length()-1);
        }        

        if(clCount < opCount) {
            generate(curr.append(")"), n, opCount, clCount+1, res);
            curr.deleteCharAt(curr.length()-1);
        }
    }

    public List<String> generateParenthesis(int n) {

        List<String> res = new ArrayList<>();
        generate(new StringBuilder(), n, 0, 0, res);
        return res;
    }
}