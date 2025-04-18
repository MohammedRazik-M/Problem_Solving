//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            String[] wordList = new String[n];
            for(int i = 0; i < n; i++){
                wordList[i] = br.readLine().trim();
            }
            String startWord, targetWord;
            startWord = br.readLine().trim();
            targetWord = br.readLine().trim();
            Solution obj = new Solution();
            int ans = obj.wordLadderLength(startWord, targetWord, wordList);
            System.out.println(ans);
       
System.out.println("~");
}
    }
}

// } Driver Code Ends

class Pair {
    String word;
    int steps;
    public Pair(String word, int steps) {
        this.word = word;
        this.steps = steps;
    }
}

class Solution
{
    public int wordLadderLength(String startWord, String targetWord, String[] wordList)
    {
        List<String> wordsList = Arrays.asList(wordList);
        Set<String> wordSet = new HashSet<>(wordsList);
        Queue<Pair> queue = new LinkedList<>();
        
        if(!wordSet.contains(targetWord)) return 0;
        
        queue.offer(new Pair(startWord, 1));
        wordSet.remove(startWord);
        
        while(!queue.isEmpty()) {
            String curr_word = queue.peek().word;
            int steps = queue.peek().steps;
            queue.poll();
            
            if(curr_word.equals(targetWord)) return steps;
            
            for(int i=0; i<curr_word.length(); i++) {
                char[] new_word = curr_word.toCharArray();
                for(char c='a'; c<='z'; c++) {
                    new_word[i] = c;
                    String newWord = new String(new_word);
                    if(wordSet.contains(newWord)) {
                        wordSet.remove(newWord);
                        queue.offer(new Pair(newWord, steps+1));
                    }
                }
            }
        }
        return 0;
    }
}

