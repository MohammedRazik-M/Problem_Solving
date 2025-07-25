class Pair {
    String word;
    int steps;
    
    public Pair(String word, int steps) {
        this.word = word;
        this.steps = steps;
    }
}

class Solution {
    public int wordLadderLength(String startWord, String targetWord,
                                String[] wordList) {
        
        Set<String> wordSet = new HashSet<>();
        
        for(String word : wordList) wordSet.add(word);
        
        Queue<Pair> queue = new ArrayDeque<>();
        
        if(!wordSet.contains(targetWord)) return 0;
        
        queue.offer(new Pair(startWord, 1));
        wordSet.remove(startWord);
        
        while(!queue.isEmpty()) {
            String currWord =  queue.peek().word;
            int steps = queue.peek().steps;
            
            queue.poll();
            
            if(currWord.equals(targetWord)) return steps;
            
            for(int index=0; index<currWord.length(); index++) {
                char[] curr_word = currWord.toCharArray();
                for(char ch='a'; ch<='z'; ch++) {
                    curr_word[index] = ch;
                    String newWord = new String(curr_word);
                    
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