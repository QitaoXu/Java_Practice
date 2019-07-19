class Point {
    
    int x;
    int y; 
    
    public Point(int x, int y) {
        
        this.x = x; 
        this.y = y; 
    }
}

public class NumberOfIslands {
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
     
    public int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; 
    
    public int numIslands(boolean[][] grid) {
        // write your code here
        
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0; 
        }
        
        int m = grid.length; 
        int n = grid[0].length; 
        
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                if (grid[i][j] == false) {
                    continue;
                }
                
                bfs(grid, i, j);
                count += 1; 
                
            }
        }
        
        return count; 
    }
    
    private void bfs(boolean[][] grid, int x, int y){
        
        Queue<Point> queue = new LinkedList<Point>(); 
        Set<Point> seen = new HashSet<Point>(); 
        
        queue.offer(new Point(x, y));
        seen.add(new Point(x, y)); 
        
        grid[x][y] = false; 
        
        while (!queue.isEmpty()) {
            
            int size = queue.size(); 
            
            for (int i = 0; i < size; i++) {
                
                Point node = queue.poll(); 
                
                for (int index = 0; index < 4; index++) {
                    
                    Point neighbor = new Point(node.x + directions[index][0], node.y + directions[index][1]); 
                    
                    if (!isValid(grid, neighbor)) continue; 
                    
                    if (seen.contains(neighbor)) continue; 
                    
                    queue.offer(neighbor);
                    seen.add(neighbor);
                    grid[neighbor.x][neighbor.y] = false;
                }
            }
        }
        
    }
        
    private boolean isValid(boolean[][]grid, Point node) {
        
        int m = grid.length;
        int n = grid[0].length; 
        
        if (node.x < 0 || node.x >= m || node.y < 0 || node.y >= n) {
            return false;
        }
        
        if (grid[node.x][node.y] == false) {
            return false;
        }
        
        return true; 
        
    }
}