import java.util.*;

public class Solution {

    public static void main(String[] args) {

        // Getting user input for the number of nodes
        System.out.println("Please enter the number of nodes in your graph: ");
        try (Scanner scanner = new Scanner(System.in)) {
            int N = scanner.nextInt();
            int[][] distanceMatrix = new int[N][N];

            // Inputting distances between nodes and ensuring no negative values
            System.out.println("Enter the distance between the nodes below: ");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) {
                        distanceMatrix[i][j] = 0; // Distance to itself is 0
                    } else {
                        int distance;
                        do {
                            System.out.println("Edge distance between node " + i + " and node " + j + ": ");
                            distance = scanner.nextInt();
                            if (distance < 0) {
                                System.out.println("Please provide a non-negative distance.");
                            }
                        } while (distance < 0);
                        distanceMatrix[i][j] = distance;
                    }
                }
            }

            // Getting the starting node input
            int startingNode;
            do {
                System.out.println("Provide the starting node (0 to " + (N - 1) + "): ");
                startingNode = scanner.nextInt();
                if (startingNode < 0 || startingNode >= N) {
                    System.out.println("Please provide a valid node number between 0 and " + (N - 1) + ".");
                }
            } while (startingNode < 0 || startingNode >= N);

            // Running Dijkstra's algorithm and printing the results
            int[] shortestPath = dijkstra(distanceMatrix, startingNode);
            System.out.println("Shortest paths from node " + startingNode + " to all other nodes: " + Arrays.toString(shortestPath));
        }
    }

    public static int[] dijkstra(int[][] distanceMatrix, int startingNode) {

        int N = distanceMatrix.length;
        int[] shortestPath = new int[N];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[startingNode] = 0; // Distance to the start node is 0

        // Priority queue to select the next node with the shortest distance
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparing(Map.Entry::getKey));

        boolean[] visited = new boolean[N]; // Tracks visited nodes

        priorityQueue.add(new AbstractMap.SimpleEntry<>(0, startingNode)); // Add starting node to the queue

        while (!priorityQueue.isEmpty()) {

            Map.Entry<Integer, Integer> queueItem = priorityQueue.poll();
            int currentDistance = queueItem.getKey();
            int currentNode = queueItem.getValue();

            // Skip if node is already visited
            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            // Update the shortest paths to neighbors
            for (int i = 0; i < N; i++) {
                if (!visited[i] && distanceMatrix[currentNode][i] != 0) { // Check only unvisited neighbors
                    int newDistance = currentDistance + distanceMatrix[currentNode][i];
                    if (newDistance < shortestPath[i]) {
                        shortestPath[i] = newDistance;
                        priorityQueue.add(new AbstractMap.SimpleEntry<>(newDistance, i)); // Add neighbor to the queue
                    }
                }
            }
        }

        return shortestPath;
    }
}