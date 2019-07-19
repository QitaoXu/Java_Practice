public class AlienDict {
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {
        // Write your code here
        
        if (words == null || words.length == 0) {
            return "";
        }
        
        HashMap<Character, HashSet<Character>> graph = this.buildGraph(words); 
        
        ArrayList<Character> order = this.topo_sorting(graph);
        
        StringBuilder sb = new StringBuilder();
        
        if (order.size() == 0) {
            return "";
        }
        else {
            
            for (char c : order) {
                sb.append(c);
            }
            
            return sb.toString();
        }
    }
    
    private HashMap<Character, HashSet<Character>> buildGraph(String[] words) {
        
        int n = words.length; 
        
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        
        for (String word : words) {
            
            for (int i = 0; i < word.length(); i++) {
                
                if (graph.containsKey(word.charAt(i)) == false) {
                    graph.put(word.charAt(i), new HashSet<Character>()); 
                }
            }
        }
        
        
        for (int i = 0; i < n - 1; i++) {
            
            for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    
                    graph.get(words[i].charAt(j)).add(words[i + 1].charAt(j));
                    break;
                }
            }
        }
        
        return graph; 
    }
    
    private HashMap<Character, Integer> getIndegrees(HashMap<Character, HashSet<Character>> graph) {
        
        HashMap<Character, Integer> indegrees = new HashMap<>(); 
        
        for (char node : graph.keySet()) {
            
            indegrees.put(node, 0);
        }
        
        for (char node : graph.keySet()) {
            
            for (char neighbor : graph.get(node)) {
                
                indegrees.put(neighbor, indegrees.get(neighbor) + 1);
            }
        }
        
        return indegrees; 
    }
    
    
    private ArrayList<Character> topo_sorting(HashMap<Character, HashSet<Character>> graph) {
        
        
        HashMap<Character, Integer> indegrees = this.getIndegrees(graph); 
        
        PriorityQueue<Character> queue = new PriorityQueue<Character>(); 
        
        for (char node : graph.keySet()) {
            
            if (indegrees.get(node) == 0) {
                
                queue.offer(node);
            }
        }
        
        ArrayList<Character> order = new ArrayList<>(); 
        
        while (!queue.isEmpty()) {
                
            char node = queue.poll();
                
            order.add(node); 
                
            for (char neighbor : graph.get(node) ) {
                    
                indegrees.put(neighbor, indegrees.get(neighbor) - 1); 
                    
                if (indegrees.get(neighbor) == 0) {
                        
                    queue.offer(neighbor);
                }
            }
            
        }
        
        if (order.size() == graph.size()) {
            return order; 
        }
        else {
            return new ArrayList<Character>();
        }
    }
    
    
}