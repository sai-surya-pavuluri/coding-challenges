import java.util.*;

public class Solution {
    public static List<int[]> aStar(char[][] grid, int[] start, int[] goal) {
        List<int[]> path = new ArrayList<>();
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.fCost));
        Set<Node> closedList = new HashSet<>();

        Node startNode = new Node(start[0], start[1]);
        Node goalNode = new Node(goal[0], goal[1]);

        openList.add(startNode);
        
        while (!openList.isEmpty()) {
            Node current = openList.poll();

            // Check if the goal node has been reached
            if (current.equals(goalNode)) {
                System.out.println("Check the shortest distance: " + current.fCost);
                return reconstructPath(current);
            }

            // Skip if the current node is already processed
            if (closedList.contains(current)) {
                continue;
            }

            closedList.add(current);

            // Get valid neighbours
            int[][] neighbours = getNeighbours(current.row, current.col, grid.length, grid[0].length);
            for (int[] neighbour : neighbours) {
                if (grid[neighbour[0]][neighbour[1]] != '1') { // Check for obstacles
                    Node neighbourNode = new Node(neighbour[0], neighbour[1]);
                    neighbourNode.setParent(current);
                    neighbourNode.gCost = current.gCost + 1; // Assuming uniform cost
                    neighbourNode.setHCost(goalNode);
                    neighbourNode.setFCost();

                    // Check if the neighbour node is already in the open list
                    if (openList.contains(neighbourNode)) {
                        // Find the existing node in the open list
                        for (Node node : openList) {
                            if (node.equals(neighbourNode) && node.fCost > neighbourNode.fCost) {
                                openList.remove(node);
                                openList.add(neighbourNode);
                                break; // Exit the loop after updating
                            }
                        }
                    } else {
                        openList.add(neighbourNode);
                    }
                }
            }
        }

        return path; // Return empty path if no path is found
    }

    private static List<int[]> reconstructPath(Node node) {
        List<int[]> path = new ArrayList<>();
        while (node != null) {
            path.add(new int[]{node.row, node.col});
            node = node.parent;
        }
        Collections.reverse(path); // Reverse the path to get the correct order
        return path;
    }

    private static int[][] getNeighbours(int row, int col, int maxRows, int maxCols) {
        List<int[]> neighbours = new ArrayList<>();
        // Possible directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newRow < maxRows && newCol >= 0 && newCol < maxCols) {
                neighbours.add(new int[]{newRow, newCol});
            }
        }

        return neighbours.toArray(new int[0][]);
    }

    // Example usage
    public static void main(String[] args) {
        char[][] grid = {
            {'0', '0', '0', '0'},
            {'0', '1', '1', '0'},
            {'0', '0', '0', '0'},
            {'0', '1', '0', '0'}
        };

        int[] start = {0, 0};
        int[] goal = {3, 3};

        List<int[]> path = aStar(grid, start, goal);
        System.out.println("Path from start to goal:");
        for (int[] step : path) {
            System.out.println(Arrays.toString(step));
        }
    }
}



class Node {

    int row;
    int col;
    Node parent;
    int gCost; // Cost from start node
    int hCost; // Heuristic cost to goal node
    int fCost; // Total cost (gCost + hCost)

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setHCost(Node goal) {
        this.hCost = Math.abs(this.row - goal.row) + Math.abs(this.col - goal.col); // Manhattan distance
    }

    public void setFCost() {
        this.fCost = this.gCost + this.hCost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return row == node.row && col == node.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

